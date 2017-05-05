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

public class Main3Activity extends AppCompatActivity {

    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView15;
    TextView textView16;
    Button button3;

    View.OnClickListener BUY = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScpw5qobXrAC3-kOV4EQr3_vbW5SN-nu9n0plslapYSnGcazA/viewform");
            Intent i = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
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

        textView9 =(TextView)findViewById(R.id.textView9);
        textView10 =(TextView)findViewById(R.id.textView10);
        textView11 =(TextView)findViewById(R.id.textView11);
        textView12 =(TextView)findViewById(R.id.textView12);
        textView13 =(TextView)findViewById(R.id.textView13);
        textView14 =(TextView)findViewById(R.id.textView14);
        textView15 =(TextView)findViewById(R.id.textView15);
        textView16 =(TextView)findViewById(R.id.textView16);
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(BUY);



    }

}
