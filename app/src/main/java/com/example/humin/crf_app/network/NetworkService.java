package com.example.humin.crf_app.network;

import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.model.ServerResponse;
import com.example.humin.crf_app.model.UserCredentials;
import com.example.humin.crf_app.model.WallpaperList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by humin on 3/24/2018.
 */

public interface NetworkService {
    String BASE_URL="https://safe-ridge-41181.herokuapp.com/";
    String GET_WALLPAPERS_URL ="rest/get/wallpapers";
    String GET_QUESTIONS_URL ="rest/get/questions";
    String POST_LOGIN ="rest/post/login";

    @GET(GET_WALLPAPERS_URL)
    Observable<WallpaperList> getWallpapers();

    @GET(GET_QUESTIONS_URL)
    Observable<List<QuestionModel>> getQuestions();

    @POST(POST_LOGIN)
    Observable<ServerResponse> submitLogin(@Body UserCredentials request);
}
