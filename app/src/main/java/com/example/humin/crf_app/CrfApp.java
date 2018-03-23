package com.example.humin.crf_app;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by humin on 3/23/2018.
 */

public class CrfApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
