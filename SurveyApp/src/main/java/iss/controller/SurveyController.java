package iss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	private String TEMPLATE_PATH = "survey_admin";
	
	@RequestMapping("/{idSurvey}/**")
	public String index(){
		return TEMPLATE_PATH+"/index";
	}
	@RequestMapping("/templates/{template}")
	public String getTemplates(@PathVariable String template) {
		System.out.println(template);
		return TEMPLATE_PATH + "/templates/" + template;
	}
}
