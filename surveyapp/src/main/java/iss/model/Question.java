package iss.model;

public class Question {
	private String name;
	private String type;
	private String label;
	private String[] children;
	private String repeat_count;
	
	

	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(String name, String type, String label) {
		super();
		this.name = name;
		this.type = type;
		this.label = label;
		this.children = children;
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
	public String[] getChildren() {
		return children;
	}
	public void setChildren(String[] children) {
		this.children = children;
	}
	public String getRepeat_count() {
		return repeat_count;
	}

	public void setRepeat_count(String repeat_count) {
		this.repeat_count = repeat_count;
	}
	
	
}
