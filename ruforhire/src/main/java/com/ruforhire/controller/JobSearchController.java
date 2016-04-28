/**
 * 
 */
package com.ruforhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruforhire.service.JobDescriptionService;
import com.ruforhire.service.JobSearchServiceResponse;
import com.ruforhire.service.IndeedServiceProvider.JOB_TYPE;
import com.ruforhire.service.IndeedServiceProvider.SORTBY;

/**
 * @author ashish
 *
 */
@Controller
@RequestMapping("/jobsearch")
public class JobSearchController {

	@Autowired
	@Qualifier("jobDescriptionService")
	private JobDescriptionService jobDescriptionService;

	@RequestMapping(value={"{query}/{location}/{start}"}, method = RequestMethod.GET)
	public @ResponseBody JobSearchServiceResponse search(
			@PathVariable String query, 
			@PathVariable String location,
			@PathVariable int start) {
		
		JobSearchServiceResponse response = jobDescriptionService.searchJobs(query, location, SORTBY.DATE, JOB_TYPE.INTERNSHIP, start, JobDescriptionService.JOBS_FETCHED_PER_QUERY);
		return response;
	}

	@RequestMapping(value={"{query}/{location}/{start}/{count}"}, method = RequestMethod.GET)
	public @ResponseBody JobSearchServiceResponse searchEnd(
			@PathVariable String query, 
			@PathVariable String location,
			@PathVariable int start, 
			@PathVariable int count) {
		
		JobSearchServiceResponse response = jobDescriptionService.searchJobs(query, location, SORTBY.DATE, JOB_TYPE.INTERNSHIP, start, count);
		return response;
	}

	@RequestMapping(value={"{query}/{location}/{jobType}/{start}/{count}"}, method = RequestMethod.GET)
	public @ResponseBody JobSearchServiceResponse searchJobType(
			@PathVariable String query, 
			@PathVariable String location,
			@PathVariable int jobType,
			@PathVariable int start, 
			@PathVariable int count) {
		
		JobSearchServiceResponse response = jobDescriptionService.searchJobs(query, location, SORTBY.DATE, (jobType == 0 ? JOB_TYPE.INTERNSHIP : JOB_TYPE.FULL_TIME), start, count);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String searchPage() {
		return "jobsearch";
	}
}
