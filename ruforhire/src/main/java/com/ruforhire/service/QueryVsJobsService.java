/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.model.JobDescription;
import com.ruforhire.model.QueryVsJobs;

/**
 * @author ashish
 *
 */
public interface QueryVsJobsService {

	void addQuery(QueryVsJobs q);
	List<QueryVsJobs> listJobsVsQuery();
	List<JobDescription> getJobsForQuery(String query);
	long getCount();
	long getJobCountForQuery(String query);
}
