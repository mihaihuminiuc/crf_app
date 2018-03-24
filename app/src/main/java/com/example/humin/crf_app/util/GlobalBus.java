package com.example.humin.crf_app.util;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by humin on 3/23/2018.
 */

public class GlobalBus {
    private static EventBus sBus;
    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
