/**
 * 
 */
package com.ruforhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruforhire.service.CompanyService;

/**
 * @author ashish
 *
 */
@Controller
public class PruneController {

	private CompanyService companyService;

    @Autowired(required=true)
    @Qualifier(value="companyService")
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
    
    @RequestMapping(value = "/prune", method = RequestMethod.GET)
    public String showPrunePage() {
    	return "prune";
    }
}
