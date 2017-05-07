package com.example.mac.mrje;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ImageButton search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search = (ImageButton)findViewById(R.id.imgbtn_search);
        search.setOnClickListener(click);

        Spinner spinnerArea = (Spinner)findViewById(R.id.spin_area);
        ArrayAdapter<CharSequence> areaList = ArrayAdapter.createFromResource(MainActivity.this,  R.array.area,  android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(areaList);

        Spinner spinnerKind = (Spinner)findViewById(R.id.spin_kind);
        ArrayAdapter<CharSequence> kindList = ArrayAdapter.createFromResource(MainActivity.this,  R.array.kind,  android.R.layout.simple_spinner_dropdown_item);
        spinnerKind.setAdapter(kindList);

        ArrayList<AlbumItem> albumlist = new ArrayList<AlbumItem>();

        albumlist.add(new AlbumItem(R.drawable.picnic, "浪浪野餐日"));
        albumlist.add(new AlbumItem(R.drawable.art, "新藝市集五月場"));
        AlbumArrayAdapter adapter = new AlbumArrayAdapter(this, albumlist);
        lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(itemclick);

    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };


    AdapterView.OnItemClickListener itemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> av, View view, int position, long id) {
           Intent intent = new Intent();
            switch (position){
                case 0:
                    intent.setClass(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent.setClass(MainActivity.this, Main3Activity.class);
                    startActivity(intent);
                    break;
            }

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
        if (id == R.id.action_login) {
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
        else{
            /*Intent intent = new Intent();
            intent.setClass(MainActivity.this, );
            startActivity(intent);*/
        }
        return super.onOptionsItemSelected(item);
    }
    /*protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        String inName;

        if (intent == null)
            return;

        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode) {
            case ACTIVITY_SET_NAME:
                inName = intent.getStringExtra(Main2Activity.KEY_NAME);
                Toast.makeText(MainActivity.this, "Hello " + inName, Toast.LENGTH_SHORT).show();
                break;
        }*/

}
