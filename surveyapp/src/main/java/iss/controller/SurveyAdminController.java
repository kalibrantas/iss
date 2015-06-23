package iss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/surveyadmin")
public class SurveyAdminController {
	private String TEMPLATE_PATH = "survey_admin";
	
	@RequestMapping("/")
	public String index(){
		return TEMPLATE_PATH+"/index";
	}
}
