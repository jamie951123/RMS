package com.example.james.rms.Setting;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.SearchObject.ProductSearchObject;
import com.example.james.rms.Core.SearchObject.SettingSearchObject;
import com.google.gson.Gson;

/**
 * Created by jamie on 2017/5/4.
 */

public class SettingCombine {
    public static String combine_partyId(String partyId) {
        SettingSearchObject settingSearchObject = new SettingSearchObject();
        settingSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(settingSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
