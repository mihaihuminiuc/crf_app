package com.example.humin.crf_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class AnswersModel implements Parcelable{


    protected AnswersModel(Parcel in) {
        singleAnswer = in.readString();
        rating = in.readInt();
        multipleAnswers = in.createStringArrayList();
    }

    public static final Creator<AnswersModel> CREATOR = new Creator<AnswersModel>() {
        @Override
        public AnswersModel createFromParcel(Parcel in) {
            return new AnswersModel(in);
        }

        @Override
        public AnswersModel[] newArray(int size) {
            return new AnswersModel[size];
        }
    };

    public String getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(String singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(List<String> multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }

    public AnswersModel(){}

    @SerializedName("singleAnswer")
    private String singleAnswer;
    @SerializedName("rating")
    private int rating;
    @SerializedName("multipleAnswers")
    private List<String> multipleAnswers;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(singleAnswer);
        parcel.writeInt(rating);
        parcel.writeStringList(multipleAnswers);
    }
}
