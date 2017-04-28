
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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.ferooz.horticulture.pojoclasses.GenericAdvisoryList;
import com.example.ferooz.horticulture.pojoclasses.RoundableImage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GenericAdvisoryDetails extends Activity  {

    RoundableImage roundableImage;

    TextView name, role, office, advice, date;
    ImageView advisorImage, cropImage;
    VideoView videoview;
    Button camera, video, audio;
    MediaController audioView;
 //   SeekBar audioView;
    String videoPath,audioPath,imagePath;
    MediaPlayer mp;
    ProgressDialog pd;
    Bitmap bmp = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_advisory_details);
        name = (TextView) findViewById(R.id.name);
        role = (TextView) findViewById(R.id.Role);
        office = (TextView) findViewById(R.id.Office);
        date = (TextView) findViewById(R.id.date);
        advice = (TextView) findViewById(R.id.advice);
        advisorImage = (ImageView) findViewById(R.id.advisorimage);

        camera = (Button) findViewById(R.id.btncamera);
        video = (Button) findViewById(R.id.btnvideo);
        audio = (Button) findViewById(R.id.btnaudio);

        ArrayList<GenericAdvisoryList> myList = (ArrayList<GenericAdvisoryList>) getIntent().getSerializableExtra("mylist");
        int position = getIntent().getExtras().getInt("position");
        videoPath=myList.get(position).getVideoPath();
        audioPath=myList.get(position).getAudioPath();
        imagePath=myList.get(position).getImagePath();

        String id = myList.get(position).getAdvisorid();
        String cropcode = myList.get(position).getCropcode();
        String adress = myList.get(position).getAddress();
        String advices = myList.get(position).getAdvice();
        String createddate = myList.get(position).getCreatedDate();
        String Name = myList.get(position).getName();
        //   String advisorVideo=myList.get(position).getAdvisory_Video_Path();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(createddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
        String finalDate = timeFormat.format(myDate);



      //advisorImage.setImageBitmap(BitmapFactory.decodeByteArray(myList.get(position).getBitmap(), 0, myList.get(position).getBitmap().length));
        Bitmap bm=BitmapFactory.decodeByteArray(myList.get(position).getBitmap(), 0, myList.get(position).getBitmap().length);

        roundableImage=new RoundableImage(bm);
        advisorImage.setImageDrawable(roundableImage);


        name.setText( Name);
        role.setText(cropcode);
        office.setText(adress);
        date.setText(finalDate);
        advice.setText(advices);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(imagePath), "image/*");
                startActivity(intent);

            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AudioURL = audioPath;

                pd = new ProgressDialog(GenericAdvisoryDetails.this);
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
        });
// For video view

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String VideoURL = videoPath;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(VideoURL), "video/*");
                startActivity(intent);

                //Create a progressbar
                pd = new ProgressDialog(GenericAdvisoryDetails.this);
                // Set progressbar message
                pd.setMessage("Buffering...");


            }

        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(GenericAdvisoryDetails.this, GenericAdvisory.class);
        startActivity(i);
    }



}





