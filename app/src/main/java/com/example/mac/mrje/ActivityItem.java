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
    //MAC
    private String des;
    private String fax;
    private Bitmap webURL;

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
    //MAC
    public void setDes(String des) {
        this.des = des;
    }
    public String getDes() {
        return des;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getFax() {
        return fax;
    }
    public void setWebURL(Bitmap webURL) {
        this.webURL = webURL;
    }
    public Bitmap getWebURL() {
        return webURL;
    }
}
