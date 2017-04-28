package com.example.ferooz.horticulture;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ferooz.horticulture.database.BaseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class LanguageSelectionActivity extends AppCompatActivity {

BaseService baseService=new BaseService(this);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                /*case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;*/
                case R.id.navigation_dashboard:

                    Intent in=new Intent(LanguageSelectionActivity.this,WelcomeActivity.class);
                    startActivity(in);

                    return true;
                /*case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }

    };

    String[] countries = new String[] {
            "English",
            "Hindi",
            "Kannada"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        baseService.openDataBase();

        copyDatabaseToSdcard();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        ListView listView = (ListView) findViewById(R.id.listview);

        // Instantiating array adapter to populate the listView
        // The layout android.R.layout.simple_list_item_single_choice creates radio button for each listview item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,countries);
        listView.setAdapter(adapter);
    }

    private void copyDatabaseToSdcard(){
		try {
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        File dbFolder = new File(sd + "/CAMS_DB");
        if(!dbFolder.exists()){
            dbFolder.mkdirs();
        }
        dbFolder = new File(dbFolder.getPath()+"/DATA_BASE");
        if(!dbFolder.exists()){
            dbFolder.mkdirs();
        }
        File	backupDBFolder = new File(dbFolder.getPath()+"/DatabaseBackup");
        if(!backupDBFolder.exists()){
            backupDBFolder.mkdirs();
        }
        dbFolder = new File(dbFolder.getPath()+"/Database");
        if(!dbFolder.exists()){
            dbFolder.mkdirs();
        }

        //Toast.makeText(UsermenuActivity.this, "Latest Db Copied to sddcard", Toast.LENGTH_LONG).show();
        if (sd.canWrite()) {
            String currentDBPath = "//data//com.example.ferooz.horticulture//databases//cams.db";
            String backupDBPath = "//CAMS_DB//DATA_BASE//DatabaseBackup//cams.db";
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(sd, backupDBPath);

            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        }
    } catch(Exception e) {
        e.printStackTrace();
    }


}}
