/**
 * 
 */
package com.ruforhire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ashish
 *
 */
@Entity
@Table(name="QUERY_VS_JOBS")
public class QueryVsJobs {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int pk;
	
	private String query;

	@ManyToOne
    @JoinColumn(name = "jobDescription_id")
	private JobDescription jobDescription;

	public QueryVsJobs() {
		super();
	}

	public QueryVsJobs(String query, JobDescription jobDescription) {
		super();
		this.query = query;
		this.jobDescription = jobDescription;
	}

	/**
	 * @return the pk
	 */
	public int getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(int pk) {
		this.pk = pk;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the jobDescription
	 */
	public JobDescription getJobDescription() {
		return jobDescription;
	}

	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(JobDescription jobDescription) {
		this.jobDescription = jobDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryVsJobs [pk=" + pk + ", query=" + query + ", jobDescription=" + jobDescription + "]";
	} 
}
