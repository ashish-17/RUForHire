/**
 * 
 */
package com.ruforhire.dao;

import java.util.List;

import com.ruforhire.model.JobTitleIndex;

/**
 * @author ashish
 *
 */
public interface JobTitleIndexDao {

	public void addJobTitle(JobTitleIndex jobTitle);
	public void updateJobTitle(JobTitleIndex jobTitle);
	public List<JobTitleIndex> listPopulerJobTitles();
	public long getCount();
}
