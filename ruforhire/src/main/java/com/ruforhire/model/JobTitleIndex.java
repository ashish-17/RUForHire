/**
 * 
 */
package com.ruforhire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ashish
 *
 */
@Entity
@Table(name="JOB_TITLE_INDEX")
public class JobTitleIndex {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int pk;
	
	private String title;
	
	private boolean isMostPopular;

	public JobTitleIndex() {
		super();
	}

	public JobTitleIndex(String title, boolean isMostPopular) {
		super();
		this.title = title;
		this.isMostPopular = isMostPopular;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the isMostPopular
	 */
	public boolean isMostPopular() {
		return isMostPopular;
	}

	/**
	 * @param isMostPopular the isMostPopular to set
	 */
	public void setMostPopular(boolean isMostPopular) {
		this.isMostPopular = isMostPopular;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobTitleIndex [pk=" + pk + ", title=" + title + ", isMostPopular=" + isMostPopular + "]";
	}
}
