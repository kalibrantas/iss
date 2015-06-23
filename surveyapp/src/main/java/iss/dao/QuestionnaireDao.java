package iss.dao;

import java.util.List;

import iss.message.Questionnaire;
import iss.message.Survey;

interface QuestionnaireDao {

	public boolean insert(Questionnaire questionnaire);

	public boolean delete(Questionnaire questionnaire);

	public boolean update(Questionnaire questionnaire);
	
	public List<Questionnaire> get(Survey survey);
}
