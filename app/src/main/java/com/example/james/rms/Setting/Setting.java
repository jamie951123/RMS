package com.example.james.rms.Setting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.james.rms.CommonProfile.Localization;
import com.example.james.rms.CommonProfile.LocationCode;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/7/2017.
 */

public class Setting extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.eng)
    Button eng;
    @BindView(R.id.language_btn)
    Button language_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        language_btn.setOnClickListener(this);
        eng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String language = null;
        String country = null;
        switch (v.getId()){
            case R.id.eng:
                language = LocationCode.english;
                country = LocationCode.america;
                break;
            case R.id.language_btn:
                language = LocationCode.chinese;
                country = LocationCode.hongkong;
                break;

        }
        Localization.setLanguage(this,language,country);
        Intent intent = new Intent();
        intent.setClass(this, NavigationController.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
