package com.example.mac.mrje;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    ListView lvPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<PhotoAlbumItem> albumlist = new ArrayList<PhotoAlbumItem>();

        albumlist.add(new PhotoAlbumItem("浪浪野餐日", R.drawable.ex001));
        albumlist.add(new PhotoAlbumItem("新藝市集五月場", R.drawable.view001));
        PhotoAlbumArrayAdapter adapter = new PhotoAlbumArrayAdapter(this, albumlist);
        lvPhoto = (ListView)findViewById(R.id.lv_photo);
        lvPhoto.setAdapter(adapter);
        lvPhoto.setOnItemClickListener(itemclick);
    }
    AdapterView.OnItemClickListener itemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> av, View view, int position, long id) {
            int [] imageIds = null;
            int columns = 3;
            switch (position){
                case 0:
                    imageIds = new int[6];
                    imageIds[0] = R.drawable.ex001;
                    imageIds[1] = R.drawable.ex002;
                    imageIds[2] = R.drawable.ex003;
                    imageIds[3] = R.drawable.ex004;
                    imageIds[4] = R.drawable.ex005;
                    imageIds[5] = R.drawable.ex006;
                    columns = 2;
                    break;
                case 1:
                    imageIds = new int[5];
                    imageIds[0] = R.drawable.view001;
                    imageIds[1] = R.drawable.view002;
                    imageIds[2] = R.drawable.view003;
                    imageIds[3] = R.drawable.view004;
                    imageIds[4] = R.drawable.view005;
                    break;
            }

            Intent intent = new Intent();
            intent.setClass(PhotoActivity.this, GridActivity.class);
            intent.putExtra("KEY_IDS", imageIds);
            intent.putExtra("KEY_COLUMNS", columns);
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
        if (id == R.id.action_login) {
            Intent intent = new Intent();
            intent.setClass(PhotoActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_photoRecord){
            Intent intent = new Intent();
            intent.setClass(PhotoActivity.this, PhotoActivity.class);
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
