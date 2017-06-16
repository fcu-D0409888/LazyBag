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

    private String actLoc;

    private String actKind;

    public Bitmap getactImgUrl() {
        return actImgUrl;
    }

    public String getactName() {
        return actName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getActLoc() {
        return actLoc;

    }

    public String getActKind() {
        return actKind;
    }
    public void setActImgUrl(Bitmap actImgUrl) {
        this.actImgUrl = actImgUrl;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public void setActLoc(String actLoc) {
        this.actLoc = actLoc;
    }

    public void setActKind(String actKind) {
        this.actKind = actKind;
    }


}
