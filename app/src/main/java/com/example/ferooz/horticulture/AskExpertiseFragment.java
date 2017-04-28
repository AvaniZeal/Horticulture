package com.example.ferooz.horticulture;

        import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.ExifInterface;
        import android.media.MediaPlayer;
        import android.media.MediaRecorder;
        import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
        import android.os.SystemClock;
        import android.provider.MediaStore;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.app.Fragment;
        import android.support.v4.content.ContextCompat;
        import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
        import android.widget.Chronometer;
        import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


        import com.example.ferooz.horticulture.database.BaseService;
import com.example.ferooz.horticulture.pojoclasses.ExpertDetails;
import com.example.ferooz.horticulture.webservice.Parser;
import com.example.ferooz.horticulture.webservice.SoapProxy;


        import org.apache.commons.net.ftp.FTP;
        import org.apache.commons.net.ftp.FTPClient;
        import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
        import java.util.Date;
import java.util.List;

        import static android.Manifest.permission.RECORD_AUDIO;
        import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
        import static android.widget.Toast.LENGTH_LONG;


public class AskExpertiseFragment extends Fragment {
    FTPClient conn;
    static String filePathvideo,filepathimage,filepathaudio,fileName,fileStatus;
    EditText editQuery;
    TableLayout tableLayoutReportList, tableReportList;
    ImageView imageCamera, imageVideo, imageMic,imagestop;
    Spinner spinnertitle;
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    Button save,upload;
    BaseService baseservice=new BaseService(getActivity());
    boolean isImageTaken=false;
    int slno=0;
    ExpertDetails obj= new ExpertDetails();
    static File CAMS_Video;
    private static MediaPlayer mediaPlayer;
    public static final int PICTURE_RESULT = 1;
    MediaRecorder mediaRecorder ;
    String Pathone="",MediaType,imagepath,lastslno,audiopath;
    String AudioSavePathInDevice = null;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static Uri imageUri;
    public static final int RequestPermissionCode = 1;
    private static final int CAMERA_REQUEST = 1888;
    static File mediaFile;
    private static final int VIDEO_CAPTURE = 101;
    private DownloadManager.Query query;
    private int pos = 0;
    int videoCount=0;
    private boolean isRecording = false;
    Uri fileUri;
    Chronometer timer;
    static String videopath;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    static String currentTimeStamp = dateFormat.format(new java.util.Date());

    String uploadXmlFile=null;


    public AskExpertiseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ask_expertise, container, false);

        upload= (Button) view.findViewById(R.id.upload);
        editQuery= (EditText)view.findViewById(R.id.edtQuery);
        imageCamera = (ImageView) view.findViewById(R.id.imageView3);
        imageVideo = (ImageView) view.findViewById(R.id.imageView1);
        imageMic= (ImageView) view.findViewById(R.id.imageView2);
        imagestop= (ImageView) view.findViewById(R.id.stoprecording);
        spinnertitle=(Spinner)view.findViewById(R.id.queryspinner);
        tableLayoutReportList=(TableLayout)view.findViewById(R.id.tableLayoutReportList);
        tableReportList=(TableLayout) view.findViewById(R.id.tableReportList);
        save= (Button) view.findViewById(R.id.button1);
        timer= (Chronometer) view.findViewById(R.id.chronometer2);
        Context applicationContext = ExpertActivity.getContextOfApplication();
        applicationContext.getContentResolver();

        lastslno=baseservice.getLastSlNo();


        if(lastslno!=null){
            int last=Integer.parseInt(lastslno);

            int SerialNo=last+1;
            slno=SerialNo;
        }
        else{
            slno=1;
        }
        String [] values =
                {"Query Title","Soil","Leaf","Irrigation Type",};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnertitle.setAdapter(adapter);


        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
                String root = Environment.getExternalStorageDirectory().toString();
                File AppFolder = new File(root + "/AdvisoryMedia/ashwin/"+slno+"/Image");
                if (!AppFolder.exists()) {
                    AppFolder.mkdirs();
                }
                File imageFile = new File(AppFolder, "camsimg"+"dateFormat1"+".jpg");
                imageUri = Uri.fromFile(imageFile);
                imagepath=imageFile.toString();
                //imagepath=imageFile.toString().substring(imageFile.toString().indexOf("CAMS_IMAGES"));
                Log.e("TAG", imageUri.getPath());
                Pathone=imageFile.toString();
                obj.setPhotoLoaction(imagepath);
                MediaType="Image";
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, PICTURE_RESULT);
            }
        });


        imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String root = Environment.getExternalStorageDirectory().toString();
                CAMS_Video = new File(root + "/AdvisoryMedia/ashwin/"+slno+"/Video");
                if (!CAMS_Video.exists()) {
                    CAMS_Video.mkdirs();
                }

                MediaType="video";
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                Uri videoUri = Uri.fromFile(mediaFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(intent, VIDEO_CAPTURE);
            }
        });



        imageMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {{

                String root = Environment.getExternalStorageDirectory().toString();
               // File AppFolder = new File(root + "/CAMS/CAMS_ASKTHEEXPERT/AUDIO");
                File AppFolder = new File(root + "/AdvisoryMedia/ashwin/"+slno+"/Audio");
                if (!AppFolder.exists()) {
                    AppFolder.mkdirs();
                }
                File audiofile = new File(AppFolder, "camsaudio"+"dateFormat1"+".mp3");
                audiopath=audiofile.toString();


               /* String audioFilePath =
                        Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/myaudio.3gp";
*/
                isRecording = true;
                imagestop.setEnabled(true);

                imageMic.setEnabled(false);

                try {
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mediaRecorder.setOutputFile(audiopath);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mediaRecorder.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mediaRecorder.start();
                timer.start();
                Toast.makeText(getActivity(),"Recording started",Toast.LENGTH_LONG).show();




               /* if(checkPermission()) {
                    String root = Environment.getExternalStorageDirectory().toString();
                    File AppFolder = new File(root + "/CAMS/CAMS_ASKTHEEXPERT/AUDIO");
                    if (!AppFolder.exists()) {
                        AppFolder.mkdirs();
                    }

                    AudioSavePathInDevice = AppFolder + "/" +currentTimeStamp+ "AudioRecording.mp3";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                     imageMic.setEnabled(false);
                    imagestop.setEnabled(true);

                    Toast.makeText(getActivity(), "Recording started", Toast.LENGTH_LONG).show();
                }
                else {

                    requestPermission();

                }*/

            }




            }


        });


        imagestop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                imagestop.setEnabled(false);
                imageMic.setEnabled(true);
                obj.setAudioLocation(audiopath);

                if (isRecording)
                {
                    imageMic.setEnabled(true);
                    mediaRecorder.stop();
                    timer.stop();
                    timer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(getActivity(),"Recording stopped",Toast.LENGTH_LONG).show();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording = false;
                } else {
                    mediaPlayer.release();
                    mediaPlayer = null;
                    imageMic.setEnabled(true);
                }




               /* mediaRecorder.stop();
                imagestop.setEnabled(false);

                imageMic.setEnabled(true);

                obj.setAudioLocation(AudioSavePathInDevice);
                Toast.makeText(getActivity(), "Recording Completed", Toast.LENGTH_LONG).show();

                long isInDb=baseservice.IsDataSaved(obj);
                if(isInDb>0) {

                    baseservice.update(obj,slno);
                }
                else{
                    long insert = baseservice.Insert(obj, slno);


            }*/
        }});


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadExpertDetails UploadExpertDetail= new UploadExpertDetails();
                UploadExpertDetail.execute();

            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                obj.setSlNo(slno);
                obj.setQueryTitle(spinnertitle.getSelectedItem().toString());
                obj.setQuery(editQuery.getText().toString());
                obj.setStatus("N");
                //obj.setAudioLocation("cams.mp3");


                long isInDb=baseservice.IsInDb(obj,slno);
                if(isInDb>0) {

                    baseservice.updateExpertDetails(obj);

                }else{
                    long insert = baseservice.Insert(obj);


                    lastslno=baseservice.getLastSlNo();
                    if(lastslno!=null){
                        int last=Integer.parseInt(lastslno);

                        int SerialNo=last+1;
                        slno=SerialNo;
                    }
                    else{
                        slno=1;
                    }


                }

                List<ExpertDetails> Expertdetails = baseservice.getAllNotUploadedData("N");
                createTable(Expertdetails);
                Log.e("Expertdetails", "Expertdetails");



            }
        });

        return view;
    }

    private void requestPermission() {


            ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {

                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {

                        Toast.makeText(getActivity(), "Permission Granted", LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(),"Permission Denied", LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    private void MediaRecorderReady() throws IOException {
        {

            mediaRecorder=new MediaRecorder();

            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);

            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

            mediaRecorder.setOutputFile(AudioSavePathInDevice);
            mediaRecorder.prepare();

        }
    }

    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getActivity(), RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;

    }

    private class UploadExpertDetails extends AsyncTask<String, Void, String> {
        BaseService baseService = new BaseService(getActivity());
        private final ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("uploading..");
            this.dialog.setCancelable(false);
            this.dialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            String strResult = new String();

            try{
                List<ExpertDetails> listExpertDetails=baseService.getAllNotUploadedData("N");
              //  Uploadmedia(listExpertDetails);
                if(listExpertDetails!=null){
                        String data =createJsonForUpload(listExpertDetails);
                    Uploadmedia(listExpertDetails);
                    if(!data.equals("")){
                            SoapProxy proxy=new SoapProxy(getActivity());
                            Parser parseResponse= new Parser(getActivity());
                            strResult=proxy.uploadExpertData(data);
                            if(strResult.equalsIgnoreCase("NoUpdates")){
                            }else if(strResult.equalsIgnoreCase("Failure")){
                                strResult="Response Failed";
                            }else if(strResult.equalsIgnoreCase("Success")){
                                long res=baseService.changeStatusUploadExpertDetails(obj);
//                                long res1=baseService.changeStatusUploadProposalInspectionDetails(obj);
                                Uploadmedia(listExpertDetails);


                                strResult="Success";
                            }else{
                                System.out.println(strResult);
                            }
                        }
                        if(strResult.equals("Success")){
//                            baseService.updateUpdatedStatus(obj);
                        }else if(strResult.equals("Inspection Done")){
//                            baseService.updateUpdatedStatus(obj);
                        }
                    }
                else{
                    strResult = "Xml Not Created";
                }
                //baseService.saveAppUser(appUserDetails);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                strResult = "Error";

            }
            return strResult;
        }

        private String createJsonForUpload(List<ExpertDetails> list) throws JSONException {

            //List<ExpertDetails> listExpertDetails=baseService.getAllUploadedData("N");
            JSONArray jsonArray = new JSONArray();
            for(int i=0;i<list.size();i++){
                JSONObject farmer = new JSONObject();
                try {
                    farmer.put("Query_Category", "1");
                    farmer.put("Query", list.get(i).getQuery());
                    farmer.put("QueryId", list.get(i).getSlNo());
                    String video=list.get(i).getVideoLocation();
                    String audio=list.get(i).getAudioLocation();
                    String image=list.get(i).getPhotoLoaction();


                    if(video!=null) {
                        farmer.put("Video_Path", list.get(i).getVideoLocation().toString().substring(list.get(i).getVideoLocation().toString().indexOf("ashwin")));
                    }else{

                        farmer.put("video_Path","No Video");
                    }

                    if(audio!=null) {
                        farmer.put("Audio_Path", list.get(i).getAudioLocation().toString().substring(list.get(i).getAudioLocation().toString().indexOf("ashwin")));
                    }else{

                        farmer.put("Audio_Path","No Audio");
                    }

                    if(image!=null) {
                        farmer.put("Image_Path", list.get(i).getPhotoLoaction().toString().substring(list.get(i).getPhotoLoaction().toString().indexOf("ashwin")));
                    }else{

                        farmer.put("Image_Path","No Image");
                    }

                    farmer.put("Query_Status", "2");
                    farmer.put("FarmerId","ashwin");
                    farmer.put("CreatedDate", "null");
                    farmer.put("CreatedBy", "mfp");
                    farmer.put("ModifiedDate", "null");
                    farmer.put("ModifiedBy", "pr");
                    farmer.put("LandId", list.get(i).getSlNo());
                    farmer.put("Crop_Code", "1");
                    farmer.put("Sub_Category", "21");
                    }
                    catch (JSONException e){
                    e.printStackTrace();
                }
                jsonArray.put(farmer);

            }
            JSONObject farmerobj = new JSONObject();
            farmerobj.put("Table", jsonArray);

            String jsonStr = farmerobj.toString();

            System.out.println("jsonString: "+jsonStr);

            return jsonStr;

        }




        protected void onPostExecute(String strResult) {
            this.dialog.dismiss();

            if(strResult.equals("Error")) {
                Toast.makeText(getActivity(), "Internet Connection unavailable", LENGTH_LONG).show();
            }else if(strResult.equals("InternetconectionProblem")) {
                Toast.makeText(getActivity(), "Internet Disconnected", LENGTH_LONG).show();
            }else if(strResult.equals("Response Failed")) {
                Toast.makeText(getActivity(), "Response Failed", LENGTH_LONG).show();
            }
            else if(strResult.equals("Inspection Done")) {
                Toast.makeText(getActivity(), "Inspection Already Done and data Uploaded", LENGTH_LONG).show();
            }else if(strResult.equals("Success")) {
                Toast.makeText(getActivity(), "Data uploaded successfully", LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(
                        getActivity()).create();
                alertDialog.setTitle("Note:");
                alertDialog.setMessage(" Upload Details Success.");
                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }else{
                Toast.makeText(getActivity(), "Data Not Uploaded", LENGTH_LONG).show();
            }
        }
    }

    private void Uploadmedia(List<ExpertDetails> listExpertDetails) throws IOException {
        String strResult=new String();

        for(int i=0;i<listExpertDetails.size();i++) {
            filePathvideo=  listExpertDetails.get(i).getVideoLocation();
            filepathimage= listExpertDetails.get(i).getPhotoLoaction();
            filepathaudio= listExpertDetails.get(i).getAudioLocation();
            if(filePathvideo!=null){
            try{
                conn=new FTPClient();
            conn.connect("124.153.106.183");
            if (conn.login("administrator", "Qmax%Infra@0808")) {
               // conn.connect("164.100.133.216",9559);
               // if(conn.login("hsduser", "H$d@4321")){
                conn.enterLocalPassiveMode(); // important!
                conn.setFileType(FTP.BINARY_FILE_TYPE);
                String data = filePathvideo;

                String s = filePathvideo;
                String[] split = s.split("/");
                String queryid = split[6];
                filePathvideo=filePathvideo.toString().substring(filePathvideo.toString().indexOf("/AdvisoryMedia/ashwin/"+queryid+"/Video"));

                File file = new File(filePathvideo);
                String getDirectoryPath = file.getParent();
               // getDirectoryPath = getDirectoryPath.toString().substring(getDirectoryPath.toString().indexOf("CAMS_Video"));
                getDirectoryPath = getDirectoryPath.toString();
                boolean createDir = createDirectory(getDirectoryPath);
                FileInputStream in = new FileInputStream(new File(data));

                boolean result = conn.storeFile("/"+filePathvideo, in);
                in.close();
                if (result) {
                    Log.v("upload result", "succeeded");
                    strResult = "Success";
                } else {

                }

                conn.logout();
                conn.disconnect();
            }
            }catch (Exception e){
                e.printStackTrace();
            }
        }if(filepathimage!=null){
                try{
                    conn=new FTPClient();
                    conn.connect("124.153.106.183");
                    if (conn.login("administrator", "Qmax%Infra@0808")) {
                        // conn.connect("164.100.133.216",9559);
                        // if(conn.login("hsduser", "H$d@4321")){
                        conn.enterLocalPassiveMode(); // important!
                        conn.setFileType(FTP.BINARY_FILE_TYPE);
                        String data = filepathimage;
                        String s = filepathimage;
                        String[] split = s.split("/");
                        String queryid = split[6];
                        filepathimage=filepathimage.toString().substring(filepathimage.toString().indexOf("/AdvisoryMedia/ashwin/"+queryid+"/Image"));

                        File file = new File(filepathimage);
                        String getDirectoryPath = file.getParent();
                        // getDirectoryPath = getDirectoryPath.toString().substring(getDirectoryPath.toString().indexOf("CAMS_Video"));
                        getDirectoryPath = getDirectoryPath.toString();
                        boolean createDir = createDirectory(getDirectoryPath);
                        FileInputStream in = new FileInputStream(new File(data));

                        boolean result = conn.storeFile("/"+filepathimage, in);
                        in.close();
                        if (result) {
                            Log.v("upload result", "succeeded");
                            strResult = "Success";
                        } else {

                        }


                        conn.logout();
                        conn.disconnect();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(filepathaudio!=null){
                try{
                    conn=new FTPClient();
                    conn.connect("124.153.106.183");
                    if (conn.login("administrator", "Qmax%Infra@0808")) {
                        // conn.connect("164.100.133.216",9559);
                        // if(conn.login("hsduser", "H$d@4321")){
                        conn.enterLocalPassiveMode(); // important!
                        conn.setFileType(FTP.BINARY_FILE_TYPE);
                        String data = filepathaudio;
                        String s = filepathaudio;
                        String[] split = s.split("/");
                        String queryid = split[6];

                        filepathaudio=filepathaudio.toString().substring(filepathaudio.toString().indexOf("/AdvisoryMedia/ashwin/"+queryid+"/Audio"));

                        File file = new File(filepathaudio);
                        String getDirectoryPath = file.getParent();
                        // getDirectoryPath = getDirectoryPath.toString().substring(getDirectoryPath.toString().indexOf("CAMS_Video"));
                        getDirectoryPath = getDirectoryPath.toString();
                        boolean createDir = createDirectory(getDirectoryPath);
                        FileInputStream in = new FileInputStream(new File(data));

                        boolean result = conn.storeFile("/"+filepathaudio, in);
                        in.close();
                        if (result) {
                            Log.v("upload result", "succeeded");
                            strResult = "Success";
                        } else {

                        }


                        conn.logout();
                        conn.disconnect();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }



        }

    }

    private boolean createDirectory(String getDirectoryPath) {

        // TODO Auto-generated method stub
        boolean created = false;
        boolean existed = false;

        try {
            existed = conn.changeWorkingDirectory(getDirectoryPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!existed) {

            try {
                String[] pathElements = getDirectoryPath.split("/");
                if (pathElements != null && pathElements.length > 0) {
                    for (String singleDir : pathElements) {
                        boolean existed1 = conn.changeWorkingDirectory(singleDir);
                        if (!existed1) {
                            created = conn.makeDirectory(singleDir);
                            if (created) {
                                System.out.println("CREATED directory: " + singleDir);
                                conn.changeWorkingDirectory(singleDir);
                            } else {
                                System.out.println("COULD NOT create directory: " + singleDir);
                                return false;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return created;

    }

    public static AskExpertiseFragment newInstance(String text) {

        AskExpertiseFragment f = new AskExpertiseFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    private void createTable(List<ExpertDetails> expertdetails) {
        // TODO Auto-generated method stub
        tableLayoutReportList.setVisibility(View.VISIBLE);
        tableReportList.removeAllViews();

        TextView textView11,textView21, textView31,textView41,textView51,textView61,textView71;
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        TableRow rowid1 = (TableRow)inflater1.inflate(R.layout.table_row_expert,null, false);
        textView11=(TextView)rowid1.findViewById(R.id.textView1);
        textView21=(TextView)rowid1.findViewById(R.id.textView2);
        textView31=(TextView)rowid1.findViewById(R.id.textView3);
        textView41=(TextView)rowid1.findViewById(R.id.textView4);
        textView51=(TextView)rowid1.findViewById(R.id.textView5);
        textView61=(TextView)rowid1.findViewById(R.id.textView6);
        textView71=(TextView)rowid1.findViewById(R.id.textView7);


        textView11.setSingleLine(false);
        textView21.setSingleLine(true);
        textView31.setSingleLine(true);
        textView41.setSingleLine(true);
        textView51.setSingleLine(true);
        textView61.setSingleLine(true);
        textView71.setSingleLine(true);


        textView11.setBackgroundColor(Color.BLACK);
        textView21.setBackgroundColor(Color.BLACK);
        textView31.setBackgroundColor(Color.BLACK);
        textView41.setBackgroundColor(Color.BLACK);
        textView51.setBackgroundColor(Color.BLACK);
        textView61.setBackgroundColor(Color.BLACK);
        textView71.setBackgroundColor(Color.BLACK);


        textView11.setText(String.valueOf("Sl No"));
        textView21.setText("Query Title");
        textView31.setText("Query");
        textView41.setText("Photo Loacation");
        textView51.setText("Video Loacation");
        textView61.setText("Audio Loacation");
        textView71.setText("Status");

        //int pr=Integer.parseInt(fcd.getPrice());
        textView11.setTextColor(Color.WHITE);
        textView21.setTextColor(Color.WHITE);
        textView31.setTextColor(Color.WHITE);
        textView41.setTextColor(Color.WHITE);
        textView51.setTextColor(Color.WHITE);
        textView61.setTextColor(Color.WHITE);
        textView71.setTextColor(Color.WHITE);


        tableReportList.addView(rowid1);

        for(int i=0;i<expertdetails.size();i++){

            TextView textView1,textView2, textView3, textView4,textView5,textView6,textView7;
            LayoutInflater inflater = getActivity().getLayoutInflater();
            TableRow rowid = (TableRow)inflater.inflate(R.layout.table_row_data,null, false);

            textView1=(TextView)rowid.findViewById(R.id.textViewOne);
            textView2=(TextView)rowid.findViewById(R.id.textViewTwo);
            textView3=(TextView)rowid.findViewById(R.id.textViewThree);
            textView4=(TextView)rowid.findViewById(R.id.textViewFour);
            textView5=(TextView)rowid.findViewById(R.id.textViewFive);
            textView6=(TextView)rowid.findViewById(R.id.textViewSix);
            textView7=(TextView)rowid.findViewById(R.id.textViewSeven);


            textView1.setSingleLine(false);
            textView2.setSingleLine(true);
            textView3.setSingleLine(true);
            textView4.setSingleLine(true);
            textView5.setSingleLine(true);
            textView6.setSingleLine(true);
            textView7.setSingleLine(true);


            textView1.setBackgroundColor(Color.WHITE);
            textView2.setBackgroundColor(Color.WHITE);
            textView3.setBackgroundColor(Color.WHITE);
            textView4.setBackgroundColor(Color.WHITE);
            textView5.setBackgroundColor(Color.WHITE);
            textView6.setBackgroundColor(Color.WHITE);
            textView7.setBackgroundColor(Color.WHITE);



            textView1.setText(String.valueOf(expertdetails.get(i).getSlNo()));
            textView2.setText(expertdetails.get(i).getQueryTitle());
            textView3.setText(expertdetails.get(i).getQuery());
            textView4.setText(expertdetails.get(i).getPhotoLoaction());
            textView5.setText(expertdetails.get(i).getVideoLocation());
            textView6.setText(expertdetails.get(i).getAudioLocation());
            textView7.setText(expertdetails.get(i).getStatus());


            tableReportList.addView(rowid);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("imageUri", "Cams_Camera");
        FileOutputStream fileOutputStream = null;
        if(MediaType.equalsIgnoreCase("Image")){
            if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK) {

                if (data != null && data.getData() != null) {
                    imageUri = data.getData();
                    obj.setPhotoLoaction(imagepath);
                    //baseService.SaveQuestionnairMediaUrl(rpqm,"1");
                    Toast.makeText(getActivity(), "Image Captured", Toast.LENGTH_SHORT).show();
                }else{
                    isImageTaken=true;

                    Toast.makeText(getActivity(), "Image captured", Toast.LENGTH_SHORT).show();
                    //imageViewshow.setVisibility(View.VISIBLE);

                }
                try {
                    Bitmap bitmap = decodeUri(imageUri);
                    if (bitmap != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                        byte[] b = baos.toByteArray();
                        bitmap = StoreByteImage(b);
                        //imageViewshow.setImageBitmap(bitmap);
                    }
                    //CurrentImage=IncreamentImageCount(CurrentImage);
                    //insertIntoComponentUpdationsPhoto(imageUri.getPath());
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } /*catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            }}else if(MediaType.equalsIgnoreCase("video")){
            if (requestCode == VIDEO_CAPTURE) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getContext(), "Video saved ", Toast.LENGTH_SHORT).show();
                    videoCount=1;
                    obj.setVideoLocation(videopath);
                    // baseService.SaveQuestionnairMediaUrl(rpqm,"2");
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getContext(), "Video recording cancelled.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to record video",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private Bitmap decodeUri (Uri selectedImage) throws FileNotFoundException {
        // TODO Auto-generated method stub
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o);

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
                getActivity().getContentResolver().openInputStream(selectedImage), null, o2);
    }



    public Bitmap StoreByteImage(byte[] imageData) {

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
            capturedImage = timestampItAndSave(rotatedBitmap);

            String ipath = imageUri.getPath().replace("/mnt/sdcard/CAMS_IMAGES-", "");
            String url=ipath;

            fileOutputStream = new FileOutputStream(url);
            BufferedOutputStream bos = new BufferedOutputStream(
                    fileOutputStream, 8129);
            capturedImage.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            capturedImage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            // byte[] b = baos.toByteArray();

            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capturedImage;
    }


    private Bitmap timestampItAndSave(Bitmap toEdit) {
        SimpleDateFormat sdate = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        String dateTime = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SS")
                .format(new Date(System.currentTimeMillis()));
        Bitmap canvasBitmap = toEdit.copy(Bitmap.Config.RGB_565, true);
        Canvas imageCanvas = new Canvas(canvasBitmap);
        Paint imagePaint = new Paint();
        imagePaint.setTextAlign(Align.CENTER);
        imagePaint.setTextSize(12f);
        imagePaint.setStyle(Paint.Style.FILL);
        imagePaint.setColor(Color.YELLOW);

        return canvasBitmap;

    }

    private static Uri getOutputMediaFileUri(int type){

        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type){

        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraVideo");



        // Create the storage directory(MyCameraVideo) if it does not exist
        if (! mediaStorageDir.exists()){

            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
                return null;
            }
        }
        // Create a media file name

        // For unique file name appending current timeStamp with file name
        java.util.Date date= new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());



        if(type == MEDIA_TYPE_VIDEO) {

            // For unique video file name appending current timeStamp with file name

            mediaFile = new File(CAMS_Video, "QueryVideo"+""+".mp4");
            String rootpath=mediaFile.toString();



        } else {
            return null;
        }
        videopath=mediaFile.toString()/*.substring(mediaFile.toString().indexOf("CAMS")*/;
        return mediaFile;
    }


}

