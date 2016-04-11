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
		
		JobSearchServiceResponse response = jobDescriptionService.searchJobs(query, location, start);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String searchPage() {
		return "jobsearch";
	}
}
