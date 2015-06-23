package iss.dao.postgre;

import java.util.List;

import iss.dao.QuestionDao;
import iss.model.Question;
import iss.model.Questionnaire;
import iss.model.pg.PgQuestion;


public class PgQuestionDao implements QuestionDao{
	
	private PgDatabaseOperation dbopr;

	@Override
	public boolean insert(Question q) {
		PgQuestion question = (PgQuestion) q;
		
		return false;
	}

	@Override
	public boolean delete(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Question> get(Questionnaire questionnaire) {
		// TODO Auto-generated method stub
		return null;
	}

}
