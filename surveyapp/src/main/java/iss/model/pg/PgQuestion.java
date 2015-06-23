package iss.model.pg;

import iss.model.Question;

public class PgQuestion extends Question{
	private int id_question;
	private int id_questionnaire;
	private String name;
	private String label;
	private String type;
	private int id_questions_choice;
	private String option;
	
	
	public PgQuestion() {
		// TODO Auto-generated constructor stub
	}

	public PgQuestion(String name, String type, String label) {
		this.name = name;
		this.type = type;
		this.label = label;
	}

	public int getId_question() {
		return id_question;
	}

	public void setId_question(int id_question) {
		this.id_question = id_question;
	}

	public int getId_questionnaire() {
		return id_questionnaire;
	}

	public void setId_questionnaire(int id_questionnaire) {
		this.id_questionnaire = id_questionnaire;
	}

	public int getId_questions_choice() {
		return id_questions_choice;
	}

	public void setId_questions_choice(int id_questions_choice) {
		this.id_questions_choice = id_questions_choice;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
