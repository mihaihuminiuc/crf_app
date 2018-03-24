package com.example.humin.crf_app.question.acitivty;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.activity.BaseActivity;
import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.question.fragment.QuestionFragment;
import com.example.humin.crf_app.question.presenter.QuestionPresenter;
import com.example.humin.crf_app.question.view.QuestionView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionActivity extends BaseActivity implements QuestionView{

    private ProgressBar mProgressBar;
    private FrameLayout mFrameX;

    private QuestionFragment mQuestionFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Inject
    Service service;

    private QuestionPresenter questionPresenter;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getNetworkInject().inject(this);

        setContentView(R.layout.questions_activity);
        mContext=getApplicationContext();

        initUI();

        questionPresenter = new QuestionPresenter(service,this);
        questionPresenter.getQuestionList();
    }

    private void initUI(){
        mFrameX = findViewById(R.id.frame_x);
        mProgressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void showWait() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(mContext,getString(R.string.error_request, appErrorMessage),Toast.LENGTH_LONG).show();
    }

    @Override
    public void getQuestions(List<QuestionModel> questionModelList) {
        mQuestionFragment = QuestionFragment.newInstance(questionModelList);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_x, mQuestionFragment);
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        mFragmentTransaction.commit();

        mProgressBar.setVisibility(View.GONE);
    }
}
