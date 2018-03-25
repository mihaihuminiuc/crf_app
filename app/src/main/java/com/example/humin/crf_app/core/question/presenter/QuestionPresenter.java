package com.example.humin.crf_app.core.question.presenter;

import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.network.callback.QuestionCallBack;
import com.example.humin.crf_app.core.question.view.QuestionView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by humin on 3/24/2018.
 */

public class QuestionPresenter {
    private final Service service;
    private final QuestionView view;
    private CompositeDisposable compositeDisposable;

    public QuestionPresenter(Service service, QuestionView view){
        this.service = service;
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
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

        compositeDisposable.add(service);
    }

    public void onStop() {
        if(compositeDisposable!=null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
