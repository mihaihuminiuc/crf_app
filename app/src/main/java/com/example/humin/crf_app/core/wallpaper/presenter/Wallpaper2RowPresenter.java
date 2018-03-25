package com.example.humin.crf_app.core.wallpaper.presenter;

import com.example.humin.crf_app.core.wallpaper.view.Wallpaper2RowView;
import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.network.callback.WallpaperCallBack;

/**
 * Created by humin on 3/24/2018.
 */

public class Wallpaper2RowPresenter {
    private final Service service;
    private final Wallpaper2RowView view;

    public Wallpaper2RowPresenter(Service service, Wallpaper2RowView view){
        this.service = service;
        this.view = view;
    }

    public void getWallpaperList() {
        view.showWait();

        service.getWallpapers(new WallpaperCallBack() {
            @Override
            public void getWallpaperListSuccess(WallpaperList wallpaperList) {
                view.removeWait();
                view.getWallpapers(wallpaperList);
            }

            @Override
            public void getWallpaperListError(Throwable error) {
                view.removeWait();
                view.onFailure(error.getMessage());
            }
        });
    }

    public void stop(){
        service.destroy(Service.WALLPAPER_OBSERVER);
    }
}
