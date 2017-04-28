package com.example.ferooz.horticulture;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ferooz.horticulture.pojoclasses.ViewAdvisory;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewAdvisoryDetails extends Activity implements View.OnClickListener {


    TextView queryId,catagory,crop,querytext,advicetext;
    ImageView advisorImage, imageview;
    VideoView videoview;
    Button btnCamera, btnAudio, btnVideo;
MediaController audioView;

    String videoPath,audioPath,imagePath;
    MediaPlayer mp;
    ProgressDialog pd;
    Bitmap bmp = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_advisory_details);
        queryId = (TextView) findViewById(R.id.QueryId);
        catagory = (TextView) findViewById(R.id.Catagory);
        querytext= (TextView) findViewById(R.id.query);

        advicetext= (TextView) findViewById(R.id.advice);
        btnCamera = (Button) findViewById(R.id.btncamera);
        btnAudio = (Button) findViewById(R.id.btnaudio);
        btnVideo= (Button) findViewById(R.id.btnvideo);

        ArrayList<ViewAdvisory> myList = (ArrayList<ViewAdvisory>) getIntent().getSerializableExtra("mylist");
        int position = getIntent().getExtras().getInt("position");
        videoPath=myList.get(position).getAdvisoryVideoPath();
        audioPath=myList.get(position).getAdvisoryAudioPath();
        imagePath=myList.get(position).getAdvisoryImagePath();

        String id = myList.get(position).getQueryId();
        String queryCategory = myList.get(position).getQueryCategory();
        String query=myList.get(position).getFarmerQuery();
        String advice=myList.get(position).getExpertAdvice();
        String createddate=myList.get(position).getCreatedDate();


        queryId.setText(id);
        catagory.setText(queryCategory);
        querytext.setText(query);
        advicetext.setText(advice);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(createddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
        String finalDate = timeFormat.format(myDate);

        btnAudio.setOnClickListener(this);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(imagePath), "image/*");
                startActivity(intent);
            }
        });


        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String VideoURL = videoPath;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(VideoURL), "video/*");
                startActivity(intent);

                //Create a progressbar
                pd = new ProgressDialog(ViewAdvisoryDetails.this);
                // Set progressbar message
                pd.setMessage("Buffering...");


            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(ViewAdvisoryDetails.this, ExpertActivity.class);
        i.putExtra("viewpager_position", 1);
        startActivity(i);
    }


    @Override
    public void onClick(View v) {

        String AudioURL = audioPath;

        pd = new ProgressDialog(ViewAdvisoryDetails.this);
        // Set progressbar message
        pd.setMessage("Buffering...");
        pd.setCancelable(false);
        pd.show();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(AudioURL), "audio/*");
        startActivity(intent);
        pd.dismiss();

    }








}
