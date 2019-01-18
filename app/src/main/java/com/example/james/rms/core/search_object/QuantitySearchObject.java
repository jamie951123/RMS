package com.example.james.rms.core.search_object;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.combine.HomeCombine;
import com.example.james.rms.core.model.QuantityProfileModel;
import com.google.gson.Gson;

/**
 * Created by jamie on 2017/5/7.
 */

public class QuantitySearchObject extends HomeCombine<QuantityProfileModel>{

    public QuantitySearchObject(Class<QuantityProfileModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        QuantityProfileModel quantityProfileModel = new QuantityProfileModel();
        quantityProfileModel.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(quantityProfileModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
