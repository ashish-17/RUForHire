/**
 * 
 */
package com.ruforhire.dao;

import java.util.List;

import com.ruforhire.model.JobDescription;

/**
 * @author ashish
 *
 */
public interface JobDescriptionDao {

	public void addJobDescription(JobDescription jd);
	public void updateJobDescription(JobDescription jd);
	public List<JobDescription> listJobDescriptions();
	public JobDescription getJobDescriptionById(int id);
	public void removeJobDescription(int id);
}
