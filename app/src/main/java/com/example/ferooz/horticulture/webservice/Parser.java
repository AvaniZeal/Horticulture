package com.example.ferooz.horticulture.webservice;

import android.content.Context;

import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.database.DBConstants;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.Configure_Files_Details;
import com.example.ferooz.horticulture.pojoclasses.FarmerDetails;
import com.example.ferooz.horticulture.pojoclasses.Farmer_Registered_Crop;
import com.example.ferooz.horticulture.pojoclasses.Master_Options;
import com.example.ferooz.horticulture.pojoclasses.Master_Questions;
import com.example.ferooz.horticulture.pojoclasses.RiskProfilling;
import com.example.ferooz.horticulture.pojoclasses.RiskProfillingQuestionnaire;
import com.example.ferooz.horticulture.pojoclasses.RiskProfillingQuestionnaireOption;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Parser {

    private Context context;
    public Parser(Context pContext){
        this.context = pContext;
    }
    Document doc;
    String date=new SimpleDateFormat( "d/M/yyyy" ).format(new Date(System.currentTimeMillis()));
    BaseService baseService=new BaseService(context);
    public void ParseXml(String strResult, String fileName) {


        try{

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = dbf.newDocumentBuilder();
            doc = docBuilder
                    .parse(new InputSource(new StringReader(strResult)));
            doc.getDocumentElement().normalize();

             if(fileName.equalsIgnoreCase("Risk_Profilling")){
                //baseService.deleteTableContent(DBConstants.TABLE_NAME_RISK_PROFILLING);
                NodeList tablePE = doc.getElementsByTagName("Row");
                for (int k = 0; k < tablePE.getLength(); k++) {
                    RiskProfilling rp = new RiskProfilling();
                    Node node = tablePE.item(k);
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        Node temp = node.getChildNodes().item(i);

                        if (temp.getNodeName().equalsIgnoreCase("id")) {
                            System.out.println(temp.getTextContent());
                            rp.setId(Integer.parseInt(temp.getTextContent()));
                        } else if (temp.getNodeName().equalsIgnoreCase("Farmer_Id")) {
                            System.out.println(temp.getTextContent());
                            rp.setFarmer_Id((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("URN_No")) {
                            System.out.println(temp.getTextContent());
                            rp.setURN_No(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("farm_coordinate_info")) {
                            System.out.println(temp.getTextContent());
                            rp.setFarm_coordinate_info((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("farm_coordinate_info_exact")) {
                            System.out.println(temp.getTextContent());
                            rp.setFarm_coordinate_info_exact(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("video_coordinate")) {
                            System.out.println(temp.getTextContent());
                            rp.setVideo_coordinate((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("answer_information")) {
                            System.out.println(temp.getTextContent());
                            rp.setAnswer_information(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("date_of_survey")) {
                            System.out.println(temp.getTextContent());
                            rp.setDate_of_survey((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("field_no")) {
                            System.out.println(temp.getTextContent());
                            rp.setField_no(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("district_name")) {
                            System.out.println(temp.getTextContent());
                            rp.setDistrict_name((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("hobli_name")) {
                            System.out.println(temp.getTextContent());
                            rp.setHobli_name(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("insured_area_in_acre")) {
                            System.out.println(temp.getTextContent());
                            rp.setInsured_area_in_acre((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("pruning_date")) {
                            System.out.println(temp.getTextContent());
                            rp.setPruning_date(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("plot_survey_no")) {
                            System.out.println(temp.getTextContent());
                            rp.setPlot_survey_no((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("variety")) {
                            System.out.println(temp.getTextContent());
                            rp.setVariety(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("approx_no_of_vines")) {
                            System.out.println(temp.getTextContent());
                            rp.setApprox_no_of_vines((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("crop_monitoring_data")) {
                            System.out.println(temp.getTextContent());
                            rp.setCrop_monitoring_data((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("center_image_coordinates")) {
                            System.out.println(temp.getTextContent());
                            rp.setCenter_image_coordinates((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("timestamp")) {
                            System.out.println(temp.getTextContent());
                            rp.setTimestamp(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("risk_profiling_status")) {
                            System.out.println(temp.getTextContent());
                            rp.setRisk_profiling_status((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("upload_status")) {
                            System.out.println(temp.getTextContent());
                            rp.setUpload_status((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("image_upload_status")) {
                            System.out.println(temp.getTextContent());
                            rp.setImage_upload_status((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("validation_string")) {
                            System.out.println(temp.getTextContent());
                            rp.setValidation_string(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("crop")) {
                            System.out.println(temp.getTextContent());
                            rp.setCrop(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("CutofDate")) {
                            System.out.println(temp.getTextContent());
                            rp.setCutofDate(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("RiskScore")) {
                            System.out.println(temp.getTextContent());
                            rp.setRiskScore((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("IsActive")) {
                            System.out.println(temp.getTextContent());
                            rp.setIsActive((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("WBCISPayout")) {
                            System.out.println(temp.getTextContent());
                            rp.setWBCISPayout((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("CreatedOn")) {
                            System.out.println(temp.getTextContent());
                            rp.setCreatedOn((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("CreatedBy")) {
                            System.out.println(temp.getTextContent());
                            rp.setCreatedBy((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("UpdatedOn")) {
                            System.out.println(temp.getTextContent());
                            rp.setUpdatedOn((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("UpdatedBy")) {
                            System.out.println(temp.getTextContent());
                            rp.setUpdatedBy((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("VarietyId")) {
                            System.out.println(temp.getTextContent());
                            if(!temp.getTextContent().equals("")){
                                rp.setVarietyId(Integer.parseInt(temp.getTextContent()));}
                            else{
                                rp.setVarietyId(Integer.parseInt("0"));
                            }
                        }
                    }
                    boolean isInDb=baseService.isInDb(rp);
                    if(isInDb){
                        baseService.UpdateRiskProfilling(rp);
                    }else{
                        baseService.insertIntoRiskProfilling(rp);
                    }

                }

            }else if(fileName.equalsIgnoreCase("Farmer")){/*

                //baseService.deleteTableContent(DBConstants.TABLE_NAME_FARMER_DETAILS);
                NodeList tablePE = doc.getElementsByTagName("Row");
                for (int k = 0; k < tablePE.getLength(); k++) {
                    FarmerDetails fd = new FarmerDetails();
                    Node node = tablePE.item(k);
                    for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                        Node temp = node.getChildNodes().item(i);

                        if (temp.getNodeName().equalsIgnoreCase("id")) {
                            System.out.println(temp.getTextContent());
                            fd.setId(temp.getTextContent());
                        } else if (temp.getNodeName().equalsIgnoreCase("name")) {
                            System.out.println(temp.getTextContent());
                            fd.setName((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("mobile_no")) {
                            System.out.println(temp.getTextContent());
                            fd.setMobile_no(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("village")) {
                            System.out.println(temp.getTextContent());
                            fd.setVillage((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("timestamp")) {
                            System.out.println(temp.getTextContent());
                            fd.setTimestamp(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("age")) {
                            System.out.println(temp.getTextContent());
                            fd.setAge((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("farm_coordinate_info")) {
                            System.out.println(temp.getTextContent());
                            fd.setFarm_coordinate_info(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("farm_coordinate_info_exact")) {
                            System.out.println(temp.getTextContent());
                            fd.setFarm_coordinate_info_exact((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("video_coordinate")) {
                            System.out.println(temp.getTextContent());
                            fd.setVideo_coordinate(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("answer_information")) {
                            System.out.println(temp.getTextContent());
                            fd.setAnswer_information((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("risk_profiling_status")) {
                            System.out.println(temp.getTextContent());
                            fd.setRisk_profiling_status(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("upload_status")) {
                            System.out.println(temp.getTextContent());
                            fd.setUpload_status((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("name_of_survayor")) {
                            System.out.println(temp.getTextContent());
                            fd.setName_of_survayor(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("contact_detail_of_survayor")) {
                            System.out.println(temp.getTextContent());
                            fd.setContact_detail_of_survayor((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("date_of_survey")) {
                            System.out.println(temp.getTextContent());
                            fd.setDate_of_survey(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("field_no")) {
                            System.out.println(temp.getTextContent());
                            fd.setField_no((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("district_name")) {
                            System.out.println(temp.getTextContent());
                            fd.setDistrict_name((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("hobli_name")) {
                            System.out.println(temp.getTextContent());
                            fd.setHobli_name(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("plot_survey_no")) {
                            System.out.println(temp.getTextContent());
                            fd.setPlot_survey_no((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("insured_area_in_acre")) {
                            System.out.println(temp.getTextContent());
                            fd.setInsured_area_in_acre(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("pruning_date")) {
                            System.out.println(temp.getTextContent());
                            fd.setPruning_date((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("variety")) {
                            System.out.println(temp.getTextContent());
                            fd.setVariety(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("approx_no_of_vines")) {
                            System.out.println(temp.getTextContent());
                            fd.setApprox_no_of_vines(temp.getTextContent());
                        }else if (temp.getNodeName().equalsIgnoreCase("crop_monitoring_data")) {
                            System.out.println(temp.getTextContent());
                            fd.setCrop_monitoring_data((temp.getTextContent()));
                        }else if (temp.getNodeName().equalsIgnoreCase("center_image_coordinates")) {
                            System.out.println(temp.getTextContent());
                            fd.setCenter_image_coordinates(temp.getTextContent());
                        }
                    }
                    boolean isInDb=baseService.isFarmerDetailsSaved(fd.getId());
                    if(!isInDb){
                        baseService.insertIntoFarmerDetails(fd);
                    }

                }

            */}

             else if(fileName.equalsIgnoreCase("question_structure")){
                 baseService.deleteTableContent(DBConstants.TABLE_NAME_RISK_PROFILLING_QUESTIONNAIRE);

                 NodeList tablePE = doc.getElementsByTagName("Row");
                 for (int k = 0; k < tablePE.getLength(); k++) {
                     RiskProfillingQuestionnaire rpq= new RiskProfillingQuestionnaire();
                     Node node = tablePE.item(k);
                     for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                         Node temp = node.getChildNodes().item(i);

                         if (temp.getNodeName().equalsIgnoreCase("id")) {
                             System.out.println(temp.getTextContent());
                             rpq.setId(Integer.parseInt((temp.getTextContent())));
                         } else if (temp.getNodeName().equalsIgnoreCase("question_number")) {
                             System.out.println(temp.getTextContent());
                             rpq.setQuestion_number(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("question")) {
                             System.out.println(temp.getTextContent());
                             rpq.setQuestion((temp.getTextContent()));
                         }else if (temp.getNodeName().equalsIgnoreCase("options")) {
                             System.out.println(temp.getTextContent());
                             rpq.setOptions((temp.getTextContent()));
                         }else if (temp.getNodeName().equalsIgnoreCase("marks")) {
                             System.out.println(temp.getTextContent());
                             rpq.setMarks(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("image_count")) {
                             System.out.println(temp.getTextContent());
                             rpq.setImage_count(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("helping_text")) {
                             System.out.println(temp.getTextContent());
                             rpq.setHelping_text(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("stage")) {
                             System.out.println(temp.getTextContent());
                             rpq.setStage(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("video_count")) {
                             System.out.println(temp.getTextContent());
                             rpq.setVideo_count(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("crop")) {
                             System.out.println(temp.getTextContent());
                             rpq.setCrop(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("key_identifier")) {
                             System.out.println(temp.getTextContent());
                             rpq.setKey_identifier(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("take_image_in_bulk")) {
                             System.out.println(temp.getTextContent());
                             rpq.setTake_image_in_bulk(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("hint_text_in_bulk")) {
                             System.out.println(temp.getTextContent());
                             rpq.setHint_text_in_bulk(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("timestamp")) {
                             System.out.println(temp.getTextContent());
                             rpq.setTimestamp(((temp.getTextContent())));
                         }
                     }
                     baseService.insertIntoRiskProfillingQuestionnaire(rpq);

                 }

             }else if(fileName.equalsIgnoreCase("Options")){
                 baseService.deleteTableContent(DBConstants.TABLE_NAME_RISK_PROFILLING_QUESTIONNAIRE_OPTION);
                 NodeList tablePE = doc.getElementsByTagName("Row");
                 for (int k = 0; k < tablePE.getLength(); k++) {
                     RiskProfillingQuestionnaireOption rpo= new RiskProfillingQuestionnaireOption();
                     Node node = tablePE.item(k);
                     for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                         Node temp = node.getChildNodes().item(i);

                         if (temp.getNodeName().equalsIgnoreCase("id")) {
                             System.out.println(temp.getTextContent());
                             rpo.setId(Integer.parseInt((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("name")) {
                             System.out.println(temp.getTextContent());
                             rpo.setName((temp.getTextContent()));
                         }else if (temp.getNodeName().equalsIgnoreCase("right_option")) {
                             System.out.println(temp.getTextContent());
                             rpo.setRight_option(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("question_id")) {
                             System.out.println(temp.getTextContent());
                             rpo.setQuestion_id(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("marks")) {
                             System.out.println(temp.getTextContent());
                             rpo.setMarks(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("key_identifier")) {
                             System.out.println(temp.getTextContent());
                             rpo.setKey_identifier(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("need_to_hide_any_question_after_this")) {
                             System.out.println(temp.getTextContent());
                             rpo.setNeed_to_hide_any_question_after_this(((temp.getTextContent())));
                         }else if (temp.getNodeName().equalsIgnoreCase("timestamp")) {
                             System.out.println(temp.getTextContent());
                             rpo.setTimestamp(((temp.getTextContent())));
                         }
                     }
                     baseService.insertIntoRiskProfillingQuestionnaireOption(rpo);

                 }

             }else{

             }


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////



    public void ParseXmlAppUser(String strResult, String contactNumber) throws Exception {
        AppUserDetails appUser=new AppUserDetails();
        try {
            BaseService baseService = new BaseService(context);
            baseService.deleteTableContent(DBConstants.TABLE_NAME_APP_USER_DETAILS_DETAILS);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = dbf.newDocumentBuilder();
            doc = docBuilder
                    .parse(new InputSource(new StringReader(strResult)));
            doc.getDocumentElement().normalize();

            NodeList tablePE = doc.getElementsByTagName("Row");
            for (int k = 0; k < tablePE.getLength(); k++) {
                Node node = tablePE.item(k);
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node temp = node.getChildNodes().item(i);

                    if (temp.getNodeName().equalsIgnoreCase("LoginId")) {
                        System.out.println(temp.getTextContent());
                        appUser.setUserID(temp.getTextContent());
                    } else if (temp.getNodeName().equalsIgnoreCase("Password")) {
                        System.out.println(temp.getTextContent());
                        appUser.setPassword((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("IMEI")) {
                        System.out.println(temp.getTextContent());
                        appUser.setIMEI(temp.getTextContent());
                    }
                }
                appUser.setLastLoginDate(date);
                appUser.setLastLoginId(appUser.getUserID());
               // appUser.setUser_Contact(contactNumber);
                baseService.saveAppUser(appUser);
            }

        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        //	doc.getDocumentElement().normalize();
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void ParseXmlConfigurationFile(String strResult) throws Exception {
        // TODO Auto-generated method stub

        try {
            BaseService baseService = new BaseService(context);
            baseService.deleteTableContent(DBConstants.TABLE_NAME_CONFIGURATION_FILE_DETAILS);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = dbf.newDocumentBuilder();
            doc = docBuilder
                    .parse(new InputSource(new StringReader(strResult)));
            doc.getDocumentElement().normalize();


            NodeList tablePE = doc.getElementsByTagName("Row");
            for (int k = 0; k < tablePE.getLength(); k++) {
                Configure_Files_Details obj = new Configure_Files_Details();
                Node node = tablePE.item(k);
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node temp = node.getChildNodes().item(i);

                    if (temp.getNodeName().equalsIgnoreCase("ConfigurationId")) {
                        System.out.println(temp.getTextContent());
                        obj.setConfigurationId(Integer.parseInt(temp.getTextContent()));
                    } else if (temp.getNodeName().equalsIgnoreCase("VersionNo")) {
                        System.out.println(temp.getTextContent());
                        obj.setVersionNo((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("versionType")) {
                        System.out.println(temp.getTextContent());
                        obj.setVersionType((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("ApplicationURL")) {
                        obj.setApplicationURL(temp.getTextContent());
                        System.out.println(temp.getTextContent());
                    }else if (temp.getNodeName().equalsIgnoreCase("WebServiceURL")) {
                        obj.setWebServiceURL((temp.getTextContent()));
                        System.out.println(temp.getTextContent());
                    }else if (temp.getNodeName().equalsIgnoreCase("DateofExpairy")) {
                        System.out.println(temp.getTextContent());
                        obj.setDateofExpairy((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("SMSreceiverMobNo")) {
                        System.out.println(temp.getTextContent());
                        obj.setSMSreceiverMobNo(temp.getTextContent());
                    }else if (temp.getNodeName().equalsIgnoreCase("Notification")) {
                        System.out.println(temp.getTextContent());
                        obj.setNotification((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("ConfigurationUpdatedDate")) {
                        System.out.println(temp.getTextContent());
                        obj.setConfigurationUpdatedDate(date);
                    }else if (temp.getNodeName().equalsIgnoreCase("ConfigurationUpdatedBy")) {
                        System.out.println(temp.getTextContent());
                        obj.setConfigurationUpdatedBy((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("ApplicableTo")) {
                        System.out.println(temp.getTextContent());
                        obj.setApplicableTo((temp.getTextContent()));
                    }else if (temp.getNodeName().equalsIgnoreCase("IsFunctionable")) {
                        System.out.println(temp.getTextContent());
                        obj.setIsFunctional((temp.getTextContent()));
                    }

                }
                long res=baseService.insertIntoConfigureFile(obj);//

            }

        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        //	doc.getDocumentElement().normalize();
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }




    }


    public void ParseJsonAppUser(String strResult, String contactNumber) {

        if (strResult != null) {
            baseService.deleteTableContent(DBConstants.TABLE_NAME_APP_USER_DETAILS_DETAILS);
            try {
                JSONObject jsonObj = new JSONObject(strResult);
                // Getting JSON Array node
                JSONArray appUserDetails = jsonObj.getJSONArray("Table1");
                AppUserDetails obj=null;
                // looping through All Contacts
                for (int i = 0; i < appUserDetails.length(); i++) {
                    JSONObject c = appUserDetails.getJSONObject(i);
                    obj=new AppUserDetails();
                    obj.setUserID(c.getString("LoginId"));
                    obj.setPassword(c.getString("Password"));
                    obj.setIMEI(c.getString("IMEI"));
                    //obj.set(c.getString("MobileNo"));

                    long res=baseService.SaveAppUserDetails(obj);

                }
            }  catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void ParseJsonMasterTables(String strResult, String tableName, AppUserDetails appUser) {


        if(tableName.equals("CA_Farmer_Details")){

            if (strResult != null) {
               baseService.deleteTableContent(DBConstants.TABLE_NAME_FARMER_DETAILS);
                try {
                    JSONObject jsonObj = new JSONObject(strResult);
                    // Getting JSON Array node
                    JSONArray appUserDetails = jsonObj.getJSONArray("Table");
                    FarmerDetails obj=null;
                    // looping through All Contacts
                    for (int i = 0; i < appUserDetails.length(); i++) {
                        JSONObject c = appUserDetails.getJSONObject(i);
                        obj=new FarmerDetails();
                        obj.setUserid(c.getString("FarmerId"));
                        obj.setUserName(c.getString("Farmer_Name"));
                        obj.setMobile_no(c.getString("MobileNo"));
                        obj.setAge(c.getInt("Age"));
                        obj.setAddress(c.getString("Address"));
                        obj.setVillage_Name(c.getString("Village"));
                        obj.setPincode(c.getString("PINCode"));
                        obj.setCreatedDate(c.getString("CreatedDate"));
                        obj.setCreatedBy(c.getString("CreatedBy"));
                        obj.setUpdatedDate(c.getString("ModifiedDate"));
                        obj.setUpdatedBy(c.getString("ModifiedBy"));
                        //obj.set(c.getString("MobileNo"));

                        long res = baseService.SaveFarmerDetails(obj);
                        System.out.print(res);
                       /* boolean isInDb=baseService.isFarmerDetailsSaved(obj.getUserid());
                        if(isInDb){

                        }else {
                            long res = baseService.SaveFarmerDetails(obj);
                        }*/
                    }
                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else if(tableName.equals("CA_Farmer_Registered_Crops")){

            if (strResult != null) {

                try {
                    JSONObject jsonObj = new JSONObject(strResult);
                    // Getting JSON Array node
                    JSONArray appUserDetails = jsonObj.getJSONArray("Table");
                    Farmer_Registered_Crop obj=null;
                    // looping through All Contacts
                    for (int i = 0; i < appUserDetails.length(); i++) {
                        JSONObject c = appUserDetails.getJSONObject(i);
                        obj=new Farmer_Registered_Crop();
                        obj.setUserID(c.getString("FarmerId"));
                        obj.setCropCode(c.getInt("Crop_Code"));


                        long res=baseService.SaveFarmerRegisteredCropDetails(obj);

                    }
                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else if(tableName.equals("Master_Questions")){

            if (strResult != null) {

                try {
                    JSONObject jsonObj = new JSONObject(strResult);
                    // Getting JSON Array node
                    JSONArray appUserDetails = jsonObj.getJSONArray("Table");
                    Master_Questions obj=null;
                    // looping through All Contacts
                    for (int i = 0; i < appUserDetails.length(); i++) {
                        JSONObject c = appUserDetails.getJSONObject(i);
                        obj=new Master_Questions();
                        obj.setLanguageId(c.getInt("LanguageId"));
                        obj.setCrop_Code(c.getInt("Crop_Code"));
                        obj.setQuestionId(c.getInt("QuestionId"));
                        obj.setQuestionOrder(c.getString("QuestionOrder"));
                        obj.setQuestion_Desc(c.getString("Question_Desc"));
                        obj.setImage_count(c.getString("Image_count"));
                        obj.setHelping_Text(c.getString("Helping_Text"));
                        obj.setStage(c.getString("Stage"));
                        obj.setVideo_count(c.getString("video_Count"));
                        obj.setTake_Image_in_Bulk(c.getString("Take_Image_in_Bulk"));
                        obj.setHint_Text_in_Bulk(c.getString("Hint_Text_in_Bulk"));


                        long res=baseService.SaveMasterQuestions(obj);

                    }
                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else if(tableName.equals("Master_Options")){


            if (strResult != null) {

                try {
                    JSONObject jsonObj = new JSONObject(strResult);
                    // Getting JSON Array node
                    JSONArray appUserDetails = jsonObj.getJSONArray("Table");
                    Master_Options obj=null;
                    // looping through All Contacts
                    for (int i = 0; i < appUserDetails.length(); i++) {
                        JSONObject c = appUserDetails.getJSONObject(i);
                        obj=new Master_Options();
                        obj.setLanguageId(c.getInt("LanguageId"));
                        obj.setQuestionId(c.getInt("QuestionId"));
                        obj.setOptionId(c.getInt("OptionId"));
                        obj.setOption_Desc(c.getString("Option_Desc"));
                        obj.setMarks(c.getString("Marks"));
                        obj.setCreatedOn(c.getString("CreatedDate"));
                        obj.setCreatedBy(c.getString("CreatedBy"));
                        obj.setUpdatedOn(c.getString("ModifiedDate"));
                        obj.setUpdatedBy(c.getString("ModifiedBy"));

                        long res=baseService.SaveMasterOptions(obj);

                    }
                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else{

        }
    }
}
