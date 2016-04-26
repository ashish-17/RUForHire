/**
 * 
 */
package com.ruforhire.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author ashish
 *
 */
public class InvertedListJobTitles {

	private Map<String, HashSet<JobTitleIndex>> invertedList;
	
	public InvertedListJobTitles() {
		super();
		invertedList = new HashMap<String, HashSet<JobTitleIndex>>();
	}
	
	public void add(String keyword, JobTitleIndex title) {
		if (!invertedList.containsKey(keyword)) {
			invertedList.put(keyword, new HashSet<JobTitleIndex>());
		}

		invertedList.get(keyword).add(title);
	}
	
	public List<JobTitleIndex> get(String keyword) {
		if (invertedList.get(keyword) != null) {
			List<JobTitleIndex> list = new ArrayList<JobTitleIndex>(invertedList.get(keyword));
			return list;
		}
		
		return null;
	}
	
	public List<String> getWordList() {
		return new ArrayList<>(invertedList.keySet());
	}
}
