package com.example.humin.crf_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionModel implements Parcelable{

    public static final int ANSWER_TYPE_TEXT = 0;
    public static final int ANSWER_TYPE_FREE_TEXT = 1;
    public static final int ANSWER_TYPE_RATING = 2;
    public static final int ANSWER_BUTTON_SEND=99;

    public QuestionModel() {}

    protected QuestionModel(Parcel in) {
        title = in.readString();
        questionGUID = in.readString();
        type = in.readString();
        answers = in.createTypedArrayList(AnswersModel.CREATOR);
        answerType = in.readInt();
        userResponses = in.readParcelable(UserResponseModel.class.getClassLoader());
        multipleChoice = in.readByte() != 0;
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(questionGUID);
        parcel.writeString(type);
        parcel.writeTypedList(answers);
        parcel.writeInt(answerType);
        parcel.writeParcelable(userResponses, i);
        parcel.writeByte((byte) (multipleChoice ? 1 : 0));
    }
}
