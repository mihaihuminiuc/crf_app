package com.example.humin.crf_app.core.question.presenter;

import com.example.humin.crf_app.core.question.view.QuestionView;
import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.network.callback.QuestionCallBack;

import java.util.List;

/**
 * Created by humin on 3/24/2018.
 */

public class QuestionPresenter {
    private final Service service;
    private final QuestionView view;

    public QuestionPresenter(Service service, QuestionView view){
        this.service = service;
        this.view = view;
    }

    public void getQuestionList() {
        view.showWait();

        service.getQuestions(new QuestionCallBack() {
            @Override
            public void getQuestionSuccess(List<QuestionModel> questionModelList) {
                view.removeWait();
                view.getQuestions(questionModelList);
            }

            @Override
            public void getQuestionError(Throwable error) {
                view.removeWait();
                view.onFailure(error.getMessage());
            }
        });
    }

    public void stop(){
        service.destroy(Service.QUESTION_OBSERVER);
    }
}
