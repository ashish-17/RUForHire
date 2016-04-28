/**
 * 
 */
package com.ruforhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruforhire.service.EmployerService;
import com.ruforhire.service.InMemoryUtils;
import com.ruforhire.service.JobDescriptionService;
import com.ruforhire.service.JobTitleService;

/**
 * @author ashish
 *
 */
@Controller
public class PruneController {

	private EmployerService employerService;
	private JobTitleService jobTitleService;
	private JobDescriptionService jobDescriptionService; 
	@Autowired
	private InMemoryUtils inMemoryUtils = null;
	
    @Autowired(required=true)
    @Qualifier(value="employerService")
	public void setEmployerService(EmployerService employerService) {
		this.employerService = employerService;
	}

    @Autowired(required=true)
    @Qualifier(value="jobTitleService")
	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

    @Autowired(required=true)
    @Qualifier(value="jobDescriptionService")
	public void setJobDescriptionService(JobDescriptionService jobDescriptionService) {
		this.jobDescriptionService = jobDescriptionService;
	}

	
    @RequestMapping(value = "/prune", method = RequestMethod.GET)
    public String showPrunePage() {
		jobTitleService.updateDatabase();
		jobDescriptionService.updateDatabase();
		inMemoryUtils.init();
    	return "prune";
    }
}
