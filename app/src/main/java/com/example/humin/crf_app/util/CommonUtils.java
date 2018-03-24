package com.example.humin.crf_app.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;

public class CommonUtils {

    public static Point getDefaultDisplaySize(Activity activity) {
        Point size = new Point();
        Display d = activity.getWindowManager().getDefaultDisplay();
        d.getSize(size);
        return size;
    }

    public static int getColor(Context context, int resId) {
        int color;
        if (Build.VERSION.SDK_INT < 23) {
            color = context.getResources().getColor(resId);
        } else {
            color = context.getResources().getColor(resId, null);
        }
        return color;
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
