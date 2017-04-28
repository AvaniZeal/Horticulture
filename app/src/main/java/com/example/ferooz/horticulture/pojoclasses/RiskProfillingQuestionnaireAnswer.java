package com.example.ferooz.horticulture.pojoclasses;

public class RiskProfillingQuestionnaireAnswer {
	
	int quest_id;
	String question_number;
	String option_id;
	String farmer_id;
	String photo_lat_lngs;
	String other;
	String upload_status;
	String risk_profilling_id ;
	String timestamp ;
	
	public int getquestid() {
		return quest_id;
	}
	public void setquestid(int id) {
		this.quest_id = id;
	}
	public String getQuestion_number() {
		return question_number;
	}
	public void setQuestion_number(String question_id) {
		this.question_number = question_id;
	}
	public String getOption_id() {
		return option_id;
	}
	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}
	public String getFarmer_id() {
		return farmer_id;
	}
	public void setFarmer_id(String farmer_id) {
		this.farmer_id = farmer_id;
	}
	public String getPhoto_lat_lngs() {
		return photo_lat_lngs;
	}
	public void setPhoto_lat_lngs(String photo_lat_lngs) {
		this.photo_lat_lngs = photo_lat_lngs;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getUpload_status() {
		return upload_status;
	}
	public void setUpload_status(String upload_status) {
		this.upload_status = upload_status;
	}
	public String getRisk_profilling_id() {
		return risk_profilling_id;
	}
	public void setRisk_profilling_id(String risk_profilling_id) {
		this.risk_profilling_id = risk_profilling_id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
