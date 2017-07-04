package com.example.james.rms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 16/4/2017.
 */

public class Facebook extends AppCompatActivity{
//    @BindView(R.id.facebooklogin)
//    LoginButton login;
    @BindView(R.id.status)
    TextView status;

    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.facebook);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
//        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                status.setText("Successful");
//            }
//
//            @Override
//            public void onCancel() {
//                status.setText("Cancel");
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                status.setText("Error");
//            }
//        });
    }
}
