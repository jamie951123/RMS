package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.ReceivingItemModel;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class ReceivingItemCombine extends HomeCombine<ReceivingItemModel> {

    public ReceivingItemCombine(Class<ReceivingItemModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        ReceivingItemModel receivingItemModel = new ReceivingItemModel();
        receivingItemModel.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(receivingItemModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
