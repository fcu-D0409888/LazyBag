package com.example.mac.mrje;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Page2 extends AppCompatActivity {

    private SQLiteDatabase mLazyBagDb;
    EditText id, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        LoginDBOpenHelper loginDBOpenHelper = new LoginDBOpenHelper(getApplicationContext(), CommonPara.DB_FILE, null, 1);
        mLazyBagDb = loginDBOpenHelper.getWritableDatabase();

        id = (EditText)findViewById(R.id.edtAccount);
        password = (EditText)findViewById(R.id.edtPassword);

        Button nextPageBtn=(Button)findViewById(R.id.button6);
        nextPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){

                //region 註冊驗證
                Cursor c;
                try {
                    c = mLazyBagDb.query(true, CommonPara.DB_USERTABLE, new String[]{"id", "password"}, "id= '" + id.getText().toString() + "'", null, null, null, null, null);
                    if (c.getCount() > 0) {
                        Toast.makeText(Page2.this, "已有人使用此帳號，請重新輸入", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ContentValues cv = new ContentValues();
                        cv.put("id", id.getText().toString());
                        cv.put("password", password.getText().toString());
                        mLazyBagDb.insert(CommonPara.DB_USERTABLE, null, cv);
                        Toast.makeText(Page2.this, "註冊成功", Toast.LENGTH_SHORT).show();
                        Page2.this.finish();
                    }

                }catch (Exception e)
                {
                    ContentValues cv = new ContentValues();
                    cv.put("id", id.getText().toString());
                    cv.put("password", password.getText().toString());
                    mLazyBagDb.insert(CommonPara.DB_USERTABLE, null, cv);
                    Toast.makeText(Page2.this, "註冊成功", Toast.LENGTH_SHORT).show();
                    Page2.this.finish();
                }
                //endregion

            }
        });







    }


}
