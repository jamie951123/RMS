package com.example.james.rms.CommonProfile.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Login.Model.LoginModel;

import java.util.HashMap;

/**
 * Created by james on 13/2/2017.
 */

public class LoginPreferences {
    private Context context;
    private String preferencesKey;
    private Integer preferencesStatus;


    public LoginPreferences(Context context, String preferencesKey, Integer preferencesStatus) {
        this.context = context;
        this.preferencesKey = preferencesKey;
        this.preferencesStatus = preferencesStatus;
    }

    public void setPreferences_loginInformation(LoginModel loginModel){
        SharedPreferences sharedLogin = context.getSharedPreferences(preferencesKey , context.MODE_PRIVATE);
        sharedLogin.edit().clear();
        sharedLogin.edit().putString("loginMessage" , loginModel.loginMessage).apply();
        sharedLogin.edit().putString("userProfileId" , loginModel.userProfileId).apply();
        sharedLogin.edit().putString("username" , loginModel.username).apply();
        sharedLogin.edit().putString("password" , loginModel.password).apply();
        sharedLogin.edit().putString("partyId" , loginModel.partyId).apply();
        sharedLogin.edit().putString("status" , loginModel.status).apply();
        sharedLogin.edit().putString("createDate" , loginModel.createDate).apply();
        sharedLogin.edit().putString("closeDate" , loginModel.closeDate).apply();
    }

    public HashMap<String,String> getPreferences_loginInformation(){
        SharedPreferences sharedLogin = context.getSharedPreferences(preferencesKey , context.MODE_PRIVATE);
        String username = sharedLogin.getString("username" , "");
        String password = sharedLogin.getString("password" , "");
        String partyId  = sharedLogin.getString("partyId" , "");
        if(!ObjectUtil.isNotNullEmpty(username) && !ObjectUtil.isNotNullEmpty(password)) {
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        map.put("partyId",partyId);
        return map;
    }

    public void clear_loginInformation(){
        SharedPreferences sharedLogin = context.getSharedPreferences("loginInformation" , context.MODE_PRIVATE);
        sharedLogin.edit().clear();
    }

}
