package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;


public class FarmerDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public  final static String KEY = "com.easyinfogeek.objectPass.par";

	String Userid ;
	String UserName;
	String Mobile_no ;
	int Age;
	String Address ;
	String Village_Name ;
	String Pincode ;
	String Pruning_date ;
	String CreatedDate;
	String CreatedBy ;
	String UpdatedDate;
	String UpdatedBy ;


	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getMobile_no() {
		return Mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		Mobile_no = mobile_no;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getVillage_Name() {
		return Village_Name;
	}

	public void setVillage_Name(String village_Name) {
		Village_Name = village_Name;
	}

	public String getPincode() {
		return Pincode;
	}

	public void setPincode(String pincode) {
		Pincode = pincode;
	}

	public String getPruning_date() {
		return Pruning_date;
	}

	public void setPruning_date(String pruning_date) {
		Pruning_date = pruning_date;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
}
