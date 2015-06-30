package iss.controller;

import iss.dao.postgre.PgRespondentDao;
import iss.message.Respondent;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/surveymonitoring")
public class SurveyMonitoringController {
	private String TEMPLATE_PATH = "survey_monitoring";

	PgRespondentDao rd;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		rd = new PgRespondentDao(dataSource);

	}

	@RequestMapping("/templates/{template}")
	public String getTemplates(@PathVariable String template) {
		return TEMPLATE_PATH + "/templates/" + template;
	}

	@RequestMapping(value = "/getrespondents")
	public @ResponseBody ArrayList<Respondent> getRespondents() {
		return rd.getRespondentSurvey(2, 1);
	}

}
