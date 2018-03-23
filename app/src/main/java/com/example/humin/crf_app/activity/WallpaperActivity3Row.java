package com.example.humin.crf_app.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.api.ApiUtils;
import com.example.humin.crf_app.fragments.ThreeRowFragment;
import com.example.humin.crf_app.model.WallpaperList;
import com.google.gson.Gson;

/**
 * Created by humin on 3/23/2018.
 */

public class WallpaperActivity3Row extends AppCompatActivity{

    private Context mContext;

    private ThreeRowFragment mThreeRowFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.row_3_activity);
        mContext=getApplicationContext();

        initFragment();
    }

    private void initFragment(){

        ApiUtils.getWallpapers(mContext, response -> {

                    Gson gson =  new Gson();

                    WallpaperList wallpapers = gson.fromJson(response,WallpaperList.class);

                    mThreeRowFragment = ThreeRowFragment.newInstance(wallpapers);

                    mFragmentManager = getFragmentManager();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.frame_x, mThreeRowFragment);
                    mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    mFragmentTransaction.commit();
                },
                error -> Toast.makeText(mContext,"Error:"+error.toString(),Toast.LENGTH_LONG).show()
        );
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
