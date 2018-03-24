package com.example.humin.crf_app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.humin.crf_app.inject.DaggerNetworkComponent;
import com.example.humin.crf_app.inject.NetworkComponent;
import com.example.humin.crf_app.network.NetworkModule;
import com.example.humin.crf_app.network.NetworkService;

/**
 * Created by humin on 3/24/2018.
 */

public class BaseActivity extends AppCompatActivity {

    NetworkComponent networkInject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkInject = DaggerNetworkComponent.builder().networkModule(new NetworkModule(NetworkService.BASE_URL)).build();
    }

    public NetworkComponent getNetworkInject(){return networkInject;}

}
