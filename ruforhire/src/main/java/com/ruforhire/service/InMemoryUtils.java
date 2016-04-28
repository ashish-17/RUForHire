/**
 * 
 */
package com.ruforhire.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ruforhire.model.InvertedListJobTitles;
import com.ruforhire.model.JobDescription;
import com.ruforhire.model.JobDescriptionVector;
import com.ruforhire.model.JobTitleIndex;
import com.ruforhire.model.QueryVsJobs;
import com.ruforhire.utils.Stemmer;
import com.ruforhire.utils.Stopwords;

/**
 * @author ashish
 *
 */
@Component
@Scope(value = "singleton")
public class InMemoryUtils {

	private static final int COUNT_MATCHING_JOBS = 5;
	
	private InvertedListJobTitles invertedListJobs;
	private Map<String, JobTitleIndex> titleMap;
	private List<QueryVsJobs> queries;
	private Map<JobDescription, JobDescriptionVector> vectors;
	private Map<String, ArrayList<JobDescription>> queryVsJobs;
	
	@Autowired
	private Stopwords stopWords;

	@Autowired
	@Qualifier("jobTitleService")
	private JobTitleService jobTitleService;

	@Autowired
	@Qualifier("queryVsJobsService")
	private QueryVsJobsService queryVsJobsService;
	
	public InMemoryUtils() {
		invertedListJobs = new InvertedListJobTitles();
		titleMap = new HashMap<>();
		vectors = new HashMap<>();
		queries = new ArrayList<>();
		queryVsJobs = new HashMap<>();
	}
	
	@PostConstruct
	public void init() {
		if (queries.size() == 0) {
			List<JobTitleIndex> titles = jobTitleService.listPopulerJobTitles();
			for (JobTitleIndex title : titles) {
				titleMap.put(title.getTitle(), title);
			}
			
			queries = queryVsJobsService.listJobsVsQuery();
			for (QueryVsJobs query : queries) {
				JobDescription jd = query.getJobDescription();
				String[] title = jd.getJobTitle().split("\\W+");
				for (String str : title) {
					str = str.trim().toLowerCase();
					if (!stopWords.is(str) && StringUtils.isAlphanumeric(str) && !StringUtils.isNumeric(str)) {
						Stemmer stemmer = new Stemmer();
						stemmer.add(str.toCharArray(), str.length());
						stemmer.stem();
						invertedListJobs.add(stemmer.toString(), titleMap.get(query.getQuery()));
					}
				}
				
				String[] snippet = Jsoup.parse(jd.getSnippet()).text().split("\\W+");
				for (String str : snippet) {
					str = str.trim().toLowerCase();
					if (!stopWords.is(str) && StringUtils.isAlphanumeric(str) && !StringUtils.isNumeric(str)) {
						Stemmer stemmer = new Stemmer();
						stemmer.add(str.toCharArray(), str.length());
						stemmer.stem();
						invertedListJobs.add(stemmer.toString(), titleMap.get(query.getQuery()));
					}
				}
				
				if (!queryVsJobs.containsKey(query.getQuery())) {
					queryVsJobs.put(query.getQuery(), new ArrayList<JobDescription>());
				}

				queryVsJobs.get(query.getQuery()).add(jd);
			}
		}
	}
	
	public List<JobTitleIndex> getMatchingJobProfiles(File file) throws IOException {

		if (vectors.size() == 0) {
			for (QueryVsJobs query : queries) {
				JobDescription jd = query.getJobDescription();
				vectors.put(jd, new JobDescriptionVector(jd, invertedListJobs.getWordList()));
			}
		}
		
		PDDocument pddDocument=PDDocument.load(file);
	    PDFTextStripper textStripper = new PDFTextStripper();
	    String content = textStripper.getText(pddDocument);
	    pddDocument.close();
	    
	    String[] words = content.split("\\W+");
	    List<String> matchedWords = new ArrayList<>();
	    Map<JobTitleIndex, Integer> votes = new HashMap<>();
	    for (String word : words) {
	    	word = word.toLowerCase();
			if (!stopWords.is(word) && StringUtils.isAlphanumeric(word) && !StringUtils.isNumeric(word)) {
				Stemmer stemmer = new Stemmer();
				stemmer.add(word.toCharArray(), word.length());
				stemmer.stem();
				matchedWords.add(stemmer.toString());
				List<JobTitleIndex> matchingJobs = invertedListJobs.get(stemmer.toString());
				if (matchingJobs != null) {
					for (JobTitleIndex job : matchingJobs) {
						if (votes.containsKey(job)) {
							votes.put(job, votes.get(job) + 1);
						} else {
							votes.put(job, 1);
						}
					}
				}
			}
		}
	    
	    int maxVotes = 0;
	    JobTitleIndex winningJob = null;
	    MatchingJobProfileAnalyticsData[] anayticsData = new MatchingJobProfileAnalyticsData[votes.size()];
	    int i = 0;
	    for (Entry<JobTitleIndex, Integer> entry : votes.entrySet()) {
	    	if (entry.getValue() > maxVotes) {
	    		maxVotes = entry.getValue();
	    		winningJob = entry.getKey();
	    	}
	    	
	    	anayticsData[i] = new MatchingJobProfileAnalyticsData(entry.getKey(), entry.getValue());
	    	i++;
	    }
	    
	    Arrays.sort(anayticsData);
	    List<JobTitleIndex> topMatchingJobs = new ArrayList<>();
	    JobDescriptionVector jdVector = new JobDescriptionVector(invertedListJobs.getWordList().size());
	    
	    for (i = 0; i < COUNT_MATCHING_JOBS; ++i) {
	    	topMatchingJobs.add(anayticsData[i].title);
	    	for (JobDescription jd : queryVsJobs.get(anayticsData[i].title.getTitle())) {
	    		jdVector.addVector(vectors.get(jd));
	    	}
	    }
	    
	    JobDescriptionVector resumeVector = new JobDescriptionVector(content, invertedListJobs.getWordList());
	    jdVector.logicalAndVector(resumeVector);
	    
	    jdVector.getImportantWords(invertedListJobs.getWordList());
	    
	    return topMatchingJobs;
	}

	public List<JobTitleIndex> getMatchingJobProfilesUsingCosineSimilarity(File file) throws IOException {

		if (vectors.size() == 0) {
			for (QueryVsJobs query : queries) {
				JobDescription jd = query.getJobDescription();
				vectors.put(jd, new JobDescriptionVector(jd, invertedListJobs.getWordList()));
			}
		}
		
		PDDocument pddDocument=PDDocument.load(file);
	    PDFTextStripper textStripper = new PDFTextStripper();
	    String content = textStripper.getText(pddDocument);
	    pddDocument.close();

	    JobDescriptionVector resumeVector = new JobDescriptionVector(content, invertedListJobs.getWordList());
	    CosineSimilarityAnalyticsData[] matchingResults = new CosineSimilarityAnalyticsData[queries.size()];
	    int index = 0;
		for (QueryVsJobs query : queries) {
			JobDescription jd = query.getJobDescription();
			Double similarity = resumeVector.cosineSimilarity(vectors.get(jd));
			matchingResults[index] = new CosineSimilarityAnalyticsData(query, similarity);
			index++;
		}
		
		Arrays.sort(matchingResults);
	    Map<JobTitleIndex, Integer> votes = new HashMap<>();
		for (int i = 0; i < 500; ++i) {
			System.out.println(matchingResults[i].queryVsJobs.getQuery() + " - " + matchingResults[i].similarity);
			if (votes.containsKey(titleMap.get(matchingResults[i].queryVsJobs.getQuery()))) {
				votes.put(titleMap.get(matchingResults[i].queryVsJobs.getQuery()), votes.get(titleMap.get(matchingResults[i].queryVsJobs.getQuery())) + 1);
			} else {
				votes.put(titleMap.get(matchingResults[i].queryVsJobs.getQuery()), 1);
			}
		}
	    
	    int maxVotes = 0;
	    JobTitleIndex winningJob = null;
	    MatchingJobProfileAnalyticsData[] anayticsData = new MatchingJobProfileAnalyticsData[votes.size()];
	    int i = 0;
	    for (Entry<JobTitleIndex, Integer> entry : votes.entrySet()) {
	    	if (entry.getValue() > maxVotes) {
	    		maxVotes = entry.getValue();
	    		winningJob = entry.getKey();
	    	}
	    	
	    	anayticsData[i] = new MatchingJobProfileAnalyticsData(entry.getKey(), entry.getValue());
	    	i++;
	    }
	    
	    Arrays.sort(anayticsData);
	    List<JobTitleIndex> topMatchingJobs = new ArrayList<>();
	    JobDescriptionVector jdVector = new JobDescriptionVector(invertedListJobs.getWordList().size());
	    
	    for (i = 0; i < COUNT_MATCHING_JOBS; ++i) {
	    	topMatchingJobs.add(anayticsData[i].title);
	    	for (JobDescription jd : queryVsJobs.get(anayticsData[i].title.getTitle())) {
	    		jdVector.addVector(vectors.get(jd));
	    	}
	    }
	    
	    //jdVector.logicalAndVector(resumeVector);
	    
	    jdVector.getImportantWords(invertedListJobs.getWordList());
	    
	    return topMatchingJobs;
	}
	
	static class CosineSimilarityAnalyticsData implements Comparable<CosineSimilarityAnalyticsData> {
		QueryVsJobs queryVsJobs;
		double similarity;
		public CosineSimilarityAnalyticsData(QueryVsJobs queryVsJobs, Double similarity) {
			super();
			this.queryVsJobs = queryVsJobs;
			this.similarity = similarity;
		}
		@Override
		public int compareTo(CosineSimilarityAnalyticsData o) {
			return (o.similarity > this.similarity ? 1 : (o.similarity == this.similarity) ? 0 : -1);
		}
	}
	
	static class MatchingJobProfileAnalyticsData implements Comparable<MatchingJobProfileAnalyticsData> {
		JobTitleIndex title;
		Integer countVotes;
		public MatchingJobProfileAnalyticsData(JobTitleIndex title, Integer countVotes) {
			super();
			this.title = title;
			this.countVotes = countVotes;
		}
		
		@Override
		public int compareTo(MatchingJobProfileAnalyticsData o) {
			if (this.countVotes != o.countVotes) {
				return (o.countVotes - this.countVotes);
			} else {
				return title.getPk() - o.title.getPk();
			}
		}
	}
}
