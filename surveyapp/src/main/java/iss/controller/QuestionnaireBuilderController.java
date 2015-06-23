package iss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questionnairebuilder")
public class QuestionnaireBuilderController extends AbstractISSController {
	private String TEMPLATE_PATH = "questionnaire_builder";

	@RequestMapping("/")
	public String index() {
		System.out.println("questionnairebuilder");
		return TEMPLATE_PATH + "/index";
	}

	@RequestMapping("/templates/{template}")
	public String getTemplates(@PathVariable String template) {
		return TEMPLATE_PATH + "/templates/" + template;
	}

}
