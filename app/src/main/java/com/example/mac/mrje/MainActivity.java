package com.example.mac.mrje;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button page1;
    Button page2;

    private static final int EDIT=1;
    View.OnClickListener P1 = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,EDIT);
            }
    };
    private static final int EDIT2=2;
    View.OnClickListener P2 = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Main3Activity.class);
            startActivityForResult(intent,EDIT2);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        page1 = (Button)findViewById(R.id.PAGE1);
        page1.setOnClickListener(P1);
        page2 = (Button)findViewById(R.id.PAGE2);
        page2.setOnClickListener(P2);


    }
    protected void onActivityReslt(int code1,int code2,Intent intent){

        if(intent==null)
            return;
        super.onActivityResult(code1,code2,intent);






    }





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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
