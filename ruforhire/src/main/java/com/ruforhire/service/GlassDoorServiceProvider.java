/**
 * 
 */
package com.ruforhire.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.model.Verb;

import com.ruforhire.model.Employer;

/**
 * @author ashish
 *
 */
public class GlassDoorServiceProvider {

	private GlassDoorServiceConfig config;

	public GlassDoorServiceProvider() {
		super();
	}

	public GlassDoorServiceProvider(GlassDoorServiceConfig config) {
		super();
		this.config = config;
	}

	/**
	 * @return the config
	 */
	public GlassDoorServiceConfig getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(GlassDoorServiceConfig config) {
		this.config = config;
	}
	
	public Employer getEmployerInformation(String companyName, String city, String state, String country) throws MalformedURLException, IOException {
		
		ApiRequest request = new ApiRequest(Verb.GET, config.getApi());
		request.addQuerystringParameter("v", "1");
		request.addQuerystringParameter("format", "json");
		request.addQuerystringParameter("t.p", config.getPartnerId());
		request.addQuerystringParameter("t.k", config.getKey());
		request.addQuerystringParameter("userip", "0.0.0.0");
		request.addQuerystringParameter("useragent", "");
		request.addQuerystringParameter("action", "employers");
		request.addQuerystringParameter("q", companyName);
		request.addQuerystringParameter("city", "");
		request.addQuerystringParameter("state", "");
		request.addQuerystringParameter("country", country);
		
		ApiResponse response = request.send();
		JSONObject jsonObject = new JSONObject(response.getBody());
		JSONObject employerInfo = jsonObject.getJSONObject("response");
		JSONArray employers = (JSONArray)employerInfo.get("employers");
		JSONObject jsonObjEmployer = employers.getJSONObject(0);
		System.out.println(jsonObjEmployer.toString());
		Employer employer = new Employer(
				jsonObjEmployer.getInt("id"), 
				jsonObjEmployer.getString("name"), 	
				jsonObjEmployer.getString("website"), 
				jsonObjEmployer.getBoolean("isEEP"), 
				jsonObjEmployer.getString("industry"), 
				jsonObjEmployer.getString("squareLogo"), 
				jsonObjEmployer.getDouble("overallRating"), 
				Double.parseDouble(jsonObjEmployer.getString("cultureAndValuesRating")), 
				Double.parseDouble(jsonObjEmployer.getString("seniorLeadershipRating")), 
				Double.parseDouble(jsonObjEmployer.getString("compensationAndBenefitsRating")), 
				Double.parseDouble(jsonObjEmployer.getString("careerOpportunitiesRating")), 
				Double.parseDouble(jsonObjEmployer.getString("workLifeBalanceRating")),
				jsonObjEmployer.getInt("numberOfRatings"));
		
		System.out.println(employer.toString());
		return employer;
	}
	
	public static void main (String[] args) throws MalformedURLException, IOException {
		GlassDoorServiceProvider service = new GlassDoorServiceProvider(new GlassDoorServiceConfig("60494", "hGmxMyancEM", "http://api.glassdoor.com/api/api.htm"));
		service.getEmployerInformation("liveramp", "", "", "us");
	}
}
