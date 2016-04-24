/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.model.JobTitleIndex;

/**
 * @author ashish
 *
 */
public interface JobTitleService {

	void updateDatabase();
	List<JobTitleIndex> listPopulerJobTitles();
}
