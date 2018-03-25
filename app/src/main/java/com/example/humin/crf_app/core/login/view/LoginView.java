package com.example.humin.crf_app.core.login.view;

import com.example.humin.crf_app.model.ServerResponse;

/**
 * Created by humin on 3/24/2018.
 */

public interface LoginView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getServerResponse(ServerResponse serverResponse);
}
