package com.example.james.rms.CommonProfile.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Login.Model.LoginModel;

import java.util.HashMap;

/**
 * Created by james on 14/2/2017.
 */

public class PartyIdPreferences {
    private Context context;
    private String preferencesKey;
    private Integer preferencesStatus;


    public PartyIdPreferences(Context context, String preferencesKey, Integer preferencesStatus) {
        this.context = context;
        this.preferencesKey = preferencesKey;
        this.preferencesStatus = preferencesStatus;
    }


    public HashMap<String,String> getPreferences_PartyId(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesKey , preferencesStatus);
        String partyId =  sharedPreferences.getString("partyId" , "");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("partyId",partyId);
        return map;
    }

}
