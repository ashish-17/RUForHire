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

	public void addJobDescription(JobDescription jd);
	public void updateJobDescription(JobDescription jd);
	public List<JobDescription> listJobDescriptions();
	public JobDescription getJobDescriptionById(int id);
	public void removeJobDescription(int id);
	public JobSearchServiceResponse searchJobs(String query, String location, SORTBY sortBy, JOB_TYPE jobType, int start);
	public void saveJobs(JobSearchServiceResponse response);
	public void updateDatabase();
}
