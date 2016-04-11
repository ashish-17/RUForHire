/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.dao.JobDescriptionDao;
import com.ruforhire.model.JobDescription;

/**
 * @author ashish
 *
 */
public class JobDescriptionServiceImpl implements JobDescriptionService {

	private JobDescriptionDao jobDescriptionDao;
	
	public void setJobDescriptionDao(JobDescriptionDao jobDescriptionDao) {
		this.jobDescriptionDao = jobDescriptionDao;
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
}
