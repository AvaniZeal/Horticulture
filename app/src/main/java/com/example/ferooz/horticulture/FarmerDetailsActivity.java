package com.example.ferooz.horticulture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.FarmerDetails;

import java.util.ArrayList;
import java.util.List;

public class FarmerDetailsActivity extends AppCompatActivity {

    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    BaseService baseService=new BaseService(this);
    TextView tvName,tvMobileNumber,tvVillageName;
    AppUserDetails appUserDetails;
    Spinner spnCrops;
    List<String> CropName=new ArrayList<String>();
    Button btnStart;
    int CropCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_details);

        appUserDetails = (AppUserDetails)getIntent().getSerializableExtra(FarmerDetailsActivity.PAR_KEY);

        tvName=(TextView)findViewById(R.id.textViewName);
        tvMobileNumber=(TextView)findViewById(R.id.textViewMobileNumber);
        tvVillageName=(TextView)findViewById(R.id.textViewVillageName);
        spnCrops=(Spinner)findViewById(R.id.spinnerCrops);
        FarmerDetails farmerDetails=baseService.getFarmerDetails(appUserDetails);
        btnStart=(Button)findViewById(R.id.startButton);





        if(farmerDetails!=null){
            tvName.setText(farmerDetails.getUserName());
            tvMobileNumber.setText(farmerDetails.getMobile_no());
            tvVillageName.setText(farmerDetails.getVillage_Name());

        }else{

        }
        List<String> lstRegisteredCrops=baseService.getAllRegisteredCrops(appUserDetails);
        if(lstRegisteredCrops!=null){
            CropName.add("--Select--");
            for (String Crop : lstRegisteredCrops) {
                if(Crop.equalsIgnoreCase("1")){
                    if(!CropName.contains("Grape")){
                        CropName.add("Grape");
                    }

                }else if(Crop.equalsIgnoreCase("2")){
                    if(!CropName.contains("Mango")){
                        CropName.add("Mango");
                    }
                }else if(Crop.equalsIgnoreCase("3")){
                    if(!CropName.contains("Apple")){
                        //CropName.add("Apple");
                    }
                }else{
                }
            }
        }

        ArrayAdapter<String> adaptorFarmerId = new ArrayAdapter<String>(FarmerDetailsActivity.this,R.layout.spinner, CropName);
        adaptorFarmerId.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnCrops.setAdapter(adaptorFarmerId);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!spnCrops.getSelectedItem().toString().equalsIgnoreCase("--Select--")) {

                    Intent i = new Intent(FarmerDetailsActivity.this, MainActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(PAR_KEY, appUserDetails);
                    i.putExtras(mBundle);
                    i.putExtra("CropCode",CropCode);
                    startActivity(i);
                }else{
                    Toast.makeText(FarmerDetailsActivity.this,"Please select crop",Toast.LENGTH_LONG).show();
                }
            }
        });

        spnCrops.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=spnCrops.getSelectedItem().toString();

                if(selectedItem.equalsIgnoreCase("Grape")){
                    CropCode=1;
                }else if(selectedItem.equalsIgnoreCase("Mango")){
                    CropCode=2;
                }else if(selectedItem.equalsIgnoreCase("Apple")){
                    CropCode=3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
