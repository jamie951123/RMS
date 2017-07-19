package com.example.james.rms.CommonProfile.Language;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jamie on 18/7/2017.
 */

public class LocalizationUtil {


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

        public static void checkCurrentLocalization(Context context,String perferencesKey){
        MyPreferences languagePreferences = new MyPreferences(context,perferencesKey);
        LinkedHashMap<String,String> language_Map = languagePreferences.getPreferences_Localization();
        String current_language = Locale.getDefault().getLanguage();
        String current_country = Locale.getDefault().getCountry();
        if(language_Map == null || language_Map.size() <= 1){
            languagePreferences.setPreferences_Localization(current_language,current_country);
        }else{
            current_language = language_Map.get("language");
            current_country = language_Map.get("country");
            LocalizationUtil.setLanguage(context,current_language,current_country);
        }
    }

    public static String getCodeToFullLanguage(Context context,String code){
        String full_language = null;
        if(ObjectUtil.isNotNullEmpty(code)){
            if(code.equalsIgnoreCase(Locale.ENGLISH.getLanguage())){
                full_language =  context.getString(R.string.english);
            }else if(code.equalsIgnoreCase(Locale.CHINESE.getLanguage())){
                full_language =  context.getString(R.string.chinese);
            }

    }
        return full_language;
    }

    public static List<LocalizationModel> getLocalizationModel(Context context){
        List<LocalizationModel> languages = new ArrayList<>();
        LocalizationModel localizationModel = new LocalizationModel();

        localizationModel.setLanguageCode(Locale.ENGLISH.getLanguage());
        localizationModel.setCountryCode(Locale.ENGLISH.getCountry());
        localizationModel.setFullName(context.getString(R.string.english));
        languages.add(localizationModel);

        localizationModel = new LocalizationModel();
        localizationModel.setLanguageCode(Locale.CHINESE.getLanguage());
        localizationModel.setCountryCode(Locale.CHINESE.getCountry());
        localizationModel.setFullName(context.getString(R.string.chinese));
        languages.add(localizationModel);
        return languages;
    }
}
