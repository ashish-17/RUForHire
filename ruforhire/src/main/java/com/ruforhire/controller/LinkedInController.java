package com.ruforhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.web.context.request.RequestAttributes.*;
import static com.ruforhire.controller.SessionAttributes.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import com.ruforhire.service.OAuthServiceProvider;


@Controller
public class LinkedInController {
	
	@Autowired
	@Qualifier("linkedInServiceProvider")
	private OAuthServiceProvider linkedInServiceProvider;
	
	@RequestMapping(value={"/login-linkedin"}, method = RequestMethod.GET)
	public String login(WebRequest request) {
		
		// getting request and access token from session
		Token requestToken = (Token) request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		Token accessToken = (Token) request.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if(requestToken == null || accessToken == null) {
			// generate new request token
			OAuthService service = linkedInServiceProvider.getService();
			requestToken = service.getRequestToken();
			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, requestToken, SCOPE_SESSION);
			
			// redirect to linkedin auth page
			return "redirect:" + service.getAuthorizationUrl(requestToken);
		}
		return "welcome";
	}
	
	@RequestMapping(value={"/linkedin-callback"}, method = RequestMethod.GET)
	public ModelAndView callback(@RequestParam(value="oauth_verifier", required=false) String oauthVerifier, WebRequest request) {
		
		// getting request tocken
		OAuthService service = linkedInServiceProvider.getService();
		Token requestToken = (Token) request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		
		// getting access token
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);
		
		// store access token as a session attribute
		request.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken, SCOPE_SESSION);
		
		// getting user profile
		
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://api.linkedin.com/v1/people/~:(id,first-name,email-address,last-name,headline,picture-url,industry,summary,specialties,positions:(id,title,summary,start-date,end-date,is-current,company:(id,name,type,size,industry,ticker)),educations:(id,school-name,field-of-study,start-date,end-date,degree,activities,notes),associations,interests,num-recommenders,date-of-birth,publications:(id,title,publisher:(name),authors:(id,name),date,url,summary),patents:(id,title,summary,number,status:(id,name),office:(name),inventors:(id,name),date,url),languages:(id,language:(name),proficiency:(level,name)),skills:(id,skill:(name)),certifications:(id,name,authority:(name),number,start-date,end-date),courses:(id,name,number),recommendations-received:(id,recommendation-type,recommendation-text,recommender),honors-awards,three-current-positions,three-past-positions,volunteer)?format=json");
		//id,first-name,last-name,headline, location,industry, summary, specialties, positions, public-profile-url
		//https://api.linkedin.com/v1/people/~:(id,first-name,last-name,headline,picture-url,industry,summary,specialties,positions:(id,title,summary,start-date,end-date,is-current,company:(id,name,type,size,industry,ticker)),educations:(id,school-name,field-of-study,start-date,end-date,degree,activities,notes),associations,interests,num-recommenders,date-of-birth,publications:(id,title,publisher:(name),authors:(id,name),date,url,summary),patents:(id,title,summary,number,status:(id,name),office:(name),inventors:(id,name),date,url),languages:(id,language:(name),proficiency:(level,name)),skills:(id,skill:(name)),certifications:(id,name,authority:(name),number,start-date,end-date),courses:(id,name,number),recommendations-received:(id,recommendation-type,recommendation-text,recommender),honors-awards,three-current-positions,three-past-positions,volunteer)?oauth2_access_token=PUT_YOUR_TOKEN_HERE
		//https://api.linkedin.com/v1/people/~:(id,first-name,email-address,last-name,headline,picture-url,industry,summary,specialties,positions:(id,title,summary,start-date,end-date,is-current,company:(id,name,type,size,industry,ticker)),educations:(id,school-name,field-of-study,start-date,end-date,degree,activities,notes),associations,interests,num-recommenders,date-of-birth,publications:(id,title,publisher:(name),authors:(id,name),date,url,summary),patents:(id,title,summary,number,status:(id,name),office:(name),inventors:(id,name),date,url),languages:(id,language:(name),proficiency:(level,name)),skills:(id,skill:(name)),certifications:(id,name,authority:(name),number,start-date,end-date),courses:(id,name,number),recommendations-received:(id,recommendation-type,recommendation-text,recommender),honors-awards,three-current-positions,three-past-positions,volunteer)?format=json
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		
		System.out.println(oauthResponse.getBody());

		/*OAuthRequest oauthPeopleSearchRequest = new OAuthRequest(Verb.GET,  "https://api.linkedin.com/v1/people-search:"
				+ "people:(id,first-name,last-name,headline,picture-url,industry,"
				+ "positions:(id,title,summary,start-date,end-date,is-current,"
				+ "company:(id,name,type,size,industry,ticker)),"
				+ "educations:(id,school-name,field-of-study,start-date,end-date,degree,activities,notes)),"
				+ "num-results)?id=DvV9mdIy59");
		
		service.signRequest(accessToken, oauthPeopleSearchRequest);
		Response oauthSearchResponse = oauthPeopleSearchRequest.send();
		
		System.out.println(oauthSearchResponse.getBody());*/
		
		ModelAndView mav = new ModelAndView("redirect:welcome");
		return mav;
	}
}
