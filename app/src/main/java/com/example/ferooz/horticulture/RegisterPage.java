package com.example.ferooz.horticulture;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.webservice.Parser;
import com.example.ferooz.horticulture.webservice.SoapProxy;

import org.json.JSONArray;
import org.json.JSONObject;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    Button btnActivate, btnSubmit;
    EditText edtUserName, edtPassword, edtOTP;
    AppUserDetails appUser = new AppUserDetails();
    String otp, ContactNumber;
    TelephonyManager tm;
    String IMEI;
    BaseService baseService = new BaseService(this);
    public final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    AppUserDetails appUserDetails = new AppUserDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        back = (ImageView) findViewById(R.id.imageButton);
        btnActivate = (Button) findViewById(R.id.activatebutton);
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);
        btnActivate.setOnClickListener(this);
        edtUserName = (EditText) findViewById(R.id.usertext);
        edtPassword = (EditText) findViewById(R.id.passwordtext);
        edtOTP = (EditText) findViewById(R.id.editTextOTP);

        // new DownloadMasterTables().execute();
        try {
            tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            IMEI = "1234";//tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // edtUserName.setText("0404489");
        // edtPassword.setText("123");

        //new ValidateUserOTP().execute();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RegisterPage.this, Login.class);
                startActivity(in);

            }
        });


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activatebutton:

                if (Internet.isAvailable(getApplicationContext())) {

                    appUser.setUserID(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    appUser.setIMEI(IMEI);

                    new ValidateValidUser().execute();

                } else {
                    //Toast.makeText(getApplicationContext(), "ಇಂಟರನೆಟ್ ಸಂಪರ್ಕ ಲಭ್ಯವಿಲ್ಲ", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("No Internet connection");
                    alertDialogBuilder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    finish();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }


                break;

            case R.id.buttonSubmit:
                if (!(edtPassword.getText().toString().equals(null) || edtUserName.getText().toString().equals(null))) {
                    if (!(edtOTP.getText().toString().equals(null))) {
                        otp = edtOTP.getText().toString().trim();
                        new ValidateUserOTP().execute();

                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter OTP", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Enter user name or Password", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private class ValidateValidUser extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            String strResult = new String();
            try {

                SoapProxy proxy = new SoapProxy(RegisterPage.this);
                Parser parseResponse = new Parser(RegisterPage.this);
                //strResult = proxy.DownloadRiskProfillingDataTables(RiskProfillingWorksTableName[i]);
                strResult = proxy.ValidateValidAppUser(appUser);
                if (strResult.equalsIgnoreCase("Invalid user")) {
                    strResult = "Invalid user";
                } else if (strResult.equalsIgnoreCase("Failure")) {
                    strResult = "Failure";
                } else if (strResult.equalsIgnoreCase("Success")) {
                    strResult = "Success";
                } else if (strResult.equalsIgnoreCase("User is already registered from another device")) {
                    strResult = "User is already registered from another device";
                } else if (strResult.equalsIgnoreCase("Version outdated")) {
                    strResult = "Version outdated";
                }


                System.out.println(strResult);
            } catch (Exception e) {
                e.printStackTrace();
                strResult = "Exception";
            }
            return strResult;


        }


        @Override
        protected void onPostExecute(String strResult) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // finalResult.setText(result);

            if (strResult.equals("Invalid user")) {
                //Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Validation : Invalid User Id");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            } else if (strResult.equals("Invalid Password")) {
                //Toast.makeText(LoginActivity.this, "ಈಗಾಗಲೇ ಬೇರೊಂದು ಮೊಬೈಲ್ ಸಾಧನದಿಂದ ಈ ವಿಶಿಷ್ಠ ಗುರುತು ಬಳಸಿ ಬಳಕೆದಾರನು ನೊಂದಾಯಿಸಿಕೊಂಡಿರುತ್ತಾನೆ", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Activation : Invalid Password");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            } else if (strResult.equals("Failure")) {
                Toast.makeText(RegisterPage.this, "Connection failed", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("User is already registered from another device")) {
                //Toast.makeText(LoginActivity.this, "ಈಗಾಗಲೇ ಬೇರೊಂದು ಮೊಬೈಲ್ ಸಾಧನದಿಂದ ಈ ವಿಶಿಷ್ಠ ಗುರುತು ಬಳಸಿ ಬಳಕೆದಾರನು ನೊಂದಾಯಿಸಿಕೊಂಡಿರುತ್ತಾನೆ", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Validation : This User id is registered with some other device");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            } else if (strResult.equals("You don't have any experiments to conduct for this season")) {
                //Toast.makeText(LoginActivity.this, "User is already registered from another device", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Validation : You don't have any experiments to conduct for this season");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

            } else {

                //
                //Toast.makeText(getApplicationContext(), "OTP has been sent to your number please enter OTP", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObj = new JSONObject(strResult);
                    // Getting JSON Array node
                    JSONArray appUserDetails = jsonObj.getJSONArray("Table1");
                    // looping through All Contacts
                    for (int i = 0; i < appUserDetails.length(); i++) {
                        JSONObject c = appUserDetails.getJSONObject(i);
                        String str = c.getString("UserDetails");
                        strResult = str;
                    }
                } catch (Exception e) {

                }

                String arr[] = strResult.split("\\|", 2);

                // layoutOTP.setVisibility(View.VISIBLE);

                android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(RegisterPage.this);
                alertDialog.setTitle("Alert:");
                alertDialog.setMessage("User id : " + arr[0] + " Assigned to Mobile number : " + arr[1] + "");

                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int n) {
                                new ValidateUser().execute();
                            }
                        });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int n) {
                                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                                        RegisterPage.this).create();
                                alertDialog.setTitle("Alert :");
                                alertDialog.setMessage("Please check registered process once again");
                                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alertDialog.show();
                            }
                        });
                alertDialog.show();


            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(RegisterPage.this,
                    "Validating User Id Please wait",
                    "");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    private class ValidateUser extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();

            try {

                SoapProxy proxy = new SoapProxy(RegisterPage.this);
                Parser parseResponse = new Parser(RegisterPage.this);

//                    appUser.setUserID("aswin3333");
//                    appUser.setPassword("123");
//                    appUser.setIMEI("1234");

                appUser.setUserID("vijay");
                appUser.setPassword("123");
                appUser.setIMEI("1234");


                strResult = proxy.ValidateAppUser(appUser);

                if (strResult.equalsIgnoreCase("Invalid user")) {
                    strResult = "Invalid user";
                } else if (strResult.equalsIgnoreCase("Failure")) {
                    strResult = "Failure";
                } else if (strResult.equalsIgnoreCase("Success")) {
                    strResult = "Success";
                } else if (strResult.equalsIgnoreCase("User is already registered from another device")) {
                    strResult = "User is already registered from another device";
                } else if (strResult.equalsIgnoreCase("Version outdated")) {
                    strResult = "Version outdated";
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
            dialog.dismiss();
            // finalResult.setText(result);


            this.dialog.dismiss();//Invalid Password
            if (strResult.equals("Invalid user")) {
                //Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Validation : Invalid User Id");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            } else if (strResult.equals("Invalid Password")) {
                //Toast.makeText(LoginActivity.this, "ಈಗಾಗಲೇ ಬೇರೊಂದು ಮೊಬೈಲ್ ಸಾಧನದಿಂದ ಈ ವಿಶಿಷ್ಠ ಗುರುತು ಬಳಸಿ ಬಳಕೆದಾರನು ನೊಂದಾಯಿಸಿಕೊಂಡಿರುತ್ತಾನೆ", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Activation : Invalid Password");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            } else if (strResult.equals("Failure")) {
                Toast.makeText(RegisterPage.this, "Connection failed", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("User is already registered from another device")) {
                //Toast.makeText(LoginActivity.this, "ಈಗಾಗಲೇ ಬೇರೊಂದು ಮೊಬೈಲ್ ಸಾಧನದಿಂದ ಈ ವಿಶಿಷ್ಠ ಗುರುತು ಬಳಸಿ ಬಳಕೆದಾರನು ನೊಂದಾಯಿಸಿಕೊಂಡಿರುತ್ತಾನೆ", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Validation : This User id is registered with some other device");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            } else if (strResult.equals("You don't have any experiments to conduct for this season")) {
                //Toast.makeText(LoginActivity.this, "User is already registered from another device", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Validation : You don't have any experiments to conduct for this season");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

            } else {//
                //Toast.makeText(getApplicationContext(), "OTP has been sent to your number please enter OTP", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObj = new JSONObject(strResult);
                    // Getting JSON Array node
                    JSONArray appUserDetails = jsonObj.getJSONArray("Table1");
                    // looping through All Contacts
                    for (int i = 0; i < appUserDetails.length(); i++) {
                        JSONObject c = appUserDetails.getJSONObject(i);
                        String str = c.getString("UserDetails");
                        strResult = str;
                    }
                } catch (Exception e) {

                }

                ContactNumber = strResult;

                if (strResult.length() == 10) {
                    android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                            RegisterPage.this).create();
                    alertDialog.setTitle("Alert :");
                    alertDialog.setMessage("Activation OTP has been sent to mobile number " + strResult + "");
                    alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                } else {
                    android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                            RegisterPage.this).create();
                    alertDialog.setTitle("Alert :");
                    alertDialog.setMessage("Mobile number is not available for this User ID");
                    alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(RegisterPage.this,
                    "Validating User - ",
                    "Requesting for OTP Please wait..!");
        }

        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    private class ValidateUserOTP extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();

            try {
                SoapProxy proxy = new SoapProxy(RegisterPage.this);
                Parser parseResponse = new Parser(RegisterPage.this);
                strResult = proxy.ValidateAppUserOTP(appUser, otp);

                if (strResult.equalsIgnoreCase("NoUpdates")) {
                } else if (strResult.equalsIgnoreCase("Failure")) {
                    strResult = "InternetconectionProblem";
                } else if (strResult.equalsIgnoreCase("Invalid OTP")) {
                    strResult = "Invalid OTP";
                } else {
                    System.out.println(strResult);
                    // parseResponse.ParseXmlAppUser(strResult,ContactNumber);
                    parseResponse.ParseJsonAppUser(strResult, ContactNumber);

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

            if (strResult.equals("Error")) {
                Toast.makeText(RegisterPage.this, "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("InternetconectionProblem")) {
                Toast.makeText(RegisterPage.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("Invalid OTP")) {
                Toast.makeText(RegisterPage.this, "Please enter valid OTP", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Invalid OTP");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }//CCE_FormOne_SurveyNumber_Details
            else {

                long res = baseService.UpdateMobileNumber(ContactNumber, edtUserName.getText().toString());

                // new ActivationFeedBack().execute();
                Toast.makeText(getApplicationContext(), "User activated", Toast.LENGTH_SHORT).show();
                new DownloadMasterTables().execute();

                /*android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("User Activated " );
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new DownloadConfigurationFile().execute();
                    }
                });
                alertDialog.show();*/
                // btnActivate.setVisibility(View.GONE);
                //layoutOTP.setVisibility(View.GONE);

            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(RegisterPage.this,
                    "Varifying OTP Please wait", "");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    private class DownloadMasterTables extends AsyncTask<String, String, String> {

        private String resp;
//        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();

            try {


                SoapProxy proxy = new SoapProxy(RegisterPage.this);
                Parser parseResponse = new Parser(RegisterPage.this);
                String[] tableName = {"CA_Farmer_Details", "CA_Farmer_Registered_Crops", "Master_Questions", "Master_Options"};
                for (int i = 0; i < 4; i++) {

                    strResult = proxy.DownloadMasterTables(tableName[i], appUser);

                    if (strResult.equalsIgnoreCase("NoUpdates")) {
                    } else if (strResult.equalsIgnoreCase("Failure")) {
                        strResult = "InternetconectionProblem";
                    } else if (strResult.equalsIgnoreCase("Invalid OTP")) {
                        strResult = "Invalid OTP";
                    } else if (strResult.equalsIgnoreCase("No Data")) {
                        strResult = "No Data";
                    } else {
                        System.out.println(strResult);
                        parseResponse.ParseJsonMasterTables(strResult, tableName[i], appUser);

                    }
                }
                String[] seasonTables = {"Master_FarmerQuestionnaire_SeasonWise", "Master_Season", "Master_Month"};

                strResult = proxy.downloadSeasonWiseQuestionnaire("Vijay", 01);

                if (strResult.equalsIgnoreCase("NoUpdates")) {
                } else if (strResult.equalsIgnoreCase("Failure")) {
                    strResult = "InternetconectionProblem";
                } else if (strResult.equalsIgnoreCase("Invalid OTP")) {
                    strResult = "Invalid OTP";
                } else if (strResult.equalsIgnoreCase("No Data")) {
                    strResult = "No Data";
                } else {
                    System.out.println(strResult);
                        parseResponse.ParseJsonMasterSeasonTables(strResult, seasonTables, appUser);
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
//            progressDialog.dismiss();
            // finalResult.setText(result);

            if (strResult.equals("Error")) {
                Toast.makeText(RegisterPage.this, "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("InternetconectionProblem")) {
                Toast.makeText(RegisterPage.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("Invalid OTP")) {
                Toast.makeText(RegisterPage.this, "Please enter valid OTP", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Activation OTP has been sent to mobile number " + strResult + "");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }//CCE_FormOne_SurveyNumber_Details
            else {

                long res = baseService.UpdateMobileNumber(ContactNumber, edtUserName.getText().toString());

                // new ActivationFeedBack().execute();
                //Toast.makeText(getApplicationContext(), "User activated", Toast.LENGTH_SHORT).show();

                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("User Activated ");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //new DownloadConfigurationFile().execute();
                        appUserDetails = baseService.getAppUserDetails();
                        if (appUserDetails != null) {
                            edtUserName.setText(appUserDetails.getUserID());
                            edtPassword.setText(appUserDetails.getPassword());

                        }
                        Intent i = new Intent(RegisterPage.this, MainActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable(PAR_KEY, appUserDetails);
                        i.putExtras(mBundle);
                        startActivity(i);
                    }
                });
                alertDialog.show();
                // btnActivate.setVisibility(View.GONE);
                //layoutOTP.setVisibility(View.GONE);

            }

        }


        @Override
        protected void onPreExecute() {
//            progressDialog = ProgressDialog.show(RegisterPage.this,
//                    "Downloading Master Tables Please Wait..!", "");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }


    private class DownloadConfigurationFile extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();
            String[] RiskProfillingWorksTableName = {"Risk_Profilling", "Farmer"};
            for (int i = 0; i < 2; i++)
                try {

                    SoapProxy proxy = new SoapProxy(RegisterPage.this);
                    Parser parseResponse = new Parser(RegisterPage.this);

                    strResult = proxy.DownloadConfigure(appUser, IMEI);
                    if (strResult.equalsIgnoreCase("NoUpdates")) {

                    } else if (strResult.equalsIgnoreCase("Failure")) {
                        strResult = "InternetconectionProblem";
                    } else if (strResult.equalsIgnoreCase("Invalid user")) {
                        strResult = "Invalid user";
                    } else {
                        System.out.println(strResult);
                        parseResponse.ParseXmlConfigurationFile(strResult);
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


            if (strResult.equals("Error")) {
                Toast.makeText(RegisterPage.this, "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("InternetconectionProblem")) {
                Toast.makeText(RegisterPage.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("Invalid User")) {
                // Toast.makeText(RegisterPage.this, "Please enter valid OTP", Toast.LENGTH_LONG).show();
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Invalid User");
                alertDialog.setButton("ಸರಿ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }//CCE_FormOne_SurveyNumber_Details
            else {


                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(
                        RegisterPage.this).create();
                alertDialog.setTitle("Alert :");
                alertDialog.setMessage("Configuration file downloaded");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new DownloadConfigurationFile().execute();
                    }
                });
                alertDialog.show();
                // btnActivate.setVisibility(View.GONE);
                //layoutOTP.setVisibility(View.GONE);

            }


        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(RegisterPage.this,
                    "ProgressDialog",
                    "Downloading data");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }
}
