package com.example.mac.mrje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText number, password;
    Button nextPageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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

        number = (EditText)findViewById(R.id.editText7);
        password = (EditText)findViewById(R.id.editText10);


        SharedPreferences pref = getSharedPreferences("login_number", MODE_PRIVATE);
        String pref_number = pref.getString("PREF_number", "");
        number.setText(pref_number);
        String pref_password  = pref.getString("PREF_password", "");
        password.setText("" + pref_password);

        nextPageBtn=(Button)findViewById(R.id.button_1);
        nextPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,Page2.class);
                startActivity(intent);
            }
        });

    }


     @Override
     public void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("login_number", MODE_PRIVATE);
        SharedPreferences.Editor preEdt = pref.edit();
        preEdt.putString("PREF_number", number.getText().toString());
        String pref_password = (password.getText().toString());
        preEdt.putString("PREF_password", pref_password);
        preEdt.commit();
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
        if (id == R.id.action_login) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
