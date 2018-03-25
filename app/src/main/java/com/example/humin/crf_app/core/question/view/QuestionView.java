package com.example.humin.crf_app.core.question.view;

import com.example.humin.crf_app.model.QuestionModel;

import java.util.List;

/**
 * Created by humin on 3/24/2018.
 */

public interface QuestionView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getQuestions(List<QuestionModel> questionModelList);
}
