package com.example.ferooz.horticulture.LandDetails;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ferooz.horticulture.MainActivity;
import com.example.ferooz.horticulture.R;
import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.LandCropGeoData;
import com.example.ferooz.horticulture.pojoclasses.LandCropQuestionnaireMedias;
import com.example.ferooz.horticulture.pojoclasses.Land_Crop_Questionnaire_Answer;
import com.example.ferooz.horticulture.webservice.SoapProxy;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class LandCropDetails extends AppCompatActivity implements View.OnClickListener{

    Button btnCornerCoordinates, btnCenterCoordinates, btnQuestionnaire, btnUploadDetails, btnBack;
    AppUserDetails appUserDetails;
    int CropCode;
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    BaseService baseService=new BaseService(this);
    FTPClient conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land_crop_details);

        appUserDetails = (AppUserDetails)getIntent().getSerializableExtra(PAR_KEY);
        Intent intent=getIntent();

        CropCode=intent.getIntExtra("CropCode",0);

        btnCornerCoordinates=(Button)findViewById(R.id.startCorner);
        btnCenterCoordinates=(Button)findViewById(R.id.startCenter);
        btnQuestionnaire=(Button)findViewById(R.id.startQuestinaaire);
        btnUploadDetails=(Button)findViewById(R.id.startUpload);
       // btnBack=(Button)findViewById(R.id.BackLandCropHomepage);
       // btnBack.setOnClickListener(this);
        btnUploadDetails.setOnClickListener(this);
        btnCornerCoordinates.setOnClickListener(this);
        btnCenterCoordinates.setOnClickListener(this);
        btnQuestionnaire.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent in4=new Intent(LandCropDetails.this, MainActivity.class);
                Bundle mBundle4 = new Bundle();
                mBundle4.putSerializable(PAR_KEY, appUserDetails);
                in4.putExtras(mBundle4);
                in4.putExtra("CropCode", "1");
                startActivity(in4);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.startCorner:
                /* Intent i =new Intent(LandCropDetailsHomePageActivity.this, GeoFencing.class);
                 startActivity(i);*/

                Intent in=new Intent(LandCropDetails.this, GeoFencing.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(PAR_KEY,appUserDetails);
                in.putExtras(mBundle);
                in.putExtra("CropCode", CropCode);
                in.putExtra("CornerId",1);
                startActivity(in);

                break;
            case R.id.startCenter:
               /* Intent i1 =new Intent(LandCropDetailsHomePageActivity.this, CenterOfTheFarm.class);
                startActivity(i1);*/
                Intent in2=new Intent(LandCropDetails.this, CenterOfTheFarm.class);
                Bundle mBundle2 = new Bundle();
                mBundle2.putSerializable(PAR_KEY, appUserDetails);
                in2.putExtras(mBundle2);
                in2.putExtra("CropCode",CropCode);
                startActivity(in2);
                break;

            case R.id.startQuestinaaire:
                /*Intent i2 =new Intent(LandCropDetailsHomePageActivity.this, CropDetailQuestionary.class);
                startActivity(i2);
*/
                Intent in3=new Intent(LandCropDetails.this, CropDetailQuestionary.class);
                Bundle mBundle3 = new Bundle();
                mBundle3.putSerializable(PAR_KEY,appUserDetails);
                in3.putExtras(mBundle3);
                in3.putExtra("CropCode",CropCode);
                startActivity(in3);
                break;

           /* case R.id.BackLandCropHomepage:

                Intent in4=new Intent(LandCropDetails.this, MainActivity.class);
                Bundle mBundle4 = new Bundle();
                mBundle4.putSerializable(PAR_KEY, appUserDetails);
                in4.putExtras(mBundle4);
                in4.putExtra("CropCode", "1");
                startActivity(in4);

                break;*/

            case R.id.startUpload:

                // new UploadLandCropDetails().execute();
                new UploadLandCropMediasDetails().execute();
                break;
        }
    }
 private class UploadLandCropDetails extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();

            try {

                SoapProxy proxy = new SoapProxy(LandCropDetails.this);

                String JsonCornerDataUploadFile=createJsonForCornerDataUpload();
                strResult=proxy.uploadLandCropDetails(JsonCornerDataUploadFile,appUserDetails,CropCode);

                //strResult="Success";
                if(strResult.equalsIgnoreCase("NoUpdates")){

                }else if(strResult.equalsIgnoreCase("Failure")){
                    strResult="InternetconectionProblem";
                }else if(strResult.equalsIgnoreCase("Invalid user")){
                    strResult="Invalid user";
                }else if(strResult.equalsIgnoreCase("Success")){

                    strResult = new String();
                    String JsonQuestionnaireUploadFile=createJsonForQuestionnaireUpload();
                    strResult=proxy.uploadLandCropQuestionnaireDetails(JsonQuestionnaireUploadFile,appUserDetails,CropCode);

                    //strResult="Success";
                    if(strResult.equalsIgnoreCase("NoUpdates")){

                    }else if(strResult.equalsIgnoreCase("Failure")){
                        strResult="InternetconectionProblem";
                    }else if(strResult.equalsIgnoreCase("Success")){
                        strResult = new String();
                        String JsonQuestionnaireMediasUploadFile=createJsonForQuestionnaireMediasUpload();
                        strResult=proxy.uploadLandCropQuestionnaireMediasDetails(JsonQuestionnaireMediasUploadFile,appUserDetails,CropCode);

                        strResult="Success";
                        if(strResult.equalsIgnoreCase("NoUpdates")){

                        }else if(strResult.equalsIgnoreCase("Failure")){
                            strResult="InternetconectionProblem";
                        }else{

                        }
                    }


                }else{
                    System.out.println(strResult);
                }


                System.out.println(strResult);
            } catch (Exception e) {
                e.printStackTrace();
                strResult = "InternetconectionProblem";
            }

            return strResult;

        }


        @Override
        protected void onPostExecute(String strResult) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // finalResult.setText(result);


            if(strResult.equals("Error")) {
                Toast.makeText(LandCropDetails.this, "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            }else if(strResult.equals("InternetconectionProblem")) {
                Toast.makeText(LandCropDetails.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
            else if(strResult.equals("Invalid User")) {
                // Toast.makeText(RegisterPage.this, "Please enter valid OTP", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        LandCropDetails.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Invalid User");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }else if(strResult.equals("Success")) {
                Toast.makeText(LandCropDetails.this, "Land Crop Data Uploaded Successfully", Toast.LENGTH_LONG).show();

                new UploadLandCropMediasDetails().execute();
            }//CCE_FormOne_SurveyNumber_Details
            else{


                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        LandCropDetails.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Not uploaded" );
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                // btnActivate.setVisibility(View.GONE);
                //layoutOTP.setVisibility(View.GONE);

            }


        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LandCropDetails.this,
                    "Uploading Land Crop Details Please wait..!",
                    "");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    private class UploadLandCropMediasDetails extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();

            try {
                List<LandCropGeoData> lstCornerData=baseService.GetCornerDetails(appUserDetails,CropCode);

                if(lstCornerData!=null) {

                    for (int j = 0; j <= lstCornerData.size(); j++) {

                        int iterator=0;
                        if(lstCornerData.get(j).getCornerType()==1){
                            iterator=3;
                        }else{
                            iterator=4;
                        }
                        for (int i = 0; i < iterator; i++) {

                            String filePath="" ;
                            if(i==0){
                                filePath = lstCornerData.get(j).getFirst_image_url();
                            }else if(i==1){
                                filePath = lstCornerData.get(j).getSecond_image_url();
                            }else if(i==2){
                                filePath = lstCornerData.get(j).getThird_image_url();
                            }else if(i==3){
                                filePath = lstCornerData.get(j).getFourth_image_url();
                            }
                            conn = new FTPClient();

                            try {
                                conn = new FTPClient();
                                conn.connect("124.153.106.183");
                                if (conn.login("administrator", "Qmax%Infra@0808")) {

                                    conn.enterLocalPassiveMode(); // important!
                                    conn.setFileType(FTP.BINARY_FILE_TYPE);
                                    String data = filePath;
                                    filePath = filePath.toString().substring(filePath.toString().indexOf("Land_Crop_Details"));

                                    File file = new File(filePath);
                                    String getDirectoryPath = file.getParent();
                                    // getDirectoryPath = getDirectoryPath.toString().substring(getDirectoryPath.toString().indexOf("CAMS_Video"));
                                    getDirectoryPath = getDirectoryPath.toString();
                                    boolean createDir = createDirectory(getDirectoryPath);
                                    FileInputStream in = new FileInputStream(new File(data));

                                    boolean result = conn.storeFile("/" + filePath, in);
                                    in.close();
                                    if (result) {
                                        Log.v("upload result", "succeeded");
                                        strResult = "Success";
                                    } else {

                                    }
                                    conn.logout();
                                    conn.disconnect();

                                    if(i==iterator){
                                        conn = new FTPClient();
                                        filePath=lstCornerData.get(i).getCorner_video_url();
                                        try {
                                            conn = new FTPClient();
                                            conn.connect("124.153.106.183");
                                            if (conn.login("administrator", "Qmax%Infra@0808")) {

                                                conn.enterLocalPassiveMode(); // important!
                                                conn.setFileType(FTP.BINARY_FILE_TYPE);
                                                data = filePath;
                                                filePath = filePath.toString().substring(filePath.toString().indexOf("Land_Crop_Details"));

                                                file = new File(filePath);
                                                getDirectoryPath = file.getParent();
                                                // getDirectoryPath = getDirectoryPath.toString().substring(getDirectoryPath.toString().indexOf("CAMS_Video"));
                                                getDirectoryPath = getDirectoryPath.toString();
                                                createDir = createDirectory(getDirectoryPath);
                                                in = new FileInputStream(new File(data));

                                                result = conn.storeFile("/" + filePath, in);
                                                in.close();
                                                if (result) {
                                                    Log.v("upload result", "succeeded");
                                                    strResult = "Success";
                                                } else {

                                                }
                                                conn.logout();
                                                conn.disconnect();

                                                if(i==iterator){

                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }else{
                    strResult="No Data";
                }


            } catch (Exception ex) {
                strResult="Error";
                ex.printStackTrace();
            } finally {

            }

            return strResult;

        }


        @Override
        protected void onPostExecute(String strResult) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // finalResult.setText(result);


            if(strResult.equals("Error")) {
                Toast.makeText(LandCropDetails.this, "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            }else if(strResult.equals("InternetconectionProblem")) {
                Toast.makeText(LandCropDetails.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            }
            else if(strResult.equals("Invalid User")) {
                // Toast.makeText(RegisterPage.this, "Please enter valid OTP", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        LandCropDetails.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Invalid User");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }else if(strResult.equals("Success")) {
                // Toast.makeText(RegisterPage.this, "Please enter valid OTP", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        LandCropDetails.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Uploaded successfully");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }//CCE_FormOne_SurveyNumber_Details
            else{


                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        LandCropDetails.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Not uploaded" );
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                // btnActivate.setVisibility(View.GONE);
                //layoutOTP.setVisibility(View.GONE);

            }


        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LandCropDetails.this,
                    "Uploading Land Crop Details Medias Please wait..!",
                    "");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    private boolean createDirectory(String getDirectoryPath) throws IOException {
        // TODO Auto-generated method stub
        boolean created = false;
        boolean existed = false;

        try {
            existed = conn.changeWorkingDirectory(getDirectoryPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!existed) {

            try {
                String[] pathElements = getDirectoryPath.split("/");
                if (pathElements != null && pathElements.length > 0) {
                    for (String singleDir : pathElements) {
                        boolean existed1 = conn.changeWorkingDirectory(singleDir);
                        if (!existed1) {
                            created = conn.makeDirectory(singleDir);
                            if (created) {
                                System.out.println("CREATED directory: " + singleDir);
                                conn.changeWorkingDirectory(singleDir);
                            } else {
                                System.out.println("COULD NOT create directory: " + singleDir);
                                return false;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return created;
    }

    private String createJsonForQuestionnaireMediasUpload() throws JSONException, IOException {

        List<LandCropQuestionnaireMedias> lstLandCropAnswersMedias=baseService.GetCLandCropAnswersMediaDetails(appUserDetails,CropCode);

        List<JSONObject> lstArrayData;
        lstArrayData=new ArrayList<JSONObject>();
        for(int i=1;i<=lstLandCropAnswersMedias.size();i++) {

            JSONObject arrayData = new JSONObject();
            try {
                arrayData.put("UserId", appUserDetails.getUserID());
                arrayData.put("CropCode", CropCode);
                arrayData.put("LandId",/*String.valueOf(lstLandCropAnswersMedias.get(i-1).getLand_Id())*/"1");
                arrayData.put("QuestionId", String.valueOf(lstLandCropAnswersMedias.get(i-1).getQuestion_id()));
                arrayData.put("MediaId", String.valueOf(lstLandCropAnswersMedias.get(i-1).getMediadId()));
                arrayData.put("MediaType", String.valueOf(lstLandCropAnswersMedias.get(i-1).getMedia_type()));
                arrayData.put("MediaUrl", String.valueOf(lstLandCropAnswersMedias.get(i-1).getMedia_url()));
                arrayData.put("MediaCoordinates", String.valueOf(/*lstLandCropAnswersMedias.get(i-1).getCoordinates()*/"12.3233,17.32323"));


                lstArrayData.add(arrayData);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        JSONArray CornerArray=new JSONArray();

        for ( JSONObject arraData: lstArrayData) {
            CornerArray.put(arraData);
        }

        JSONObject[] MultiArray=new JSONObject[3];

        JSONObject finalCornerDataArray=new JSONObject();
        finalCornerDataArray.put("Questionnaire_Medias_Data",CornerArray);

        String JsonCornerData= finalCornerDataArray.toString();

        //MultiArray[0]
        String root = Environment.getExternalStorageDirectory().toString();
        File AppFolder = new File(root + "/CAMS/CAMS_JSON_FILE");
        if (!AppFolder.exists()) {
            AppFolder.mkdirs();
        }

        File myFile = new File(AppFolder+"/Upload_Data_Questionnaire_Medias"+".json");
        myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter =
                new OutputStreamWriter(fOut);
        myOutWriter.append(JsonCornerData);
        myOutWriter.close();
        fOut.close();

        return JsonCornerData;
    }

    private String createJsonForQuestionnaireUpload() throws JSONException, IOException {

        List<Land_Crop_Questionnaire_Answer> lstLandCropAnswers=baseService.GetCLandCropAnswersDetails(appUserDetails,CropCode);
        //List<LandCropQuestionnaireMedias> lstLandCropAnswersMedias=baseService.GetCLandCropAnswersMediaDetails(appUserDetails,CropCode);

        List<JSONObject> lstArrayData;
        lstArrayData=new ArrayList<JSONObject>();
        for(int i=1;i<=lstLandCropAnswers.size();i++) {

            JSONObject arrayData = new JSONObject();
            try {
                arrayData.put("UserId", appUserDetails.getUserID());
                arrayData.put("CropCode", CropCode);
                arrayData.put("QuestionId", String.valueOf(lstLandCropAnswers.get(i-1).getQuest_id()));
                arrayData.put("OptionId", String.valueOf(lstLandCropAnswers.get(i-1).getOption_id()));
                arrayData.put("LandId", String.valueOf(/*lstLandCropAnswers.get(i-1).getLand_Id()*/"1"));

                lstArrayData.add(arrayData);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        JSONArray CornerArray=new JSONArray();

        for ( JSONObject arraData: lstArrayData) {
            CornerArray.put(arraData);
        }

        JSONObject[] MultiArray=new JSONObject[3];

        JSONObject finalCornerDataArray=new JSONObject();
        finalCornerDataArray.put("Questionnaire_Data",CornerArray);

        String JsonCornerData= finalCornerDataArray.toString();

        //MultiArray[0]
        String root = Environment.getExternalStorageDirectory().toString();
        File AppFolder = new File(root + "/CAMS/CAMS_JSON_FILE");
        if (!AppFolder.exists()) {
            AppFolder.mkdirs();
        }

        File myFile = new File(AppFolder+"/Upload_Data_Questionnaire"+".json");
        myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter =
                new OutputStreamWriter(fOut);
        myOutWriter.append(JsonCornerData);
        myOutWriter.close();
        fOut.close();

        return JsonCornerData;
    }


    private String createJsonForCornerDataUpload() throws JSONException, IOException {

        List<LandCropGeoData> lstCornerData=baseService.GetCornerDetails(appUserDetails,CropCode);
        //List<Land_Crop_Questionnaire_Answer> lstLandCropAnswers=baseService.GetCLandCropAnswersDetails(appUserDetails,CropCode);
        //List<LandCropQuestionnaireMedias> lstLandCropAnswersMedias=baseService.GetCLandCropAnswersMediaDetails(appUserDetails,CropCode);

        List<JSONObject> lstArrayData;
        lstArrayData=new ArrayList<JSONObject>();
        for(int i=1;i<=lstCornerData.size();i++) {

            JSONObject arrayData = new JSONObject();
            try {
                arrayData.put("UserId", appUserDetails.getUserID());
                arrayData.put("CropCode", CropCode);
                arrayData.put("CornerId", String.valueOf(lstCornerData.get(i-1).getCorner_ID()));
                arrayData.put("CornerType", String.valueOf(lstCornerData.get(i-1).getCornerType()));
                arrayData.put("Coordinates", lstCornerData.get(i-1).getCoordinates());
                arrayData.put("LandId", /*lstCornerData.get(i-1).getLandId()*/"1");

                arrayData.put("FirstImageUrl", lstCornerData.get(i-1).getFirst_image_url());
                arrayData.put("SecondImageUrl", lstCornerData.get(i-1).getSecond_image_url());
                arrayData.put("ThirdImageUrl", lstCornerData.get(i-1).getThird_image_url());

                if(lstCornerData.get(i-1).getCornerType()==2) {
                    arrayData.put("FourthImageUrl", lstCornerData.get(i - 1).getThird_image_url());
                }else{
                    arrayData.put("FourthImageUrl", "Nill");
                }
                arrayData.put("VideoUrl", lstCornerData.get(i-1).getCorner_video_url());


                lstArrayData.add(arrayData);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        JSONArray CornerArray=new JSONArray();

        for ( JSONObject arraData: lstArrayData) {
            CornerArray.put(arraData);
        }

        JSONObject[] MultiArray=new JSONObject[3];

        JSONObject finalCornerDataArray=new JSONObject();
        finalCornerDataArray.put("Corner_Data",CornerArray);

        String JsonCornerData= finalCornerDataArray.toString();

        //MultiArray[0]
        String root = Environment.getExternalStorageDirectory().toString();
        File AppFolder = new File(root + "/CAMS/CAMS_JSON_FILE");
        if (!AppFolder.exists()) {
            AppFolder.mkdirs();
        }

        File myFile = new File(AppFolder+"/Upload_Data_Corner"+".json");
        myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter =
                new OutputStreamWriter(fOut);
        myOutWriter.append(JsonCornerData);
        myOutWriter.close();
        fOut.close();

        return /*"http://schemas.xmlsoap.org/soap/envelope/"+*/JsonCornerData;
    }

    @Override
    public void onBackPressed(){

        Intent i=new Intent(LandCropDetails.this,MainActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(PAR_KEY, appUserDetails);
        i.putExtra("CropCode",CropCode);
        //i.putExtra("CornerId",CornerId);
        i.putExtras(mBundle);
        startActivity(i);
    }


}
