package com.example.humin.crf_app.network;

import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.model.ServerResponse;
import com.example.humin.crf_app.model.UserCredentials;
import com.example.humin.crf_app.model.WallpaperList;
import com.example.humin.crf_app.network.callback.QuestionCallBack;
import com.example.humin.crf_app.network.callback.UserCallBack;
import com.example.humin.crf_app.network.callback.WallpaperCallBack;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by humin on 3/24/2018.
 */

public class Service implements Disposable {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public void getWallpapers(final WallpaperCallBack callBack){

        DisposableObserver<WallpaperList> wallpaperObserver = new DisposableObserver<WallpaperList>() {
            @Override
            public void onNext(WallpaperList wallpaperList) {
                callBack.getWallpaperListSucces(wallpaperList);
            }

            @Override
            public void onError(Throwable e) {
                callBack.getWallpaperListError(e);
            }

            @Override
            public void onComplete() {

            }
        };

        networkService.getWallpapers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallpaperObserver);
    }

    public void getQuestions(final QuestionCallBack callBack){

        DisposableObserver<List<QuestionModel>> questionModelObserver = new DisposableObserver<List<QuestionModel>>() {
            @Override
            public void onNext(List<QuestionModel> questionModel) {
                callBack.getQuestionSucces(questionModel);
            }

            @Override
            public void onError(Throwable e) {
                callBack.getQuestionError(e);
            }

            @Override
            public void onComplete() {

            }
        };

        networkService.getQuestions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(questionModelObserver);
    }

    public void submitUserCredentials(UserCredentials userCredentials, final UserCallBack callBack){

        DisposableObserver<ServerResponse> userCredentialsObserver = new DisposableObserver<ServerResponse>() {
            @Override
            public void onNext(ServerResponse serverResponse) {
                callBack.submitCredentialsSuccess(serverResponse);
            }

            @Override
            public void onError(Throwable e) {
                callBack.submitCredentialsError(e);
            }

            @Override
            public void onComplete() {

            }
        };

        networkService.submitLogin(userCredentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userCredentialsObserver);
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
