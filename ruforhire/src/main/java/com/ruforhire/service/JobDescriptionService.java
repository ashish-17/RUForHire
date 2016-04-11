/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.model.JobDescription;

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
	public JobSearchServiceResponse searchJobs(String query, String location, int start);
}
