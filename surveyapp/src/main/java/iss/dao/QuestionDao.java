package iss.dao;

import java.util.List;

import iss.model.Question;
import iss.model.Questionnaire;

public interface QuestionDao {
	
	public boolean insert(Question question);
	
	public boolean delete(Question question); 
	
	public boolean update(Question question); 
	
	public List<Question> get(Questionnaire questionnaire);

}
