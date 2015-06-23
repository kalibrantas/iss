package iss.model.pg;

public class PgQuestionAnswer {
	private int id_respondent;
	private int id_question;
	private String name;
	private String answer;

	public int getId_respondent() {
		return id_respondent;
	}

	public void setId_respondent(int id_respondent) {
		this.id_respondent = id_respondent;
	}

	public int getId_question() {
		return id_question;
	}

	public void setId_question(int id_question) {
		this.id_question = id_question;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
