package com.example.humin.crf_app;

import android.app.Application;

import com.example.humin.crf_app.inject.DaggerNetworkComponent;
import com.example.humin.crf_app.inject.NetworkComponent;
import com.example.humin.crf_app.network.NetworkModule;
import com.example.humin.crf_app.network.NetworkService;
import com.orm.SugarContext;

/**
 * Created by humin on 3/23/2018.
 */

public class CrfApp extends Application {

    NetworkComponent networkInject;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        networkInject = DaggerNetworkComponent.builder().networkModule(new NetworkModule(NetworkService.BASE_URL)).build();
    }

    public NetworkComponent getNetworkInject(){return networkInject;}

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
