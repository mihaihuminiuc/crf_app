package com.example.humin.crf_app.core.wallpaper.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.listener.WallpaperListClickListener;
import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.core.wallpaper.listadapter.WallpaperListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by humin on 3/23/2018.
 */

public class TwoRowFragment extends Fragment implements WallpaperListClickListener {

    @BindView(R.id.simple_recyclerview) RecyclerView recyclerView;
    private WallpaperList mWallpapersList;

    private Context mContext;

    public static TwoRowFragment newInstance(WallpaperList mList) {
        TwoRowFragment fragment = new TwoRowFragment();
        fragment.mWallpapersList = mList;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.row_wallpaper_2_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mContext = getActivity();
        setupList();
    }

    private void setupList() {
        WallpaperListViewAdapter adapter = new WallpaperListViewAdapter(mWallpapersList, WallpaperListViewAdapter.ADAPTER_STATE_1, mContext, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
