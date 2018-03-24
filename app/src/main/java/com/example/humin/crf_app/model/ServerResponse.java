package com.example.humin.crf_app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by humin on 3/24/2018.
 */

public class ServerResponse {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServerResponse(){}

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;
}
