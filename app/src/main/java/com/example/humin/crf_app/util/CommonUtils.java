package com.example.humin.crf_app.util;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class CommonUtils {

    public static Point getDefaultDisplaySize(Activity activity) {
        Point size = new Point();
        Display d = activity.getWindowManager().getDefaultDisplay();
        d.getSize(size);
        return size;
    }
}
