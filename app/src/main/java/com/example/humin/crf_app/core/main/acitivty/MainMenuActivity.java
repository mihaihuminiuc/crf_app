package com.example.humin.crf_app.core.main.acitivty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.core.login.activity.LoginActivity;
import com.example.humin.crf_app.core.question.acitivty.QuestionActivity;
import com.example.humin.crf_app.core.wallpaper.activity.Wallpaper2RowActivity;
import com.example.humin.crf_app.core.wallpaper.activity.Wallpaper3RowActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainMenuActivity extends AppCompatActivity{

    private Context mContext;

    @BindView(R.id.button_1) Button mButtonActivity1;
    @BindView(R.id.button_2) Button mButtonActivity2;
    @BindView(R.id.button_3) Button mButtonActivity3;
    @BindView(R.id.button_4) Button mButtonActivity4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_acivity);
        ButterKnife.bind(this, this);
        mContext=getApplicationContext();
    }

    @OnClick(R.id.button_1)
    void setmButtonActivity1(){
        Intent i = new Intent(mContext, Wallpaper2RowActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_2)
    void setmButtonActivity2(){
        Intent i = new Intent(mContext, Wallpaper3RowActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_3)
    void setmButtonActivity3(){
        Intent i = new Intent(mContext, QuestionActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_4)
    void setmButtonActivity4(){
        Intent i = new Intent(mContext, LoginActivity.class);
        startActivity(i);
    }

}
