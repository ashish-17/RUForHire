/**
 * 
 */
package com.ruforhire.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.dao.JobTitleIndexDao;
import com.ruforhire.dao.JobTitleIndexDaoImpl;
import com.ruforhire.model.JobTitleIndex;

/**
 * @author ashish
 *
 */
@Service
public class JobTitleServiceImpl implements JobTitleService {
	private static final String FETCH_URL = "http://www.glassdoor.com/site-directory/title-jobs.htm";
	private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36";
	
	private JobTitleIndexDao jobTitleIndexDao;

	public void setJobTitleIndexDao(JobTitleIndexDao jobTitleIndexDao) {
		this.jobTitleIndexDao = jobTitleIndexDao;
	}

	public JobTitleServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobTitleServiceImpl(JobTitleIndexDao jobTitleIndexDao) {
		super();
		this.jobTitleIndexDao = jobTitleIndexDao;
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.service.JobTitleService#updateDatabase()
	 */
	@Override
	@Transactional
	public void updateDatabase() {
		try {
			if (jobTitleIndexDao.getCount() == 0) {
				Document doc;

				doc = Jsoup.connect(FETCH_URL)
						.userAgent(USER_AGENT)
						.maxBodySize(0)
						.get();

				Element alphaPages = doc.getElementById("AlphaPages");
				Elements allTitles = alphaPages.getElementsByTag("li");
				List<String> urls = new ArrayList<>();
				urls.add(FETCH_URL);
				for (Element e : allTitles) {
					Element link = e.select("a").first();
					String href = link.attr("abs:href");
					urls.add(href);
				}

				TimeUnit.SECONDS.sleep(10);
				
				boolean isFirst = true;
				for (String url : urls) {
					doc = Jsoup.connect(url)
							.userAgent(USER_AGENT)
							.maxBodySize(0)
							.timeout(600000)
							.get();
					Element pageLinks = doc.getElementById("PageLinks");
					Elements links = pageLinks.getElementsByTag("a");
					for (Element e : links) {
						String title = e.text();
						title = title.substring(0, title.lastIndexOf(" "));
						JobTitleIndex jobTitle = new JobTitleIndex(title, isFirst);
						jobTitleIndexDao.addJobTitle(jobTitle);
					}
					
					isFirst = false;
					break; // Only store popular job titles for now.
				}
			}
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	@Transactional
	public List<JobTitleIndex> listPopulerJobTitles() {
		return jobTitleIndexDao.listPopulerJobTitles();
	}
	
	public static void main(String[] args) {
		JobTitleServiceImpl jobTitleService = new JobTitleServiceImpl(new JobTitleIndexDaoImpl());
		jobTitleService.updateDatabase();
	}
}
