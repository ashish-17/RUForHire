/**
 * 
 */
package com.ruforhire.service;

import java.util.ArrayList;
import java.util.List;

import com.ruforhire.model.JobDescription;

/**
 * @author ashish
 *
 */
public class JobSearchServiceResponse {
	private int totalResults;
	private int start;
	private int end;
	private int pageNumber;
	private List<JobDescription> jobs;
	
	public JobSearchServiceResponse() {
		super();
		totalResults = 0;
		start = 0;
		end = 0;
		pageNumber = 0;
		jobs = new ArrayList<>();
	}
	
	public JobSearchServiceResponse(int totalResults, int start, int end, int pageNumber, List<JobDescription> jobs) {
		super();
		this.totalResults = totalResults;
		this.start = start;
		this.end = end;
		this.pageNumber = pageNumber;
		this.jobs = jobs;
	}

	/**
	 * @return the totalResults
	 */
	public int getTotalResults() {
		return totalResults;
	}

	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the jobs
	 */
	public List<JobDescription> getJobs() {
		return jobs;
	}

	/**
	 * @param jobs the jobs to set
	 */
	public void setJobs(List<JobDescription> jobs) {
		this.jobs = jobs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobSearchServiceResponse [totalResults=" + totalResults + ", start=" + start + ", end=" + end
				+ ", pageNumber=" + pageNumber + ", jobs=" + jobs + "]";
	}
}
