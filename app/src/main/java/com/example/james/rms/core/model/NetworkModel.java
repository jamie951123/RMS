package com.example.james.rms.core.model;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jamie on 15/7/2017.
 */

public class NetworkModel {

    private AppCompatActivity appCompatActivity;

    public NetworkModel(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }
}
