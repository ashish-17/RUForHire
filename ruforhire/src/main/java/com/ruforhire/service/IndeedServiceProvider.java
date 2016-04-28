/**
 * 
 */
package com.ruforhire.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

/**
 * @author ashish
 *
 */
@Service
public class IndeedServiceProvider {

	private final String API_VERSION = "2";
	private final String RESPONSE_DATA_FORMAT = "json";
	private IndeedServiceConfig config;

	public IndeedServiceProvider() {
		super();
	}

	public IndeedServiceProvider(IndeedServiceConfig config) {
		super();
		this.config = config;
	}

	/**
	 * @return the config
	 */
	public IndeedServiceConfig getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(IndeedServiceConfig config) {
		this.config = config;
	}
	
	public IndeedServiceResponse getJobs(
			String query, 
			String location, 
			SORTBY sortBy, 
			int radius, 
			SITE_TYPE site, 
			JOB_TYPE jobType,
			int start,
			int limit,
			int fromAge,
			String country) {
		
		IndeedServiceResponse apiResult = new IndeedServiceResponse();

		ApiRequest request = new ApiRequest(Verb.GET, config.getApi());
		request.addQuerystringParameter("publisher", config.getPublisherId());
		request.addQuerystringParameter("v", API_VERSION);
		request.addQuerystringParameter("format", RESPONSE_DATA_FORMAT);
		request.addQuerystringParameter("q", query);
		request.addQuerystringParameter("l", location);
		request.addQuerystringParameter("sort", sortBy.toString());
		request.addQuerystringParameter("radius", Integer.toString(radius));
		request.addQuerystringParameter("st", site.toString());
		request.addQuerystringParameter("jt", jobType.toString());
		request.addQuerystringParameter("start", Integer.toString(start));
		request.addQuerystringParameter("limit", Integer.toString(limit));
		request.addQuerystringParameter("fromage", Integer.toString(fromAge));
		request.addQuerystringParameter("latlong", "1");
		request.addQuerystringParameter("co", country);
		request.addQuerystringParameter("userip", "0.0.0.0");
		request.addQuerystringParameter("useragent", "");

		ApiResponse response = request.send();
		JSONObject jsonObject = new JSONObject(response.getBody());
		
		apiResult.setStart(jsonObject.getInt("start"));
		apiResult.setEnd(jsonObject.getInt("end"));
		apiResult.setTotalResults(jsonObject.getInt("totalResults"));
		apiResult.setPageNumber(jsonObject.getInt("pageNumber"));
		
		JSONArray jobs = (JSONArray)jsonObject.get("results");
		for (int jobIndex = 0; jobIndex < jobs.length(); ++jobIndex) {
			JSONObject job = jobs.getJSONObject(jobIndex);
			try {

				IndeedServiceResponse.Job jd = new IndeedServiceResponse.Job(
						job.getString("jobkey"), 
						job.getString("jobtitle"), 
						job.getString("company"), 
						job.getString("city"), 
						job.getString("state"), 
						job.getString("country"), 
						job.getString("formattedLocation"), 
						job.getString("source"), 
						job.getString("date"), 
						job.getString("snippet"), 
						job.getString("url"), 
						job.getDouble("latitude"), 
						job.getDouble("longitude"), 
						job.getBoolean("sponsored"), 
						job.getBoolean("expired"), 
						job.getString("formattedLocationFull"), 
						job.getString("formattedRelativeTime"));
				
				//System.out.println(jd);
				apiResult.getJobs().add(jd);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
		
		return apiResult;
	}
	
	public enum SORTBY {
		RELEVANCE("relevance"),
		DATE("date");
		
		private String str;
		private SORTBY(String str) {
			this.str= str;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return this.str;
		}
	}

	public enum SITE_TYPE {
		EMPLOYER("employer"),
		JOB_SITE("jobsite");
		
		private String str;
		private SITE_TYPE(String str) {
			this.str= str;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return this.str;
		}
	}

	public enum JOB_TYPE {
		FULL_TIME("fulltime"),
		PART_TIME("parttime"),
		CONTRACT("contract"),
		INTERNSHIP("internship"),
		TEMPORARY("temporary");
		
		private String str;
		private JOB_TYPE(String str) {
			this.str= str;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return this.str;
		}
	}
	
	public static void main(String[] args) {
		IndeedServiceProvider service = new IndeedServiceProvider(new IndeedServiceConfig("8246993129278389", "http://api.indeed.com/ads/apisearch"));
		service.getJobs("Software", "", SORTBY.DATE, 25, SITE_TYPE.EMPLOYER, JOB_TYPE.INTERNSHIP, 0, 25, 15, "us");
	}
}
