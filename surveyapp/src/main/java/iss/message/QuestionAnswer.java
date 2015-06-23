package iss.message;

public class QuestionAnswer {
	private int id_repondent;
	private int id_question;
	private String name;
	private String answer;
	public int getId_repondent() {
		return id_repondent;
	}
	public void setId_repondent(int id_repondent) {
		this.id_repondent = id_repondent;
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
