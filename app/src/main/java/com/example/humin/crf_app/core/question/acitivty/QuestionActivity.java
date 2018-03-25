package com.example.humin.crf_app.core.question.acitivty;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.humin.crf_app.CrfApp;
import com.example.humin.crf_app.R;
import com.example.humin.crf_app.core.question.fragment.QuestionFragment;
import com.example.humin.crf_app.core.question.presenter.QuestionPresenter;
import com.example.humin.crf_app.core.question.view.QuestionView;
import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.network.Service;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionActivity extends AppCompatActivity implements QuestionView{

    private ProgressBar mProgressBar;

    private QuestionFragment mQuestionFragment;
    private FragmentTransaction mFragmentTransaction;

    private QuestionPresenter questionPresenter;

    private Context mContext;

    @Inject
    Service service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.questions_activity);
        mContext=getApplicationContext();

        ((CrfApp)mContext).getNetworkInject().inject(this);

        initUI();

        questionPresenter = new QuestionPresenter(service,this);
        questionPresenter.getQuestionList();
    }

    private void initUI(){
        mProgressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onBackPressed() {
        questionPresenter.onStop();
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

        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.frame_x, mQuestionFragment);
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        mFragmentTransaction.commit();

        mProgressBar.setVisibility(View.GONE);
    }
}
