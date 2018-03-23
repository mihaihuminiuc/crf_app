package com.example.humin.crf_app.holder;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.humin.crf_app.R;
import com.example.humin.crf_app.adapter.ListViewAdapter;
import com.example.humin.crf_app.listener.WallpaperListClickListener;
import com.example.humin.crf_app.model.Wallpaper;


public class ListViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout mBoderLayout;
    private ImageView mWallpaperImage;
    private View view;

    /**
     * The ViewHolder that will be used to display the data in each item shown
     * in the RecyclerView
     *
     * @param itemView
     *         The layout view group used to display the data
     */
    public ListViewHolder(final View itemView) {
        super(itemView);
        mBoderLayout = itemView.findViewById(R.id.layout_border);
        mWallpaperImage = itemView.findViewById(R.id.wallpaper_img);
        view = itemView;
    }

    /**
     * Method that is used to bind the data to the ViewHolder
     *
     * @param mWallpaper
     *         The viewmodel that contains the data
     */
    public void bindData(final Wallpaper mWallpaper, int type, Context context, Point mScreenDimensions, WallpaperListClickListener mWallpaperListClickListener) {
        RequestOptions options;
        LinearLayout.LayoutParams params;
        switch (type){
            case ListViewAdapter.ADAPTER_STATE_1:
                mBoderLayout.setBackgroundResource(R.drawable.yellow_border);

                params = (LinearLayout.LayoutParams) mBoderLayout.getLayoutParams();
                params.height = (int) (mScreenDimensions.y*0.2f);
                params.width = (int) (mScreenDimensions.x*0.45f);

                mBoderLayout.setLayoutParams(params);

                options = new RequestOptions();
                options.centerInside();

                Glide.with(context)
                        .load(mWallpaper.getTmbUrl())
                        .apply(options)
                        .into(mWallpaperImage);

                view.setOnClickListener(view -> mWallpaperListClickListener.onItemClick(mWallpaper.getImgUrl()));

                break;

            case ListViewAdapter.ADAPTER_STATE_2:
                mBoderLayout.setBackgroundResource(R.drawable.blue_border);

                params = (LinearLayout.LayoutParams) mBoderLayout.getLayoutParams();
                params.height = (int) (mScreenDimensions.y*0.2f);
                params.width = (int) (mScreenDimensions.x*0.3f);

                mBoderLayout.setLayoutParams(params);

                options = new RequestOptions();
                options.centerInside();

                Glide.with(context)
                        .load(mWallpaper.getTmbUrl())
                        .apply(options)
                        .into(mWallpaperImage);

                view.setOnClickListener(view -> mWallpaperListClickListener.onItemClick(mWallpaper.getImgUrl()));

                break;
        }

    }
}
