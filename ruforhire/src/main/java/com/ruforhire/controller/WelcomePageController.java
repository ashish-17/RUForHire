/**
 * 
 */
package com.ruforhire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ashish
 *
 */
@Controller
public class WelcomePageController {

	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
		return mav;
	}
}
