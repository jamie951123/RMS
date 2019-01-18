package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.ReceivingOrderModel;
import com.example.james.rms.core.search_object.ReceivingOrderSearchObject;
import com.google.gson.Gson;

/**
 * Created by james on 26/2/2017.
 */

public class ReceivingOrderCombine extends HomeCombine<ReceivingOrderModel>{

    public ReceivingOrderCombine(Class<ReceivingOrderModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        ReceivingOrderSearchObject receivingOrderSearchObject = new ReceivingOrderSearchObject();
        receivingOrderSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(receivingOrderSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
