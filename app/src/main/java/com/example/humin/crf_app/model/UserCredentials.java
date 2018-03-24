package com.example.humin.crf_app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by humin on 3/24/2018.
 */

public class UserCredentials {

    public UserCredentials(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;
}
