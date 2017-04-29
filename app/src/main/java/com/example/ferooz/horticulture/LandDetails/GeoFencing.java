package com.example.ferooz.horticulture.LandDetails;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
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

//import static com.example.admin.myapplication.R.id.imageView;

public class GeoFencing extends AppCompatActivity implements View.OnClickListener, LocationListener {

    int imageCount = 0, videoCount = 0, coordinatesCount = 0;
    static FarmerDetails farmerDetails;
    String Coordinates = "";
    String imagepath;
    static String videopath;
    BaseService baseService = new BaseService(this);
    static String date = new SimpleDateFormat("d-M-yyyy").format(new Date(System.currentTimeMillis()));
    String firstimageUri, secondImageUri, cornerVideoUri, mediatype, thirdImageUri;
    public static final int MEDIA_TYPE_VIDEO = 2;
    static File FarmCornerFolder;
    Uri imageUri, fileUri;
    Button btnNext, btnBack;
    TextView tvCornerNumber, tvCoordinates, tvCamdesc, tvSavedStatus;
    LocationManager myLocationManager;
    double latitude = 0.00; // for latitude
    double longitude = 0.00; // for longitude
    Location location;
    LocationManager locationManager;
    Handler handler = new Handler();
    boolean isImageSave = false;
    float acc;
    boolean isDataSaved;
    TextView camera, video, coordinate;
    private static final int CAMERA_REQUEST = 1888;
    static File mediaFile;
    private static final int VIDEO_CAPTURE = 101;
    static AppUserDetails appUserDetails;
    static int CropCode;
    static int CornerId;
    public final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    LandCropGeoData rpgd = new LandCropGeoData();
    private final long FIVE_SECONDS = 100;
    private String currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_fencing);

        appUserDetails = (AppUserDetails) getIntent().getSerializableExtra(FarmerDetailsActivity.PAR_KEY);
        Intent intent = getIntent();

        CropCode = intent.getIntExtra("CropCode", 0);
        CornerId = intent.getIntExtra("CornerId", 1);

        tvCornerNumber=(TextView)findViewById(R.id.textViewMonitoringCornerNumber);
        btnNext = (Button) findViewById(R.id.buttonNextRiskProfillingGeoFencing);
       // btnBack=(Button)findViewById(R.id.buttonBackGeoData);
        camera = (TextView) findViewById(R.id.buttonRiskProfillingCornerImage);
        video = (TextView) findViewById(R.id.buttonRiskProfillingCornerVideo);
        coordinate=(TextView) findViewById(R.id.buttonCoordinate);
        tvCoordinates = (TextView) findViewById(R.id.textViewGPS);
        tvCamdesc=(TextView)findViewById(R.id.textViewCamDescMonitoring);
        tvCoordinates.setText("Coordinates");
        tvCornerNumber.setText("Corner Id "+CornerId);
        tvSavedStatus=(TextView)findViewById(R.id.textViewSavedStatus);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, GeoFencing.this);
        location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            scheduleSendLocation();
        }else{
           // buildAlertMessageNoGps();
        }

        Calendar cal= Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("d_MMMM_yyyy");
        currentDate = month_date.format(cal.getTime());

        isDataSaved=baseService.IsDataSavedForCorner(appUserDetails,CornerId,CropCode);
        if(isDataSaved){
            tvSavedStatus.setVisibility(View.VISIBLE);
            tvSavedStatus.setTextColor(Color.parseColor("#ff0000"));
            tvSavedStatus.setText("Data captured already");
            imageCount=3;
            videoCount=1;
            coordinatesCount=1;
        }else{
            tvSavedStatus.setVisibility(View.GONE);
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

        File FarmCornerMediasFolder = new File(HomeCropFolder + "/Farm_Corner_Medias");
        if (!FarmCornerMediasFolder.exists()) {
            FarmCornerMediasFolder.mkdirs();
        }

        File FarmCornerFolderImages = new File(FarmCornerMediasFolder + "/Farm_Corner_Images");
        if (!FarmCornerFolderImages.exists()) {
            FarmCornerFolderImages.mkdirs();
        }

        FarmCornerFolder = new File(FarmCornerFolderImages + "/Farm_Corner_"+CornerId);
        if (!FarmCornerFolder.exists()) {
            FarmCornerFolder.mkdirs();
        }

        /*btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GeoFencing.this,LandCropDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(PAR_KEY, appUserDetails);
                i.putExtra("CropCode",CropCode);
                //i.putExtra("CornerId",CornerId);
                i.putExtras(mBundle);
                startActivity(i);
            }
        });*/
        coordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                    if(location!=null){
                        Coordinates=latitude+" "+longitude;
                        //farmerDetails.setFarm_coordinate_info(location.getLatitude()+" "+location.getLongitude());
                        //locationAccuracy=location.getAccuracy();
                        // tvCoordinates.setText("GPS Accuracy : "+locationAccuracy+" Mts");
                        Toast.makeText(GeoFencing.this, "Coordinates captured", Toast.LENGTH_LONG).show();
                        coordinatesCount=1;
                    }else{
                        Toast.makeText(GeoFencing.this, "Please try again", Toast.LENGTH_LONG).show();
                        scheduleSendLocation();
                    }

                }else{
                    //buildAlertMessageNoGps();
                    Toast.makeText(GeoFencing.this, "Please turn on GPS location", Toast.LENGTH_LONG).show();
                }
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(coordinatesCount==1){
                    if((imageCount<3)){
                        mediatype="image";
//                        filePath = filePath.toString().substring(filePath.toString().indexOf("Land_Crop_Details"));
                        File imageFile = new File(FarmCornerFolder, "Corner_Image_"+(imageCount+1)+ ".jpg");
                        imageUri = Uri.fromFile(imageFile);
                        //imagepath=imageFile.toString().substring(imageFile.toString().indexOf("Land_Crop_Details"));
                        imagepath=imageFile.toString();//.substring(imageFile.toString().indexOf("Land_Crop_Details"));
                        Log.e("TAG", imageUri.getPath());
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(cameraIntent, 1);
                    }else{
                        Toast.makeText(getApplicationContext(), "Images already taken", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please take coordinates", Toast.LENGTH_SHORT).show();
                }

            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(coordinatesCount==1){
                    if(imageCount==3){
                        mediatype="video";
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        Uri videoUri = Uri.fromFile(mediaFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                        startActivityForResult(intent, VIDEO_CAPTURE);

                    }else{
                        Toast.makeText(getApplicationContext(), "Please take images", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please take coordinates", Toast.LENGTH_SHORT).show();
                }
                File mediaFile =
                        new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/myvideo.mp4");
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                Uri videoUri = Uri.fromFile(mediaFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(intent, VIDEO_CAPTURE);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isDataSaved) {
                    if (imageCount == 3 && videoCount == 1 && coordinatesCount == 1) {
                        if (!(CornerId < 3)) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(GeoFencing.this);
                            alertDialog.setTitle("Confirm..!");
                            alertDialog.setMessage("Do you want to add one more Corner...?");

                            alertDialog.setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int n) {
                                            CornerId = CornerId + 1;
                                            Intent i = new Intent(GeoFencing.this, GeoFencing.class);
                                            Bundle mBundle = new Bundle();
                                            mBundle.putSerializable(PAR_KEY, appUserDetails);
                                            i.putExtras(mBundle);
                                            i.putExtra("CropCode", CropCode);
                                            i.putExtra("CornerId", CornerId);
                                            startActivity(i);
                                        }
                                    });
                            alertDialog.setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int n) {
                                            //if(!(CornerCount<3)){
                                            Intent i = new Intent(GeoFencing.this, LandCropDetails.class);
                                            Bundle mBundle = new Bundle();
                                            mBundle.putSerializable(PAR_KEY, appUserDetails);
                                            i.putExtra("CropCode", CropCode);
                                            //i.putExtra("CornerId",CornerId);
                                            i.putExtras(mBundle);
                                            startActivity(i);
                                            dialog.cancel();
							   /* }else{
							    	Toast.makeText(MonitoringGeoFencingActivity.this, "Please take at least three corners", Toast.LENGTH_LONG).show();
							    }*/
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            CornerId = CornerId + 1;
                            Intent i1 = new Intent(GeoFencing.this, GeoFencing.class);
                            Bundle mBundle1 = new Bundle();
                            mBundle1.putSerializable(PAR_KEY, appUserDetails);
                            i1.putExtras(mBundle1);
                            i1.putExtra("CropCode", CropCode);
                            i1.putExtra("CornerId", CornerId);
                            startActivity(i1);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please captured required inputs", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    CornerId = CornerId + 1;
                    Intent i1 = new Intent(GeoFencing.this, GeoFencing.class);
                    Bundle mBundle1 = new Bundle();
                    mBundle1.putSerializable(PAR_KEY, appUserDetails);
                    i1.putExtras(mBundle1);
                    i1.putExtra("CropCode", CropCode);
                    i1.putExtra("CornerId", CornerId);
                    startActivity(i1);
                }
            }
        });
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

        File FarmCornerMediasFolder = new File(HomeCropFolder + "/Farm_Corner_Medias");
        if (!FarmCornerMediasFolder.exists()) {
            FarmCornerMediasFolder.mkdirs();
        }

        File FarmCornerFolderImages = new File(FarmCornerMediasFolder + "/Farm_Corner_Videos");
        if (!FarmCornerFolderImages.exists()) {
            FarmCornerFolderImages.mkdirs();
        }

        File FarmCornerFolderVideos = new File(FarmCornerFolderImages + "/Farm_Corner_"+CornerId);
        if (!FarmCornerFolderVideos.exists()) {
            FarmCornerFolderVideos.mkdirs();
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
            mediaFile = new File(FarmCornerFolderVideos.getPath(),"Corner_"+CornerId+"_video"+".mp4");
            String rootpath=mediaFile.toString();


        } else {
            return null;
        }
        videopath=mediaFile.toString();//.substring(mediaFile.toString().indexOf("Land_Crop_Details"));
        return mediaFile;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FileOutputStream fileOutputStream = null;
        if(mediatype.equalsIgnoreCase("image")){
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

                if (data != null && data.getData() != null) {
                    imageUri = data.getData();
                    Toast.makeText(GeoFencing.this, "Image not taken", Toast.LENGTH_LONG).show();
                }else{
                    imageCount=imageCount+1;
                    if(imageCount==1){
                        firstimageUri=imagepath;
                    }else if(imageCount==2){
                        secondImageUri=imagepath;
                    }else if(imageCount==3){
                        thirdImageUri=imagepath;
                    }tvCamdesc.setText("3 photographs one below and one above the platform. Image left : "+(3-imageCount));

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
                }/* catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            }
        }else{
            if (requestCode == VIDEO_CAPTURE) {
                if (resultCode == RESULT_OK) {
					    /* Toast.makeText(this, "Video saved to:\n" +
					               data.getData(), Toast.LENGTH_LONG).show();*/
                    rpgd.setFirst_image_url(firstimageUri);
                    rpgd.setSecond_image_url(secondImageUri);
                    rpgd.setThird_image_url(thirdImageUri);
                    rpgd.setCorner_video_url(videopath);
                    rpgd.setCorner_ID(CornerId);
                    rpgd.setCornerType(1);
                    rpgd.setCoordinates(Coordinates);
                    rpgd.setCropCode(CropCode);
                    rpgd.setFourth_image_url("Nill");
                    rpgd.setLandId("1");
                    rpgd.setUploadedStatus("N");

                    boolean isCornerSaved=baseService.IsCornerDataSaved(appUserDetails,rpgd);
                    if(isCornerSaved){
                         baseService.updateLandCropGeoData(appUserDetails, rpgd);
                    }else{
                        baseService.insertIntoLandCropGeoData(appUserDetails, rpgd);
                    }



                    videoCount=1;
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

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }*/

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


   /* if ( locationManager. ) {
        scheduleSendLocation();
    }else{
        buildAlertMessageNoGps();
    }*/


    @Override
    public void onLocationChanged(Location loc) {

        this.location=loc;
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();
        tvCoordinates.setText("Lat:"+latitude+" Long:"+longitude);
        //Toast.makeText(getBaseContext(), "Lat:"+latitude+" Long:"+longitude, Toast.LENGTH_SHORT).show();
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
                    root + "/Land_Crop_Details/FarmerFolder/", "");// for nexus 4
            String url = "/sdcard/Land_Crop_Details/FarmerFolder/" + "C-" + ipath;// for nexus 4
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

    @Override
    public void onBackPressed(){

        Intent i=new Intent(GeoFencing.this,LandCropDetails.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(PAR_KEY, appUserDetails);
        i.putExtra("CropCode",CropCode);
        //i.putExtra("CornerId",CornerId);
        i.putExtras(mBundle);
        startActivity(i);
    }

}










