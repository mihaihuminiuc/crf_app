package com.example.humin.crf_app.database;

import com.orm.SugarRecord;

public class WallpaperDB extends SugarRecord{

    public WallpaperDB(){}

    public WallpaperDB(String imgUrl, String tmbUrl){
        this.imgUrl=imgUrl;
        this.tmbUrl=tmbUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTmbUrl() {
        return tmbUrl;
    }

    public void setTmbUrl(String tmbUrl) {
        this.tmbUrl = tmbUrl;
    }

    private String imgUrl;

    private String tmbUrl;
}
