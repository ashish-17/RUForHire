/**
 * 
 */
package com.ruforhire.model;

import java.sql.Date;

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
@Table(name="JOB")
public class JobDescription {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int pk;
	
	private String jobkey;
	private String jobTitle;
	
	@ManyToOne
    @JoinColumn(name = "employer_id")
	private Employer employer;
	
	private String city;
	private String state;
	private String country;
	private String formattedLocation;
	private String source;
	private Date date;
	private String snippet;
	private String url;
	private Double latitude;
	private Double longitude;
	private boolean sponsored;
	private boolean expired;
	private String formattedLocationFull;
	private String formattedRelativeTime;
	private boolean noUniqueUrl;
	
	public JobDescription() {
		super();
	}

	public JobDescription(String jobkey, String jobTitle, Employer employer, String city, String state, String country,
			String formattedLocation, String source, Date date, String snippet, String url, Double latitude,
			Double longitude, boolean sponsored, boolean expired, String formattedLocationFull,
			String formattedRelativeTime, boolean noUniqueUrl) {
		super();
		this.jobkey = jobkey;
		this.jobTitle = jobTitle;
		this.employer = employer;
		this.city = city;
		this.state = state;
		this.country = country;
		this.formattedLocation = formattedLocation;
		this.source = source;
		this.date = date;
		this.snippet = snippet;
		this.url = url;
		this.latitude = latitude;
		this.longitude = longitude;
		this.sponsored = sponsored;
		this.expired = expired;
		this.formattedLocationFull = formattedLocationFull;
		this.formattedRelativeTime = formattedRelativeTime;
		this.noUniqueUrl = noUniqueUrl;
	}

	/**
	 * @return the jobkey
	 */
	public String getJobkey() {
		return jobkey;
	}

	/**
	 * @param jobkey the jobkey to set
	 */
	public void setJobkey(String jobkey) {
		this.jobkey = jobkey;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the employer
	 */
	public Employer getEmployer() {
		return employer;
	}

	/**
	 * @param employer the employer to set
	 */
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the formattedLocation
	 */
	public String getFormattedLocation() {
		return formattedLocation;
	}

	/**
	 * @param formattedLocation the formattedLocation to set
	 */
	public void setFormattedLocation(String formattedLocation) {
		this.formattedLocation = formattedLocation;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param data the data to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the snippet
	 */
	public String getSnippet() {
		return snippet;
	}

	/**
	 * @param snippet the snippet to set
	 */
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the sponsored
	 */
	public boolean isSponsored() {
		return sponsored;
	}

	/**
	 * @param sponsored the sponsored to set
	 */
	public void setSponsored(boolean sponsored) {
		this.sponsored = sponsored;
	}

	/**
	 * @return the expired
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * @return the formattedLocationFull
	 */
	public String getFormattedLocationFull() {
		return formattedLocationFull;
	}

	/**
	 * @param formattedLocationFull the formattedLocationFull to set
	 */
	public void setFormattedLocationFull(String formattedLocationFull) {
		this.formattedLocationFull = formattedLocationFull;
	}

	/**
	 * @return the formattedRelativeTime
	 */
	public String getFormattedRelativeTime() {
		return formattedRelativeTime;
	}

	/**
	 * @param formattedRelativeTime the formattedRelativeTime to set
	 */
	public void setFormattedRelativeTime(String formattedRelativeTime) {
		this.formattedRelativeTime = formattedRelativeTime;
	}

	/**
	 * @return the noUniqueUrl
	 */
	public boolean isNoUniqueUrl() {
		return noUniqueUrl;
	}

	/**
	 * @param noUniqueUrl the noUniqueUrl to set
	 */
	public void setNoUniqueUrl(boolean noUniqueUrl) {
		this.noUniqueUrl = noUniqueUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobDescription [jobkey=" + jobkey + ", jobTitle=" + jobTitle + ", employer=" + employer + ", city="
				+ city + ", state=" + state + ", country=" + country + ", formattedLocation=" + formattedLocation
				+ ", source=" + source + ", data=" + date + ", snippet=" + snippet + ", url=" + url + ", latitude="
				+ latitude + ", longitude=" + longitude + ", sponsored=" + sponsored + ", expired=" + expired
				+ ", formattedLocationFull=" + formattedLocationFull + ", formattedRelativeTime="
				+ formattedRelativeTime + ", noUniqueUrl=" + noUniqueUrl + "]";
	}
}
