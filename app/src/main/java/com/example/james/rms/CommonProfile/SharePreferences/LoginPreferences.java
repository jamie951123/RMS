package com.example.james.rms.CommonProfile.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.LoginModel;
import com.example.james.rms.Core.Model.UserProfile;

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

    public void setPreferences_loginInformation(UserProfile userProfile){
//        clear_loginInformation();
        sharedLogin.edit().putLong("userProfileId" , userProfile.getUserProfileId()).apply();
        sharedLogin.edit().putString("username" , userProfile.getUsername()).apply();
        sharedLogin.edit().putString("password" , userProfile.getPassword()).apply();
        sharedLogin.edit().putString("partyId" , userProfile.getPartyId()).apply();
        sharedLogin.edit().putString("status" , userProfile.getStatus()).apply();
        sharedLogin.edit().putString("createDate" , ObjectUtil.dateToString(userProfile.getCreateDate())).apply();
        sharedLogin.edit().putString("closeDate" , ObjectUtil.dateToString(userProfile.getCloseDate())).apply();
    }

    public HashMap<String,String> getPreferences_loginInformation(){
        String username = sharedLogin.getString("username" , "");
        String password = sharedLogin.getString("password" , "");
        String partyId  = sharedLogin.getString("partyId" , "");

        HashMap<String, String> map = new HashMap<String, String>();
        if(ObjectUtil.isNotNullEmpty(username))map.put("username",username);
        if(ObjectUtil.isNotNullEmpty(password))map.put("password",password);
        if(ObjectUtil.isNotNullEmpty(partyId))map.put("partyId",partyId);
        return map;
    }



    public boolean remove_partyId(){
        return sharedLogin.edit().remove("partyId").commit();
    }
    public void clear_loginInformation(){
        sharedLogin.edit().clear().commit();
    }

}
