package com.example.mac.mrje;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user on 2017/5/5.
 */

public class ActivityDB {
    final static String ACTIVITYTABLE = "activitytable";

    static ArrayList<String> getTitleList(SQLiteDatabase db) {
        ArrayList<String> titlelist = new ArrayList<String>();
        Cursor c = db.rawQuery("select title from " +
                ACTIVITYTABLE, null);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            int titleIndex = c.getColumnIndex("name");
            String title = c.getString(titleIndex);
            titlelist.add(title);
            c.moveToNext();
        }
        return titlelist;
    }

    static String getBody(SQLiteDatabase db, String title) {
        Cursor c = db.rawQuery("select * from " +
                ACTIVITYTABLE + " where title='" + title +"';", null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex("body"));
    }
}
