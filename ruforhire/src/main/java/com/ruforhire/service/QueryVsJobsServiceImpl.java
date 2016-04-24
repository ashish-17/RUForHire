/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.dao.QueryVsJobsDao;
import com.ruforhire.model.JobDescription;
import com.ruforhire.model.QueryVsJobs;

/**
 * @author ashish
 *
 */
@Service
public class QueryVsJobsServiceImpl implements QueryVsJobsService {

	private QueryVsJobsDao queryVsJobsDao;
	
	/**
	 * @param queryVsJobsDao the queryVsJobsDao to set
	 */
	public void setQueryVsJobsDao(QueryVsJobsDao queryVsJobsDao) {
		this.queryVsJobsDao = queryVsJobsDao;
	}

	@Override
	@Transactional
	public void addQuery(QueryVsJobs q) {
		queryVsJobsDao.addQuery(q);
	}

	@Override
	@Transactional
	public List<JobDescription> getJobsForQuery(String query) {
		return queryVsJobsDao.getJobsForQuery(query);
	}

	@Override
	@Transactional
	public long getCount() {
		return queryVsJobsDao.getCount();
	}

	@Override
	public long getJobCountForQuery(String query) {
		return queryVsJobsDao.getJobCountForQuery(query);
	}
}
