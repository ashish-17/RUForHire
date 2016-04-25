/**
 * 
 */
package com.ruforhire.service;

import java.io.File;
import java.io.IOException;
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
	
	public JobTitleIndex getMatchingJobProfile(File file) throws IOException {
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
	    for (Entry<JobTitleIndex, Integer> entry : votes.entrySet()) {
	    	if (entry.getValue() > maxVotes) {
	    		maxVotes = entry.getValue();
	    		winningJob = entry.getKey();
	    	}
	    }
	    
	    return winningJob;
	}
}
