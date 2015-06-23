package iss.controller;

import java.util.ArrayList;

import javax.sql.DataSource;

import iss.dao.postgre.PgQuestionnaireDao;
import iss.dao.postgre.PgRespondentDao;
import iss.message.Respondent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/datacollection")
public class DataCollectionController {
	private String TEMPLATE_PATH = "data_collection";
	PgRespondentDao rd;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		rd = new PgRespondentDao(dataSource);

	}
	
	@RequestMapping("/")
	public String index(){
		return TEMPLATE_PATH+"/index";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/getrespondents")
	public @ResponseBody ArrayList<Respondent> getRespondents(){
		return rd.getRespondentSurvey(2, 1);
	}
}
