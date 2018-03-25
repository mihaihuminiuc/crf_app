package com.example.humin.crf_app.core.login.activity;

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
import com.example.humin.crf_app.appevents.AppEvents;
import com.example.humin.crf_app.core.login.fragment.LoginFragment;
import com.example.humin.crf_app.core.login.presenter.LoginPresenter;
import com.example.humin.crf_app.core.login.view.LoginView;
import com.example.humin.crf_app.model.ServerResponse;
import com.example.humin.crf_app.network.Service;
import com.example.humin.crf_app.util.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


/**
 * Created by humin on 3/24/2018.
 */

public class LoginActivity extends BaseActivity implements LoginView{

    private ProgressBar mProgressBar;
    private FrameLayout mFrameX;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private LoginFragment loginFragment;

    private Context mContext;
    private LoginPresenter loginPresenter;

    @Inject
    Service service;

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getNetworkInject().inject(this);

        setContentView(R.layout.login_activity);
        mContext=getApplicationContext();

        initUI();

        loginPresenter =  new LoginPresenter(service,this);
    }

    private void initUI(){
        mFrameX = findViewById(R.id.frame_x);
        mProgressBar = findViewById(R.id.progressBar);

        initFragment();
    }

    private void initFragment(){
        loginFragment = LoginFragment.newInstance();

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_x, loginFragment);
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        mFragmentTransaction.commit();

        mProgressBar.setVisibility(View.GONE);
    }


    @Subscribe
    public void login(AppEvents.Login login) {
        loginPresenter.submitCredentials(login.getUserCredentials());
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }

    @Override
    public void onBackPressed() {
        loginPresenter.onStop();
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
    public void getServerResponse(ServerResponse serverResponse) {
        Toast.makeText(mContext,serverResponse.getMessage(),Toast.LENGTH_LONG).show();
    }
}
