package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
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
