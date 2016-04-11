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

/**
 * @author ashish
 *
 */
@Controller
public class PruneController {

	private EmployerService employerService;

    @Autowired(required=true)
    @Qualifier(value="employerService")
	public void setEmployerService(EmployerService employerService) {
		this.employerService = employerService;
	}
    
    @RequestMapping(value = "/prune", method = RequestMethod.GET)
    public String showPrunePage() {
    	return "prune";
    }
}
