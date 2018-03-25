package com.example.humin.crf_app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.core.login.activity.LoginActivity;
import com.example.humin.crf_app.core.question.acitivty.QuestionActivity;
import com.example.humin.crf_app.core.wallpaper.activity.Wallpaper2RowActivity;
import com.example.humin.crf_app.core.wallpaper.activity.Wallpaper3RowActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;

    private Button mButtonActivity1, mButtonActivity2, mButtonActivity3, mButtonActivity4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_acivity);
        mContext=getApplicationContext();

        initUI();
    }

    private void initUI(){

        mButtonActivity1 = findViewById(R.id.button_1);
        mButtonActivity2 = findViewById(R.id.button_2);
        mButtonActivity3 = findViewById(R.id.button_3);
        mButtonActivity4 = findViewById(R.id.button_4);

        mButtonActivity1.setOnClickListener(this);
        mButtonActivity2.setOnClickListener(this);
        mButtonActivity3.setOnClickListener(this);
        mButtonActivity4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.button_1:
                i = new Intent(mContext, Wallpaper2RowActivity.class);
                startActivity(i);
                break;
            case R.id.button_2:
                i = new Intent(mContext, Wallpaper3RowActivity.class);
                startActivity(i);
                break;
            case R.id.button_3:
                i = new Intent(mContext, QuestionActivity.class);
                startActivity(i);
                break;
            case R.id.button_4:
                i = new Intent(mContext, LoginActivity.class);
                startActivity(i);
                break;
        }
    }
}
