package com.example.humin.crf_app.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.app_events.AppEvents;
import com.example.humin.crf_app.model.UserCredentials;
import com.example.humin.crf_app.util.GlobalBus;

/**
 * Created by humin on 3/24/2018.
 */

public class LoginFragment extends Fragment implements View.OnClickListener{

    private Context mContext;
    private EditText usernameEdiText;
    private EditText passwordEdiText;
    private Button loginButton;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        setupList(view);
    }

    private void setupList(View view){
        usernameEdiText = view.findViewById(R.id.username);
        passwordEdiText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.send_button);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_button:

                UserCredentials userCredentials = new UserCredentials();
                userCredentials.setUsername(usernameEdiText.getText().toString());
                userCredentials.setPassword(passwordEdiText.getText().toString());

                GlobalBus.getBus().post(new AppEvents.Login(userCredentials));

                break;
        }
    }
}
