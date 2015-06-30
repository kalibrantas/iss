package iss.controller.rest;

import java.security.Principal;
import java.util.ArrayList;

import javax.sql.DataSource;

import iss.dao.postgre.PgQuestionnaireDao;
import iss.dao.postgre.PgRespondentDao;
import iss.dao.postgre.PgSurveyDao;
import iss.dao.postgre.PgUserDao;
import iss.message.ResponseMessage;
import iss.message.Survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey")
public class SurveyServiceController {

	protected PgSurveyDao surveyDao;
	protected PgUserDao userDao;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		surveyDao = new PgSurveyDao(dataSource);
		userDao = new PgUserDao(dataSource);

	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseMessage createSurvey(@RequestBody Survey survey, Principal user) {
		survey.setId_user(userDao.getUser(user.getName()).getId_user());
		surveyDao.createNewSurvey(survey);
		System.out.println(user.getName());
		System.out.println(survey.getName());
		return new ResponseMessage("success","survey created");
	}
	
	@RequestMapping(value="/getall")
	public ArrayList<Survey> getAllSurvey(Principal user){
		return surveyDao.getAllSurvey(userDao.getUser(user.getName()));
	}

}
