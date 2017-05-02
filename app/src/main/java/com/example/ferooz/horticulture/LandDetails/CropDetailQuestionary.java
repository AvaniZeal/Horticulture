package com.example.ferooz.horticulture.LandDetails;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.ferooz.horticulture.MainActivity;
import com.example.ferooz.horticulture.R;
import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.FarmerDetails;
import com.example.ferooz.horticulture.pojoclasses.LandCropQuestionnaireMedias;
import com.example.ferooz.horticulture.pojoclasses.Land_Crop_Questionnaire_Answer;
import com.example.ferooz.horticulture.pojoclasses.Master_Options;
import com.example.ferooz.horticulture.pojoclasses.Master_Questions;
import com.example.ferooz.horticulture.pojoclasses.RiskProfilling;
import com.example.ferooz.horticulture.pojoclasses.RiskProfillingQuestionnaire;
import com.example.ferooz.horticulture.pojoclasses.RiskProfillingQuestionnaireOption;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import android.os.Handler;
import android.widget.Toast;

public class CropDetailQuestionary extends AppCompatActivity implements View.OnClickListener,LocationListener {

    TextView tvParameter, tvCam, tvVideo, tvCoordinates, tvGuide, tvQuestionNumber, tvSavedStatus;
    LinearLayout linearLayoutVideo, linearLayoutImage;
    RadioGroup radioGroupQuestionnaire;
    RadioButton radioButtonSelected;
    Button nextRiskProfillingQuestionnaire, backBtnQuestionnare;
    ImageButton btnVideo, btnImage;
    int questCount=0,i=0,imageCount=0, Number_Of_Questions=0, videoCount=0, Num_Images=0, Num_Videos=0;
    LocationManager myLocationManager;
    double latitude = 0.00; // for latitude
    double longitude = 0.00; // for longitude
    Location location;
    LocationManager locationManager;

    static File FarmQuestionerFolder;
    boolean isfirst=true, savecam1,isVideoTaken=false, IsDataSaved=false;
    static String videopath;
    Uri imageUri;
    private final long FIVE_SECONDS = 100;
    public static final int PICTURE_RESULT = 1;
    float acc;
    static File mediaFile;
    Uri fileUri;
    String currentDate,imagepath,MediaType, AppUserId;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int VIDEO_CAPTURE = 101;
    Handler handler= new Handler();
    FarmerDetails farmerDetails=new FarmerDetails();
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    static String date=new SimpleDateFormat( "d-M-yyyy" ).format(new Date(System.currentTimeMillis()));
    BaseService baseService=new BaseService(this);
    static AppUserDetails appUserDetails;
    static int CropCode;
    static RiskProfilling riskProfilling;
    Land_Crop_Questionnaire_Answer questAns=new Land_Crop_Questionnaire_Answer();
    static Master_Questions quest=new Master_Questions();
    List<Master_Options> lstOption=new ArrayList<Master_Options>();
    //FarmerDetails farmerDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_detail_questionary);

        appUserDetails = (AppUserDetails) getIntent().getSerializableExtra(MainActivity.PAR_KEY);
        Intent intent = getIntent();

        CropCode = intent.getIntExtra("CropCode", 0);

        farmerDetails=baseService.getFarmerName(Integer.parseInt("105"));
        //static RiskProfilling riskProfilling;
        // riskProfilling=baseService.getRiskProfileData("105");
        //farmerDetails = (FarmerDetails)getIntent().getSerializableExtra(GeoFencingaicActivity.PAR_KEY);
        riskProfilling=baseService.getRiskProfileData("105");
        riskProfilling.setCrop("2");
        //AppUserId=String.valueOf(farmerDetails.getAppUserId());

        linearLayoutVideo=(LinearLayout)findViewById(R.id.linearLayoutRiskProfillingVideo);
        linearLayoutImage=(LinearLayout)findViewById(R.id.linearLayoutRiskProfillingImage);
        linearLayoutVideo.setVisibility(View.GONE);
        linearLayoutImage.setVisibility(View.GONE);
        tvParameter=(TextView)findViewById(R.id.textViewParameter);
        tvCam=(TextView)findViewById(R.id.textViewCamDescRiskProfillingQuestionnaire);
        tvVideo=(TextView)findViewById(R.id.textViewVidRiskProfillingQuestionnaire);
        tvGuide=(TextView)findViewById(R.id.textViewGuide);
        tvQuestionNumber=(TextView)findViewById(R.id.textViewQuestionNumber);
        nextRiskProfillingQuestionnaire=(Button)findViewById(R.id.buttonNextRiskProfillingQuestionnaire);
        btnVideo=(ImageButton)findViewById(R.id.buttonRiskProfillingQuestionnaireVideo);
        btnImage=(ImageButton)findViewById(R.id.buttonRiskProfillingQuestionnaireImage);
        radioGroupQuestionnaire=(RadioGroup)findViewById(R.id.radioGroupQuestionnaireOption);
        radioGroupQuestionnaire.clearCheck();
        tvCoordinates=(TextView)findViewById(R.id.textViewGPS);
        tvSavedStatus=(TextView)findViewById(R.id.textViewSavedStatus);
        nextRiskProfillingQuestionnaire.setOnClickListener(this);
        btnVideo.setOnClickListener(this);
        btnImage.setOnClickListener(this);
        backBtnQuestionnare=(Button)findViewById(R.id.buttonBackQuestionnaire);
        backBtnQuestionnare.setOnClickListener(this);
			/*latitude = location.getLatitude();
			longitude = location.getLongitude();*/

       /* if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            scheduleSendLocation();
        }else{
            buildAlertMessageNoGps();
        }*/

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, CropDetailQuestionary.this);
        location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            scheduleSendLocation();
        }else{
            // buildAlertMessageNoGps();
        }
        Number_Of_Questions=baseService.getNuberOfQuestions(String.valueOf(CropCode));
        // if(String.valueOf(CropCode).equalsIgnoreCase("1")){
        Number_Of_Questions=Number_Of_Questions-1;
        // }
        displayQuestions();

    }

    public void scheduleSendLocation() {
        handler.postDelayed(new Runnable() {
            public void run() {
                getAccuracy();          // this method will contain your almost-finished HTTP calls
                handler.postDelayed(this, 3);
            }

        }, (long) 3);
    }

    private void getAccuracy() {
        // TODO Auto-generated method stub

        if(!(acc==0.00)) {
            tvCoordinates.setText("GPS Accuracy : "+acc+" Mts");
        }else{
            if((locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, this);

                if (location != null) {
                    onLocationChanged(location);
                    acc=Math.round(location.getAccuracy());
                    tvCoordinates.setText("GPS Accuracy : "+acc+" Mts");
                }
            }
        }

    }


    private void displayQuestions() {
        // TODO Auto-generated method stub
        IsDataSaved=false;
        questCount=questCount+1;
        radioGroupQuestionnaire.clearCheck();
        ((RadioButton) radioGroupQuestionnaire.getChildAt(0)).setVisibility(View.GONE);
        ((RadioButton) radioGroupQuestionnaire.getChildAt(2)).setVisibility(View.GONE);
        ((RadioButton) radioGroupQuestionnaire.getChildAt(1)).setVisibility(View.GONE);
        ((RadioButton) radioGroupQuestionnaire.getChildAt(3)).setVisibility(View.GONE);

        quest=baseService.getMasterQuestionnaire(String.valueOf(CropCode),questCount);
        if(quest.getImage_count()!=null){

        }
        lstOption=baseService.getOption(quest);
        tvParameter.setText(quest.getQuestion_Desc());

        for(int i=0;i<lstOption.size();i++){
            ((RadioButton) radioGroupQuestionnaire.getChildAt(i)).setText(lstOption.get(i).getOption_Desc());
            ((RadioButton) radioGroupQuestionnaire.getChildAt(i)).setVisibility(View.VISIBLE);

            if(quest.getHelping_Text().equals("")){
                tvGuide.setVisibility(View.GONE);
            }else{
                tvGuide.setVisibility(View.VISIBLE);
                tvGuide.setText("Guide : "+quest.getHelping_Text());
            }
            tvQuestionNumber.setText("Question No : "+quest.getQuestionOrder());

        }
        if(!(String.valueOf(quest.getImage_count()).equals("") || String.valueOf(quest.getImage_count()).equals("null"))){

            if(quest.getImage_count().equalsIgnoreCase("0")){
                linearLayoutImage.setVisibility(View.GONE);
            }
            else{
                linearLayoutImage.setVisibility(View.VISIBLE);
                Num_Images=Integer.parseInt(quest.getImage_count());
            }
        }else{
            linearLayoutImage.setVisibility(View.GONE);
        }

        if((quest.getQuestionId())==9){
            linearLayoutVideo.setVisibility(View.VISIBLE);
            Num_Videos=Integer.parseInt(quest.getVideo_count());
        }else{
            linearLayoutVideo.setVisibility(View.GONE);

        }

        boolean IsAnswerSaved=baseService.IsAnswerSaved(quest,appUserDetails,CropCode);

        if(IsAnswerSaved){
            IsDataSaved=true;
            tvSavedStatus.setText("Data Saved");
        }else{
            IsDataSaved=false;
            tvSavedStatus.setText("Not saved");
        }

        //To get all answers saved
        /*Land_Crop_Questionnaire_Answer savedAnswers=baseService.IsQuestionnaireSaved(quest,appUserDetails,CropCode);*/

    }

    @Override
    public void onClick(View v) {

        String root = Environment.getExternalStorageDirectory().toString();
        File AppFolder = new File(root + "/Land_Crop_Details");
        if (!AppFolder.exists()) {
            AppFolder.mkdirs();
        }
        File HomeFolder = new File(AppFolder + "/"+appUserDetails.getUserID()+"_1");
        if (!HomeFolder.exists()) {
            HomeFolder.mkdirs();
        }

        String cropName="";
        if(CropCode==1){
            cropName="Grape";
        }else{
            cropName="Mango";
        }
        File HomeCropFolder = new File(HomeFolder + "/"+cropName);
        if (!HomeCropFolder.exists()) {
            HomeCropFolder.mkdirs();
        }

        File FarmCornerMediasFolder = new File(HomeCropFolder + "/Farm_Questionnaire_Medias");
        if (!FarmCornerMediasFolder.exists()) {
            FarmCornerMediasFolder.mkdirs();
        }

        FarmQuestionerFolder = new File(FarmCornerMediasFolder + "/Farm_Questionnaire_Images");
        if (!FarmQuestionerFolder.exists()) {
            FarmQuestionerFolder.mkdirs();
        }

        boolean isDateAvailable=CheckAnsForQuest();
        switch (v.getId()) {

            case R.id.buttonNextRiskProfillingQuestionnaire:
                if(!IsDataSaved) {
                    if (isDateAvailable) {
                        if ((imageCount == Num_Images)) {
                            if (videoCount == Num_Videos) {
                                if (questCount < Number_Of_Questions) {
                                    boolean isQuestionSaved=baseService.IsQuestionSaved(questAns);
                                    if(isQuestionSaved){
                                        baseService.updateQuestionAnswer(questAns);
                                    }else{
                                        baseService.saveQuestionnaireAnswer(questAns);
                                    }

                                    imageCount = 0;
                                    videoCount = 0;
                                    Num_Images = 0;
                                    Num_Videos = 0;
                                    displayQuestions();
                                } else {
                                    //AlertMessageQuest();
                                    boolean isQuestionSaved=baseService.IsQuestionSaved(questAns);
                                    if(isQuestionSaved){
                                        baseService.updateQuestionAnswer(questAns);
                                    }else{
                                        baseService.saveQuestionnaireAnswer(questAns);
                                    }

                                    Toast.makeText(CropDetailQuestionary.this, "Questionnaire Completed Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(CropDetailQuestionary.this, LandCropDetails.class);
                                    Bundle mBundle = new Bundle();
                                    mBundle.putSerializable(PAR_KEY, farmerDetails);
                                    i.putExtras(mBundle);
                                    i.putExtra("CropCode", CropCode);
                                    startActivity(i);
                                }
                            } else {
                                Toast.makeText(CropDetailQuestionary.this, "Please take video", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CropDetailQuestionary.this, "Please take enough images", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    if (questCount < Number_Of_Questions) {

                        imageCount = 0;
                        videoCount = 0;
                        Num_Images = 0;
                        Num_Videos = 0;
                        displayQuestions();
                    } else {
                        Toast.makeText(CropDetailQuestionary.this, "Questionnaire Completed", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CropDetailQuestionary.this, LandCropDetails.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable(PAR_KEY, farmerDetails);
                        i.putExtras(mBundle);
                        i.putExtra("CropCode", CropCode);
                        startActivity(i);
                    }
                }
                break;

            case R.id.buttonRiskProfillingQuestionnaireVideo:
                if(isDateAvailable){
                    if((imageCount==Num_Images)){
                        if(videoCount<Num_Videos){
                            MediaType="video";
                            fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                            Uri videoUri = Uri.fromFile(mediaFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                            startActivityForResult(intent, VIDEO_CAPTURE);
                        }else{
                            Toast.makeText(CropDetailQuestionary.this, "Video already captured", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CropDetailQuestionary.this, "Please take enough images", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.buttonRiskProfillingQuestionnaireImage:
                if(isDateAvailable){
                    if(!(quest.getImage_count().equalsIgnoreCase(""))){
                        if(imageCount<Num_Images){
                            MediaType="Image";
                            File imageFile = new File(FarmQuestionerFolder, "Questionnaire_"+quest.getQuestionId()+"_Image_"+(imageCount+1)+".jpg");
                            imageUri = Uri.fromFile(imageFile);
                            imagepath=imageFile.toString();//.substring(imageFile.toString().indexOf("CAMS"));
                            Log.e("TAG", imageUri.getPath());
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            startActivityForResult(cameraIntent, 1);
                        }else{
                            Toast.makeText(CropDetailQuestionary.this, "Images already captured", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.buttonBackQuestionnaire:
                Intent i = new Intent(CropDetailQuestionary.this, LandCropDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(PAR_KEY, appUserDetails);
                i.putExtras(mBundle);
                i.putExtra("CropCode", CropCode);
                startActivity(i);
                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.e("imageUri", "Crop Image");
        LandCropQuestionnaireMedias rpqm=new LandCropQuestionnaireMedias();
        rpqm.setLand_Id(String.valueOf("123"));
        rpqm.setMedia_help_text("image description");
        rpqm.setQuestion_id(String.valueOf(quest.getQuestionId()));



        FileOutputStream fileOutputStream = null;
        if(MediaType.equalsIgnoreCase("Image")){
            if (requestCode == PICTURE_RESULT && resultCode == Activity.RESULT_OK) {

                if (data != null && data.getData() != null) {
                    imageUri = data.getData();
                    Toast.makeText(CropDetailQuestionary.this, "Image not captured", Toast.LENGTH_SHORT).show();
                }else{
                    imageCount=imageCount+1;
                    // camOneDesc.setText("Photographs focusing on damages to leaves, shoots, bunches - "+(4-firstImagesCount)+" images left");
                    rpqm.setMedia_url(imagepath);
                    rpqm.setMediadId(imageCount);
                    rpqm.setUser_Id(appUserDetails.getUserID());
                    rpqm.setCrop_Id(String.valueOf(CropCode));
                    rpqm.setLand_Id(String.valueOf("1"));
                    rpqm.setCoordinates(latitude+","+longitude);
                    rpqm.setUploadedStatus("N");
                    boolean isMediaSaved=baseService.IsMediaSaved(rpqm,"1");
                    if (isMediaSaved){
                        baseService.updateQuestionnaireMedias(rpqm,"1");
                    }else{
                        baseService.SaveQuestionnairMediaUrl(rpqm,"1");
                    }
                }
                try {
                    Bitmap bitmap = decodeUri(imageUri);
                    if (bitmap != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                        byte[] b = baos.toByteArray();
                        bitmap = StoreByteImage(b);
                    }
                    //CurrentImage=IncreamentImageCount(CurrentImage);
                    //insertIntoComponentUpdationsPhoto(imageUri.getPath());
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }else if(MediaType.equalsIgnoreCase("video")){
            if (requestCode == VIDEO_CAPTURE) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Video saved ", Toast.LENGTH_SHORT).show();
                    videoCount=1;
                    rpqm.setCrop_Id(String.valueOf(CropCode));
                    rpqm.setUser_Id(appUserDetails.getUserID());
                    rpqm.setMedia_url(videopath);
                    rpqm.setMediadId(imageCount);
                    rpqm.setUser_Id(appUserDetails.getUserID());
                    rpqm.setCrop_Id(String.valueOf(CropCode));
                    rpqm.setLand_Id(String.valueOf("1"));
                    rpqm.setCoordinates(latitude+","+longitude);
                    rpqm.setUploadedStatus("N");

                    boolean isMediaSaved=baseService.IsMediaSaved(rpqm,"2");
                    if (isMediaSaved){
                        baseService.updateQuestionnaireMedias(rpqm,"2");
                    }else{
                        baseService.SaveQuestionnairMediaUrl(rpqm,"2");
                    }

                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Video recording cancelled.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to record video",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 500;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(
                getContentResolver().openInputStream(selectedImage), null, o2);
    }


    private Bitmap StoreByteImage(byte[] imageData) {

        Bitmap rotatedBitmap = null;
        Bitmap capturedImage = null;
        FileOutputStream fileOutputStream = null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) 0.5;
            Bitmap myImage = BitmapFactory.decodeByteArray(imageData, 0,
                    imageData.length, options);
            Matrix matrix = new Matrix();
            ExifInterface exifReader = null;

            try {
                exifReader = new ExifInterface(imageUri.getPath());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }// Location of your image

            int orientation = exifReader.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            if (orientation == ExifInterface.ORIENTATION_NORMAL) {

                // Do nothing. The original image is fine.
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {

                matrix.postRotate(90);

            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {

                matrix.postRotate(180);

            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {

                matrix.postRotate(270);

            }
            // matrix.postRotate(90);
            // matrix.postRotate();
            // matrix.invert(matrix);
            rotatedBitmap = Bitmap.createBitmap(myImage, 0, 0,
                    myImage.getWidth(), myImage.getHeight(), matrix, true);
            capturedImage =rotatedBitmap;
            // String ipath =
            // imageUri.getPath().replace("/mnt/sdcard/PWMTPhotos/PWMT-", "");
            // String url="/sdcard/PWMTPhotos/"+"PWMT-"+ipath;

            String ipath = imageUri.getPath().replace(
                    root + "/CAMS/FarmerFolder/", "");// for nexus 4
            String url = "/sdcard/CAMS/FarmerFolder/" + "C-" + ipath;// for nexus 4
            fileOutputStream = new FileOutputStream(url);
            BufferedOutputStream bos = new BufferedOutputStream(
                    fileOutputStream, 8129);
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            // byte[] b = baos.toByteArray();

            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotatedBitmap;
    }

    private static Uri getOutputMediaFileUri(int type){

        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type){

        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraVideo");

        String root = Environment.getExternalStorageDirectory().toString();
        File AppFolder = new File(root + "/Land_Crop_Details");
        if (!AppFolder.exists()) {
            AppFolder.mkdirs();
        }
        File HomeFolder = new File(AppFolder + "/"+appUserDetails.getUserID()+"_1");
        if (!HomeFolder.exists()) {
            HomeFolder.mkdirs();
        }

        String cropName="";
        if(CropCode==1){
            cropName="Grape";
        }else{
            cropName="Mango";
        }
        File HomeCropFolder = new File(HomeFolder + "/"+cropName);
        if (!HomeCropFolder.exists()) {
            HomeCropFolder.mkdirs();
        }

        File FarmCornerMediasFolder = new File(HomeCropFolder + "/Farm_Questionnaire_Medias");
        if (!FarmCornerMediasFolder.exists()) {
            FarmCornerMediasFolder.mkdirs();
        }

        File FarmQuestionerFolder = new File(FarmCornerMediasFolder + "/Farm_Questionnaire_Videos");
        if (!FarmQuestionerFolder.exists()) {
            FarmQuestionerFolder.mkdirs();
        }


        // Create the storage directory(MyCameraVideo) if it does not exist
        if (! mediaStorageDir.exists()){

            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
                return null;
            }
        }
        // Create a media file name

        // For unique file name appending current timeStamp with file name
        Date date= new Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());



        if(type == MEDIA_TYPE_VIDEO) {

            // For unique video file name appending current timeStamp with file name
            mediaFile = new File(FarmQuestionerFolder, "Questionnaire_Video_"+quest.getQuestionId()+ ".mp4");
            String rootpath=mediaFile.toString();


        } else {
            return null;
        }
        videopath=mediaFile.toString();//.substring(mediaFile.toString().indexOf("CAMS"));
        return mediaFile;
    }

    private boolean CheckAnsForQuest() {
        // TODO Auto-generated method stub
        if(!(radioGroupQuestionnaire.getCheckedRadioButtonId()==-1)){
            int selectedId=radioGroupQuestionnaire.getCheckedRadioButtonId();
            radioButtonSelected=(RadioButton)findViewById(selectedId);
            String Ans_Text=radioButtonSelected.getText().toString();

            View radioButton = radioGroupQuestionnaire.findViewById(selectedId);
            int index = radioGroupQuestionnaire.indexOfChild(radioButton);
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMMMyyyy_HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());
            questAns.setQuest_id(quest.getQuestionId());
            questAns.setQuestion_order(String.valueOf(quest.getQuestionOrder()));
            questAns.setOption_id(String.valueOf(lstOption.get(index).getOptionId()));
            questAns.setOther("-");
            questAns.setCrop_Id(String.valueOf(CropCode));
            questAns.setUser_Id(appUserDetails.getUserID());
            questAns.setPhoto_lat_lngs(latitude+" "+longitude);
            questAns.setUpload_status("N");
            questAns.setRisk_profilling_id(String.valueOf("1"));
            questAns.setTimestamp(currentDate);

            return true;

        }else{
            Toast.makeText(CropDetailQuestionary.this, "Please select answer", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location loc) {
        this.location=loc;
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();
        tvCoordinates.setText("Lat:"+latitude+" Long:"+longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onBackPressed(){

        Intent i=new Intent(CropDetailQuestionary.this,LandCropDetails.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(PAR_KEY, appUserDetails);
        i.putExtra("CropCode",CropCode);
        //i.putExtra("CornerId",CornerId);
        i.putExtras(mBundle);
        startActivity(i);
    }

}
