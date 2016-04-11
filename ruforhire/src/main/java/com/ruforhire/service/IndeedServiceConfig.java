/**
 * 
 */
package com.ruforhire.service;

/**
 * @author ashish
 *
 */
public class IndeedServiceConfig {
	private String publisherId;
	public String api;
	
	public IndeedServiceConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndeedServiceConfig(String publisherId, String api) {
		super();
		this.publisherId = publisherId;
		this.api = api;
	}

	/**
	 * @return the publisherId
	 */
	public String getPublisherId() {
		return publisherId;
	}

	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	/**
	 * @return the api
	 */
	public String getApi() {
		return api;
	}

	/**
	 * @param api the api to set
	 */
	public void setApi(String api) {
		this.api = api;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IndeedServiceConfig [publisherId=" + publisherId + ", api=" + api + "]";
	}
}
