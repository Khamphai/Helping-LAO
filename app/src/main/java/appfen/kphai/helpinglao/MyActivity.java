package appfen.kphai.helpinglao;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyActivity extends FragmentActivity {
    //Explicit

    private InfoTABLE objInfoTABLE;
    private CallTABLE objCallTABLE;
    private LocationTABLE objLocationTABLE;

    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        objInfoTABLE = new InfoTABLE(this);
        objCallTABLE = new CallTABLE(this);
        objLocationTABLE = new LocationTABLE(this);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.title);
        //pagerTabStrip.setDrawFullUnderline(false);

        //SynInfoTABLE
        synInfoTABLE();

        //DeleteAllSQLite
       //deleteAllSQLite();


        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));


    } //onCreate
//
//
//    public void ClickVievAllTopic(View view) {
//
//        Intent objIntent = new Intent(getApplicationContext(), ViewAllTopicInfomation.class);
//        startActivity(objIntent);
//
//    }


    private void deleteAllSQLite() {

        SQLiteDatabase objSQLite = openOrCreateDatabase("lao_helping", MODE_PRIVATE, null);
        objSQLite.delete("info_table", null, null);


    }//DeleteAllSQLite

    private void synInfoTABLE() {

        //Setup New Policy
        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }

        InputStream objInputStream = null;
        String strJSON = "";

        //for Connect Http
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://10.80.28.195/helpinglao/getdata.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

            Log.d("helping", "Daileo");
        } catch (Exception e) {
            Log.d("helping", "Error Connected" + e.toString());
        }



        //create JSON
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(strLine);
            }

            objInputStream.close();
            strJSON = stringBuilder.toString();



            Log.d("helping", "Daileo JSON");
        } catch (Exception e) {
            Log.d("helping", "Error Convert JSON" + toString());
        }


        //Syn to SQLite


        try {

            JSONObject jsonObject = new JSONObject(strJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("information");
            JSONArray jsonArray_call = jsonObject.getJSONArray("callnumber");
            JSONArray jsonArray_location = jsonObject.getJSONArray("hoslocation");



            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objJSONObject = jsonArray.getJSONObject(i);
                String strTopic = objJSONObject.getString("topic");
                String strInfo = objJSONObject.getString("info");
                String strPicurl = objJSONObject.getString("pic_url");

                long insertInfo = objInfoTABLE.AddInfoToSQLite(strTopic, strInfo, strPicurl);

            }

            for (int i = 0; i < jsonArray_call.length(); i++) {
                JSONObject objJSONObject = jsonArray_call.getJSONObject(i);
                String strNAME = objJSONObject.getString("Name");
                String strTEL = objJSONObject.getString("TEL");

                long insertInfo = objCallTABLE.AddCallToSQLite(strNAME, strTEL);
            }

            for (int i = 0; i < jsonArray_location.length(); i++) {
                JSONObject objJSONObject = jsonArray_location.getJSONObject(i);
                String strHosName = objJSONObject.getString("hospital_name");
                String strLat = objJSONObject.getString("lat");
                String strLng = objJSONObject.getString("lng");
                String strTel = objJSONObject.getString("Tel");

                long insertInfo = objLocationTABLE.AddLocationToSQLite(strHosName, strLat, strLng, strTel);

            }

            Log.d("helping", "Completed to String");
        } catch (Exception e) {
            Log.d("helping", "Error for update ==>" + e.toString());

        }//SynToSQLite


    }//synInfoTABLE


    class MyAdapter extends FragmentStatePagerAdapter
    {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = null;
            //Log.d("help", "get Item is called" + i);
            if (i == 0) {
                fragment = new fragmentA();
            }

            if (i == 1) {
                fragment = new fragmentB();
            }

            if (i == 2) {
                fragment = new FragmentC();
            }

            if (i == 3) {
                fragment = new fragmentD();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            //Log.d("help", "get count is called");
            return 4;
        }

         private final String[] titles = {"ການແພດ", "ໂທສຸກເສີນ", "ບອກຕຳແໝ່ງ", "ພວກເຮົາ"};


        @Override
        public CharSequence getPageTitle(int position) {


//            String title = new String();
//
//            if (position == 0) {
//                return "ການແພດ";
//            }
//            if (position == 1) {
//                return "ໂທດ່ວນ";
//            }
//            if (position == 2) {
//                return "Location";
//            }
//            if (position == 3) {
//                return "About";
//            }
            return titles[position];
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


} // Main Class
