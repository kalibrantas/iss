package iss.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import iss.dao.postgre.PgQuestionnaireDao;
import iss.dao.postgre.PgRespondentDao;

public class AbstractISSController {
	protected PgQuestionnaireDao qd;
	protected PgRespondentDao rd;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		qd = new PgQuestionnaireDao(dataSource);
		rd = new PgRespondentDao(dataSource);

	}
}
