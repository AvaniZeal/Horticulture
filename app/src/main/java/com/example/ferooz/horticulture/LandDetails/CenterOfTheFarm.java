package com.example.ferooz.horticulture.LandDetails;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferooz.horticulture.FarmerDetailsActivity;
import com.example.ferooz.horticulture.R;
import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;
import com.example.ferooz.horticulture.pojoclasses.FarmerDetails;
import com.example.ferooz.horticulture.pojoclasses.LandCropGeoData;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CenterOfTheFarm extends AppCompatActivity implements View.OnClickListener, LocationListener {
    //static int  CropCode;
    Button next;
    static int CropCode;
    static int CornerId;
    boolean isDataSaved;
    TextView btnImage, btnVideo, btnCoordinates;
    Button btnNextCenterOfTheFarm, btnBack;
    File FarmCornerFolder;
    static File mediaFile;
    int imageCount = 0, videoCount = 0, coordinatesCount = 0;
    String imagepath, AppUserId;
    static String videopath;
    TextView tvCamDesc;
    float acc;
    String Coordinates = "";
    private static final int VIDEO_CAPTURE = 101;
    boolean isVideoTaken, isCoordinatesTaken, isImageTaken;
    Uri imageUri, fileUri;
    String firstImageUrl, secondImageurl, thirdImageurl, fourthImageUrl, videourl, coordinates, mediaType;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    LocationManager myLocationManager;
    static AppUserDetails appUserDetails = new AppUserDetails();
    double latitude = 0.00; // for latitude
    double longitude = 0.00; // for longitude
    Location location;
    LocationManager locationManager;
    static String date = new SimpleDateFormat("d-M-yyyy").format(new Date(System.currentTimeMillis()));
    BaseService baseService = new BaseService(this);
    static FarmerDetails farmerDetails;
    TextView tvCoordinates,tvSavedStatus;
    static String UploadXmlFile = "";
    //static CropMonitoringMain cropMonitoringMain=new CropMonitoringMain();

    LandCropGeoData rpcf=new LandCropGeoData();
    private final long FIVE_SECONDS = 100;
   Handler handler= new Handler();
    private String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_of_the_farm);

        tvSavedStatus=(TextView)findViewById(R.id.textViewSavedStatus);
        appUserDetails = (AppUserDetails) getIntent().getSerializableExtra(FarmerDetailsActivity.PAR_KEY);
        Intent intent = getIntent();

        CropCode = intent.getIntExtra("CropCode", 0);
        CornerId = intent.getIntExtra("CornerId", 1);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, CenterOfTheFarm.this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        btnBack=(Button)findViewById(R.id.buttonBackCenterOfTheFarm);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("d_MMMM_yyyy");
        currentDate = month_date.format(cal.getTime());


        isDataSaved=baseService.IsDataSavedForCenterOfFarm(appUserDetails,100,CropCode);
        if(isDataSaved){
            tvSavedStatus.setVisibility(View.VISIBLE);
            tvSavedStatus.setTextColor(Color.parseColor("#ff0000"));
            tvSavedStatus.setText("Data captured already");
            isImageTaken=true;
            isVideoTaken=true;
            isCoordinatesTaken=true;
        }else{
            tvSavedStatus.setVisibility(View.GONE);
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            scheduleSendLocation();
        } else {
            //buildAlertMessageNoGps();
        }

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

        File FarmCornerMediasFolder = new File(HomeCropFolder + "/Farm_Center_Medias");
        if (!FarmCornerMediasFolder.exists()) {
            FarmCornerMediasFolder.mkdirs();
        }

        FarmCornerFolder = new File(FarmCornerMediasFolder + "/Farm_Center_Images");
        if (!FarmCornerFolder.exists()) {
            FarmCornerFolder.mkdirs();
        }
        locationManager = (LocationManager) CenterOfTheFarm.this
                .getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000, 10, CenterOfTheFarm.this);

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
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        tvCamDesc=(TextView)findViewById(R.id.textViewCamDescCenterOfThefarmRiskProfilling);
        btnImage= (TextView) findViewById(R.id.buttonCenterOfTheFarmImageRiskProfilling);
        btnVideo=(TextView) findViewById(R.id.buttonCenterOfTheFarmVideoRiskProfilling);
        btnCoordinates=(TextView) findViewById(R.id.buttonCenterOfTheFarmCoordinateRiskProfilling);
        tvCoordinates=(TextView)findViewById(R.id.textViewCenterOfTheFarmGPSRiskProfilling);

        btnNextCenterOfTheFarm= (Button) findViewById(R.id.buttonNextCenterOfTheFarmRiskProfilling);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(CenterOfTheFarm.this,LandCropDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(PAR_KEY, appUserDetails);
                i.putExtra("CropCode",CropCode);
                //i.putExtra("CornerId",CornerId);
                i.putExtras(mBundle);
                startActivity(i);
            }
        });
        btnNextCenterOfTheFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isImageTaken=true && isVideoTaken==true && isCoordinatesTaken==true){

                    //appUserDetails.setId(String.valueOf(farmerDetails.getAppUserId()));
                    Intent i=new Intent(CenterOfTheFarm.this,LandCropDetails.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(PAR_KEY, appUserDetails);
                    i.putExtra("CropCode",CropCode);
                    //i.putExtra("CornerId",CornerId);
                    i.putExtras(mBundle);
                    startActivity(i);

                }else{
                    Toast.makeText(CenterOfTheFarm.this, "Please take all credentials",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isCoordinatesTaken){
                    if(imageCount<4){
                        mediaType="Image";
                        File imageFile = new File(FarmCornerFolder, "damages_leaves_shoots_bunches_"+(imageCount+1)+ ".jpg");
                        imageUri = Uri.fromFile(imageFile);
                        imagepath=imageFile.toString();//.substring(imageFile.toString().indexOf("CAMS"));
                        Log.e("TAG", imageUri.getPath());
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(cameraIntent, 1);
                    }else{
                        Toast.makeText(CenterOfTheFarm.this, "Images already taken",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CenterOfTheFarm.this, "Please take coordinates",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isCoordinatesTaken){
                    if(!(imageCount<4)){
                        mediaType="video";
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        Uri videoUri = Uri.fromFile(mediaFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                        startActivityForResult(intent, VIDEO_CAPTURE);
                    }else{
                        Toast.makeText(CenterOfTheFarm.this, "Please take images",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CenterOfTheFarm.this, "Please take coordinates",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //gps=new GPSTracker(CenterOfTheFarmActivity.this);
                if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                    if(location!=null){
                        //rpcf.setCoordinates(location.getLatitude()+" "+location.getLongitude());
                        //farmerDetails.setFarm_coordinate_info(location.getLatitude()+" "+location.getLongitude());
                        coordinates=latitude+" "+longitude;
                        // tvCoordinates.setText("GPS Accuracy : "+locationAccuracy+" Mts");
                        Toast.makeText(CenterOfTheFarm.this, "Coordinates captured", Toast.LENGTH_LONG).show();
                        isCoordinatesTaken=true;
                    }else{
                        Toast.makeText(CenterOfTheFarm.this, "Please try again", Toast.LENGTH_LONG).show();
                        scheduleSendLocation();
                    }

                }else{
                    //buildAlertMessageNoGps();
                    Toast.makeText(CenterOfTheFarm.this, "Please turn on GPS Location", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed(){

        Intent i=new Intent(CenterOfTheFarm.this,LandCropDetails.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(PAR_KEY, appUserDetails);
        i.putExtra("CropCode",CropCode);
        //i.putExtra("CornerId",CornerId);
        i.putExtras(mBundle);
        startActivity(i);
    }

    @Override
    public void onLocationChanged(Location location) {

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
    public void onClick(View v) {

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

        File FarmCornerMediasFolder = new File(HomeCropFolder + "/Farm_Center_Medias");
        if (!FarmCornerMediasFolder.exists()) {
            FarmCornerMediasFolder.mkdirs();
        }

        File FarmCornerFolderImages = new File(FarmCornerMediasFolder + "/Farm_Center_Videos");
        if (!FarmCornerFolderImages.exists()) {
            FarmCornerFolderImages.mkdirs();
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
            mediaFile = new File(FarmCornerFolderImages.getPath(),"Observation_VID_" +
                    "Patch_"+ "1"+ ".mp4");
            videopath=mediaFile.toString();//.substring(mediaFile.toString().indexOf("CAMS"));
            //  =mediaFile.toString();


        } else {
            return null;
        }

        return mediaFile;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FileOutputStream fileOutputStream = null;
        if(mediaType.equalsIgnoreCase("image")){
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

                if (data != null && data.getData() != null) {
                    imageUri = data.getData();
                    Toast.makeText(CenterOfTheFarm.this, "Image not taken", Toast.LENGTH_LONG).show();
                }else{
                    imageCount=imageCount+1;
                    if(imageCount==1){
                        firstImageUrl=imagepath;
                    }else if(imageCount==2){
                        secondImageurl=imagepath;
                    }else if(imageCount==3){
                        thirdImageurl=imagepath;
                    }else if(imageCount==4){
                        fourthImageUrl=imagepath;
                        isImageTaken=true;
                    }
                    tvCamDesc.setText("One long shot photographs ever four direction. Images Left : "+(4-imageCount));
                }
                try {
                    Bitmap bitmap = decodeUri(imageUri);
                    if (bitmap != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                        byte[] b = baos.toByteArray();
                        bitmap = StoreByteImage(b);
                    }


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } /*catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            }
        }else{
            if (requestCode == VIDEO_CAPTURE) {
                if (resultCode == RESULT_OK) {
					    /* Toast.makeText(this, "Video saved to:\n" +
					               data.getData(), Toast.LENGTH_LONG).show();*/
                   // rpcf.setFarmer_Id(String.valueOf(riskProfilling.getFarmer_Id()));
                    //rpcf.setRiskProfillingId(String.valueOf(riskProfilling.getId()));
                    //rpcf.setURN_No(riskProfilling.getURN_No());
                    rpcf.setUser_Id(appUserDetails.getUserID());
                    rpcf.setCropCode(CropCode);
                    rpcf.setCorner_ID((100));
                    rpcf.setCornerType(2);
                    rpcf.setFirst_image_url(firstImageUrl);
                    rpcf.setSecond_image_url(secondImageurl);
                    rpcf.setThird_image_url(thirdImageurl);
                    rpcf.setFourth_image_url(fourthImageUrl);
                    rpcf.setCorner_video_url(videopath);
                    rpcf.setCoordinates(coordinates);
                    rpcf.setLandId("1");
                    rpcf.setUploadedStatus("N");

                    boolean isdataSaved=baseService.IsCenterFarmDataSaved(appUserDetails,rpcf);
                    if(isdataSaved){
                            baseService.updateCenterOfTheFarmData(appUserDetails,rpcf);
                    }else{
                        baseService.insertIntoCenterFarmData(appUserDetails,rpcf);
                    }
                   // baseService.insertIntoRiskProfillingCenterOfTheFarm(rpcf);

                    isVideoTaken=true;
                    // baseService.insertIntoMonitoringGeoData(farmerDetails, mgd);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Video recording cancelled.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Failed to record video",
                            Toast.LENGTH_LONG).show();
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
}
