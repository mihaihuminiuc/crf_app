package com.example.humin.crf_app.appevents;

import com.example.humin.crf_app.model.UserCredentials;

/**
 * Created by humin on 3/23/2018.
 */

public class AppEvents {

    public static class Login {
        
        private UserCredentials mUserCredentials;
        
        public Login(UserCredentials userCredentials) {
            this.mUserCredentials = userCredentials;
        }
        
        public UserCredentials getUserCredentials() {return mUserCredentials;}
    }
}
