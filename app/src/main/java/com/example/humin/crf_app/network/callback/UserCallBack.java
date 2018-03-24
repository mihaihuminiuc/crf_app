package com.example.humin.crf_app.network.callback;

import com.example.humin.crf_app.model.ServerResponse;

/**
 * Created by humin on 3/24/2018.
 */

public interface UserCallBack {
    void submitCredentialsSuccess(ServerResponse serverResponse);
    void submitCredentialsError(Throwable error);
}
