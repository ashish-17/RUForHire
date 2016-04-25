/**
 * 
 */
package com.ruforhire.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.dao.JobDescriptionDao;
import com.ruforhire.model.Employer;
import com.ruforhire.model.JobDescription;
import com.ruforhire.model.JobTitleIndex;
import com.ruforhire.model.QueryVsJobs;
import com.ruforhire.service.IndeedServiceProvider.JOB_TYPE;
import com.ruforhire.service.IndeedServiceProvider.SITE_TYPE;
import com.ruforhire.service.IndeedServiceProvider.SORTBY;

/**
 * @author ashish
 *
 */
@Service
public class JobDescriptionServiceImpl implements JobDescriptionService {

	private static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z"; //Sat, 05 Mar 2016 00:54:36 GMT
	private static final int COUNT_JOBS_PER_TITLE = 100;
	private static final int JOBS_FETCHED_PER_QUERY = 25;
	private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	
	private JobDescriptionDao jobDescriptionDao;
	private IndeedServiceProvider indeedServiceProvider;
	private EmployerService employerService;
	private GlassDoorServiceProvider glassDoorServiceProvider;
	private JobTitleService jobTitleService;
	private QueryVsJobsService queryVsJobsService;

	public void setQueryVsJobsService(QueryVsJobsService queryVsJobsService) {
		this.queryVsJobsService = queryVsJobsService;
	}
	
	public void setJobDescriptionDao(JobDescriptionDao jobDescriptionDao) {
		this.jobDescriptionDao = jobDescriptionDao;
	}
	
	public void setIndeedServiceProvider(IndeedServiceProvider indeedServiceProvider) {
		this.indeedServiceProvider = indeedServiceProvider;
	}
	
	public void setEmployerService(EmployerService employerService) {
		this.employerService = employerService;
	}
	
	public void setGlassDoorServiceProvider(GlassDoorServiceProvider glassDoorServiceProvider) {
		this.glassDoorServiceProvider = glassDoorServiceProvider;
	}

	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	@Override
	@Transactional
	public void addJobDescription(JobDescription jd) {
		jobDescriptionDao.addJobDescription(jd);
	}

	@Override
	@Transactional
	public void updateJobDescription(JobDescription jd) {
		jobDescriptionDao.updateJobDescription(jd);		
	}

	@Override
	@Transactional
	public List<JobDescription> listJobDescriptions() {
		return jobDescriptionDao.listJobDescriptions();
	}

	@Override
	@Transactional
	public JobDescription getJobDescriptionById(int id) {
		return jobDescriptionDao.getJobDescriptionById(id);
	}

	@Override
	@Transactional
	public void removeJobDescription(int id) {
		jobDescriptionDao.removeJobDescription(id);		
	}

	@Override
	public JobSearchServiceResponse searchJobs(String query, String location, SORTBY sortBy,JOB_TYPE jobType, int start) {
		try {
			IndeedServiceResponse indeedServiceResponse = indeedServiceProvider.getJobs(
					query, 
					location, 
					sortBy, 
					25, 
					SITE_TYPE.EMPLOYER, 
					jobType, 
					start, 
					JOBS_FETCHED_PER_QUERY, 
					90, 
					"us");

			JobSearchServiceResponse response = new JobSearchServiceResponse();
			response.setStart(indeedServiceResponse.getStart());
			response.setEnd(indeedServiceResponse.getEnd());
			response.setPageNumber(indeedServiceResponse.getPageNumber());
			response.setTotalResults(indeedServiceResponse.getTotalResults());

			for (IndeedServiceResponse.Job job : indeedServiceResponse.getJobs()) {
				Employer employer = employerService.findEmployerByName(job.getEmployerName());
				if (employer == null) {
					try {
						employer = glassDoorServiceProvider.getEmployerInformation(job.getEmployerName(), "", "", "us");
					} catch (Exception e) {
						System.out.println("Unable to fetch employer info");
						System.out.println(e.getMessage());
					}
					if (employer == null) {
						continue;
					}
					employerService.addEmployer(employer);
				}
				
				JobDescription jd = new JobDescription(
						job.getJobkey(), 
						job.getJobTitle(), 
						employer, 
						job.getCity(), 
						job.getState(), 
						job.getCountry(), 
						job.getFormattedLocation(), 
						job.getSource(), 
						new Date(formatter.parse(job.getDate()).getTime()), 
						job.getSnippet(), 
						job.getUrl(), 
						job.getLatitude(), 
						job.getLongitude(), 
						job.isSponsored(), 
						job.isExpired(), 
						job.getFormattedLocationFull(), 
						job.getFormattedRelativeTime());
				
				System.out.println(jd);
				response.getJobs().add(jd);
			}
			
			return response;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void saveJobs(JobSearchServiceResponse response) {
		for (JobDescription job : response.getJobs()) {
			jobDescriptionDao.addJobDescription(job);
		}
	}
	
	@Override
	public void updateDatabase() {
		List<JobTitleIndex> jobTitles = jobTitleService.listPopulerJobTitles();
		if (queryVsJobsService.getCount() < jobTitles.size()*COUNT_JOBS_PER_TITLE) {
			for (JobTitleIndex jobTitle : jobTitles) {
				long countJobsForQuery = queryVsJobsService.getJobCountForQuery(jobTitle.getTitle());
				if (countJobsForQuery < COUNT_JOBS_PER_TITLE) {
					for (int query = 0; query < (COUNT_JOBS_PER_TITLE - countJobsForQuery) / JOBS_FETCHED_PER_QUERY; ++query) {
						JobSearchServiceResponse response = null;
						if (jobTitle.getTitle().indexOf("intern") == -1) {
							response = searchJobs(jobTitle.getTitle(), "us", SORTBY.RELEVANCE, JOB_TYPE.FULL_TIME, query*JOBS_FETCHED_PER_QUERY + (int)countJobsForQuery);
						} else {
							response = searchJobs(jobTitle.getTitle(), "us", SORTBY.RELEVANCE, JOB_TYPE.INTERNSHIP, query*JOBS_FETCHED_PER_QUERY + (int)countJobsForQuery);
						}
						
						for (JobDescription job : response.getJobs()) {
							jobDescriptionDao.addJobDescription(job);
							queryVsJobsService.addQuery(new QueryVsJobs(jobTitle.getTitle(), job));
						}
					}
				}
			}
		}
	}
}
