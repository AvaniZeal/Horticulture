package com.example.ferooz.horticulture;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferooz.horticulture.pojoclasses.AdvisorAdapter;
import com.example.ferooz.horticulture.pojoclasses.AdvisorDataProvider;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.GenericAdvisoryList;
import com.example.ferooz.horticulture.webservice.SoapProxy;
import com.example.ferooz.horticulture.webservice.WebServiceConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenericAdvisory extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private int position;

    // ImageView imageview;
    ListView listView;

    String[] advisor_names;
    String[] dates;

    //  String TodaysDate = new SimpleDateFormat( "dd-MMM-yyyy" ).format(new Date(System.currentTimeMillis()));
    AppUserDetails appUserDetails = new AppUserDetails();
    AdvisorAdapter adapter;
    List<GenericAdvisoryList> genericdata = new ArrayList<GenericAdvisoryList>();

// JSON

    ArrayList<HashMap<String, String>> advisorList;
    private ExpertActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static Context contextOfApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_advisory);

        advisorList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listview);

        // imageview= (ImageView) findViewById(R.id.imageView3);
        advisor_names = getResources().getStringArray(R.array.advisor_names);
        dates = getResources().getStringArray(R.array.advised_dates);

        int i = 0;
        adapter = new AdvisorAdapter(getApplicationContext(), advisorList, R.layout.advisor_row_layout, new String[]{"AdvisoryId", "Crop_Code", "Category", "Name", "office_Address", "Photo"});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(GenericAdvisory.this);
        new DownloadGenericAdvisory().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(GenericAdvisory.this, GenericAdvisoryDetails.class);
        intent.putExtra("mylist", (Serializable) genericdata);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    private class DownloadGenericAdvisory extends AsyncTask<String, Void, String> {
        private final ProgressDialog dialog = new ProgressDialog(GenericAdvisory.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String strResult = new String();

            try {

                SoapProxy proxy = new SoapProxy(GenericAdvisory.this);
                strResult = proxy.DownloadGenericAdvisoryTables(appUserDetails);
                genericdata= ParseFormDownloadedExperimentData(strResult);

                if (strResult.equalsIgnoreCase("NoUpdates")) {
                } else if (strResult.equalsIgnoreCase("Failure")) {
                    strResult = "InternetconectionProblem";
                } else if (strResult.equalsIgnoreCase("Failed")) {
                    strResult = "UserName does not exists!";
                } else {

                    System.out.println(strResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
                strResult = "Error";
            }

            return strResult;
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void onPostExecute(String strResult) {
            this.dialog.dismiss();

            if (strResult.equals("Error")) {
                Toast.makeText(GenericAdvisory.this, "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("InternetconectionProblem")) {
                Toast.makeText(GenericAdvisory.this, "Respond failure", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("Failed")) {
                Toast.makeText(GenericAdvisory.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("UserName does not exists!")) {
                Toast.makeText(GenericAdvisory.this, "No data for this user", Toast.LENGTH_LONG).show();
            } else if(strResult.contains("Table")){
                AdvisorDataProvider advisorDataProvider;
                for (int i=0;i<genericdata.size(); i++) {

                    advisorDataProvider= new AdvisorDataProvider(genericdata.get(i).getName(), genericdata.get(i).getAdvisorid(),genericdata.get(i).getAddress(), genericdata.get(i).getBitmap());
                    adapter.add(advisorDataProvider);


                }
            }else{
                Toast.makeText(GenericAdvisory.this, "List not created", Toast.LENGTH_LONG).show();
            }

        }

    }

    private List<GenericAdvisoryList> ParseFormDownloadedExperimentData(String jsonStr) {
        List<GenericAdvisoryList> generic = new ArrayList<GenericAdvisoryList>();
        GenericAdvisoryList obj;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray Tables = jsonObj.getJSONArray("Table");

                // looping through Tables
                for (int i = 0; i < Tables.length(); i++) {

                    obj = new GenericAdvisoryList();
                    JSONObject c = Tables.getJSONObject(i);

                    String advisorid = c.getString("AdvisoryId");
                    String cropcode = c.getString("Crop_Code");
                    String category = c.getString("Category");
                    String name = c.getString("Name");
                    String address = c.getString("office_Address");
                    String advice = c.getString("Expert_Advice");
                    String date = c.getString("CreatedDate");

                    String videopath = WebServiceConstants.HOST_URL_PROD + c.getString("Advisory_Video_Path");
                    String audiopath = WebServiceConstants.HOST_URL_PROD + c.getString("Advisory_Audio_Path");
                    String imagepath = WebServiceConstants.HOST_URL_PROD + c.getString("Advisory_Image_Path");

                    String image = c.getString("Photo");

                    byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);

                    obj.setAdvisorid(advisorid);
                    obj.setCropcode(cropcode);
                    obj.setCategory(category);
                    obj.setName(name);
                    obj.setAddress(address);
                    obj.setBitmap(imageAsBytes);
                    obj.setAdvice(advice);
                    obj.setCreatedDate(date);
                    obj.setVideoPath(videopath);
                    obj.setAudioPath(audiopath);
                    obj.setImagePath(imagepath);


                    if (obj != null) {
                        generic.add(obj);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return generic;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(GenericAdvisory.this, MainActivity.class);
        startActivity(i);
    }

}
