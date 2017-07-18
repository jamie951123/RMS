package com.example.james.rms.CommonProfile;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.Controller.NavigationController;

import java.util.Locale;

/**
 * Created by Jamie on 18/7/2017.
 */

public class Localization {


    public static void setLanguage(Context context, String languageCode,String country){
        MyPreferences myPreferences = new MyPreferences(context, PreferencesKey.localization);

        Locale mLocale = new Locale(languageCode,country);
        Locale.setDefault(mLocale);
        Configuration config = context.getResources().getConfiguration();
        if (!config.locale.equals(mLocale))
        {
            config.locale = mLocale;
            context.getResources().updateConfiguration(config, null);
        }
        Log.v("asd","Curreny Language :" + Locale.getDefault().getLanguage());
        Log.v("asd","Curreny Country :" +  Locale.getDefault().getCountry());
        myPreferences.setPreferences_Localization(Locale.getDefault().getLanguage(),Locale.getDefault().getCountry());
    }

}
