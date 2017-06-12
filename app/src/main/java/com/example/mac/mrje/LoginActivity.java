package com.example.mac.mrje;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SQLiteDatabase mLazyBagDb;
    EditText number, password;
    Button nextPageBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //region 初始化資料庫
        LoginDBOpenHelper loginDBOpenHelper = new LoginDBOpenHelper(getApplicationContext(), CommonPara.DB_FILE, null, 1);
        mLazyBagDb = loginDBOpenHelper.getWritableDatabase();


        // 檢查資料表是否存在，如果不存在就建立一個
        Cursor cursor = mLazyBagDb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + CommonPara.DB_USERTABLE + "'", null);


        if (cursor != null) {
            if (cursor.getCount() == 0)    // 沒有資料表，就要建立一個資料表
                mLazyBagDb.execSQL("CREATE TABLE " + CommonPara.DB_USERTABLE + " (" +
                        "id TEXT PRIMARY KEY NOT NULL," +
                        "password TEXT  NOT NULL);");

            cursor.close();
        }
        //endregion

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

        loginBtn=(Button)findViewById(R.id.button);

        nextPageBtn=(Button)findViewById(R.id.button_1);
        nextPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,Page2.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = number.getText().toString();
                String pass = password.getText().toString();

                //region  登入驗證
                if(!email.equals("") && !pass.equals("")){
                    mAuth.signInWithEmailAndPassword(email, pass);

                    Cursor c = null;
                    try {
                        c = mLazyBagDb.query(true, CommonPara.DB_USERTABLE, new String[]{"id", "password"}, "id= '" + email + "'", null, null, null, null, null);

                        if (c.getCount() > 0) {
                            c.moveToFirst();
                            if (c.getString(1).equals(pass)) {
                                Intent intent = new Intent();
                                intent.setClass(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "密碼錯誤，請重新輸入", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "無此使用者，請重新輸入", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e)
                    {
                        Toast.makeText(LoginActivity.this, "無此使用者，請重新輸入", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "帳號或密碼不可為空", Toast.LENGTH_SHORT).show();

                }
                //endregion

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
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
         if (mAuthListener != null) {
             mAuth.removeAuthStateListener(mAuthListener);
         }
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
        if(id == R.id.action_search){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_login) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_photoRecord){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, PhotoActivity.class);
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
