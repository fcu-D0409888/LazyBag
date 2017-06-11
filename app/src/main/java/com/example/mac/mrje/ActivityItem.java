package com.example.mac.mrje;

import android.graphics.Bitmap;

/**
 * Created by user on 2017/6/10.
 */

public class ActivityItem {
    private Bitmap actImgUrl;

    private String actName;



    public Bitmap getactImgUrl() {
        return actImgUrl;
    }

    public String getactName() {
        return actName;
    }

    public void setActImgUrl(Bitmap actImgUrl) {
        this.actImgUrl = actImgUrl;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

}
