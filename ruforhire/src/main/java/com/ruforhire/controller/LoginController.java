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
public class LoginController {

	@RequestMapping(value={"/", "/signin", "/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
}
