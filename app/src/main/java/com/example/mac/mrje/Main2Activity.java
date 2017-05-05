package com.example.mac.mrje;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    Button button2;

    View.OnClickListener BUY = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Uri uri = Uri.parse("http://www.accupass.com/go/langlangdontcry#anchor-tickets");
            Intent i = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
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

        textView =(TextView)findViewById(R.id.textView);
        textView2 =(TextView)findViewById(R.id.textView2);
        textView3 =(TextView)findViewById(R.id.textView3);
        textView4 =(TextView)findViewById(R.id.textView4);
        textView5 =(TextView)findViewById(R.id.textView5);
        textView6 =(TextView)findViewById(R.id.textView6);
        textView7 =(TextView)findViewById(R.id.textView7);
        textView8 =(TextView)findViewById(R.id.textView8);
        button2=(Button)findViewById(R.id.button2);

        button2.setOnClickListener(BUY);


    }

}
