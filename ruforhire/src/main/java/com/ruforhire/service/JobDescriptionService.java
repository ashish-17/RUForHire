/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.model.JobDescription;
import com.ruforhire.service.IndeedServiceProvider.JOB_TYPE;
import com.ruforhire.service.IndeedServiceProvider.SORTBY;

/**
 * @author ashish
 *
 */
public interface JobDescriptionService {

	public static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z"; //Sat, 05 Mar 2016 00:54:36 GMT
	public static final int COUNT_JOBS_PER_TITLE = 300;
	public static final int JOBS_FETCHED_PER_QUERY = 25;
	
	public void addJobDescription(JobDescription jd);
	public void updateJobDescription(JobDescription jd);
	public List<JobDescription> listJobDescriptions();
	public JobDescription getJobDescriptionById(int id);
	public void removeJobDescription(int id);
	public JobSearchServiceResponse searchJobs(String query, String location, SORTBY sortBy, JOB_TYPE jobType, int start, int count);
	public void saveJobs(JobSearchServiceResponse response);
	public void updateDatabase();
}
