package com.example.humin.crf_app.network.callback;

import com.example.humin.crf_app.model.WallpaperList;

/**
 * Created by humin on 3/24/2018.
 */

public interface WallpaperCallBack {

    void getWallpaperListSucces(WallpaperList wallpaperList);
    void getWallpaperListError(Throwable error);
}
