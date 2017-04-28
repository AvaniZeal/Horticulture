package com.example.ferooz.horticulture.webservice;

public class WebServiceConstants {


	//public static String HOST_URRL ="http://192.168.1.177:8181/CropAdvisory.asmx";// Local
	public static String HOST_URL_RP ="http://124.153.106.183:65/";// public Land



	public static String HOST_URL_PROD="http://124.153.106.183:1212/CAMS_Media/"; //production media access
	public static String HOST_URRL="http://124.153.106.183:1818/CropAdvisory.asmx";//production webservice

	public static String HOST_URL_FOR_DOWNLOAD_TABLES_RP = HOST_URL_RP+"MobileServices.asmx";


	//public static String HOST_URRL="http://192.168.1.177:151/CropAdvisory.asmx";//local data download
	//public static String HOST_URRL="http://124.153.106.183:1818/CropAdvisory.asmx";


	public static final String NAMESPACE="http://tempuri.org/";
	public static String HOST_URL_FOR_DOWNLOAD_TABLES = HOST_URRL+"CropAdvisory.asmx";

	public static final String DOWNLOAD_LOSS_ASSESSMENT_METHOD="DownLoadLossAssessmentDetails";
	public static String DOWNLOAD_LOSS_ASSESSMENT_METHOD_REQRESULT="DownLoadLossAssessmentDetailsResult";

	public static final String DOWNLOAD_APP_USER_DATA_METHOD="ValidateLoginDetails";
	public static String DOWNLOAD_APP_USER_DATA_METHOD_REQRESULT="ValidateLoginDetailsResult";

	public static final String UPLOAD_LOSS_ASSESS_DATA_METHOD="UpdateLossAssessmentDetails";
	public static String UPLOAD_LOSS_ASSESS_DATA_METHOD_REQRESULT="UpdateLossAssessmentDetailsResult";

	public static final String DOWNLOAD_MASTER_TABLES_METHOD="Downloadmastertables";
	public static String DOWNLOAD_MASTER_TABLES_METHOD_REQRESULT="DownloadmastertablesResult";

	public static final String DOWNLOAD_MONITORING_TABLES_METHOD="DownLoadCropMonitoringDetails";
	public static String DOWNLOAD_MONITORING_TABLES_METHOD_REQRESULT="DownLoadCropMonitoringDetailsResult";

	public static final String UPLOAD_MONITORING_TABLES_METHOD="UploadCropMonitoringData";
	public static String UPLOAD_MONITORING_TABLES_METHOD_REQRESULT="UploadCropMonitoringDataResult";

	public static final String DOWNLOAD_RISK_PROFILLING_TABLES_METHOD="DownLoadRiskProfilingWorks";
	public static String DOWNLOAD_RISK_PROFILLING_TABLES_METHOD_REQRESULT="DownLoadRiskProfilingWorksResult";

	public static final String UPLOAD_RISK_PROFILLING_TABLES_METHOD="";
	public static String UPLOAD_RISK_PROFILLING_TABLES_METHOD_REQRESULT="";

	public static final String UPLOAD_EXPERT_DETAILS="UploadingFarmerQuery";
	public static String UPLOAD_EXPERT_DETAILS_REQRESULT="UploadingFarmerQueryResult";

	public static final String DOWNLOAD_EXPERT_VIEW_ADVISORY_METHOD="DownloadingExpertAdvisory";
	public static String DOWNLOAD_EXPERT_VIEW_ADVISORY_METHOD_REQRESULT="DownloadingExpertAdvisoryResult";



	public static final String UPLOAD_LAND_CROP_CORNER_DATA="UploadFarmerLandDetails";
	public static String UPLOAD_LAND_CROP_CORNER_DATA_REQRESULT="UploadFarmerLandDetailsResult";

	public static final String UPLOAD_LAND_CROP_QUESTIONNAIRE_DATA="UploadQuestionnaireAnswers";
	public static String UPLOAD_LAND_CROP_QUESTIONNAIRE_DATA_REQRESULT="UploadQuestionnaireAnswersResult";

	public static final String UPLOAD_LAND_CROP_QUESTIONNAIRE_MEDIAS_DATA="UploadQuestionnaire_MultiMedia";
	public static String UPLOAD_LAND_CROP_QUESTIONNAIRE_MEDIAS_DATA_REQRESULT="UploadQuestionnaire_MultiMediaResult";


	public static final String DOWNLOAD_GENERIC_ADVISORY_METHOD="DownloadGenericAdvisory";
	public static final String DOWNLOAD_GENERIC_ADVISORY_METHOD_REQRESULT="DownloadGenericAdvisoryResult";

	public static final String VALIDATE_VALID_USER_METHOD="DownloadUserDetails";
	public static String VALIDATE_USER_VALID_METHOD_REQRESULT="DownloadUserDetailsResult";

	public static final String VALIDATE_USER_METHOD="GetOTPForRegistration";
	public static String VALIDATE_USER_METHOD_REQRESULT="GetOTPForRegistrationResult";

	public static final String VALIDATE_USER_OTP_METHOD="DownloadLoginDetails";
	public static String VALIDATE_USER_OTP_METHOD_REQRESULT="DownloadLoginDetailsResult";

	public static final String DOWNLOAD_CONFIGURATION_FILE_METHOD="DownloadConfigurationFile";
	public static String DOWNLOAD_CONFIGURATION_FILE_METHOD_REQRESULT="DownloadConfigurationFileResult";


}

