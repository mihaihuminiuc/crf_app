package com.example.humin.crf_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class AnswersModel{

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
}
