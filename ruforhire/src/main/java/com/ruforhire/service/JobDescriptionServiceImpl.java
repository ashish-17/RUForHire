/**
 * 
 */
package com.ruforhire.service;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ruforhire.dao.JobDescriptionDao;
import com.ruforhire.model.Employer;
import com.ruforhire.model.JobDescription;
import com.ruforhire.service.IndeedServiceProvider.JOB_TYPE;
import com.ruforhire.service.IndeedServiceProvider.SITE_TYPE;
import com.ruforhire.service.IndeedServiceProvider.SORTBY;

/**
 * @author ashish
 *
 */
public class JobDescriptionServiceImpl implements JobDescriptionService {

	private static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z"; //Sat, 05 Mar 2016 00:54:36 GMT
	private JobDescriptionDao jobDescriptionDao;
	private IndeedServiceProvider indeedServiceProvider;
	private EmployerService employerService;
	private GlassDoorServiceProvider glassDoorServiceProvider;
	private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	
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

	@Override
	public void addJobDescription(JobDescription jd) {
		jobDescriptionDao.addJobDescription(jd);
	}

	@Override
	public void updateJobDescription(JobDescription jd) {
		jobDescriptionDao.updateJobDescription(jd);		
	}

	@Override
	public List<JobDescription> listJobDescriptions() {
		return jobDescriptionDao.listJobDescriptions();
	}

	@Override
	public JobDescription getJobDescriptionById(int id) {
		return jobDescriptionDao.getJobDescriptionById(id);
	}

	@Override
	public void removeJobDescription(int id) {
		jobDescriptionDao.removeJobDescription(id);		
	}

	@Override
	public JobSearchServiceResponse searchJobs(String query, String location, int start) {
		try {
			IndeedServiceResponse indeedServiceResponse = indeedServiceProvider.getJobs(
					query, 
					location, 
					SORTBY.RELEVANCE, 
					25, 
					SITE_TYPE.EMPLOYER, 
					JOB_TYPE.FULL_TIME, 
					start, 
					10, 
					30, 
					"us");

			JobSearchServiceResponse response = new JobSearchServiceResponse();
			response.setStart(indeedServiceResponse.getStart());
			response.setEnd(indeedServiceResponse.getEnd());
			response.setPageNumber(indeedServiceResponse.getPageNumber());
			response.setTotalResults(indeedServiceResponse.getTotalResults());

			for (IndeedServiceResponse.Job job : indeedServiceResponse.getJobs()) {
				Employer employer = employerService.findEmployerByName(job.getEmployerName());
				if (employer == null) {
					employer = glassDoorServiceProvider.getEmployerInformation(job.getEmployerName(), "", "", "us");
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
						job.getFormattedRelativeTime(), 
						job.isNoUniqueUrl());
				
				System.out.println(jd);
				response.getJobs().add(jd);
			}
			
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
