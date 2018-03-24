package com.example.humin.crf_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class UserResponseModel{

    public UserResponseModel(){}

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
}
