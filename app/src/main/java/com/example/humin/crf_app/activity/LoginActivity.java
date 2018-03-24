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
import com.example.humin.crf_app.app_events.AppEvents;
import com.example.humin.crf_app.fragments.LoginFragment;
import com.example.humin.crf_app.util.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by humin on 3/24/2018.
 */

public class LoginActivity extends AppCompatActivity{

    private ProgressBar mProgressBar;
    private FrameLayout mFrameX;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private LoginFragment loginFragment;

    private Context mContext;

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
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
        Toast.makeText(mContext,"Username: "+login.getUserCredentials().getUsername(),Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext,"Password: "+login.getUserCredentials().getPassword(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
