package com.example.mac.mrje;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mac.mrje.ActivityDB;
import com.example.mac.mrje.ActivityDB;

/**
 * Created by user on 2017/5/5.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    public DBOpenHelper(Context context) {
        super(context, "mydb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ActivityDB.ACTIVITYTABLE + "(name,  date, time, detail, traffic, web);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }
}
