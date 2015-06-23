package iss.message;

import java.util.ArrayList;

public class QuestionMessage {

	private String name;
	private String label;
	private String type;
	private String choice;
	private ArrayList<QuestionMessage> children;
	private String option;
	private String repeat_count;
	
	
	
	public QuestionMessage() {
		// TODO Auto-generated constructor stub
	}

	public QuestionMessage(String name, String type, String label) {
		this.name = name;
		this.type = type;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public ArrayList<QuestionMessage> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<QuestionMessage> children) {
		this.children = children;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public String getRepeat_count() {
		return repeat_count;
	}

	public void setRepeat_count(String repeat_count) {
		this.repeat_count = repeat_count;
	}

}
