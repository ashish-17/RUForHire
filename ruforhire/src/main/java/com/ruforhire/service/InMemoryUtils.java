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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ruforhire.model.InvertedListJobTitles;
import com.ruforhire.model.JobDescription;
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
		stopWords = new Stopwords();
	}
	
	@PostConstruct
	public void init() {
		List<JobTitleIndex> titles = jobTitleService.listPopulerJobTitles();
		for (JobTitleIndex title : titles) {
			titleMap.put(title.getTitle(), title);
		}
		
		List<QueryVsJobs> queries = queryVsJobsService.listJobsVsQuery();
		for (QueryVsJobs query : queries) {
			JobDescription jd = query.getJobDescription();
			String[] title = jd.getJobTitle().split("\\s+");
			for (String str : title) {
				str = str.toLowerCase();
				if (!stopWords.is(str)) {
					Stemmer stemmer = new Stemmer();
					stemmer.add(str.toCharArray(), str.length());
					stemmer.stem();
					invertedListJobs.add(stemmer.toString(), titleMap.get(query.getQuery()));
				}
			}
			
			String[] snippet = Jsoup.parse(jd.getSnippet()).text().split("\\s+");
			for (String str : snippet) {
				str = str.toLowerCase();
				if (!stopWords.is(str)) {
					Stemmer stemmer = new Stemmer();
					stemmer.add(str.toCharArray(), str.length());
					stemmer.stem();
					invertedListJobs.add(stemmer.toString(), titleMap.get(query.getQuery()));
				}
			}
		}
	}
	
	public List<JobTitleIndex> getMatchingJobProfiles(File file) throws IOException {
		PDDocument pddDocument=PDDocument.load(file);
	    PDFTextStripper textStripper = new PDFTextStripper();
	    String content = textStripper.getText(pddDocument);
	    String[] words = content.split("\\s+");
	    Map<JobTitleIndex, Integer> votes = new HashMap<>();
	    for (String word : words) {
	    	word = word.toLowerCase();
			if (!stopWords.is(word)) {
				Stemmer stemmer = new Stemmer();
				stemmer.add(word.toCharArray(), word.length());
				stemmer.stem();
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
	    for (i = 0; i < COUNT_MATCHING_JOBS; ++i) {
	    	topMatchingJobs.add(anayticsData[i].title);
	    }
	    
	    return topMatchingJobs;
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
