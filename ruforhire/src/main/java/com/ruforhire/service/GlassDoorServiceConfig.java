/**
 * 
 */
package com.ruforhire.service;

/**
 * @author ashish
 *
 */
public class GlassDoorServiceConfig {

	private String partnerId;
	private String key;
	private String api;
	
	public GlassDoorServiceConfig() {
		super();
	}
	
	public GlassDoorServiceConfig(String partnerId, String key, String api) {
		super();
		this.partnerId = partnerId;
		this.key = key;
		this.api = api;
	}
	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}
	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
		return "GlassDoorServiceConfig [partnerId=" + partnerId + ", key=" + key + ", api=" + api + "]";
	}
}
