package com.example.james.rms.CommonProfile.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by james on 14/2/2017.
 */

public class NavPreferences {

    private Context context;
    private String preferencesKey;
    private Integer preferencesStatus;


    public NavPreferences(Context context, String preferencesKey, Integer preferencesStatus) {
        this.context = context;
        this.preferencesKey = preferencesKey;
        this.preferencesStatus = preferencesStatus;
    }

    public HashMap<String,String> getPreferences_NavPreferences(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesKey , preferencesStatus);
        String username =  sharedPreferences.getString("username" , "");
        String partyId =  sharedPreferences.getString("partyId" , "");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username",username);
//        map.put("password",password);
        map.put("partyId",partyId);
        return map;
    }

}
