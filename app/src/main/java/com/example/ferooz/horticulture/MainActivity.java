package com.example.ferooz.horticulture;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ferooz.horticulture.LandDetails.LandCropDetails;
import com.example.ferooz.horticulture.SeasonDetails.SeasonWiseQuestions;
import com.example.ferooz.horticulture.pojoclasses.AppUserDetails;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    public static AppUserDetails appUserDetails;
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    public  static int CropCode=0;



    String[] fileNmae={"Loss_Master_Magnitude","Loss_Master_Observation_Media","Loss_Master_Observations","Loss_Master_Peril",
            "Loss_Master_RiskType","Loss_Master_Stage","Loss_Master_Stage_Period","Master_CropMonitoringType",
            "question_structure", "Options","variety"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        appUserDetails = (AppUserDetails)getIntent().getSerializableExtra(MainActivity.PAR_KEY);
        Intent intent=getIntent();
        CropCode=intent.getIntExtra("CropCode",0);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

         /* try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Album album = albumList.get(position);
                switch(position) {
                    case 0:;
                        break;
                    case 1:   Intent in3=new Intent(getApplicationContext(),Questionarie.class);
                        startActivity(in3);;

                        break;
                    case 2:  Intent in1=new Intent(getApplicationContext(),ExpertActivity.class);
                        startActivity(in1);
                        break;
                    case 3: ;
                        Intent in11= new Intent(getApplicationContext(), GenericAdvisory.class);
                        startActivity(in11);
                        break;
                    case 4:
                        Intent in12= new Intent(getApplicationContext(), LandCropDetails.class);
                        Bundle mBundle = new Bundle();
                       mBundle.putSerializable(PAR_KEY, appUserDetails);
                        in12.putExtras(mBundle);
                        in12.putExtra("CropCode",CropCode);
                        startActivity(in12);
                        break;


                    case 7:
                        Intent in17= new Intent(getApplicationContext(), SeasonWiseQuestions.class);
                        Bundle mBundleSeason = new Bundle();
                        mBundleSeason.putSerializable(PAR_KEY, appUserDetails);
                        in17.putExtras(mBundleSeason);
                        in17.putExtra("CropCode",CropCode);
                        startActivity(in17);
                        break;




                    default: Intent in=new Intent(getApplicationContext(),Login.class);
                        //in.putExtra("arti",txts.getText().toString());
                        startActivity(in);
                        Toast.makeText(getApplicationContext(), album.getName() + "is selected!", Toast.LENGTH_SHORT).show();
                        break;

                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) Toast.makeText(getApplicationContext(),"Selected Settings Menu",Toast.LENGTH_LONG).show();

      /* MainActivity.AsyncTaskRunner runner = new MainActivity.AsyncTaskRunner();
        String sleepTime = "5";
        runner.execute();
*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Handle the camera action
        } else if (id == R.id.Changelanguage) {

        } else if (id == R.id.LandDetails) {

        } else if (id == R.id.About) {

        } else if (id == R.id.Contact) {

        } else if (id == R.id.Contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;




    }

    @Override
    public void onClick(View v) {

    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8
              /*  R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11*/
        };

        Album a = new Album("Plant Protection", 13, covers[0]);
        albumList.add(a);

        a = new Album("Crop Advisory", 8, covers[1]);
        albumList.add(a);

        a = new Album("Ask The Expert", 11, covers[2]);
        albumList.add(a);

        a = new Album("Advisor", 12, covers[3]);
        albumList.add(a);

        a = new Album("Land Details", 14, covers[4]);
        albumList.add(a);

        a = new Album("Weather Report", 1, covers[5]);
        albumList.add(a);

        a = new Album("Weather Advisory", 11, covers[6]);
        albumList.add(a);

     a = new Album("Season Question", 14, covers[7]);
        albumList.add(a);

      /*  a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);*/

        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

   /* private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()


            String strResult = new String();
            String[] RiskProfillingWorksTableName={"Risk_Profilling", "Farmer"};
            for(int i=0;i<2;i++)
                try {

                    SoapProxy proxy = new SoapProxy(MainActivity.this);
                    Parser parseResponse=new Parser(MainActivity.this);

                    strResult = proxy.DownloadRiskProfillingDataTables(RiskProfillingWorksTableName[i]);
                    if (strResult.equalsIgnoreCase("NoUpdates")) {

                    } else if (strResult.equalsIgnoreCase("Failure")) {
                        strResult = "InternetconectionProblem";
                    } else if (strResult.equalsIgnoreCase("No data")) {
                        return strResult;
                    } else {
                        parseResponse.ParseXml(strResult,RiskProfillingWorksTableName[i]);
                        // saveXmlFile(strResult,RiskProfillingWorksTableName[i]);
                    }

                    if(strResult.contains("<NewDataSet>")){

                        for(int j=0;j<=fileNmae.length;j++) {

                            strResult = proxy.DownloadMasterTables(j + 1);
                            if (strResult.equalsIgnoreCase("NoUpdates")) {

                            } else if (strResult.equalsIgnoreCase("Failure")) {
                                strResult = "InternetconectionProblem";
                            } else {
                                parseResponse.ParseXml(strResult, fileNmae[j]);
                                //saveXmlFile(strResult,fileNmae[i]);
                            }
                        }

                    }
                    System.out.println(strResult);
                } catch (Exception e) {
                    e.printStackTrace();
                    strResult = "InternetconectionProblem";
                }
            return strResult;



           *//* try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;*//*
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // finalResult.setText(result);
            Toast.makeText(MainActivity.this, "Please take video", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Downloading data");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }
*/

}
