package com.example.ferooz.horticulture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ferooz.horticulture.LandDetails.CustomizeList;
import com.example.ferooz.horticulture.database.BaseService;

import java.util.ArrayList;
import java.util.List;

public class Questionarie extends AppCompatActivity {

    BaseService baseService=new BaseService(this);

    List<String> lstSeason=new ArrayList<String>();

    ListView listView;

    CustomizeList customizeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionarie);


        listView= (ListView) findViewById(R.id.list_view);


        lstSeason.add("1 time a day");
        lstSeason.add("2 times a day");
        lstSeason.add("3 times a day");
        lstSeason.add("4 times a day and sometime more than that");

        customizeList = new CustomizeList(this, lstSeason);
        listView.setAdapter(customizeList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                view.setSelected(true);


            }
        });
       listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               view.setSelected(true);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });





    }

}
