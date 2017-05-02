package com.example.ferooz.horticulture.database;

public class DBConstants {

	public static final String DATABASE_NAME = "cams.db";
	public static final int DATABASE_VERSION = 8;



	public static final String TABLE_NAME_RISK_PROFILLING="Risk_Profilling";
	public static final String COLUMN_RISK_PROFILLING_ID="id";
	public static final String COLUMN_RISK_PROFILLING_FARMER_ID="farmer_id";
	public static final String COLUMN_RISK_PROFILLING_URN_NO="urn_no";
	public static final String COLUMN_RISK_PROFILLING_FARM_COORDINATE_INFO="farm_coordinate_info";
	public static final String COLUMN_RISK_PROFILLING_FARM_COORDINATE_INFO_EXACT="farm_coordinate_info_exact";
	public static final String COLUMN_RISK_PROFILLING_VIDEO_COORDINATE="video_coordinate";
	public static final String COLUMN_RISK_PROFILLING_ANSWER_INFORMATION="answer_information";
	public static final String COLUMN_RISK_PROFILLING_DATE_OF_SURVEY="date_of_survey";
	public static final String COLUMN_RISK_PROFILLING_FIELD_NO="field_no";
	public static final String COLUMN_RISK_PROFILLING_DISTRICY_NAME="district_name";
	public static final String COLUMN_RISK_PROFILLING_HOBLI="hobli_name";
	public static final String COLUMN_RISK_PROFILLING_PLOT_SURVEY_NUMBER="plot_survey_no";
	public static final String COLUMN_RISK_PROFILLING_INSURED_AREA_IN_ACRE="insured_area_in_acre";
	public static final String COLUMN_RISK_PROFILLING_PRUNING_DATE="pruning_date";
	public static final String COLUMN_RISK_PROFILLING_VARIETY="variety";//
	public static final String COLUMN_RISK_PROFILLING_VARIETY_ID="VarietyId";
	public static final String COLUMN_RISK_PROFILLING_APPROX_NO_OF_VINES="approx_no_of_vines";
	public static final String COLUMN_RISK_PROFILLING_CROP_MONITORING_DATA="crop_monitoring_data";
	public static final String COLUMN_RISK_PROFILLING_CENTER_IMAGE_COORDINATES="center_image_coordinates";
	public static final String COLUMN_RISK_PROFILLING_TIME_STAMP="timestamp";
	public static final String COLUMN_RISK_PROFILLING_RISK_PROFILLING_STATUS="risk_profiling_status";
	public static final String COLUMN_RISK_PROFILLING_UPLOAD_STATUS="upload_status";
	public static final String COLUMN_RISK_PROFILLING_IMAGE_UPLOAD_STATUS="image_upload_status";
	public static final String COLUMN_RISK_PROFILLING_VALIDATION_STRING="validation_string";
	public static final String COLUMN_RISK_PROFILLING_CROP="crop";
	public static final String COLUMN_RISK_PROFILLING_CUT_OF_DATE="CutofDate";
	public static final String COLUMN_RISK_PROFILLING_RISK_SCORE="RiskScore";
	public static final String COLUMN_RISK_PROFILLING_IS_ACTIVE="IsActive";
	public static final String COLUMN_RISK_PROFILLING_WBCISPAYOUT="WBCISPayout";
	public static final String COLUMN_RISK_PROFILLING_CREATED_BY="CreatedBy";
	public static final String COLUMN_RISK_PROFILLING_UPDATED_ON="UpdatedOn";
	public static final String COLUMN_RISK_PROFILLING_UPDATED_BY="UpdatedBy";
	public static final String COLUMN_RISK_PROFILLING_CREATED_ON="CreatedOn";

	public static final String TABLE_NAME_RISK_PROFILLING_QUESTIONNAIRE="Risk_Profiling_Questionnaire";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_ID="id";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_QUEST_NO="question_number";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_OPTION="options";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_QUESTION="question";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_MARKS="marks";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_IMAGE_COUNT="image_count";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_HELPING_TEXT="helping_text";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_STAGE="stage";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_VIDEO_COUNT="video_count";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_CROP="crop";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_KEY_IDENTIFIER="key_identifier";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_TAKE_IMAGE_IN_BULK="take_image_in_bulk";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_HINT_TEXT_IN_BULK="hint_text_in_bulk";
	public static final String COLUMN_RISK_PROFILLING_QUESTIONNAIRE_TIME_STAMP="timestamp";

	public static final String TABLE_NAME_RISK_PROFILLING_QUESTIONNAIRE_OPTION="Risk_Profiling_Questionnaire_Option";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_ID="id";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_NAME="name";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_RIGH_OPTION="right_option";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_QUESTION_ID="question_id";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_MARKS="marks";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_KEY_IDENTIFIER="key_identifier";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_NEED_TO_HIDE="need_to_hide_any_question_after_this";
	public static final String COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_TIME_STAMP="timestamp";


	////////////////////////////////////////


	public static final String TABLE_NAME_APP_USER_DETAILS_DETAILS="App_User_Details";
	public static final String COLUMN_APP_USER_DETAILS_DETAILS_USER_ID="UserID";
	public static final String COLUMN_APP_USER_DETAILS_DETAILS_PASSWORD="Password";
	public static final String COLUMN_APP_USER_DETAILS_DETAILS_IMEI="IMEI";
	public static final String COLUMN_APP_USER_DETAILS_DETAILS_USER_CONTACT="User_Contact";
	public static final String COLUMN_APP_USER_DETAILS_DETAILS_LAST_LOGIN_ID="LastLoginId";
	public static final String COLUMN_APP_USER_DETAILS_DETAILS_LAST_LOGIN_DATE="LastLoginDate";//


	public static final String TABLE_NAME_CONFIGURATION_FILE_DETAILS="Configuration_File_Details";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_CONFIGURATION_ID="ConfigurationId";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_VERSION_NO="VersionNo";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_VERSION_TYPE="versionType";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_APPLICATION_URL="ApplicationURL";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_WEBSERVICE_URL="WebServiceURL";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_DATE_OF_EXPIRY="DateofExpairy";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_SMS_RECEIVER_MOB_NO="SMSreceiverMobNo";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_NOTIFICATION="Notification";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_CONFIGURATION_UPDATED_DATE="ConfigurationUpdatedDate";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_CONFIGURATION_UPDATED_BY="ConfigurationUpdatedBy";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_APPLICABLE_TO="ApplicableTo";
	public static final String COLUMN_CONFIGURATION_FILE_DETAILS_IS_FUNCTIONABLE="IsFunctionable";

	public static final String TABLE_NAME_FARMER_DETAILS="CA_Farmer_Details";
	public static final String COLUMN_FARMER_DETAILS_USER_ID="Userid";
	public static final String COLUMN_FARMER_DETAILS_USER_NAME="UserName";
	public static final String COLUMN_FARMER_DETAILS_MOBILE_NUMBER="Mobile_no";
	public static final String COLUMN_FARMER_DETAILS_AGE="Age";
	public static final String COLUMN_FARMER_DETAILS_ADDRESS="Address";
	public static final String COLUMN_FARMER_DETAILS_VILLAGE_NAME="Village_Name";
	public static final String COLUMN_FARMER_DETAILS_PINCODE="Pincode";
	public static final String COLUMN_FARMER_DETAILS_PRUNINGDATE="Pruning_date";
	public static final String COLUMN_FARMER_DETAILS_CREATED_DATE="CreatedDate";
	public static final String COLUMN_FARMER_DETAILS_CREATED_USER="CreatedBy";
	public static final String COLUMN_FARMER_DETAILS_UPDATED_DATE="UpdatedDate";
	public static final String COLUMN_FARMER_DETAILS_UPDATED_BY="UpdatedBy";

	public static final String TABLE_NAME_FARMER_REGISTERED_CROP="CA_Farmer_Registered_Crops";
	public static final String COLUMN_FARMER_REGISTERED_CROP_USER_ID="UserId"; //CropCode
	public static final String COLUMN_FARMER_REGISTERED_CROP_CROP_CODE="CropCode";
	public static final String COLUMN_FARMER_REGISTERED_CREATED_BY="CreatedBy";
	public static final String COLUMN_FARMER_REGISTERED_CREATED_DATE="CreatedDate";
	public static final String COLUMN_FARMER_REGISTERED_UPDATED_DATE="ModifiedDate";
	public static final String COLUMN_FARMER_REGISTERED_UPDATED_BY="ModifiedBy";

	public static final String TABLE_NAME_MASTRE_QUESTIONS="Master_Questions";
	public static final String COLUMN_MASTRE_QUESTIONS_LANGUAGE_ID="LanguageId";
	public static final String COLUMN_MASTRE_QUESTIONS_CROP_CODE="Crop_Code";
	public static final String COLUMN_MASTRE_QUESTIONS_QUESTION_ID="QuestionId";
	public static final String COLUMN_MASTRE_QUESTIONS_QUESTION_ORDER="QuestionOrder";
	public static final String COLUMN_MASTRE_QUESTIONS_QUESTION_DESC="Question_Desc";
	public static final String COLUMN_MASTRE_QUESTIONS_IMAGE_COUNT="Image_count";
	public static final String COLUMN_MASTRE_QUESTIONS_HELPING_TEXT="Helping_Text";
	public static final String COLUMN_MASTRE_QUESTIONS_STAGE="Stage";
	public static final String COLUMN_MASTRE_QUESTIONS_VIDEO_COUNT="video_count";
	public static final String COLUMN_MASTRE_QUESTIONS_TAKE_IMAGE_IN_BULK="Take_Image_in_Bulk";
	public static final String COLUMN_MASTRE_QUESTIONS_HINT_TEXT_IN_BULK="Hint_Text_in_Bulk";
	public static final String COLUMN_MASTRE_QUESTIONS_CREATED_ON="CreatedOn";
	public static final String COLUMN_MASTRE_QUESTIONS_CREATED_BY="CreatedBy";
	public static final String COLUMN_MASTRE_QUESTIONS_UPDATED_ON="UpdatedOn";
	public static final String COLUMN_MASTRE_QUESTIONS_UPDATED_BY="UpdatedBy";

	public static final String TABLE_NAME_MASTRE_OPTIONS="Master_Options";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_LANGUAGE_ID="LanguageId";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_QUESTION_ID="QuestionId";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_ID="OptionId";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_OPTIONS_DESC="Option_Desc";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_MARKS="Marks";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_CREATED_ON="CreatedOn";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_CREATED_BY="CreatedBy";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_UPDATED_ON="UpdatedOn";
	public static final String COLUMN_MASTRE_MASTRE_OPTIONS_UPDATED_BY="UpdatedBy";


	public static final String TABLE_NAME_LAND_CROP_GEO_DATA="Land_Crop_Geo_Data";
	public static final String COLUMN_LAND_CROP_GEO_DATA_LAND_ID="LandId";
	public static final String COLUMN_LAND_CROP_GEO_DATA_USER_ID="User_Id";
	public static final String COLUMN_LAND_CROP_GEO_DATA_CROP_CODE="CropCode";
	public static final String COLUMN_LAND_CROP_GEO_DATA_CORNER_ID="Corner_ID";
	public static final String COLUMN_LAND_CROP_GEO_DATA_CORNER_TYPE="CornerType";
	public static final String COLUMN_LAND_CROP_GEO_DATA_COORDINATES="Coordinates";
	public static final String COLUMN_LAND_CROP_GEO_DATA_FIRST_IMAGE_URL="first_image_url";
	public static final String COLUMN_LAND_CROP_GEO_DATA_SECOND_IMAGE_URL="second_image_url";
	public static final String COLUMN_LAND_CROP_GEO_DATA_THIRD_IMAGE_URL="third_image_url";
	public static final String COLUMN_LAND_CROP_GEO_DATA_CORNER_VIDEO_URL="corner_video_url";//
	public static final String COLUMN_LAND_CROP_GEO_DATA_FOURTH_IMAGE_URL="fourth_image_url";
	public static final String COLUMN_LAND_CROP_GEO_DATA_UPLOADED_STATUS="upload_status";


    public static final String TABLE_NAME_LAND_CROP_CENTER_FARM_DATA = "Land_Crop_Center_Of_The_Farm";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_LANDID = "LandId";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_USER_ID = "User_Id";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_CROP_CODE = "CropCode";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_CORNER_ID = "Corner_ID";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_CORNER_TYPE = "CornerType";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_COORDINATED = "Coordinates";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_FIRST_IMAGE_URL = "first_image_url";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_SECOND_IMAGE_URL = "second_image_url";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_THIRD_IMAGE_URL = "third_image_url";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_FOURTH_IMAGE_URL = "fourth_image_url";
    public static final String COLUMN_LAND_CROP_LAND_CROP_CENTER_FARM_DATA_CENTER_VIDEO_URL = "center_video_url";

	public static final String TABLE_NAME_LAND_CROP_QUESTION_ANSWERS = "Land_Crop_Questionnaire_Answer";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID = "User_Id";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID = "Crop_Id";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_LAND_ID = "Land_Id";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID = "question_id";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ORDER = "question_order";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OPTION_ID = "option_id";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_PHOTO_LAT_LNGS = "photo_lat_lngs";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OTHERS = "other";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_UPLOAD_STATUS = "upload_status";
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_TIME_STAMP = "timestamp";//upload_status
	public static final String COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_UPLOADED_STATUS = "upload_status";//upload_status

	public static final String TABLE_NAME_LAND_CROP_QUESTIONNAIRE_MEDIAS= "Land_Crop_Questionnaire_Media";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_USER_ID = "User_Id";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_CROP_ID = "Crop_Id";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_LAND_ID = "Land_Id";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_QUESTION_ID = "Question_id";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_PHOTO_LAT_LNGS = "photo_lat_lngs";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_ID = "Media_Id";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_TYPE = "Media_type";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_URL = "Media_url";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_HELP_TEXT = "Media_help_text";
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_COORDINATES = "photo_lat_lngs";//
	public static final String COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_UPLOADED_STATUS = "upload_status";


	public static final String TABLE_NAME_ASK_EXPERT_QUERY="Ask_Expert_Query";
	public static final String COLUMN_ASK_EXPERT_QUERY_SL_NO="sl_no";
	public static final String COLUMN_ASK_EXPERT_QUERY_QUERY_TITLE="query_title";
	public static final String COLUMN_ASK_EXPERT_QUERY_QUERY="query";
	public static final String COLUMN_ASK_EXPERT_QUERY_PHOTO_LOCATION="photo_location";
	public static final String COLUMN_ASK_EXPERT_QUERY_VIDEO_LOCATION="video_location";
	public static final String COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION="audio_location";
	public static final String COLUMN_ASK_EXPERT_QUERY_STATUS="status";


	//Season Wise Questionnaire
	public static final String TABLE_NAME_MASTER_FARMERQUESTIONNAIRE_SEASONWISE="Master_FarmerQuestionnaire_SeasonWise";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_LANGUAGEID="LanguageId";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_SEASONID="SeasonId";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_CROPID="CropId";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_QUESTIONID="QuestionId";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_QUESTIONORDER="QuestionOrder";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_QUESTION="Question";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_QUESTIONIMAGECOUNT="QuestionImageCount";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_QUESTIONVIDEOCOUNT="QuestionVideoCount";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_QUESTIONAUDIOCOUNT="QuestionAudioCount";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_CREATEDBY="CreatedBy";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_CREATEDON="CreatedOn";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_UPDATEDBY="UpdatedBy";
	public static final String COLUMN_MASTER_FARMERQUESTIONNAIRE_SEASONWISE_UPDATEDON="UpdatedOn";

	public static final String TABLE_NAME_FARMERQUESTIONNAIREANSWERS_SEASONWISE="FarmerQuestionnaireAnswers_SeasonWise";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_SEASONID="SeasonId";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_CROPID="CropId";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_QUESTIONID="QuestionId";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_FARMERID="FarmerId";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_FARMERRESPONSE="FarmerResponse";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_RESPONSEIMAGEPATH="ResponseImagePath";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_IMAGECOUNT="ImageCount";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_RESPONSEVIDEOPATH="ResponseVideoPath";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_VIDEOCOUNT="VideoCount";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_RESPONSEAUDIOPATH="ResponseAudioPath";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_AUDIOCOUNT="AudioCount";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_CREATEDBY="CreatedBy";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_CREATEDON="CreatedOn";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_UPDATEDBY="UpdatedBy";
	public static final String COLUMN_FARMERQUESTIONNAIREANSWERS_SEASONWISE_UPDATEDON="UpdatedOn";



	public static final String TABLE_NAME_MASTER_SEASON="Master_Season";
	public static final String COLUMN_MASTER_SEASON_SEASONID="SeasonId";
	public static final String COLUMN_MASTER_SEASON_SEASONNAME_EN="SeasonName_EN";
	public static final String COLUMN_MASTER_SEASON_SEASONNAME_HI="SeasonName_HI";
	public static final String COLUMN_MASTER_SEASON_SEASONSTART="SeasonStart";
	public static final String COLUMN_MASTER_SEASON_SEASONEND="SeasonEnd";



	public static final String TABLE_NAME_MASTER_MONTH="Master_Month";
	public static final String COLUMN_MASTER_MONTH_MONTHID="MonthId";
	public static final String COLUMN_MASTER_MONTH_MONTHNAME_EN="MonthName_EN";
	public static final String COLUMN_MASTER_MONTH_MONTHNAME_HI="MonthName_HI";
	public static final String COLUMN_MASTER_MONTH_NO_OF_DAYS="No_Of_Days";
	public static final String COLUMN_MASTER_MONTH_YEARTYPE="YearType";


}
