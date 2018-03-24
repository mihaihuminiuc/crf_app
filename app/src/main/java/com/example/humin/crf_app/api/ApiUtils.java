package com.example.humin.crf_app.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.humin.crf_app.util.CustomStringRequest;

/**
 * Created by humin on 3/23/2018.
 */

public class ApiUtils {
    private static String BASE_URL="http://192.168.56.1:8082/";
    private static String GET_WALLPAPERS_URL =BASE_URL+"rest/get/wallpapers";
    private static String GET_QUESTIONS_URL =BASE_URL+"rest/get/questions";

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
}
