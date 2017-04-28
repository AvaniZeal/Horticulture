package com.example.ferooz.horticulture.webservice;

import android.content.Context;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.example.ferooz.horticulture.database.BaseDao;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;

import java.io.IOException;

/**
 * Created by admin on 3/22/2017.
 */

public class SoapProxy {

    String HOST_URL="";

    @SuppressWarnings("unused")
    private Context context;
    private BaseDao baseDao;

    public SoapProxy(Context context) {
        this.context = context;
    }

    public String DownloadLossAssessmentTables(String tableName) {


        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    "DownLoadRiskProfilingWorks");
            request.addProperty("userName","sandeep");
            request.addProperty("tableName", tableName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URL_FOR_DOWNLOAD_TABLES, 30000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + "DownLoadRiskProfilingWorks", envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        "DownLoadRiskProfilingWorksResult")
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;


    }

    public String DownloadRiskProfillingDataTables(String tableName) {

        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.DOWNLOAD_RISK_PROFILLING_TABLES_METHOD);
            request.addProperty("userName","sandeep");
            request.addProperty("tableName", tableName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URL_FOR_DOWNLOAD_TABLES, 30000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.DOWNLOAD_RISK_PROFILLING_TABLES_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.DOWNLOAD_RISK_PROFILLING_TABLES_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;

    }

    public String DownloadMasterTables(String table, AppUserDetails appUser) {
        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.DOWNLOAD_MASTER_TABLES_METHOD);
            request.addProperty("loginId", appUser.getUserID());
            request.addProperty("tableName", table);

            // request.addProperty("Year_Code", yearCode);
            //request.addProperty("Season_Code", seasonCode);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL,2147483647);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.DOWNLOAD_MASTER_TABLES_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.DOWNLOAD_MASTER_TABLES_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }


    public String uploadExpertData(String data) throws IOException, XmlPullParserException {
        String response = null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    "UploadingFarmerQuery");

            request.addProperty("FarmerLoginId", "ashwin");
            request.addProperty("jsonFarmerQueryDetails", data);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL, 30000000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + "UploadingFarmerQuery", envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        "UploadingFarmerQueryResult")
                        .toString();
                System.out.println(response);
            } else if (envelope.bodyIn instanceof SoapFault) {

                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }

    public String DownloadingExpertAdvisory() {


        String response = null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.DOWNLOAD_EXPERT_VIEW_ADVISORY_METHOD);//
            //request.addProperty("loginId", appUserDetails.getUserID());
            request.addProperty("FarmerLoginId", "ashwin");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL, 30000000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.DOWNLOAD_EXPERT_VIEW_ADVISORY_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.DOWNLOAD_EXPERT_VIEW_ADVISORY_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            } else if (envelope.bodyIn instanceof SoapFault) {

                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;


    }
    public String uploadLandCropDetails(String jsonUploadFile, AppUserDetails appUserDetails, int cropCode) {
        String response = null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.UPLOAD_LAND_CROP_CORNER_DATA);

            request.addProperty("loginId", appUserDetails.getUserID());
            request.addProperty("landDetails", jsonUploadFile);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URL_FOR_DOWNLOAD_TABLES, 30000000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.UPLOAD_LAND_CROP_CORNER_DATA, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.UPLOAD_LAND_CROP_CORNER_DATA_REQRESULT)
                        .toString();
                System.out.println(response);
            } else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }

    public String uploadLandCropQuestionnaireDetails(String jsonQuestionnaireUploadFile, AppUserDetails appUserDetails, int cropCode) {
        String response = null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.UPLOAD_LAND_CROP_QUESTIONNAIRE_DATA);

            request.addProperty("loginId",appUserDetails.getUserID());
            request.addProperty("questionnaireAnswers", jsonQuestionnaireUploadFile);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URL_FOR_DOWNLOAD_TABLES, 30000000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.UPLOAD_LAND_CROP_QUESTIONNAIRE_DATA, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.UPLOAD_LAND_CROP_QUESTIONNAIRE_DATA_REQRESULT)
                        .toString();
                System.out.println(response);
            } else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }


    public String uploadLandCropQuestionnaireMediasDetails(String jsonQuestionnaireMediasUploadFile, AppUserDetails appUserDetails, int cropCode) {
        String response = null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.UPLOAD_LAND_CROP_QUESTIONNAIRE_MEDIAS_DATA);

            request.addProperty("loginId",appUserDetails.getUserID());
            request.addProperty("questionnaireMultiMedia", jsonQuestionnaireMediasUploadFile);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URL_FOR_DOWNLOAD_TABLES, 30000000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.UPLOAD_LAND_CROP_QUESTIONNAIRE_MEDIAS_DATA, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.UPLOAD_LAND_CROP_QUESTIONNAIRE_MEDIAS_DATA_REQRESULT)
                        .toString();
                System.out.println(response);
            } else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }

    public String DownloadGenericAdvisoryTables(AppUserDetails appUserDetails) {


        String response = null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.DOWNLOAD_GENERIC_ADVISORY_METHOD);
            //request.addProperty("loginId", appUserDetails.getUserID());
            request.addProperty("FarmerLoginId", "vikram007");
            request.addProperty("CropId", 01);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL, 30000000);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.DOWNLOAD_GENERIC_ADVISORY_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.DOWNLOAD_GENERIC_ADVISORY_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            } else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;


    }

    public String ValidateValidAppUser(AppUserDetails appUser) {
        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.VALIDATE_VALID_USER_METHOD);

            request.addProperty("loginId", appUser.getUserID());
            request.addProperty("password", appUser.getPassword());
            request.addProperty("IMEINo", appUser.getIMEI());

            //request.addProperty("Year_Code", yearCode);
            //request.addProperty("Season_Code", seasonCode);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL,2147483647);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    +WebServiceConstants.VALIDATE_VALID_USER_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.VALIDATE_USER_VALID_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }

    public String ValidateAppUser(AppUserDetails appUser) {
        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.VALIDATE_USER_METHOD);

            request.addProperty("loginId", appUser.getUserID()/*"PW_0107124"*/);
            request.addProperty("password", appUser.getPassword());
            request.addProperty("IMEINo", appUser.getIMEI());
            request.addProperty("Version", "1.0.0");

            // request.addProperty("Year_Code", yearCode);
            // request.addProperty("Season_Code", seasonCode);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL,2147483647);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    +WebServiceConstants.VALIDATE_USER_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.VALIDATE_USER_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }

    public String ValidateAppUserOTP(AppUserDetails appUser, String OTP) {
        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.VALIDATE_USER_OTP_METHOD);
            request.addProperty("loginId", appUser.getUserID());
            request.addProperty("password", appUser.getPassword());
            request.addProperty("IMEINo", appUser.getIMEI());
            request.addProperty("OTP", OTP);

            //request.addProperty("Year_Code", "114");
            //request.addProperty("Season_Code", "1");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URRL);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    +WebServiceConstants.VALIDATE_USER_OTP_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.VALIDATE_USER_OTP_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }

    public String DownloadConfigure(AppUserDetails appUserDetails, String iMEI) {
        String response=null;
        try {
            SoapObject request = new SoapObject(WebServiceConstants.NAMESPACE,
                    WebServiceConstants.DOWNLOAD_CONFIGURATION_FILE_METHOD);
            request.addProperty("loginId", appUserDetails.getUserID());
            request.addProperty("password", appUserDetails.getPassword());
            request.addProperty("IMEINo", iMEI);

            // request.addProperty("Year_Code", yearCode);
            //request.addProperty("Season_Code", seasonCode);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    WebServiceConstants.HOST_URL_FOR_DOWNLOAD_TABLES,2147483647);
            androidHttpTransport.call(WebServiceConstants.NAMESPACE
                    + WebServiceConstants.DOWNLOAD_CONFIGURATION_FILE_METHOD, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                response = soapObject.getProperty(
                        WebServiceConstants.DOWNLOAD_CONFIGURATION_FILE_METHOD_REQRESULT)
                        .toString();
                System.out.println(response);
            }else if (envelope.bodyIn instanceof SoapFault) {
                @SuppressWarnings("unused")
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                response = "Failure";
            }

        }catch (Exception e) {
            e.printStackTrace();
            response = "Failure";
        }
        return response;
    }



}
