package com.example.ferooz.horticulture.pojoclasses;

public class Land_Crop_Questionnaire_Answer {
	
	int quest_id;
	String question_order;
	String option_id;
	String User_Id	;
	String Crop_Id ;
	String Land_Id ;
	String photo_lat_lngs;
	String other;
	String upload_status;
	String risk_profilling_id ;
	String timestamp ;
	String UploadedStatus;


	public String getUploadedStatus() {
		return UploadedStatus;
	}

	public void setUploadedStatus(String uploadedStatus) {
		UploadedStatus = uploadedStatus;
	}

	public String getQuestion_order() {
		return question_order;
	}

	public void setQuestion_order(String question_order) {
		this.question_order = question_order;
	}

	public int getQuest_id() {
		return quest_id;
	}

	public void setQuest_id(int quest_id) {
		this.quest_id = quest_id;
	}


	public String getOption_id() {
		return option_id;
	}

	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}

	public String getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}

	public String getCrop_Id() {
		return Crop_Id;
	}

	public void setCrop_Id(String crop_Id) {
		Crop_Id = crop_Id;
	}

	public String getLand_Id() {
		return Land_Id;
	}

	public void setLand_Id(String land_Id) {
		Land_Id = land_Id;
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
