package com.example.ferooz.horticulture;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ferooz.horticulture.pojoclasses.ViewAdvisorDataProvider;
import com.example.ferooz.horticulture.pojoclasses.ViewAdvisory;
import com.example.ferooz.horticulture.webservice.SoapProxy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.ferooz.horticulture.R.id.container;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAdvisoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    ViewAdvisorAdapter adapter;
    List<ViewAdvisory> advisordata = new ArrayList<ViewAdvisory>();
    ArrayList<HashMap<String, String>> advisorList;

    public ViewAdvisoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_advisory, container, false);





        listView = (ListView) v.findViewById(R.id.listview);



        //advisor_names = getResources().getStringArray(R.array.advisor_names);
        //dates = getResources().getStringArray(R.array.advised_dates);

        int i = 0;
        adapter = new ViewAdvisorAdapter(getActivity(),  R.layout.view_row_layout, new String[]{"AdvisoryId", "Crop_Code", "Category", "Name", "office_Address", "Photo"});





        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        new DownloadGenericAdvisory().execute();



        return v;
    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), ViewAdvisoryDetails.class);

        intent.putExtra("mylist", (Serializable) advisordata);
        intent.putExtra("position",position);
        startActivity(intent);

    }
    private class DownloadGenericAdvisory extends AsyncTask<String, Void, String> {
        private final ProgressDialog dialog = new ProgressDialog(getActivity());



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

                SoapProxy proxy = new SoapProxy(getActivity());
                strResult = proxy.DownloadingExpertAdvisory();
                advisordata = ParseFormDownloadedExperimentData(strResult);


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

        @Override
        protected void onPostExecute(String strResult) {
            this.dialog.dismiss();

            if (strResult.equals("Error")) {
                Toast.makeText(getActivity(), "Internet Connection unavailable", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("InternetconectionProblem")) {
                Toast.makeText(getActivity(), "Respond failure", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("Failed")) {
                Toast.makeText(getActivity(), "Internet Disconnected", Toast.LENGTH_LONG).show();
            } else if (strResult.equals("UserName does not exists!")) {
                Toast.makeText(getActivity(), "No data for this user", Toast.LENGTH_LONG).show();
            } else if(strResult.contains("Table")){
                ViewAdvisorDataProvider advisorDataProvider;
                for (int i=0;i<advisordata.size(); i++) {

                    advisorDataProvider= new ViewAdvisorDataProvider(advisordata.get(i).getQueryId(), advisordata.get(i).getQueryCategory(),"Himachal");
                    adapter.add(advisorDataProvider);


                }
            }else{
                Toast.makeText(getActivity(), "List not created", Toast.LENGTH_LONG).show();
            }




        }


        private List<ViewAdvisory> ParseFormDownloadedExperimentData(String jsonStr) {
            List<ViewAdvisory> advisor = new ArrayList<ViewAdvisory>();
            ViewAdvisory obj;
            String querycategory = null;
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray Tables = jsonObj.getJSONArray("Table");

                    // looping through Tables
                    for (int i = 0; i < Tables.length(); i++) {

                        obj=new ViewAdvisory();
                        JSONObject c = Tables.getJSONObject(i);

                        String queryId = c.getString("QueryId");
                        String cropcode = c.getString("Crop_Code");
                        String farmerId = c.getString("FarmerId");
                        String landId = c.getString("LandId");
                        String queryCategory = c.getString("Query_Category");
                        String subCategory = c.getString("Sub_Category");
                        String QueryStatus = c.getString("Query_Status");
                        String expertAdvice = c.getString("Expert_Advice");
                        String advisoryVideoPath = "http://124.153.106.183:1212/CAMS_Media/"+c.getString("Advisory_Video_Path");
                        String advisoryAudioPath ="http://124.153.106.183:1212/CAMS_Media/"+ c.getString("Advisory_Audio_Path");
                        String advisoryImagePath = "http://124.153.106.183:1212/CAMS_Media/"+c.getString("Advisory_Image_Path");
                        String createdDate = c.getString("CreatedDate");
                        String cropExpertId = c.getString("Crop_ExpertId");
                        String farmerRatings = c.getString("FarmerRatings");
                        String createdBy = c.getString("CreatedBy");
                        String modifiedDate = c.getString("ModifiedDate");
                        String modifiedBy = c.getString("ModifiedBy");
                        String farmerQuery=  c.getString("FarmerQuery");

            /*// To change date format
                    String oldFormat= "dd-MMM-yy HH:mm:ss";
                    String newFormat= "dd-MM-yyyy HH:mm";
                    String formatedDate = "";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
                    Date myDate = null;
                    try {
                        myDate = dateFormat.parse(date);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat timeFormat = new SimpleDateFormat(newFormat);
                    formatedDate = timeFormat.format(myDate);

*/

                        obj.setQueryId(queryId);
                        if(queryCategory.equals("1")){
                            querycategory="Irrigation";
                        }
                        if(queryCategory.equals("2")){
                            querycategory="Canopy Management";
                        } if(queryCategory.equals("3")){
                            querycategory="Leaf Management";
                        } if(queryCategory.equals("4")){
                            querycategory="Irrigation";
                        } if(queryCategory.equals("5")){
                            querycategory="Canopy Management";
                        } if(queryCategory.equals("6")){
                            querycategory="Leaf Managment";
                        }
                        obj.setQueryCategory(querycategory);
                        obj.setAdvisoryVideoPath(advisoryVideoPath);
                        obj.setAdvisoryAudioPath(advisoryAudioPath);
                        obj.setAdvisoryImagePath(advisoryImagePath);
                        obj.setCreatedBy(createdBy);
                        obj.setCreatedDate(createdDate);
                        obj.setCropcode(cropcode);
                        obj.setCropExpertId(cropExpertId);
                        obj.setExpertAdvice(expertAdvice);
                        obj.setFarmerId(farmerId);
                        obj.setFarmerRatings(farmerRatings);
                        obj.setLandId(landId);
                        obj.setModifiedBy(modifiedBy);
                        obj.setModifiedDate(modifiedDate);
                        obj.setQueryStatus(QueryStatus);
                        obj.setSubCategory(subCategory);
                        obj.setFarmerQuery(farmerQuery);


                        if(obj!=null){
                            advisor.add(obj);

                        }




                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }


            return advisor;
        }

}


    public static ViewAdvisoryFragment newInstance(String text) {

        ViewAdvisoryFragment f = new ViewAdvisoryFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }




}
