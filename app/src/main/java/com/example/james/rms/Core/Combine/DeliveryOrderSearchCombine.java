package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.SearchObject.DeliveryOrderSearchObject;
import com.google.gson.Gson;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryOrderSearchCombine extends HomeCombine<DeliveryOrderSearchObject> {

    public DeliveryOrderSearchCombine(Class<DeliveryOrderSearchObject> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        DeliveryOrderSearchObject deliveryOrderSearchObject = new DeliveryOrderSearchObject();
        deliveryOrderSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(deliveryOrderSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String combine_partyIdAndStatus(String partyId, Status status){
        DeliveryOrderSearchObject deliveryOrderSearchObject = new DeliveryOrderSearchObject();
        deliveryOrderSearchObject.setPartyId(partyId);
        deliveryOrderSearchObject.setStatus(status);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(deliveryOrderSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
