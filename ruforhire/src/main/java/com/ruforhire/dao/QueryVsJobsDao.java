/**
 * 
 */
package com.ruforhire.dao;

import java.util.List;

import com.ruforhire.model.JobDescription;
import com.ruforhire.model.QueryVsJobs;

/**
 * @author ashish
 *
 */
public interface QueryVsJobsDao {

	void addQuery(QueryVsJobs q);
	List<QueryVsJobs> listJobsVsQuery();
	List<JobDescription> getJobsForQuery(String query);
	long getJobCountForQuery(String query);
	long getCount();
}
