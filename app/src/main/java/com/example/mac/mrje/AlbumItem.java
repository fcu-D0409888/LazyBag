package com.example.mac.mrje;

/**
 * Created by user on 2017/5/5.
 */

class AlbumItem {

    private int actImgId;

    private String actName;


    public AlbumItem(int actImgId, String actName) {
        this.actImgId = actImgId;
        this.actName = actName;

    }

    public int getactImgId() {
        return actImgId;
    }

    public String getactName() {
        return actName;
    }

}
