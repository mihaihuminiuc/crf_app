package com.example.humin.crf_app.wallpaper.presenter;

import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.network.callback.WallpaperCallBack;
import com.example.humin.crf_app.wallpaper.view.Wallpaper3RowView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by humin on 3/24/2018.
 */

public class Wallpaper3RowPresenter {
    private final Service service;
    private final Wallpaper3RowView view;
    private CompositeDisposable compositeDisposable;

    public Wallpaper3RowPresenter(Service service, Wallpaper3RowView view){
        this.service = service;
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void getWallpaperList() {
        view.showWait();

        service.getWallpapers(new WallpaperCallBack() {
            @Override
            public void getWallpaperListSucces(WallpaperList wallpaperList) {
                view.removeWait();
                view.getWallpapers(wallpaperList);
            }

            @Override
            public void getWallpaperListError(Throwable error) {
                view.removeWait();
                view.onFailure(error.getMessage());
            }
        });

        compositeDisposable.add(service);

    }

    public void onStop() {
        if(compositeDisposable!=null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}