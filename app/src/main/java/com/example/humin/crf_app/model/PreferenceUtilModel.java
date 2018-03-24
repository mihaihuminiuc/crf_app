package com.example.humin.crf_app.model;

/**
 * Created by humin on 3/23/2018.
 */

public class PreferenceUtilModel{

    private boolean isSaved;

    public PreferenceUtilModel() {}

    public PreferenceUtilModel(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
