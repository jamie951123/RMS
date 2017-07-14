package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.SearchObject.ReceivingOrderSearchObject;
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
