/**
 * 
 */
package com.ruforhire.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ashish
 *
 */
public class InvertedListJobTitles {

	private Map<String, ArrayList<JobTitleIndex>> invertedList;
	
	public InvertedListJobTitles() {
		super();
		invertedList = new HashMap<String, ArrayList<JobTitleIndex>>();
	}
	
	public void add(String keyword, JobTitleIndex title) {
		if (!invertedList.containsKey(keyword)) {
			invertedList.put(keyword, new ArrayList<JobTitleIndex>());
		}
		
		invertedList.get(keyword).add(title);
	}
	
	public List<JobTitleIndex> get(String keyword) {
		return invertedList.get(keyword);
	}
}
