package com.example.ferooz.horticulture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;

public class Login extends AppCompatActivity {

    TextView newuser;
    EditText edtUserName,edtPassword;
    Button login;
    AppUserDetails appUserDetails =new AppUserDetails();
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";

    BaseService baseService=new BaseService(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        newuser= (TextView) findViewById(R.id.newuser);
        login= (Button) findViewById(R.id.loginbutton);
        edtUserName=(EditText)findViewById(R.id.usertext);
        edtPassword=(EditText)findViewById(R.id.passwordtext);

        appUserDetails=baseService.getAppUserDetails();
        if(appUserDetails!=null){
            edtUserName.setText(appUserDetails.getUserID());
            edtPassword.setText(appUserDetails.getPassword());
            newuser.setVisibility(View.GONE);
          /*  if(Internet.isAvailable(getApplicationContext())){
                showUpdateAlert=false;
                new DownloadConfigurationFile().execute();
            }*/
        }else{
            newuser.setVisibility(View.VISIBLE);
        }


       /* newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Login.this,RegisterPage.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_up_info,R.anim.no_change);


            }
        });*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*SoapProxy proxy=new SoapProxy(Login.this);
                proxy.DownloadGenericAdvisoryTables(appUserDetails);*/

                Intent i=new Intent(Login.this,FarmerDetailsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(PAR_KEY, appUserDetails);
                i.putExtras(mBundle);
                startActivity(i);
            }
        });

    }




}
