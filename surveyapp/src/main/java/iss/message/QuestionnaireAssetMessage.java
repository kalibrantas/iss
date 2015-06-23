package iss.message;

import iss.model.Choice;

import java.util.ArrayList;

public class QuestionnaireAssetMessage {
	private ArrayList<QuestionMessage> questionList;
	private ArrayList<Choice> choiceList;

	public ArrayList<QuestionMessage> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<QuestionMessage> questionList) {
		this.questionList = questionList;
	}

	public ArrayList<Choice> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(ArrayList<Choice> choiceList) {
		this.choiceList = choiceList;
	}

}
