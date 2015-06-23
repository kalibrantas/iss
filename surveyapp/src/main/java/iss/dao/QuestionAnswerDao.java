package iss.dao;

import java.util.List;

import iss.message.QuestionMessage;
import iss.message.QuestionAnswer;
import iss.message.Respondent;

interface QuestionAnswerDao {
	
	public boolean insert(QuestionAnswer answer);
	
	public boolean delete(QuestionAnswer answer);
	
	public boolean update(QuestionAnswer answer);
	
	public List<QuestionAnswer> get(Respondent respondent);
	
	public List<QuestionAnswer> get(QuestionMessage question);
	
}
