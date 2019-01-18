package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.DeliveryItemModel;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryItemCombine extends HomeCombine<DeliveryItemModel> {

    public DeliveryItemCombine(Class<DeliveryItemModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        DeliveryItemModel deliveryItemModel = new DeliveryItemModel();
        deliveryItemModel.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(deliveryItemModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
