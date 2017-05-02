package com.example.ferooz.horticulture.SeasonDetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferooz.horticulture.LandDetails.CustomizeList;
import com.example.ferooz.horticulture.R;
import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.Master_Season;
import com.example.ferooz.horticulture.pojoclasses.Master_SeasonWise_Questions;
import com.example.ferooz.horticulture.pojoclasses.Master_Seasonwise_Response;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SeasonWiseQuestions extends AppCompatActivity implements View.OnClickListener {
    TextView questionText;
    ListView listView;
    Button btnBack, btnNext;
    ImageView ivVideo, ivCamera;
    LinearLayout videoLayout;
    LinearLayout imageLayout;
    BaseService baseService;

    Integer numberOfQuestions;
    Integer seasonID;
    String seasonName;
    Integer month;
    Integer cropId;

    static Integer questionCount = 0;

    Integer selectedOption = -99;
    Integer imageCount = 0;
    Integer videoCount = 0;
    Integer audioCount = 0;
    Integer noOfImages;
    Integer noOfVideos;
    Integer noOfAudios;
    String photo;
    private static final String PAR_KEY = "com.easyinfogeek.objectPass.par";
    private static final int IMAGE_REQUEST = 1888;
    private static final int VIDEO_REQUEST = 200;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private Uri fileUri;
    Master_Seasonwise_Response response;
    Master_SeasonWise_Questions question;
    Master_Season master_season;
    CustomizeList customizeList;
    List<String> lstSeasonOptions = new ArrayList<String>();
    AppUserDetails appUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_wise_qustions);
        appUserDetails = (AppUserDetails) getIntent().getSerializableExtra(PAR_KEY);
        Intent intent = getIntent();

        cropId = intent.getIntExtra("CropCode", 0);

        question = new Master_SeasonWise_Questions();
        response = new Master_Seasonwise_Response();
        master_season = new Master_Season();
        baseService = new BaseService(SeasonWiseQuestions.this);
        getSeason();
        listView = (ListView) findViewById(R.id.list_view);
        questionText = (TextView) findViewById(R.id.tvQuestion);
        btnBack = (Button) findViewById(R.id.btnBackQuestionnaire);
        btnBack.setOnClickListener(this);
        btnNext = (Button) findViewById(R.id.btnNextQuestionnaire);
        btnNext.setOnClickListener(this);
        ivVideo = (ImageView) findViewById(R.id.ivVideo);
        ivVideo.setOnClickListener(this);
        ivCamera = (ImageView) findViewById(R.id.ivCamera);
        ivCamera.setOnClickListener(this);

        videoLayout = (LinearLayout) findViewById(R.id.video);
        imageLayout = (LinearLayout) findViewById(R.id.image);
        lstSeasonOptions.add("Option 1");
        lstSeasonOptions.add("Option 2");
        lstSeasonOptions.add("Option 3");
        lstSeasonOptions.add("Option 4");
        lstSeasonOptions.add("Option 5");
        lstSeasonOptions.add("Option 6");

        customizeList = new CustomizeList(this, lstSeasonOptions);
        listView.setAdapter(customizeList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                selectedOption = i;
            }
        });
        displayQuestion();
    }

    private void displayQuestion() {
        numberOfQuestions = baseService.getNumberOfSeasonQuestions(String.valueOf(seasonID), String.valueOf(cropId));
//        Toast.makeText(this, " numberOfQuestions  " + numberOfQuestions, Toast.LENGTH_SHORT).show();
//        questionText.setText("Q" + (questionCount + 1) + ". First Question khkhkhlkjlkhlkjlkjlkhkjkhkhlkjlkjljlkh" + " ?");
        if (questionCount < numberOfQuestions) {
            question = baseService.getMasterSeasonWiseQuestions(String.valueOf(seasonID), String.valueOf(cropId), questionCount + 1);
            noOfImages = question.getQuestionImageCount();
            noOfVideos = question.getQuestionVideoCount();
            imageCount = 0;
            videoCount = 0;
            if (noOfImages > 0) {
                imageLayout.setVisibility(View.VISIBLE);
            } else {
                imageLayout.setVisibility(View.INVISIBLE);
            }
            if (noOfVideos > 0) {
                videoLayout.setVisibility(View.VISIBLE);
            } else {
                videoLayout.setVisibility(View.INVISIBLE);
            }
            questionText.setText("Q" + (questionCount + 1) + ". " + question.getQuestion() + " ?");

            customizeList.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Please Upload the Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSeason() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(calendar.MONTH) + 1;
        Toast.makeText(this, "Month " + month, Toast.LENGTH_SHORT).show();
        master_season = baseService.getSeason(month);
        seasonID = master_season.getSeasonId();
        seasonName = master_season.getSeasonName_EN();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextQuestionnaire:
                if (selectedOption == -99) {
                    Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if(imageCount < noOfImages){
                        Toast.makeText(this, "Please Capture Required Images", Toast.LENGTH_SHORT).show();

                        return;
                    }
                    if(videoCount < noOfVideos){
                        Toast.makeText(this, "Please Capture Required Videos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(this, "Selected Option :" + selectedOption, Toast.LENGTH_SHORT).show();
                    questionCount++;
                    selectedOption = -99;
                    displayQuestion();

                }
                break;
            case R.id.btnBackQuestionnaire:

                break;
            case R.id.ivCamera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, IMAGE_REQUEST);
                Toast.makeText(this, "Image Button Clicked", Toast.LENGTH_SHORT).show();
                imageCount++;
                break;
            case R.id.ivVideo:
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                // create a file to save the video
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

                // set the image file name
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                // set the video image quality to high
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                // start the Video Capture Intent
                startActivityForResult(intent, VIDEO_REQUEST);
                Toast.makeText(this, "Video Button Clicked", Toast.LENGTH_SHORT).show();
                videoCount++;
                break;
            default:
                break;
        }
    }
    private static Uri getOutputMediaFileUri(int type) {

        return Uri.fromFile(getOutputMediaFile(type));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
//            if (data != null && data.getData() != null) {
                photo = "TestImage" + (questionCount+1) + ".jpg";
                Bitmap photos = (Bitmap) data.getExtras().get("data");
//                createDirectoryAndSaveFile(photos, Environment.getExternalStorageDirectory().toString() + "/DemoImages/", photo);
                createDirectoryAndSaveFile(photos, photo);
//            }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "User Canceled Image Capture", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Image Capture Failed", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == VIDEO_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Video captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Video saved to: " +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
                Toast.makeText(this, "User cancelled the video capture.", Toast.LENGTH_LONG).show();
            } else {
                // Video capture failed, advise user
                Toast.makeText(this, "Video capture failed.", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/DirName");
        if (!direct.exists()) {
            boolean fc = direct.mkdirs();
        }
        File file = new File(new File(Environment.getExternalStorageDirectory() + "/DirName/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static File getOutputMediaFile(int type) {

        // Check that the SDCard is mounted
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "MyCameraVideo");
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), File.separator + "DirName");
//         Create the storage directory(MyCameraVideo) if it does not exist
        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {
                Log.d("DirName", "Failed to create directory MyCameraVideo.");
                return null;
            }
        }

        // Create a media file name

        // For unique file name appending current timeStamp with file name
        File mediaFile;
        if (type == MEDIA_TYPE_VIDEO) {
            // For unique video file name appending current timeStamp with file name
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_Demo_" + (questionCount+1) + ".mp4");

        } else {
            return null;
        }

        return mediaFile;
    }

}