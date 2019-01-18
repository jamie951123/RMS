package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.QuantityProfileModel;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class QuantityProfileCombine extends HomeCombine<QuantityProfileModel> {

    public QuantityProfileCombine(Class<QuantityProfileModel> classType) {
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
