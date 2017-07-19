package com.example.james.rms.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.DialogModel;
import com.example.james.rms.CommonProfile.Language.LocalizationUtil;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/7/2017.
 */

public class Setting extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.setting_toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting_language)
    TextView setting_language;

    //
    private String current_language;
    private String full_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);

        //Language
        current_language = Locale.getDefault().getLanguage();
        full_language = LocalizationUtil.getCodeToFullLanguage(this,current_language);
        setting_language.setText(full_language);
        //
        setting_language.setOnClickListener(this);
        //
        setUpActionBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_language:
                DialogModel dialogModel = new DialogModel();
                dialogModel.setContext(this);
                dialogModel.setTitle(getString(R.string.title_choice_language));
                dialogModel.setItemId_str(current_language);
                dialogModel.setModeles(LocalizationUtil.getLocalizationModel(this));
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
