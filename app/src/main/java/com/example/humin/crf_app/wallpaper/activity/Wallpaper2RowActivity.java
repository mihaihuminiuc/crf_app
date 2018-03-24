package com.example.humin.crf_app.wallpaper.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.activity.BaseActivity;
import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.wallpaper.fragment.TwoRowFragment;
import com.example.humin.crf_app.wallpaper.presenter.Wallpaper2RowPresenter;
import com.example.humin.crf_app.wallpaper.view.Wallpaper2RowView;

import javax.inject.Inject;

/**
 * Created by humin on 3/23/2018.
 */

public class Wallpaper2RowActivity extends BaseActivity implements Wallpaper2RowView{

    private ProgressBar mProgressBar;
    private FrameLayout mFrameX;

    private Context mContext;

    private TwoRowFragment twoRowFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Inject
    Service service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getNetworkInject().inject(this);

        setContentView(R.layout.row_wallpaper_2_activity);
        mContext=getApplicationContext();

        initUI();

        Wallpaper2RowPresenter presenter = new Wallpaper2RowPresenter(service, this);
        presenter.getWallpaperList();}

    private void initUI(){
        mFrameX = findViewById(R.id.frame_x);
        mProgressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void showWait() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(mContext,getString(R.string.error_request, appErrorMessage),Toast.LENGTH_LONG).show();
    }

    @Override
    public void getWallpapers(WallpaperList wallpaperList) {
        twoRowFragment = TwoRowFragment.newInstance(wallpaperList);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_x, twoRowFragment);
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        mFragmentTransaction.commit();
    }
}
