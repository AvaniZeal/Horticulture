package com.example.ferooz.horticulture.pojoclasses;

public class Configure_Files_Details {
	
	int ConfigurationId;
	String VersionNo;
	String versionType;
	String ApplicationURL;
	String WebServiceURL;
	String DateofExpairy;
	String SMSreceiverMobNo;
	String Notification;
	String ConfigurationUpdatedDate ;
	String ConfigurationUpdatedBy;
	String ApplicableTo;
	String IsFunctional;
	
	
	
	public String getIsFunctional() {
		return IsFunctional;
	}
	public void setIsFunctional(String isFunctional) {
		IsFunctional = isFunctional;
	}
	public String getVersionNo() {
		return VersionNo;
	}
	public void setVersionNo(String versionNo) {
		VersionNo = versionNo;
	}
	public int getConfigurationId() {
		return ConfigurationId;
	}
	public void setConfigurationId(int configurationId) {
		ConfigurationId = configurationId;
	}
		public String getVersionType() {
		return versionType;
	}
	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	public String getApplicationURL() {
		return ApplicationURL;
	}
	public void setApplicationURL(String applicationURL) {
		ApplicationURL = applicationURL;
	}
	public String getWebServiceURL() {
		return WebServiceURL;
	}
	public void setWebServiceURL(String webServiceURL) {
		WebServiceURL = webServiceURL;
	}
	public String getDateofExpairy() {
		return DateofExpairy;
	}
	public void setDateofExpairy(String dateofExpairy) {
		DateofExpairy = dateofExpairy;
	}
	public String getSMSreceiverMobNo() {
		return SMSreceiverMobNo;
	}
	public void setSMSreceiverMobNo(String sMSreceiverMobNo) {
		SMSreceiverMobNo = sMSreceiverMobNo;
	}
	public String getNotification() {
		return Notification;
	}
	public void setNotification(String notification) {
		Notification = notification;
	}
	public String getConfigurationUpdatedDate() {
		return ConfigurationUpdatedDate;
	}
	public void setConfigurationUpdatedDate(String configurationUpdatedDate) {
		ConfigurationUpdatedDate = configurationUpdatedDate;
	}
	public String getConfigurationUpdatedBy() {
		return ConfigurationUpdatedBy;
	}
	public void setConfigurationUpdatedBy(String configurationUpdatedBy) {
		ConfigurationUpdatedBy = configurationUpdatedBy;
	}
	public String getApplicableTo() {
		return ApplicableTo;
	}
	public void setApplicableTo(String applicableTo) {
		ApplicableTo = applicableTo;
	}
	
	
}
