package com.example.humin.crf_app.network.callback;

import com.example.humin.crf_app.model.QuestionModel;

import java.util.List;

/**
 * Created by humin on 3/24/2018.
 */

public interface QuestionCallBack {

    void getQuestionSuccess(List<QuestionModel> questionModelList);
    void getQuestionError(Throwable error);
}
