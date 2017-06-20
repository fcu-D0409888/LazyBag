package com.example.mac.mrje;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton search;
    Spinner spinnerArea, spinnerKind;

    private ActivityArrayAdapter adapter = null;

    private static final int LIST_ACTIVITIES = 1;

    private List<ActivityItem> mlsActivities;

    String [] area = {"全部", "北部", "中部", "南部", "東部" ,"離島"};
    String [] kind = {"全部", "藝文活動", "景點"};


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_ACTIVITIES: {
                    List<ActivityItem> activities = (List<ActivityItem>)msg.obj;
                    refreshActivityList(activities);
                    break;
                }
            }
        }
    };

    private void refreshActivityList(List<ActivityItem> activities) {
        adapter.clear();
        mlsActivities = activities;
        adapter.addAll(mlsActivities);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search = (ImageButton)findViewById(R.id.imgbtn_search);
        search.setOnClickListener(click);

        spinnerArea = (Spinner)findViewById(R.id.spin_area);
        ArrayAdapter<CharSequence> areaList = ArrayAdapter.createFromResource(MainActivity.this,  R.array.area,  android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(areaList);

        spinnerKind = (Spinner)findViewById(R.id.spin_kind);
        ArrayAdapter<CharSequence> kindList = ArrayAdapter.createFromResource(MainActivity.this,  R.array.kind,  android.R.layout.simple_spinner_dropdown_item);
        spinnerKind.setAdapter(kindList);

        ListView lvActivities = (ListView)findViewById(R.id.lv);

        adapter = new ActivityArrayAdapter(this, new ArrayList<ActivityItem>());
        lvActivities.setAdapter(adapter);

        getActivitiesFromFirebase();

        lvActivities.setOnItemClickListener(itemclick);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List<ActivityItem> filterActivities = new ArrayList<ActivityItem>();
            for(ActivityItem item: mlsActivities) {
                if(spinnerArea.getSelectedItemPosition() == 0 && spinnerKind.getSelectedItemPosition() == 0){
                        filterActivities.add(item);
                }
                else if(spinnerArea.getSelectedItemPosition() == 0 && spinnerKind.getSelectedItemPosition() == 1){
                    if(item.getActKind().equals(kind[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 0 && spinnerKind.getSelectedItemPosition() == 2){
                    if(item.getActKind().equals(kind[2])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 1 && spinnerKind.getSelectedItemPosition() == 0){
                    if(item.getActLoc().equals(area[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 1 && spinnerKind.getSelectedItemPosition() == 1){
                    if(item.getActLoc().equals(area[1]) && item.getActKind().equals(kind[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 1 && spinnerKind.getSelectedItemPosition() == 2){
                    if(item.getActLoc().equals(area[1]) && item.getActKind().equals(kind[2])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 2 && spinnerKind.getSelectedItemPosition() == 0){
                    if(item.getActLoc().equals(area[2])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 2 && spinnerKind.getSelectedItemPosition() == 1){
                    if(item.getActLoc().equals(area[2]) && item.getActKind().equals(kind[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 2 && spinnerKind.getSelectedItemPosition() == 2){
                    if(item.getActLoc().equals(area[2]) && item.getActKind().equals(kind[2])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 3 && spinnerKind.getSelectedItemPosition() == 0){
                    if(item.getActLoc().equals(area[3])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 3 && spinnerKind.getSelectedItemPosition() == 1){
                    if(item.getActLoc().equals(area[3]) && item.getActKind().equals(kind[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 3 && spinnerKind.getSelectedItemPosition() == 2){
                    if(item.getActLoc().equals(area[3]) && item.getActKind().equals(kind[2])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 4 && spinnerKind.getSelectedItemPosition() == 0){
                    if(item.getActLoc().equals(area[4])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 4 && spinnerKind.getSelectedItemPosition() == 1){
                    if(item.getActLoc().equals(area[4]) && item.getActKind().equals(kind[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 4 && spinnerKind.getSelectedItemPosition() == 2){
                    if(item.getActLoc().equals(area[4]) && item.getActKind().equals(kind[2])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 5 && spinnerKind.getSelectedItemPosition() == 0){
                    if(item.getActLoc().equals(area[5])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 5 && spinnerKind.getSelectedItemPosition() == 1){
                    if(item.getActLoc().equals(area[5]) && item.getActKind().equals(kind[1])) {
                        filterActivities.add(item);
                    }
                }
                else if(spinnerArea.getSelectedItemPosition() == 5 && spinnerKind.getSelectedItemPosition() == 2){
                    if(item.getActLoc().equals(area[5]) && item.getActKind().equals(kind[2])) {
                        filterActivities.add(item);
                    }
                }
            }
            adapter.clear();
            adapter.addAll(filterActivities);
            if( filterActivities.isEmpty()){
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setMessage("\t\t\t\t\t\t\t\t\t查無資料");
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface di, int i){

                    }
                };
                ad.setPositiveButton("                           ", listener);
                ad.setNegativeButton("確定", listener);
                ad.show();
            }
        }
    };


    class FirebaseThread extends Thread {

        private DataSnapshot dataSnapshot;

        public FirebaseThread(DataSnapshot dataSnapshot) {
            this.dataSnapshot = dataSnapshot;
        }

        @Override
        public void run() {
            List<ActivityItem> lslvActivities = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    DataSnapshot dsName = ds.child("title");
                    DataSnapshot dssDate = ds.child("startDate");
                    DataSnapshot dseDate = ds.child("endDate");
                    DataSnapshot dsLocation = ds.child("area");
                    DataSnapshot dsKind = ds.child("kind");
                //MAC
               /* DataSnapshot dsLoc = ds.child("location");
                DataSnapshot dsUnit = ds.child("masterUnit");
                DataSnapshot dsDes = ds.child("descriptionFilterHtml");
                DataSnapshot dsWeb = ds.child("sourceWebPromote");
                DataSnapshot dsUrl = ds.child("webSales");*/


                    String name = (String) dsName.getValue();
                    String startDate = (String) dssDate.getValue();
                    String endDate = (String) dseDate.getValue();
                    String location = (String) dsLocation.getValue();
                    String kind = (String) dsKind.getValue();


                    DataSnapshot dsImg = ds.child("imageUrl");
                    String imgUrl = (String) dsImg.getValue();
                    Bitmap activityImg = getImgBitmap(imgUrl);

                    ActivityItem aActivity = new ActivityItem();
                    aActivity.setActName(name);
                    aActivity.setStartDate(startDate);
                    aActivity.setEndDate(endDate);
                    aActivity.setActLoc(location);
                    aActivity.setActKind(kind);
                    aActivity.setActImgUrl(activityImg);
                    lslvActivities.add(aActivity);
                    Log.v("LazyBag", name + ";" + imgUrl + ";" + location + ";" + kind);
            }
            Message msg = new Message();
            msg.what = LIST_ACTIVITIES;
            msg.obj = lslvActivities;
            handler.sendMessage(msg);
        }
    }

    private void getActivitiesFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new FirebaseThread(dataSnapshot).start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("LazyBag", databaseError.getMessage());
            }
        });
    }

    private Bitmap getImgBitmap(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            Bitmap bm = BitmapFactory.decodeStream(url.openConnection()
                    .getInputStream());
            return bm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    class ActivityArrayAdapter extends ArrayAdapter<ActivityItem> {
        Context context;

        public ActivityArrayAdapter(Context context, List<ActivityItem> items) {
            super(context, 0, items);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout itemlayout = null;
            if (convertView == null) {
                itemlayout = (LinearLayout) inflater.inflate(R.layout.listitem, null);
            } else {
                itemlayout = (LinearLayout) convertView;
            }
            ActivityItem item = (ActivityItem) getItem(position);
            TextView tvName = (TextView) itemlayout.findViewById(R.id.tv_act);
            tvName.setText(item.getactName());
            TextView tvsDate = (TextView) itemlayout.findViewById(R.id.tv_start_date);
            tvsDate.setText("開始日期 : " + item.getStartDate());
            TextView tveDate = (TextView) itemlayout.findViewById(R.id.tv_end_date);
            tveDate.setText("結束日期 : " + item.getEndDate());
            ImageView ivActivity = (ImageView) itemlayout.findViewById(R.id.img_act);
            if(item.getactImgUrl() != null){
                ivActivity.setImageBitmap(item.getactImgUrl());
            }else{
                ivActivity.setImageResource(R.drawable.art);
            }

            return itemlayout;
        }
    }


    AdapterView.OnItemClickListener itemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> av, View view, int position, long id) {
           Intent intent = new Intent();
            intent.setClass(MainActivity.this, Main2Activity.class);
            intent.putExtra("switch",position);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

       if(id == R.id.action_search){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
       else if (id == R.id.action_login) {
           Intent intent = new Intent();
           intent.setClass(MainActivity.this, LoginActivity.class);
           startActivity(intent);
           return true;
       }
        else if(id == R.id.action_photoRecord){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, PhotoActivity.class);
            startActivity(intent);
        }

       else if(id == R.id.action_settings){
           Intent intent = new Intent();
           intent.setClass(MainActivity.this, uploadimagetoserver.class);
           startActivity(intent);
       }

        else{
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("關於升級成會員");
            ad.setMessage("\n升級成會員即可移除廣告\n\n以及享有購票9折優惠\n");

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface di, int i){

                }
            };

            ad.setPositiveButton("立即升級", listener);
            ad.setNegativeButton("不用了", listener);
            ad.show();
        }
        return super.onOptionsItemSelected(item);
    }

}
