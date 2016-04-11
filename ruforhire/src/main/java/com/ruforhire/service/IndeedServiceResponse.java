/**
 * 
 */
package com.ruforhire.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ashish
 *
 */
public class IndeedServiceResponse {
	private int totalResults;
	private int start;
	private int end;
	private int pageNumber;
	private List<Job> jobs;
	public IndeedServiceResponse() {
		super();
		
		totalResults = 0;
		start = 0;
		end = 0;
		pageNumber = 0;
		jobs = new ArrayList<>();
	}
	
	public IndeedServiceResponse(int totalResults, int start, int end, int pageNumber, List<Job> jobs) {
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
	public List<Job> getJobs() {
		return jobs;
	}

	/**
	 * @param jobs the jobs to set
	 */
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public static class Job {

		private String jobkey;
		private String jobTitle;
		private String employerName;
		private String city;
		private String state;
		private String country;
		private String formattedLocation;
		private String source;
		private String date;
		private String snippet;
		private String url;
		private Double latitude;
		private Double longitude;
		private boolean sponsored;
		private boolean expired;
		private String formattedLocationFull;
		private String formattedRelativeTime;
		private boolean noUniqueUrl;
		public Job() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Job(String jobkey, String jobTitle, String employerName, String city, String state, String country,
				String formattedLocation, String source, String date, String snippet, String url, Double latitude,
				Double longitude, boolean sponsored, boolean expired, String formattedLocationFull,
				String formattedRelativeTime, boolean noUniqueUrl) {
			super();
			this.jobkey = jobkey;
			this.jobTitle = jobTitle;
			this.employerName = employerName;
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
		 * @return the employerName
		 */
		public String getEmployerName() {
			return employerName;
		}
		/**
		 * @param employerName the employerName to set
		 */
		public void setEmployerName(String employerName) {
			this.employerName = employerName;
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
		public String getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
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
			return "Job [jobkey=" + jobkey + ", jobTitle=" + jobTitle + ", employerName=" + employerName + ", city="
					+ city + ", state=" + state + ", country=" + country + ", formattedLocation=" + formattedLocation
					+ ", source=" + source + ", date=" + date + ", snippet=" + snippet + ", url=" + url + ", latitude="
					+ latitude + ", longitude=" + longitude + ", sponsored=" + sponsored + ", expired=" + expired
					+ ", formattedLocationFull=" + formattedLocationFull + ", formattedRelativeTime="
					+ formattedRelativeTime + ", noUniqueUrl=" + noUniqueUrl + "]";
		}
	}
}
