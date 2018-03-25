package com.example.humin.crf_app.core.wallpaper.listadapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.core.wallpaper.holder.WallpaperListViewHolder;
import com.example.humin.crf_app.listener.WallpaperListClickListener;
import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.util.CommonUtils;

public class WallpaperListViewAdapter extends RecyclerView.Adapter{

    private WallpaperList wallpapers = new WallpaperList();
    private WallpaperListClickListener mWallpaperListClickListener;
    private int mType = 0;
    private Context mContext;

    public static final int ADAPTER_STATE_1=1;
    public static final int ADAPTER_STATE_2=2;

    private Point mScreenDimensions;

    public WallpaperListViewAdapter(final WallpaperList wallpapers, int type, Context context, WallpaperListClickListener listener) {
        if (wallpapers != null) {
            this.wallpapers = wallpapers;
        }
        this.mType = type;
        this.mContext = context;
        this.mScreenDimensions = CommonUtils.getDefaultDisplaySize((AppCompatActivity) context);
        this.mWallpaperListClickListener = listener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new WallpaperListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((WallpaperListViewHolder) holder).bindData(wallpapers.getWallpapers().get(position), mType, mContext, mScreenDimensions,mWallpaperListClickListener);
    }

    @Override
    public int getItemCount() {
        return wallpapers.getWallpapers().size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.wallpaper_list_item;
    }

    public void updateList(WallpaperList wallpapers){
        if (wallpapers != null) {
            this.wallpapers = wallpapers;
        }
        notifyDataSetChanged();
    }

    public WallpaperList getList(){
        return this.wallpapers;
    }
}
