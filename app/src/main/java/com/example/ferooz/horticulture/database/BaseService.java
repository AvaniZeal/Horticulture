package com.example.ferooz.horticulture.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.Configure_Files_Details;
import com.example.ferooz.horticulture.pojoclasses.ExpertDetails;
import com.example.ferooz.horticulture.pojoclasses.FarmerDetails;
import com.example.ferooz.horticulture.pojoclasses.Farmer_Registered_Crop;
import com.example.ferooz.horticulture.pojoclasses.LandCropCenterOfTheFarm;
import com.example.ferooz.horticulture.pojoclasses.LandCropGeoData;
import com.example.ferooz.horticulture.pojoclasses.LandCropQuestionnaireMedias;
import com.example.ferooz.horticulture.pojoclasses.Land_Crop_Questionnaire_Answer;
import com.example.ferooz.horticulture.pojoclasses.Master_Options;
import com.example.ferooz.horticulture.pojoclasses.Master_Questions;
import com.example.ferooz.horticulture.pojoclasses.RiskProfilling;
import com.example.ferooz.horticulture.pojoclasses.RiskProfillingQuestionnaire;
import com.example.ferooz.horticulture.pojoclasses.RiskProfillingQuestionnaireOption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("UseSparseArrays")
public class BaseService {
    private BaseDao baseDao;
    @SuppressWarnings("unused")
    private Context context;
    ContentValues contentValues;

    public BaseService(Context context) {
        baseDao = new BaseDao(context);
        this.context = context;
    }

    public void openDataBase(){
        baseDao.open();
    }

    public void closeDataBase(){
        baseDao.open();
    }


    public boolean isFarmerDetailsSaved(String id) {
        String[] args = { id };
        Cursor cursor = null;
        boolean isInDb=false;
        try {
            // baseDao.open();
            cursor = baseDao
                    .Query(DBConstants.TABLE_NAME_FARMER_DETAILS,
                            DBConstants.COLUMN_FARMER_DETAILS_USER_ID+ " =? ", args);
            if (cursor != null) {
                int count = cursor.getCount();
                if (count >= 1) {
                    cursor.close();
                    isInDb= true;
                }
            }
        }
        finally {
            if (cursor != null) {
                if (cursor != null)
                    cursor.close();
                // baseDao.close();
            }

        }
        return isInDb;
    }

    public void insertIntoFarmerDetails(FarmerDetails fd) {

    }

    public boolean isInDb(RiskProfilling rp) {//1
        String[] args = { String.valueOf(rp.getId()) };
        Cursor cursor = null;
        boolean isInDb=false;
        try {
            // baseDao.open();
            cursor = baseDao
                    .Query(DBConstants.TABLE_NAME_RISK_PROFILLING,
                            DBConstants.COLUMN_RISK_PROFILLING_ID+ " =? ", args);
            if (cursor != null) {
                int count = cursor.getCount();
                if (count >= 1) {
                    cursor.close();
                    isInDb= true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (cursor != null) {
                if (cursor != null)
                    cursor.close();
                // baseDao.close();
            }

        }
        return isInDb;
    }

    public void UpdateRiskProfilling(RiskProfilling rp) {
        long cursor = 0;
        String[] selargs={String.valueOf(rp.getId())};

        ContentValues values = new ContentValues();
        values.put(DBConstants.COLUMN_RISK_PROFILLING_ID,rp.getId());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FARMER_ID,rp.getFarmer_Id());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_URN_NO,rp.getURN_No());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FARM_COORDINATE_INFO,rp.getFarm_coordinate_info());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FARM_COORDINATE_INFO_EXACT,rp.getFarm_coordinate_info_exact());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VIDEO_COORDINATE,rp.getVideo_coordinate());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_ANSWER_INFORMATION,rp.getAnswer_information());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_DATE_OF_SURVEY,rp.getDate_of_survey());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FIELD_NO,rp.getField_no());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_DISTRICY_NAME,rp.getDistrict_name());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_HOBLI,rp.getHobli_name());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_PLOT_SURVEY_NUMBER,rp.getPlot_survey_no());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_INSURED_AREA_IN_ACRE,rp.getInsured_area_in_acre());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_PRUNING_DATE,rp.getPruning_date());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VARIETY,rp.getVariety());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_APPROX_NO_OF_VINES,rp.getApprox_no_of_vines());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CROP_MONITORING_DATA,rp.getCrop_monitoring_data());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CENTER_IMAGE_COORDINATES,rp.getCenter_image_coordinates());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_TIME_STAMP,rp.getTimestamp());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_RISK_PROFILLING_STATUS,rp.getRisk_profiling_status());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_UPLOAD_STATUS,rp.getUpload_status());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_IMAGE_UPLOAD_STATUS,rp.getImage_upload_status());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VALIDATION_STRING,rp.getValidation_string());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CROP,rp.getCrop());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CUT_OF_DATE,rp.getCutofDate());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_RISK_SCORE,rp.getRiskScore());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CROP,rp.getCrop());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CREATED_BY,rp.getCreatedBy());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CREATED_ON,rp.getCreatedOn());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_UPDATED_BY,rp.getUpdatedBy());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_UPDATED_ON,rp.getUpdatedOn());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_IS_ACTIVE,rp.getIsActive());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_WBCISPAYOUT,rp.getWBCISPayout());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VARIETY_ID,rp.getVarietyId());

        try {
            baseDao.open();
            baseDao.Update(DBConstants.TABLE_NAME_RISK_PROFILLING, values,
                    DBConstants.COLUMN_RISK_PROFILLING_ID + " =? ",selargs);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baseDao.close();
        }

    }

    public void insertIntoRiskProfilling(RiskProfilling rp) {
        long cursor = 0;
        ContentValues values = new ContentValues();
        values.put(DBConstants.COLUMN_RISK_PROFILLING_ID,rp.getId());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FARMER_ID,rp.getFarmer_Id());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_URN_NO,rp.getURN_No());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FARM_COORDINATE_INFO,rp.getFarm_coordinate_info());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FARM_COORDINATE_INFO_EXACT,rp.getFarm_coordinate_info_exact());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VIDEO_COORDINATE,rp.getVideo_coordinate());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_ANSWER_INFORMATION,rp.getAnswer_information());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_DATE_OF_SURVEY,rp.getDate_of_survey());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_FIELD_NO,rp.getField_no());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_DISTRICY_NAME,rp.getDistrict_name());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_HOBLI,rp.getHobli_name());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_PLOT_SURVEY_NUMBER,rp.getPlot_survey_no());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_INSURED_AREA_IN_ACRE,rp.getInsured_area_in_acre());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_PRUNING_DATE,rp.getPruning_date());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VARIETY,rp.getVariety());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_APPROX_NO_OF_VINES,rp.getApprox_no_of_vines());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CROP_MONITORING_DATA,rp.getCrop_monitoring_data());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CENTER_IMAGE_COORDINATES,rp.getCenter_image_coordinates());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_TIME_STAMP,rp.getTimestamp());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_RISK_PROFILLING_STATUS,rp.getRisk_profiling_status());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_UPLOAD_STATUS,rp.getUpload_status());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_IMAGE_UPLOAD_STATUS,rp.getImage_upload_status());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VALIDATION_STRING,rp.getValidation_string());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CROP,rp.getCrop());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CUT_OF_DATE,rp.getCutofDate());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_RISK_SCORE,rp.getRiskScore());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CREATED_ON,rp.getCreatedOn());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_CREATED_BY,rp.getCreatedBy());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_UPDATED_BY,rp.getUpdatedBy());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_UPDATED_ON,rp.getUpdatedOn());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_IS_ACTIVE,rp.getIsActive());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_WBCISPAYOUT,rp.getWBCISPayout());
        values.put(DBConstants.COLUMN_RISK_PROFILLING_VARIETY_ID,rp.getVarietyId());

        try {
            baseDao.open();
            cursor = baseDao.Insert(DBConstants.TABLE_NAME_RISK_PROFILLING,	values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baseDao.close();
        }

    }


    public int getNuberOfQuestions(String crop) {
        // TODO Auto-generated method stub
        int QuestCount=0;
        String[] selAArgs = { crop };
        Cursor cursor = null;
        try{
            baseDao.open();
            cursor = baseDao.Query(DBConstants.TABLE_NAME_MASTRE_QUESTIONS,
                    DBConstants.COLUMN_MASTRE_QUESTIONS_CROP_CODE+ " = ?  ", selAArgs);
            if(cursor!=null){
                if(cursor.getCount()>0){
                    QuestCount=cursor.getCount();
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return QuestCount;
    }

    public Master_Questions getMasterQuestionnaire(String crop, int questCount) {


        String[] selAArgs = { crop, String.valueOf(questCount)};
        Cursor cursor = null;

        // baseDao.open();
        Master_Questions rpq= new Master_Questions();
        cursor = baseDao.Query(DBConstants.TABLE_NAME_MASTRE_QUESTIONS,
                DBConstants.COLUMN_MASTRE_QUESTIONS_CROP_CODE+ " = ? and "
                        +DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_ORDER+" = ? ", selAArgs);
        try {
            if (cursor != null && cursor.moveToFirst()) {

                rpq.setQuestionOrder(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_ORDER)));
                rpq.setQuestionId(cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_ID)));
                rpq.setQuestion_Desc(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_DESC)));
                rpq.setHelping_Text(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_QUESTIONS_HELPING_TEXT)));
                rpq.setImage_count(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_QUESTIONS_IMAGE_COUNT)));
                rpq.setVideo_count(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_QUESTIONS_VIDEO_COUNT)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            // baseDao.close();
        }
        return rpq;
    }

    public List<Master_Options> getOption(
            Master_Questions quest) {
        String[] selAArgs = { String.valueOf(quest.getQuestionId()) };
        Cursor cursor = null;

        // baseDao.open();
        List<Master_Options> lstOption=new ArrayList<Master_Options>();
        cursor = baseDao.Query(DBConstants.TABLE_NAME_MASTRE_OPTIONS,
                DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_QUESTION_ID+ " = ? ", selAArgs);
        try {
            if (cursor != null ) {
                while (!cursor.isAfterLast()) {
                    Master_Options rpo=new Master_Options();
                    rpo.setOptionId(cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_ID)));
                    rpo.setOption_Desc(cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_OPTIONS_DESC)));
                    //rpo.setOptionId(cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_ID)));

                    lstOption.add(rpo);
                    cursor.moveToNext();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            // baseDao.close();
        }
        return lstOption;
    }

    public void deleteTableContent(String tableName) {
        // TODO Auto-generated method stub
        String query="delete from "+tableName;
        baseDao.RawQuery(query, null);

    }

    public void insertIntoRiskProfillingQuestionnaire(
            RiskProfillingQuestionnaire rpq) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_ID, rpq.getId());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_QUEST_NO, rpq.getQuestion_number());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_QUESTION, rpq.getQuestion());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_OPTION, rpq.getOptions());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_MARKS, rpq.getMarks());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_IMAGE_COUNT, rpq.getImage_count());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_HELPING_TEXT, rpq.getHelping_text());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_STAGE, rpq.getStage());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_VIDEO_COUNT, rpq.getVideo_count());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_CROP, rpq.getCrop());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_KEY_IDENTIFIER, rpq.getKey_identifier());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_TAKE_IMAGE_IN_BULK, rpq.getTake_image_in_bulk());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_HINT_TEXT_IN_BULK, rpq.getHint_text_in_bulk());
        cv.put(DBConstants.COLUMN_RISK_PROFILLING_QUESTIONNAIRE_TIME_STAMP, rpq.getTimestamp());
        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_RISK_PROFILLING_QUESTIONNAIRE, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }
    }

    public void insertIntoRiskProfillingQuestionnaireOption(
            RiskProfillingQuestionnaireOption rpo) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_ID, rpo.getId());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_NAME, rpo.getName());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_RIGH_OPTION, rpo.getRight_option());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_QUESTION_ID, rpo.getQuestion_id());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_MARKS, rpo.getMarks());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_KEY_IDENTIFIER, rpo.getKey_identifier());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_NEED_TO_HIDE, rpo.getNeed_to_hide_any_question_after_this());
        cv.put(DBConstants.COLUMN_RISK_RISK_PROFILLING_QUESTIONNAIRE_OPTION_TIME_STAMP, rpo.getTimestamp());
        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_RISK_PROFILLING_QUESTIONNAIRE_OPTION, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }
    }

    public FarmerDetails getFarmerName(int farmer_Id) {
        // TODO Auto-generated method stub




        return null;
    }

    public RiskProfilling getRiskProfileData(String selectedFarmerId) {
        // TODO Auto-generated method stub
        String[] selargs = { selectedFarmerId };
        RiskProfilling rp=new RiskProfilling();
        try {
            baseDao.open();
            Cursor c= baseDao.Query(DBConstants.TABLE_NAME_RISK_PROFILLING, DBConstants.COLUMN_RISK_PROFILLING_FARMER_ID+ " = ? ", selargs);
            if(c!=null){
                c.moveToFirst();
                rp.setId(c.getInt(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_ID)));
                rp.setCutofDate(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_CUT_OF_DATE)));
                rp.setRiskScore(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_RISK_SCORE)));
                rp.setURN_No(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_URN_NO)));
                rp.setDate_of_survey(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_DATE_OF_SURVEY)));
                rp.setPlot_survey_no(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_PLOT_SURVEY_NUMBER)));
                rp.setInsured_area_in_acre(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_INSURED_AREA_IN_ACRE)));
                rp.setPruning_date(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_PRUNING_DATE)));
                rp.setVariety(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_VARIETY)));
                rp.setCrop(c.getString(c.getColumnIndex(DBConstants.COLUMN_RISK_PROFILLING_CROP)));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            baseDao.close();
        }
        return rp;
    }

    /////////////////////////////////////////////

    public void saveAppUser(AppUserDetails appUser) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_ID, appUser.getUserID());
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_PASSWORD, appUser.getPassword());
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_IMEI, appUser.getIMEI());
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_LAST_LOGIN_DATE, appUser.getLastLoginDate());
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_LAST_LOGIN_ID, appUser.getLastLoginId());
        //cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_CONTACT, appUser.getUser_Contact());
        //cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_DESIGNATION_ID, appUser.getDesignationId());

        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_APP_USER_DETAILS_DETAILS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }

    }

    public long UpdateMobileNumber(String contactNumber, String name) {
        // TODO Auto-generated method stub
        long res=0;
        ContentValues cv = new ContentValues();
        String[] whereArgs={name};
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_CONTACT, contactNumber);

        try{
            baseDao.open();
            res=baseDao.Update(DBConstants.TABLE_NAME_APP_USER_DETAILS_DETAILS, cv,
                    DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_ID+" = ? ", whereArgs);
        }catch(Exception e){
            e.printStackTrace();
            res=0;
        }finally{
            baseDao.close();
        }
        return res;

    }

    public long insertIntoConfigureFile(Configure_Files_Details obj) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        long c;
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_CONFIGURATION_ID, obj.getConfigurationId());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_CONFIGURATION_UPDATED_DATE, obj.getConfigurationUpdatedDate());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_CONFIGURATION_UPDATED_BY, obj.getConfigurationUpdatedBy());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_APPLICABLE_TO, obj.getApplicableTo());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_VERSION_NO, obj.getVersionNo());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_DATE_OF_EXPIRY, obj.getDateofExpairy());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_SMS_RECEIVER_MOB_NO, obj.getSMSreceiverMobNo());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_WEBSERVICE_URL, obj.getWebServiceURL());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_NOTIFICATION, obj.getNotification());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_VERSION_TYPE, obj.getVersionType());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_APPLICATION_URL, obj.getApplicationURL());
        cv.put(DBConstants.COLUMN_CONFIGURATION_FILE_DETAILS_IS_FUNCTIONABLE, obj.getIsFunctional());

        try{
            baseDao.open();
            c=baseDao.Insert(DBConstants.TABLE_NAME_CONFIGURATION_FILE_DETAILS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }finally{
            baseDao.close();
        }

        return c;
    }

    public AppUserDetails getAppUserDetails() {
        // TODO Auto-generated method stub
        String query="select * from "+DBConstants.TABLE_NAME_APP_USER_DETAILS_DETAILS;
        Cursor c=null;
        AppUserDetails obj=null;

        try{
            c=baseDao.RawQuery(query, null);
            if(c!=null){
                if(c.getCount()>0){
                    obj=new AppUserDetails();
                    obj.setUserID(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_ID)));
                    obj.setPassword(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_PASSWORD)));
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            obj=null;
        }
        return obj;
    }

    public long SaveAppUserDetails(AppUserDetails appUser) {
        // TODO Auto-generated method stub
        long c=0;
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_ID, appUser.getUserID());
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_PASSWORD, appUser.getPassword());
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_IMEI, appUser.getIMEI());
        //cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_LAST_LOGIN_DATE, Date);
        cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_LAST_LOGIN_ID, appUser.getUserID());
        //cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_USER_CONTACT, appUser.getUser_Contact());
        //cv.put(DBConstants.COLUMN_APP_USER_DETAILS_DETAILS_DESIGNATION_ID, appUser.getDesignationId());

        try{
            baseDao.open();
            c=baseDao.Insert(DBConstants.TABLE_NAME_APP_USER_DETAILS_DETAILS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }finally{
            baseDao.close();
        }
        return c;
    }

    public long SaveFarmerDetails(FarmerDetails obj) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        long c;
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_USER_ID, obj.getUserid());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_USER_NAME, obj.getUserName());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_MOBILE_NUMBER, obj.getMobile_no());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_AGE, obj.getAge());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_ADDRESS, obj.getAddress());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_VILLAGE_NAME, obj.getVillage_Name());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_PINCODE, obj.getPincode());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_PRUNINGDATE, obj.getPruning_date());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_CREATED_DATE, obj.getCreatedDate());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_CREATED_USER, obj.getCreatedBy());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_UPDATED_DATE, obj.getUpdatedDate());
        cv.put(DBConstants.COLUMN_FARMER_DETAILS_UPDATED_BY, obj.getUpdatedBy());

        try{
            baseDao.open();
            c=baseDao.Insert(DBConstants.TABLE_NAME_FARMER_DETAILS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }finally{
            baseDao.close();
        }

        return c;
    }

    public long SaveFarmerRegisteredCropDetails(Farmer_Registered_Crop obj) {

        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        long c;
        cv.put(DBConstants.COLUMN_FARMER_REGISTERED_CROP_USER_ID, obj.getUserID());
        cv.put(DBConstants.COLUMN_FARMER_REGISTERED_CROP_CROP_CODE, obj.getCropCode());

        try{
            baseDao.open();
            c=baseDao.Insert(DBConstants.TABLE_NAME_FARMER_REGISTERED_CROP, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }finally{
            baseDao.close();
        }

        return c;

    }

    public long SaveMasterQuestions(Master_Questions obj) {

        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        long c;
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_LANGUAGE_ID, obj.getLanguageId());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_CROP_CODE, obj.getCrop_Code());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_ID, obj.getQuestionId());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_ORDER, obj.getQuestionOrder());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_QUESTION_DESC, obj.getQuestion_Desc());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_IMAGE_COUNT, obj.getImage_count());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_HELPING_TEXT, obj.getHelping_Text());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_STAGE, obj.getStage());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_VIDEO_COUNT, obj.getVideo_count());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_TAKE_IMAGE_IN_BULK, obj.getTake_Image_in_Bulk());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_HINT_TEXT_IN_BULK, obj.getHint_Text_in_Bulk());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_CREATED_ON, obj.getCreatedOn());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_CREATED_BY, obj.getCreatedBy());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_UPDATED_BY, obj.getUpdatedBy());
        cv.put(DBConstants.COLUMN_MASTRE_QUESTIONS_UPDATED_ON, obj.getUpdatedOn());

        try{
            baseDao.open();
            c=baseDao.Insert(DBConstants.TABLE_NAME_MASTRE_QUESTIONS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }finally{
            baseDao.close();
        }

        return c;
    }

    public long SaveMasterOptions(Master_Options obj) {

        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        long c;
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_LANGUAGE_ID, obj.getLanguageId());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_QUESTION_ID, obj.getQuestionId());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_ID, obj.getOptionId());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_OPTIONS_DESC, obj.getOption_Desc());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_MARKS, obj.getMarks());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_CREATED_BY, obj.getCreatedBy());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_CREATED_ON, obj.getCreatedOn());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_UPDATED_BY, obj.getUpdatedBy());
        cv.put(DBConstants.COLUMN_MASTRE_MASTRE_OPTIONS_UPDATED_ON, obj.getCreatedOn());

        try{
            baseDao.open();
            c=baseDao.Insert(DBConstants.TABLE_NAME_MASTRE_OPTIONS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }finally{
            baseDao.close();
        }

        return c;
    }

    public FarmerDetails getFarmerDetails(AppUserDetails appUserDetails) {
        // TODO Auto-generated method stub
        String query="select * from "+DBConstants.TABLE_NAME_FARMER_DETAILS+ " where "
                +DBConstants.COLUMN_FARMER_DETAILS_USER_ID+" = '"+appUserDetails.getUserID()+"'";

        Cursor c=null;
        FarmerDetails obj=null;
        String[] args={appUserDetails.getUserID()};
        try{
            //c=baseDao.RawQuery(query, null);
            c=baseDao.Query(DBConstants.TABLE_NAME_FARMER_DETAILS,DBConstants.COLUMN_FARMER_DETAILS_USER_ID+" = ? ",args);
            if(c!=null){
                if(c.getCount()>0){
                    obj=new FarmerDetails();
                    obj.setUserName(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_FARMER_DETAILS_USER_NAME)));
                    obj.setMobile_no(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_FARMER_DETAILS_MOBILE_NUMBER)));
                    obj.setVillage_Name(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_FARMER_DETAILS_VILLAGE_NAME)));

                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            obj=null;
        }
        return obj;
    }

    public List<String> getAllRegisteredCrops(AppUserDetails farmerDetails) {
        // TODO Auto-generated method stub
        List<String> lstCrop = null;
        try {

            Cursor c =null;
            String[] args={farmerDetails.getUserID()};
            c=baseDao.Query(DBConstants.TABLE_NAME_FARMER_REGISTERED_CROP,DBConstants.COLUMN_FARMER_REGISTERED_CROP_USER_ID+" = ? ",args);
            if (c != null) {
                if(c.getCount()>0){
                    lstCrop = new ArrayList<String>();
                    while(!c.isAfterLast()){
                        String Crop="";

                        Crop=c.getString(c.getColumnIndex(DBConstants.COLUMN_FARMER_REGISTERED_CROP_CROP_CODE));

                        if(Crop!=null){
                            lstCrop.add(Crop);
                        }
                        c.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lstCrop;

    }

    public void insertIntoLandCropGeoData(AppUserDetails appUserDetails, LandCropGeoData rpgd) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID, appUserDetails.getUserID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE, rpgd.getCropCode());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID, rpgd.getCorner_ID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_TYPE, rpgd.getCornerType());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_COORDINATES, rpgd.getCoordinates());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FIRST_IMAGE_URL, rpgd.getFirst_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_SECOND_IMAGE_URL, rpgd.getSecond_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_THIRD_IMAGE_URL, rpgd.getThird_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_VIDEO_URL, rpgd.getCorner_video_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID, rpgd.getLandId());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_UPLOADED_STATUS, rpgd.getUploadedStatus());

        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA, cv);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }

    }

    public boolean IsCornerDataSaved(AppUserDetails appUserDetails, LandCropGeoData rpgd) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(rpgd.getCorner_ID()), String.valueOf(rpgd.getCropCode())};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;

    }

    public void updateLandCropGeoData(AppUserDetails appUserDetails, LandCropGeoData rpgd) {

        // TODO Auto-generated method stub
        String args[]={appUserDetails.getUserID(),String.valueOf(rpgd.getCropCode()),
                String.valueOf(rpgd.getLandId()),String.valueOf(rpgd.getCorner_ID())};
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID, appUserDetails.getUserID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE, rpgd.getCropCode());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID, rpgd.getCorner_ID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_TYPE, rpgd.getCornerType());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_COORDINATES, rpgd.getCoordinates());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FIRST_IMAGE_URL, rpgd.getFirst_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_SECOND_IMAGE_URL, rpgd.getSecond_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_THIRD_IMAGE_URL, rpgd.getThird_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_VIDEO_URL, rpgd.getCorner_video_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID, rpgd.getLandId());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_UPLOADED_STATUS, rpgd.getUploadedStatus());


        try{
            baseDao.open();
            baseDao.Update(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,cv,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID+" = ? ",args);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }

    }

    public boolean IsCenterFarmDataSaved(AppUserDetails appUserDetails, LandCropGeoData rpcf) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(rpcf.getCorner_ID()), String.valueOf(rpcf.getCropCode())};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;


    }

    public void insertIntoCenterFarmData(AppUserDetails appUserDetails, LandCropGeoData rpgd) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID, appUserDetails.getUserID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE, rpgd.getCropCode());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID, rpgd.getCorner_ID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_TYPE, rpgd.getCornerType());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_COORDINATES, rpgd.getCoordinates());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FIRST_IMAGE_URL, rpgd.getFirst_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_SECOND_IMAGE_URL, rpgd.getSecond_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_THIRD_IMAGE_URL, rpgd.getThird_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_VIDEO_URL, rpgd.getCorner_video_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FOURTH_IMAGE_URL, rpgd.getFourth_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID, rpgd.getLandId());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_UPLOADED_STATUS, rpgd.getUploadedStatus());

        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA, cv);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }

    }

    public boolean IsDataSavedForCorner(AppUserDetails appUserDetails, int cornerId, int cropCode) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(cornerId), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;

    }

    public void saveQuestionnaireAnswer(
            Land_Crop_Questionnaire_Answer ans) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();

        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID, ans.getUser_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID, ans.getCrop_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_LAND_ID, ans.getLand_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID, ans.getQuest_id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ORDER, ans.getQuestion_order());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OPTION_ID, ans.getOption_id());
        // cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_PHOTO_LAT_LNGS, ans.getPhoto_lat_lngs());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OTHERS, ans.getOther());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_UPLOAD_STATUS, ans.getUpload_status());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_TIME_STAMP, ans.getTimestamp());

        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_LAND_CROP_QUESTION_ANSWERS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }
    }

    public void SaveQuestionnairMediaUrl(LandCropQuestionnaireMedias rpqm, String mediaType) {
        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();//
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_TYPE, mediaType);
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_USER_ID, rpqm.getUser_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_URL, rpqm.getMedia_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_QUESTION_ID, rpqm.getQuestion_id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_HELP_TEXT, rpqm.getMedia_help_text());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_ID, rpqm.getMediadId());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_CROP_ID, rpqm.getCrop_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_LAND_ID, rpqm.getLand_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_COORDINATES, rpqm.getCoordinates());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_UPLOADED_STATUS, rpqm.getUploadedStatus());


        try{
            baseDao.open();
            baseDao.Insert(DBConstants.TABLE_NAME_LAND_CROP_QUESTIONNAIRE_MEDIAS, cv);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }

    }

    public Land_Crop_Questionnaire_Answer IsQuestionnaireSaved(Master_Questions quest, AppUserDetails appUserDetails, int cropCode) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        Land_Crop_Questionnaire_Answer obj = new Land_Crop_Questionnaire_Answer();
        try {
            Cursor c = null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(quest.getQuestionId()), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_QUESTION_ANSWERS,
                    DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                    obj.setQuest_id(c.getInt(c.getColumnIndex(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID)));
                    obj.setOption_id(c.getString(c.getColumnIndex(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OPTION_ID)));
                    //obj.setQuest_id(c.getInt(c.getColumnIndex(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID)));
                    //obj.setQuest_id(c.getInt(c.getColumnIndex(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID)));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    public List<LandCropGeoData> GetCornerDetails(AppUserDetails appUserDetails, int cropCode) {
        // TODO Auto-generated method stub
        List<LandCropGeoData> lstCornerData=null;
        try{
            Cursor c= null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE + " = ? ", args);

            if (c != null) {
                if(c.getCount()>0){
                    lstCornerData=new ArrayList<LandCropGeoData>();
                    while(!c.isAfterLast()){
                        LandCropGeoData obj=new LandCropGeoData();
                        //String ExpNum=c.getString(arg0)
                        obj.setCorner_ID(c.getInt(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID)));
                        obj.setCoordinates(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_COORDINATES)));
                        obj.setFirst_image_url(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FIRST_IMAGE_URL)));
                        obj.setSecond_image_url(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_SECOND_IMAGE_URL)));
                        obj.setThird_image_url(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_THIRD_IMAGE_URL)));
                        obj.setCornerType(c.getInt(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_TYPE)));
                        obj.setCorner_video_url(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_VIDEO_URL)));
                        obj.setFourth_image_url(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FOURTH_IMAGE_URL)));
                        obj.setLandId(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID)));

                        if(obj!=null){
                            lstCornerData.add(obj);
                        }
                        c.moveToNext();
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            lstCornerData=null;
        }

        return lstCornerData;

    }

    public List<Land_Crop_Questionnaire_Answer> GetCLandCropAnswersDetails(AppUserDetails appUserDetails, int cropCode) {

        // TODO Auto-generated method stub
        List<Land_Crop_Questionnaire_Answer> lstCornerData=null;
        try{
            Cursor c= null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_QUESTION_ANSWERS,
                    DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID + " = ? ", args);

            if (c != null) {
                if(c.getCount()>0){
                    lstCornerData=new ArrayList<Land_Crop_Questionnaire_Answer>();
                    while(!c.isAfterLast()){
                        Land_Crop_Questionnaire_Answer obj=new Land_Crop_Questionnaire_Answer();
                        //String ExpNum=c.getString(arg0)
                        obj.setQuest_id(c.getInt(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID)));
                        obj.setQuestion_order(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ORDER)));
                        obj.setOption_id(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OPTION_ID)));
                        obj.setLand_Id(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_LAND_ID)));

                        if(obj!=null){
                            lstCornerData.add(obj);
                        }
                        c.moveToNext();
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            lstCornerData=null;
        }

        return lstCornerData;

    }

    public List<LandCropQuestionnaireMedias> GetCLandCropAnswersMediaDetails(AppUserDetails appUserDetails, int cropCode) {
        // TODO Auto-generated method stub
        List<LandCropQuestionnaireMedias> lstCornerData=null;
        try{
            Cursor c= null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_QUESTIONNAIRE_MEDIAS,
                    DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_CROP_ID + " = ? ", args);

            if (c != null) {
                if(c.getCount()>0){
                    lstCornerData=new ArrayList<LandCropQuestionnaireMedias>();
                    while(!c.isAfterLast()){
                        LandCropQuestionnaireMedias obj=new LandCropQuestionnaireMedias();
                        //String ExpNum=c.getString(arg0)
                        obj.setMediadId(c.getInt(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_ID)));
                        obj.setMedia_type(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_TYPE)));
                        obj.setMedia_url(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_URL)));
                        obj.setQuestion_id(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_QUESTION_ID)));
                        obj.setCoordinates(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_PHOTO_LAT_LNGS)));
                        if(obj!=null){
                            lstCornerData.add(obj);
                        }
                        c.moveToNext();
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            lstCornerData=null;
        }

        return lstCornerData;
    }

    public boolean IsAnswerSaved(Master_Questions quest, AppUserDetails appUserDetails, int cropCode) {

        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(quest.getQuestionId()), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_QUESTION_ANSWERS,
                    DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;
    }

    public boolean IsDataSavedForCenterOfFarm(AppUserDetails appUserDetails, int cornerId, int cropCode) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {appUserDetails.getUserID(), String.valueOf(cornerId), String.valueOf(cropCode)};
            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;

    }

    public void updateCenterOfTheFarmData(AppUserDetails appUserDetails, LandCropGeoData rpgd) {
        String args[]={appUserDetails.getUserID(),String.valueOf(rpgd.getCropCode()),
                String.valueOf(rpgd.getLandId()),String.valueOf(rpgd.getCorner_ID())};
        ContentValues cv=new ContentValues();
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID, appUserDetails.getUserID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE, rpgd.getCropCode());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID, rpgd.getCorner_ID());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_TYPE, rpgd.getCornerType());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_COORDINATES, rpgd.getCoordinates());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FIRST_IMAGE_URL, rpgd.getFirst_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_SECOND_IMAGE_URL, rpgd.getSecond_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_THIRD_IMAGE_URL, rpgd.getThird_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_VIDEO_URL, rpgd.getCorner_video_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_FOURTH_IMAGE_URL, rpgd.getFourth_image_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID, rpgd.getLandId());
        cv.put(DBConstants.COLUMN_LAND_CROP_GEO_DATA_UPLOADED_STATUS, rpgd.getUploadedStatus());

        try{
            baseDao.open();
            baseDao.Update(DBConstants.TABLE_NAME_LAND_CROP_GEO_DATA,cv,
                    DBConstants.COLUMN_LAND_CROP_GEO_DATA_USER_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CROP_CODE+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_LAND_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_GEO_DATA_CORNER_ID+" = ? ",args);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }
    }

    public boolean IsQuestionSaved(Land_Crop_Questionnaire_Answer questAns) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {questAns.getUser_Id(), String.valueOf(questAns.getQuest_id()),
                    String.valueOf(questAns.getCrop_Id()),String.valueOf(questAns.getLand_Id())};

            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_QUESTION_ANSWERS,
                    DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID + " = ? and "+
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_LAND_ID + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;

    }

    public void updateQuestionAnswer(Land_Crop_Questionnaire_Answer ans) {

        ContentValues cv=new ContentValues();
        String[] args = {ans.getUser_Id(), String.valueOf(ans.getQuest_id()),
                String.valueOf(ans.getCrop_Id()),String.valueOf(ans.getLand_Id())};

        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID, ans.getUser_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID, ans.getCrop_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_LAND_ID, ans.getLand_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID, ans.getQuest_id());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ORDER, ans.getQuestion_order());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OPTION_ID, ans.getOption_id());
        // cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_PHOTO_LAT_LNGS, ans.getPhoto_lat_lngs());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_OTHERS, ans.getOther());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_UPLOAD_STATUS, ans.getUpload_status());
        cv.put(DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_TIME_STAMP, ans.getTimestamp());

        try{
            baseDao.open();

            baseDao.Update(DBConstants.TABLE_NAME_LAND_CROP_QUESTION_ANSWERS,cv,
                    DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_USER_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_QUESTION_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_CROP_ID+" = ? and "+
                            DBConstants.COLUMN_LAND_CROP_LAND_CROP_QUESTION_ANSWERS_LAND_ID+" = ? ",args);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }
    }

    public boolean IsMediaSaved(LandCropQuestionnaireMedias rpqm, String mediaType) {
        // TODO Auto-generated method stub
        boolean isSaved = false;
        try {
            Cursor c = null;
            String[] args = {rpqm.getUser_Id(), String.valueOf(rpqm.getQuestion_id()),
                    String.valueOf(rpqm.getCrop_Id()),String.valueOf(rpqm.getLand_Id()),
                    mediaType};

            c = baseDao.Query(DBConstants.TABLE_NAME_LAND_CROP_QUESTIONNAIRE_MEDIAS,
                    DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_QUESTION_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_CROP_ID + " = ? and "+
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_LAND_ID + " = ? and "+
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_TYPE + " = ? ", args);
            if (c != null) {
                if (c.getCount() > 0) {
                    isSaved = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isSaved = false;
        }
        return isSaved;
    }

    public void updateQuestionnaireMedias(LandCropQuestionnaireMedias rpqm, String mediaType) {
        // TODO Auto-generated method stub
        String[] args = {rpqm.getUser_Id(), String.valueOf(rpqm.getQuestion_id()),
                String.valueOf(rpqm.getCrop_Id()),String.valueOf(rpqm.getLand_Id()),
                mediaType};

        ContentValues cv=new ContentValues();//
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_TYPE, mediaType);
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_USER_ID, rpqm.getUser_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_URL, rpqm.getMedia_url());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_QUESTION_ID, rpqm.getQuestion_id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_HELP_TEXT, rpqm.getMedia_help_text());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_ID, rpqm.getMediadId());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_CROP_ID, rpqm.getCrop_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_LAND_ID, rpqm.getLand_Id());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_COORDINATES, rpqm.getCoordinates());
        cv.put(DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_UPLOADED_STATUS, rpqm.getUploadedStatus());


        try{
            baseDao.open();

            baseDao.Update(DBConstants.TABLE_NAME_LAND_CROP_QUESTIONNAIRE_MEDIAS,cv,
                    DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_USER_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_QUESTION_ID + " = ? and " +
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_CROP_ID + " = ? and "+
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_LAND_ID + " = ? and "+
                            DBConstants.COLUMN_LAND_CROP_QUESTIONNAIRE_MEDIAS_MEDIA_TYPE + " = ? ", args);


        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            baseDao.close();
        }

    }



    public long Insert(ExpertDetails obj) {
        {
            long res = 0;
            // TODO Auto-generated method stub
            ContentValues cv = new ContentValues();
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY_TITLE,obj.getQueryTitle());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY,obj.getQuery());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION,obj.getAudioLocation());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_PHOTO_LOCATION,obj.getPhotoLoaction());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_VIDEO_LOCATION,obj.getVideoLocation());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_STATUS,obj.getStatus());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO,obj.getSlNo());

            try {
                baseDao.open();
                res=baseDao.Insert(DBConstants.TABLE_NAME_ASK_EXPERT_QUERY, cv);
                baseDao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                baseDao.close();
            }
            return res;

        }


    }

    public long Insert(ExpertDetails obj, int slno) {

        {
            long res = 0;
            // TODO Auto-generated method stub
            ContentValues cv = new ContentValues();

            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION,obj.getAudioLocation());
            cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO,slno);

            try {
                baseDao.open();
                res=baseDao.Insert(DBConstants.TABLE_NAME_ASK_EXPERT_QUERY, cv);

                baseDao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                baseDao.close();
            }
            return res;

        }
    }

    public long IsDataSaved(ExpertDetails obj) {


        // TODO Auto-generated method stub

        // TODO Auto-generated method stub

        long res=0;
        String query ="select * from "+DBConstants.TABLE_NAME_ASK_EXPERT_QUERY+ " where "+
                DBConstants.COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION+ " = '"+obj.getAudioLocation()+"'"+" and "+
                DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO+ " = '"+obj.getSlNo()+"'";

        Cursor c = baseDao.RawQuery(query, null);
        if(c!=null){
            if(c.getCount()>0){
                res=1;
            }
        }
        return res;


    }

    public void update(ExpertDetails obj, int slno){
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        String[] args={String.valueOf(slno)};
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION,obj.getAudioLocation());

        try {
            baseDao.open();
            long resprop = baseDao.Update(DBConstants.TABLE_NAME_ASK_EXPERT_QUERY, cv,

                    DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO+ " = ?  ", args);
            baseDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            baseDao.close();
        }

        // TODO Auto-generated method stub


    }



    public List<ExpertDetails> getAllNotUploadedData(String n) {

        List<ExpertDetails> notUploadedData = null;
        String query="select * from "+DBConstants.TABLE_NAME_ASK_EXPERT_QUERY+" where "

                +DBConstants.COLUMN_ASK_EXPERT_QUERY_STATUS+" = '"+n+"'";
        try{
            Cursor c=baseDao.RawQuery(query, null);
            if (c != null) {
                if(c.getCount()>0){
                    notUploadedData=new ArrayList<ExpertDetails>();
                    while(!c.isAfterLast()){
                        ExpertDetails obj=new ExpertDetails();

                        obj.setAudioLocation(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION)));
                        obj.setPhotoLoaction(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_PHOTO_LOCATION)));
                        obj.setQuery(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY)));
                        obj.setQueryTitle(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY_TITLE)));
                        obj.setVideoLocation(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_VIDEO_LOCATION)));
                        obj.setSlNo(c.getInt(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO)));
                        obj.setStatus(c.getString(c.getColumnIndexOrThrow(DBConstants.COLUMN_ASK_EXPERT_QUERY_STATUS)));


                        if(obj!=null){

                            notUploadedData.add(obj);
                        }
                        c.moveToNext();
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception

            e.printStackTrace();
            notUploadedData=null;
        }

        return notUploadedData;

    }

    public long IsInDb(ExpertDetails obj, int slno) {

        {
            // TODO Auto-generated method stub

            // TODO Auto-generated method stub

            long res=0;
            String query ="select * from "+DBConstants.TABLE_NAME_ASK_EXPERT_QUERY+ " where "+

                    DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO+ " = '"+obj.getSlNo()+"'";

            Cursor c = baseDao.RawQuery(query, null);
            if(c!=null){
                if(c.getCount()>0){
                    res=1;
                }
            }
            return res;


        }
    }


    public void updateExpertDetails(ExpertDetails obj) {{
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        String[] args={String.valueOf(obj.getSlNo())};
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY,obj.getQuery());
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY_TITLE,obj.getQueryTitle());
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_PHOTO_LOCATION,obj.getPhotoLoaction());
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_STATUS,obj.getStatus());
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_VIDEO_LOCATION,obj.getVideoLocation());



        try {
            baseDao.open();
            long resprop = baseDao.Update(DBConstants.TABLE_NAME_ASK_EXPERT_QUERY, cv,

                    DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO+ " = ?  ", args);
            baseDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            baseDao.close();
        }

        // TODO Auto-generated method stub


    }


    }


    public String getLastSlNo(){
        String slno="";
        String schema="select MAX(sl_no) from " + DBConstants.TABLE_NAME_ASK_EXPERT_QUERY;
        Cursor cursor = baseDao.RawQuery(schema, null);

        if (cursor != null) {

            while (cursor.moveToLast()) {
                cursor.moveToFirst();
                slno=cursor.getString(0);
                return slno;
            }
        }
        return slno;

    }


    public long changeStatusUploadExpertDetails(ExpertDetails obj) {
        // TODO Auto-generated method stub
        long res=0;
        ContentValues cv = new ContentValues();
        String[] whereArgs={String.valueOf(obj.getSlNo())};
        cv.put(DBConstants.COLUMN_ASK_EXPERT_QUERY_STATUS, "Y");


        try{
            baseDao.open();
            res=baseDao.Update(DBConstants.TABLE_NAME_ASK_EXPERT_QUERY, cv,
                            DBConstants.COLUMN_ASK_EXPERT_QUERY_SL_NO+" = ? and "+
                            DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY_TITLE+" = ? and "+
                            DBConstants.COLUMN_ASK_EXPERT_QUERY_QUERY+" = ? and "+
                            DBConstants.COLUMN_ASK_EXPERT_QUERY_PHOTO_LOCATION+" = ? and "+
                            DBConstants.COLUMN_ASK_EXPERT_QUERY_VIDEO_LOCATION+" = ? and "+
                            DBConstants.COLUMN_ASK_EXPERT_QUERY_AUDIO_LOCATION+" = ? ", whereArgs);

        }catch(Exception e){
            e.printStackTrace();
            res=0;
        }finally{
            baseDao.close();
        }
        return res;

    }

}
