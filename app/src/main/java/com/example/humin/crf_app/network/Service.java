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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by humin on 3/24/2018.
 */

public class Service {

    public final static int WALLPAPER_OBSERVER=1;
    public final static int USER_OBSERVER=2;
    public final static int QUESTION_OBSERVER=3;


    private NetworkService networkService;

    private CompositeDisposable compositeDisposable;

    DisposableObserver<WallpaperList> wallpaperObserver;
    DisposableObserver<ServerResponse> userCredentialsObserver;
    DisposableObserver<List<QuestionModel>> questionModelObserver;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void getWallpapers(final WallpaperCallBack callBack){

        wallpaperObserver = new DisposableObserver<WallpaperList>() {
            @Override
            public void onNext(WallpaperList wallpaperList) {
                callBack.getWallpaperListSuccess(wallpaperList);
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

        compositeDisposable.add(wallpaperObserver);
    }

    public void getQuestions(final QuestionCallBack callBack){

        questionModelObserver = new DisposableObserver<List<QuestionModel>>() {
            @Override
            public void onNext(List<QuestionModel> questionModel) {
                callBack.getQuestionSuccess(questionModel);
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

        compositeDisposable.add(questionModelObserver);
    }

    public void submitUserCredentials(UserCredentials userCredentials, final UserCallBack callBack){

        userCredentialsObserver = new DisposableObserver<ServerResponse>() {
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

        compositeDisposable.add(userCredentialsObserver);
    }

    public void destroy(int observer){
        if(this.compositeDisposable!=null && !this.compositeDisposable.isDisposed())
                switch (observer){
                    case WALLPAPER_OBSERVER:
                        if(wallpaperObserver!=null)
                            compositeDisposable.delete(wallpaperObserver);
                        break;
                    case USER_OBSERVER:
                        if(userCredentialsObserver!=null)
                            compositeDisposable.delete(userCredentialsObserver);
                        break;
                    case QUESTION_OBSERVER:
                        if(questionModelObserver!=null)
                            compositeDisposable.delete(questionModelObserver);
                        break;
                }
    }
}
