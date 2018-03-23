package com.example.humin.crf_app.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.api.ApiUtils;
import com.example.humin.crf_app.fragments.TwoRowFragment;
import com.example.humin.crf_app.listener.TwoRowFragmentListener;
import com.example.humin.crf_app.model.WallpaperList;
import com.google.gson.Gson;

/**
 * Created by humin on 3/23/2018.
 */

public class WallpaperActivity2Row extends AppCompatActivity implements TwoRowFragmentListener{

    private ProgressBar mProgressBar;
    private FrameLayout mFrameX;

    private Context mContext;

    private TwoRowFragment twoRowFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_2_activity);
        mContext=getApplicationContext();

        initUI();
    }

    private void initUI(){
        mFrameX = findViewById(R.id.frame_x);
        mProgressBar = findViewById(R.id.progressBar1);

        initFragment();
    }

    private void initFragment(){
        mFrameX.setVisibility(View.VISIBLE);

        ApiUtils.getWallpapers(mContext, response -> {

            Gson gson =  new Gson();

            WallpaperList wallpapers = gson.fromJson(response,WallpaperList.class);

            twoRowFragment = twoRowFragment.newInstance(wallpapers);

            mFragmentManager = getFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_x, twoRowFragment);
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            mFragmentTransaction.commit();

            mProgressBar.setVisibility(View.GONE);

        }, error -> {
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(mContext,"Error:"+error.toString(),Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onItemCLick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

}
