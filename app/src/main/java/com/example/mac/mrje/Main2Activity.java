package com.example.mac.mrje;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;  TextView textView2; TextView textView3; TextView textView4;
    TextView textView5; TextView textView6; TextView textView7; TextView textView8;
    TextView textView9; TextView textView10;TextView textView11;TextView textView12; TextView textView13;
    Button button2;
    String Web;
    String Url;
    int position;
    View.OnClickListener BUY = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(Url.isEmpty()){
                if(Web.isEmpty())
                    Toast.makeText(Main2Activity.this, "抱歉 此展場沒有售票功能", Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(Main2Activity.this, "抱歉 此展場沒有售票功能\n幫您跳至官網頁面", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse(Web);
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);
                }
            }
            else {
                Uri uri = Uri.parse(Url);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = this.getIntent();
        position = intent.getIntExtra("switch",-1);
        if(position!=-1)
            getActivitiesFromFirebase();
        textView =(TextView)findViewById(R.id.textView);
        imageView =(ImageView)findViewById(R.id.imageView);
        textView2 =(TextView)findViewById(R.id.textView2);  textView3 =(TextView)findViewById(R.id.textView3);
        textView4 =(TextView)findViewById(R.id.textView4);  textView5 =(TextView)findViewById(R.id.textView5);
        textView6 =(TextView)findViewById(R.id.textView6);  textView7 =(TextView)findViewById(R.id.textView7);
        textView8 =(TextView)findViewById(R.id.textView8);  textView9 =(TextView)findViewById(R.id.textView9);
        textView10 =(TextView)findViewById(R.id.textView10);textView11 =(TextView)findViewById(R.id.textView11);
        textView12 =(TextView)findViewById(R.id.textView12);textView13 =(TextView)findViewById(R.id.textView13);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(BUY);

    }
    private void getActivitiesFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(position == count){
                        DataSnapshot dsTit = ds.child("title");
                        DataSnapshot dsSdate = ds.child("startDate");
                        DataSnapshot dsEdate = ds.child("endDate");
                        DataSnapshot dsLoc = ds.child("location");
                        DataSnapshot dsUnit = ds.child("masterUnit");
                        DataSnapshot dsDes = ds.child("descriptionFilterHtml");
                        DataSnapshot dsWeb = ds.child("sourceWebPromote");
                        DataSnapshot dsUrl = ds.child("webSales");

                        String Tit = (String) dsTit.getValue();
                        String Sdate = (String) dsSdate.getValue();
                        String Edate = (String) dsEdate.getValue();
                        String Loc = (String) dsLoc.getValue();
                        String Unit = (String) dsUnit.getValue();
                        String Des = (String) dsDes.getValue();
                        Web = (String) dsWeb.getValue();
                        Url = (String) dsUrl.getValue();

                        /*DataSnapshot dsImg = ds.child("imageUrl");
                        String imgUrl = (String) dsImg.getValue();
                        Bitmap Img= getImgBitmap(imgUrl);

                        if(!imgUrl.isEmpty())
                           imageView.setImageBitmap(Img);*/
                        textView2.setText(Sdate);
                        textView4.setText(Edate);
                        textView6.setText(Loc);
                        textView8.setText(Unit);
                        if(Des.isEmpty())
                            textView10.setText("無");
                        else
                            textView10.setText(Des);
                        if(Web.isEmpty())
                            textView12.setText("無");
                        else
                            textView12.setText(Web);
                        textView13.setText(Tit);
                        break;
                    }
                    else
                        count++;
                }
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
            Bitmap bm = BitmapFactory.decodeStream(
                    url.openConnection()
                            .getInputStream());
            return bm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
