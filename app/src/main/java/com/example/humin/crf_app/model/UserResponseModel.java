package com.example.humin.crf_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class UserResponseModel implements Parcelable{

    public UserResponseModel(){}

    protected UserResponseModel(Parcel in) {
        questionGUID = in.readString();
        answer = in.createStringArrayList();
    }

    public static final Creator<UserResponseModel> CREATOR = new Creator<UserResponseModel>() {
        @Override
        public UserResponseModel createFromParcel(Parcel in) {
            return new UserResponseModel(in);
        }

        @Override
        public UserResponseModel[] newArray(int size) {
            return new UserResponseModel[size];
        }
    };

    public String getQuestionGUID() {
        return questionGUID;
    }

    public void setQuestionGUID(String questionGUID) {
        this.questionGUID = questionGUID;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    @SerializedName("questionGUID")
    private String questionGUID;
    @SerializedName("answer")
    private List<String> answer;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questionGUID);
        parcel.writeStringList(answer);
    }
}
