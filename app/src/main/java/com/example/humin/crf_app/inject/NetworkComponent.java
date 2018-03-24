package com.example.humin.crf_app.inject;

import com.example.humin.crf_app.login.activity.LoginActivity;
import com.example.humin.crf_app.question.acitivty.QuestionActivity;
import com.example.humin.crf_app.network.NetworkModule;
import com.example.humin.crf_app.wallpaper.activity.Wallpaper2RowActivity;
import com.example.humin.crf_app.wallpaper.activity.Wallpaper3RowActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by humin on 3/24/2018.
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    void inject(Wallpaper2RowActivity wallpaper2RowActivity);
    void inject(Wallpaper3RowActivity wallpaper2RowActivity);
    void inject(QuestionActivity questionActivity);
    void inject(LoginActivity loginActivity);
}

