package com.example.james.rms.CommonProfile.Language

import android.content.Context
import android.content.res.Configuration
import android.util.Log

import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey
import com.example.james.rms.CommonProfile.Util.ObjectUtil
import com.example.james.rms.R

import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Locale

/**
 * Created by Jamie on 18/7/2017.
 */

object LocalizationUtil {


    fun setLanguage(context: Context, languageCode: String, country: String) {
        val myPreferences = MyPreferences(context, PreferencesKey.localization)

        val mLocale = Locale(languageCode, country)
        Locale.setDefault(mLocale)
        val config = context.resources.configuration
        if (config.locale != mLocale) {
            config.locale = mLocale
            context.resources.updateConfiguration(config, null)
        }
        Log.v("asd", "Curreny Language :" + Locale.getDefault().language)
        Log.v("asd", "Curreny Country :" + Locale.getDefault().country)
        myPreferences.setPreferences_Localization(Locale.getDefault().language, Locale.getDefault().country)
    }

    fun checkCurrentLocalization(context: Context, perferencesKey: String) {
        val languagePreferences = MyPreferences(context, perferencesKey)
        val language_Map = languagePreferences.preferences_Localization
        var current_language = Locale.getDefault().language
        var current_country = Locale.getDefault().country
        if (language_Map == null || language_Map.size <= 1) {
            languagePreferences.setPreferences_Localization(current_language, current_country)
        } else {
            current_language = language_Map["language"]
            current_country = language_Map["country"]
            LocalizationUtil.setLanguage(context, current_language, current_country)
        }
    }

    fun getCodeToFullLanguage(context: Context, code: String): String? {
        var full_language: String? = null
        if (ObjectUtil.isNotNullEmpty(code)) {
            if (code.equals(Locale.ENGLISH.language, ignoreCase = true)) {
                full_language = context.getString(R.string.english)
            } else if (code.equals(Locale.CHINESE.language, ignoreCase = true)) {
                full_language = context.getString(R.string.chinese)
            }

        }
        return full_language
    }

    fun getLocalizationModel(context: Context): List<LocalizationModel> {
        val languages = ArrayList<LocalizationModel>()
        var localizationModel = LocalizationModel()

        localizationModel.languageCode = Locale.ENGLISH.language
        localizationModel.countryCode = Locale.ENGLISH.country
        localizationModel.fullName = context.getString(R.string.english)
        languages.add(localizationModel)

        localizationModel = LocalizationModel()
        localizationModel.languageCode = Locale.CHINESE.language
        localizationModel.countryCode = Locale.CHINESE.country
        localizationModel.fullName = context.getString(R.string.chinese)
        languages.add(localizationModel)
        return languages
    }
}
