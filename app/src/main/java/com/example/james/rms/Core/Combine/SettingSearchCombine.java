package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.SearchObject.SettingSearchObject;
import com.google.gson.Gson;

/**
 * Created by jamie on 2017/5/4.
 */

public class SettingSearchCombine extends HomeCombine<SettingSearchObject>{

    public SettingSearchCombine(Class<SettingSearchObject> classType) {
        super(classType);
    }

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
