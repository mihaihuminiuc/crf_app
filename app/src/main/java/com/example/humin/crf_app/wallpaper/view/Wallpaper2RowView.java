package com.example.humin.crf_app.wallpaper.view;

import com.example.humin.crf_app.model.WallpaperList;

/**
 * Created by humin on 3/24/2018.
 */

public interface Wallpaper2RowView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getWallpapers(WallpaperList wallpaperList);
}
