package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.Status;
import com.example.james.rms.core.search_object.DeliveryItemSearchObject;
import com.google.gson.Gson;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryItemSearchCombine extends HomeCombine<DeliveryItemSearchObject> {

    public DeliveryItemSearchCombine(Class<DeliveryItemSearchObject> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        DeliveryItemSearchObject deliveryItemSearchObject = new DeliveryItemSearchObject();
        deliveryItemSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(deliveryItemSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String combine_partyIdAndStatus(String partyId, Status status){
        DeliveryItemSearchObject deliveryItemSearchObject = new DeliveryItemSearchObject();
        deliveryItemSearchObject.setPartyId(partyId);
        deliveryItemSearchObject.setStatus(status);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(deliveryItemSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
