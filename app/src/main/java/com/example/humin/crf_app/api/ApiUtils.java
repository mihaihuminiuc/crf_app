package com.example.humin.crf_app.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.humin.crf_app.model.UserCredentials;
import com.example.humin.crf_app.util.CustomStringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by humin on 3/23/2018.
 */

public class ApiUtils {
    private static String BASE_URL="https://safe-ridge-41181.herokuapp.com/";
    private static String GET_WALLPAPERS_URL =BASE_URL+"rest/get/wallpapers";
    private static String GET_QUESTIONS_URL =BASE_URL+"rest/get/questions";
    private static String POST_LOGIN =BASE_URL+"rest/post/login";

    public static void getWallpapers(Context context, Response.Listener<String> successListener, Response.ErrorListener errorListener){
        RequestQueue queue = Volley.newRequestQueue(context);

        CustomStringRequest request = new CustomStringRequest(null, Request.Method.GET, GET_WALLPAPERS_URL, successListener, errorListener, context);
        queue.add(request);
    }

    public static void getQuestions(Context context, Response.Listener<String> successListener, Response.ErrorListener errorListener){
        RequestQueue queue = Volley.newRequestQueue(context);

        CustomStringRequest request = new CustomStringRequest(null, Request.Method.GET, GET_QUESTIONS_URL, successListener, errorListener, context);
        queue.add(request);
    }

    public static void submitCredentials(Context context, UserCredentials userCredentials, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Map<String, String> params = new HashMap<>();
        params.put("username", userCredentials.getUsername());
        params.put("password", userCredentials.getPassword());

        CustomStringRequest request = new CustomStringRequest(params,Request.Method.POST, POST_LOGIN, successListener, errorListener, context);
        queue.add(request);
    }
}
