package com.example.mac.mrje;

import android.graphics.Bitmap;

/**
 * Created by user on 2017/6/10.
 */

public class ActivityItem {

    private Bitmap actImgUrl;

    private String actName;

    private String startDate;

    private String endDate;


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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}
