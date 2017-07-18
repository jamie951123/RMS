package com.example.james.rms.CommonProfile.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.LoginModel;
import com.example.james.rms.Core.Model.UserProfile;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by james on 13/2/2017.
 */

public class MyPreferences {
    private Context context;
    private String preferencesKey;
    private SharedPreferences sharedPreferences;

    public MyPreferences(Context context, String preferencesKey) {
        this.context = context;
        this.preferencesKey = preferencesKey;
        this.sharedPreferences = this.context.getSharedPreferences(this.preferencesKey , this.context.MODE_PRIVATE);
    }


    public void setPreferences_loginInformation(String username, String password){
        clear();
        sharedPreferences.edit().putString("username" , username).apply();
        sharedPreferences.edit().putString("password" , password).apply();

    }
    public void setPreferences_loginInformation(LoginModel loginModel){
//        clear_loginInformation();
        sharedPreferences.edit().putString("loginStatus" , loginModel.getLoginStatus()).apply();
        sharedPreferences.edit().putString("loginMessage" , loginModel.getLoginMessage()).apply();
        sharedPreferences.edit().putLong("userProfileId" , loginModel.getUserProfile().getUserProfileId()).apply();
        sharedPreferences.edit().putString("username" , loginModel.getUserProfile().getUsername()).apply();
        sharedPreferences.edit().putString("password" , loginModel.getUserProfile().getPassword()).apply();
        sharedPreferences.edit().putString("partyId" , loginModel.getUserProfile().getPartyId()).apply();
        sharedPreferences.edit().putString("status" , loginModel.getUserProfile().getStatus()).apply();
        sharedPreferences.edit().putString("createDate" , ObjectUtil.dateToString(loginModel.getUserProfile().getCreateDate())).apply();
        sharedPreferences.edit().putString("closeDate" , ObjectUtil.dateToString(loginModel.getUserProfile().getCloseDate())).apply();
    }

    public void setPreferences_loginInformation(UserProfile userProfile){
//        clear_loginInformation();
        sharedPreferences.edit().putLong("userProfileId" , userProfile.getUserProfileId()).apply();
        sharedPreferences.edit().putString("username" , userProfile.getUsername()).apply();
        sharedPreferences.edit().putString("password" , userProfile.getPassword()).apply();
        sharedPreferences.edit().putString("partyId" , userProfile.getPartyId()).apply();
        sharedPreferences.edit().putString("status" , userProfile.getStatus()).apply();
        sharedPreferences.edit().putString("createDate" , ObjectUtil.dateToString(userProfile.getCreateDate())).apply();
        sharedPreferences.edit().putString("closeDate" , ObjectUtil.dateToString(userProfile.getCloseDate())).apply();
    }

    public LinkedHashMap<String,String> getPreferences_loginInformation(){
        String username = sharedPreferences.getString("username" , "");
        String password = sharedPreferences.getString("password" , "");
        String partyId  = sharedPreferences.getString("partyId" , "");

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        if(ObjectUtil.isNotNullEmpty(username))map.put("username",username);
        if(ObjectUtil.isNotNullEmpty(password))map.put("password",password);
        if(ObjectUtil.isNotNullEmpty(partyId))map.put("partyId",partyId);
        return map;
    }

    public LinkedHashMap<String,String> getPreferences_PartyId(){
        String partyId =  sharedPreferences.getString("partyId" , "");
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("partyId",partyId);
        return map;
    }

    public boolean remove_partyId(){
        return sharedPreferences.edit().remove("partyId").commit();
    }


    //Localization
    public LinkedHashMap<String,String> getPreferences_Localization(){
        String language = sharedPreferences.getString("language" , "");
        String country = sharedPreferences.getString("country" , "");

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        if(ObjectUtil.isNotNullEmpty(language))map.put("language",language);
        if(ObjectUtil.isNotNullEmpty(country))map.put("country",country);
        return map;
    }

    public void setPreferences_Localization(String language, String country){
        clear();
        sharedPreferences.edit().putString("language" , language).apply();
        sharedPreferences.edit().putString("country" , country).apply();
    }

    public void clear(){
        sharedPreferences.edit().clear().commit();
    }

}
