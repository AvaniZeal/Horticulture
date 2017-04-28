package com.example.ferooz.horticulture.pojoclasses;

public class RiskProfillingQuestionnaireOption {
	
	int id;
	String name;
	String right_option;
	String question_id;
	String marks;
	String key_identifier;
	String need_to_hide_any_question_after_this ;
	String timestamp;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRight_option() {
		return right_option;
	}
	public void setRight_option(String right_option) {
		this.right_option = right_option;
	}
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getKey_identifier() {
		return key_identifier;
	}
	public void setKey_identifier(String key_identifier) {
		this.key_identifier = key_identifier;
	}
	public String getNeed_to_hide_any_question_after_this() {
		return need_to_hide_any_question_after_this;
	}
	public void setNeed_to_hide_any_question_after_this(
			String need_to_hide_any_question_after_this) {
		this.need_to_hide_any_question_after_this = need_to_hide_any_question_after_this;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
