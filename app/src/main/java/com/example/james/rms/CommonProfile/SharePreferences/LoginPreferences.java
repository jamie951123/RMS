package com.example.james.rms.CommonProfile.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.LoginModel;

import java.util.HashMap;

/**
 * Created by james on 13/2/2017.
 */

public class LoginPreferences {
    private Context context;
    private String preferencesKey;
    private Integer preferencesStatus;
    private SharedPreferences sharedLogin;

    public LoginPreferences(Context context, String preferencesKey, Integer preferencesStatus) {
        this.context = context;
        this.preferencesKey = preferencesKey;
        this.preferencesStatus = preferencesStatus;
        this.sharedLogin = this.context.getSharedPreferences(this.preferencesKey , this.context.MODE_PRIVATE);
    }

    public void setPreferences_loginInformation(){

    }

    public void setPreferences_loginInformation(String username, String password){
        clear_loginInformation();
        sharedLogin.edit().putString("username" , username).apply();
        sharedLogin.edit().putString("password" , password).apply();

    }
    public void setPreferences_loginInformation(LoginModel loginModel){
//        clear_loginInformation();
        sharedLogin.edit().putString("loginStatus" , loginModel.getLoginStatus()).apply();
        sharedLogin.edit().putString("loginMessage" , loginModel.getLoginMessage()).apply();
        sharedLogin.edit().putLong("userProfileId" , loginModel.getUserProfile().getUserProfileId()).apply();
        sharedLogin.edit().putString("username" , loginModel.getUserProfile().getUsername()).apply();
        sharedLogin.edit().putString("password" , loginModel.getUserProfile().getPassword()).apply();
        sharedLogin.edit().putString("partyId" , loginModel.getUserProfile().getPartyId()).apply();
        sharedLogin.edit().putString("status" , loginModel.getUserProfile().getStatus()).apply();
        sharedLogin.edit().putString("createDate" , ObjectUtil.dateToString(loginModel.getUserProfile().getCreateDate())).apply();
        sharedLogin.edit().putString("closeDate" , ObjectUtil.dateToString(loginModel.getUserProfile().getCloseDate())).apply();
    }

    public HashMap<String,String> getPreferences_loginInformation(){
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
        sharedLogin.edit().clear().commit();
    }

}
