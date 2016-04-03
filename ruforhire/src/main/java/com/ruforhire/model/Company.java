/**
 * 
 */
package com.ruforhire.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author ashish
 *
 */
@DynamicUpdate
@Table(name="COMPANY")
public class Company {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String jobUrl;

	/**
	 * @return the jobUrl
	 */
	public String getJobUrl() {
		return jobUrl;
	}

	/**
	 * @param jobUrl the jobUrl to set
	 */
	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (id + " - " + name);
	}
}
