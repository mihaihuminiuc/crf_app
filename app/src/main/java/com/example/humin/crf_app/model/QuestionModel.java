package com.example.humin.crf_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionModel{

    public static final int ANSWER_TYPE_TEXT = 0;
    public static final int ANSWER_TYPE_FREE_TEXT = 1;
    public static final int ANSWER_TYPE_RATING = 2;
    public static final int ANSWER_BUTTON_SEND=99;

    public QuestionModel() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionGUID() {
        return questionGUID;
    }

    public void setQuestionGUID(String questionGUID) {
        this.questionGUID = questionGUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnswersModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersModel> answers) {
        this.answers = answers;
    }

    public int getAnswerType() {
        return answerType;
    }

    public void setAnswerType(int answerType) {
        this.answerType = answerType;
    }

    public UserResponseModel getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(UserResponseModel userResponses) {
        this.userResponses = userResponses;
    }

    public boolean isMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    @SerializedName("title")
    private String title;
    @SerializedName("questionGUID")
    private String questionGUID;
    @SerializedName("type")
    private String type;
    @SerializedName("answers")
    private List<AnswersModel> answers;
    @SerializedName("answerType")
    private int answerType;
    @SerializedName("userResponses")
    private UserResponseModel userResponses;
    @SerializedName("multipleChoice")
    private boolean multipleChoice;
}
