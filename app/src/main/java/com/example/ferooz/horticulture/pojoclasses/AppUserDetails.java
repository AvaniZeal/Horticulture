package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;

public class AppUserDetails implements Serializable {

	String UserID;
	String Password;
	String IMEI;
	String LastLoginId;
	String LastLoginDate;



	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getLastLoginId() {
		return LastLoginId;
	}
	public void setLastLoginId(String lastLoginId) {
		LastLoginId = lastLoginId;
	}
	public String getLastLoginDate() {
		return LastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}
}
