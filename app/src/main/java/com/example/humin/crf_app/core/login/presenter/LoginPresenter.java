package com.example.humin.crf_app.core.login.presenter;

import com.example.humin.crf_app.core.login.view.LoginView;
import com.example.humin.crf_app.model.ServerResponse;
import com.example.humin.crf_app.model.UserCredentials;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.network.callback.UserCallBack;

/**
 * Created by humin on 3/24/2018.
 */

public class LoginPresenter {
    private final Service service;
    private final LoginView view;

    public LoginPresenter(Service service, LoginView view){
        this.service = service;
        this.view = view;
    }

    public void submitCredentials(UserCredentials userCredentials) {
        view.showWait();

        service.submitUserCredentials(userCredentials, new UserCallBack() {
            @Override
            public void submitCredentialsSuccess(ServerResponse serverResponse) {
                view.removeWait();
                view.getServerResponse(serverResponse);
            }

            @Override
            public void submitCredentialsError(Throwable error) {
                view.removeWait();
                view.onFailure(error.getMessage());
            }
        });
    }

    public void stop(){
        service.destroy(Service.USER_OBSERVER);
    }
}
