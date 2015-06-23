package iss.message;

import java.util.ArrayList;

public class Respondent {
	private int id_respondent;
	private int id_user;
	private int id_survey;
	private ArrayList<QuestionAnswer> questionnaire_answer;
	
	public Respondent() {
		// TODO Auto-generated constructor stub
	}
	

	public Respondent(int id_respondent, int id_user, int id_survey) {
		super();
		this.id_respondent = id_respondent;
		this.id_user = id_user;
		this.id_survey = id_survey;
	}


	public int getId_respondent() {
		return id_respondent;
	}

	public void setId_respondent(int id_repondent) {
		this.id_respondent = id_repondent;
	}

	public int getId_user() {
		
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_survey() {
		return id_survey;
	}

	public void setId_survey(int id_survey) {
		this.id_survey = id_survey;
	}

	public ArrayList<QuestionAnswer> getQuestionnaire_answer() {
		return questionnaire_answer;
	}

	public void setQuestionnaire_answer(ArrayList<QuestionAnswer> question_answer) {
		this.questionnaire_answer = question_answer;
	}

}
