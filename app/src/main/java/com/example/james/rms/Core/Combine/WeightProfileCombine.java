package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.SearchObject.WeightSearchObject;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class WeightProfileCombine extends HomeCombine<WeightProfileModel>{

    public WeightProfileCombine(Class<WeightProfileModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        WeightSearchObject weightSearchObject = new WeightSearchObject();
        weightSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(weightSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
