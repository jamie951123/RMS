package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.SearchObject.DeliverySearchObject;
import com.example.james.rms.Core.SearchObject.HomeSearchObject;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryOrderCombine extends HomeCombine<DeliveryOrderModel> {

    public DeliveryOrderCombine(Class<DeliveryOrderModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        DeliveryOrderModel deliveryOrderModel = new DeliveryOrderModel();
        deliveryOrderModel.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(deliveryOrderModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
