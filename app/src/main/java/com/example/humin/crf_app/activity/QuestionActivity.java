package com.example.humin.crf_app.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.api.ApiUtils;
import com.example.humin.crf_app.fragments.QuestionFragment;
import com.example.humin.crf_app.model.QuestionModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private FrameLayout mFrameX;

    private QuestionFragment mQuestionFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_wallpaper_2_activity);
        mContext=getApplicationContext();

        initUI();
    }

    private void initUI(){
        mFrameX = findViewById(R.id.frame_x);
        mProgressBar = findViewById(R.id.progressBar1);

        initFragment();
    }

    private void initFragment(){
        mFrameX.setVisibility(View.VISIBLE);

        ApiUtils.getQuestions(mContext, response -> {

            Gson gson =  new Gson();
            Type listType = new TypeToken<ArrayList<QuestionModel>>(){}.getType();
            List<QuestionModel> questionModel = gson.fromJson(response, listType);

            mQuestionFragment = QuestionFragment.newInstance(questionModel);

            mFragmentManager = getFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_x, mQuestionFragment);
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            mFragmentTransaction.commit();

            mProgressBar.setVisibility(View.GONE);

        }, error -> {
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(mContext,"Error:"+error.toString(),Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
