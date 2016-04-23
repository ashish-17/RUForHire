/**
 * 
 */
package com.ruforhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ruforhire.service.JobTitleService;

/**
 * @author ashish
 *
 */
@Controller
public class LoginController {

	private JobTitleService jobTitleService;

    @Autowired(required=true)
    @Qualifier(value="jobTitleService")
	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}
    
	@RequestMapping(value={"/", "/signin", "/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		jobTitleService.updateDatabase();
		mav.setViewName("login");
		return mav;
	}
}
