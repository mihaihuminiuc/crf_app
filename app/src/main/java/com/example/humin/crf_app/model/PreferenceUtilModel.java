package com.example.humin.crf_app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by humin on 3/23/2018.
 */

public class PreferenceUtilModel implements Parcelable{

    public static final Creator<PreferenceUtilModel> CREATOR = new Creator<PreferenceUtilModel>() {
        @Override
        public PreferenceUtilModel createFromParcel(Parcel in) {
            return new PreferenceUtilModel(in);
        }

        @Override
        public PreferenceUtilModel[] newArray(int size) {
            return new PreferenceUtilModel[size];
        }
    };

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

    protected PreferenceUtilModel(Parcel in) {
        isSaved = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isSaved ? 1 : 0));
    }

    @Override
    public String toString() {
        return "PreferenceUtilModel{" +
                "isSaved=" + isSaved +
                '}';
    }
}
