package com.example.james.rms.main.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.james.rms.BuildConfig;
import com.example.james.rms.common.DialogBox.ClassicDialog;
import com.example.james.rms.common.DialogBox.DialogModel;
import com.example.james.rms.common.Language.LocalizationUtil;
import com.example.james.rms.common.SharePreferences.MyPreferences;
import com.example.james.rms.constant.PreferencesKey;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.KeyModel;
import com.example.james.rms.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/7/2017.
 */

public class SettingAct extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.setting_toolbar)
    Toolbar toolbar;
    @BindView(R.id.txv_setting_language)
    TextView setting_language;
    @BindView(R.id.txv_setting_date_duration)
    TextView setting_date_duration;
    @BindView(R.id.txv_version)
    TextView txv_version;
    //
    private String current_language;
    private String full_language;

    //MyPreferences
    private MyPreferences settingPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);
        ButterKnife.bind(this);

        //Preference
        settingPreference = new MyPreferences(this, PreferencesKey.INSTANCE.getLocalization());

        String duration = ObjectUtil.intToString((Integer) settingPreference.getPreferences_Setting().get("dateRange"));

        //Language
        current_language = getCurrentAppLang();
        full_language = LocalizationUtil.INSTANCE.getCodeToFullLanguage(this,current_language);
        setting_language.setText(full_language);
        setting_date_duration.setText(ObjectUtil.isNotNullEmpty(duration)?duration+getString(R.string.label_days):"");
        //
        setting_language.setOnClickListener(this);
        txv_version.setText(BuildConfig.VERSION_NAME);
        //
        setUpActionBar();


    }
    private String getCurrentAppLang(){
        String lang = settingPreference.getPreferences_Localization().get("language");
        if (ObjectUtil.isNotNullEmpty(lang)){
            return lang;
        }else {
            return Locale.getDefault().getLanguage();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txv_setting_language:
                DialogModel dialogModel = new DialogModel();
                dialogModel.setContext(this);
                dialogModel.setTitle(getString(R.string.title_choice_language));
                dialogModel.setItemId_str(current_language);
                dialogModel.setModeles(LocalizationUtil.INSTANCE.getLocalizationModel(this));
                dialogModel.setKey(KeyModel.language);
                ClassicDialog.showSingleChoice(dialogModel);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
