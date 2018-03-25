package com.example.humin.crf_app.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.adapter.WallpaperListViewAdapter;
import com.example.humin.crf_app.database.WallpaperDB;
import com.example.humin.crf_app.listener.WallpaperListClickListener;
import com.example.humin.crf_app.model.Wallpaper;
import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.model.PreferenceUtilModel;
import com.example.humin.crf_app.util.sharedpreference.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by humin on 3/23/2018.
 */

public class ThreeRowFragment extends Fragment implements View.OnClickListener, WallpaperListClickListener{

    private RecyclerView recyclerView;
    private WallpaperList mWallpapersList;
    private List<Wallpaper> mWallpaper;
    private WallpaperListViewAdapter adapter;
    private FloatingActionButton mFloatingActionButton;
    private Context mContext;
    private PreferenceUtilModel mPreferenceUtilModel;

    public static ThreeRowFragment newInstance(WallpaperList mList){
        ThreeRowFragment fragment = new ThreeRowFragment();
        fragment.mWallpapersList=mList;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.row_wallpaper_3_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();
        initUI(view);
        setupList(view);
    }

    private void initUI(View view){
        mFloatingActionButton = view.findViewById(R.id.fab);

        mFloatingActionButton.setOnClickListener(this);
    }

    private void setupList(View view){
        mPreferenceUtilModel = PreferencesUtils.getPreferenceModel(mContext);

        if(mPreferenceUtilModel!=null && mPreferenceUtilModel.isSaved()
                && (WallpaperDB.listAll(WallpaperDB.class) !=null || !WallpaperDB.listAll(WallpaperDB.class).isEmpty())){

            Toast.makeText(mContext, R.string.db_restored, Toast.LENGTH_SHORT).show();

            mWallpaper = new ArrayList<>();

            for(WallpaperDB w : WallpaperDB.listAll(WallpaperDB.class)){
                mWallpaper.add(new Wallpaper(w.getImgUrl(),w.getTmbUrl()));
            }

            mWallpapersList.setWallpapers(mWallpaper);
        }

        adapter = new WallpaperListViewAdapter(mWallpapersList, WallpaperListViewAdapter.ADAPTER_STATE_2,mContext,this);
        recyclerView = view.findViewById(R.id.simple_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                int random = new Random().nextInt(mWallpapersList.getWallpapers().size()) ;
                Wallpaper tempWallpaper = mWallpapersList.getWallpapers().get(random);

                mWallpapersList.getWallpapers().set(0,tempWallpaper);

                adapter.updateList(mWallpapersList);

                recyclerView.smoothScrollToPosition(0);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        mPreferenceUtilModel = new PreferenceUtilModel();

        WallpaperDB.deleteAll(WallpaperDB.class);

        for(Wallpaper w : adapter.getList().getWallpapers()){
            WallpaperDB wallpaperDB = new WallpaperDB(w.getImgUrl(),w.getTmbUrl());
            wallpaperDB.save();
        }

        mPreferenceUtilModel.setSaved(true);

        PreferencesUtils.setPreferenceModel(mContext,mPreferenceUtilModel);

        Toast.makeText(mContext, R.string.db_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(String url) {
        Toast.makeText(mContext,url,Toast.LENGTH_LONG).show();
    }
}
