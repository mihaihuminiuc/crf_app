package com.example.humin.crf_app.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.humin.crf_app.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;

    private Button mButtonActivity1, mButtonActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_wallpaper_acivity);
        mContext=getApplicationContext();

        initUI();
    }

    private void initUI(){

        mButtonActivity1 = findViewById(R.id.button_1);
        mButtonActivity2 = findViewById(R.id.button_2);

        mButtonActivity1.setOnClickListener(this);
        mButtonActivity2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.button_1:
                i = new Intent(mContext, WallpaperActivity2Row.class);
                startActivity(i);
                break;
            case R.id.button_2:
                i = new Intent(mContext, WallpaperActivity3Row.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

}
