package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.WeightProfileModel;
import com.example.james.rms.core.search_object.WeightSearchObject;
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
