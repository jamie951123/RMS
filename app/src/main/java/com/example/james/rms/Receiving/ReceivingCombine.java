package com.example.james.rms.Receiving;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.SearchObject.ReceivingSearchObject;
import com.google.gson.Gson;

/**
 * Created by james on 26/2/2017.
 */

public class ReceivingCombine {

    public static String combine_partyId(String partyId) {
        ReceivingSearchObject receivingSearchObject = new ReceivingSearchObject();
        receivingSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(receivingSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String itemModelToJson(ReceivingItemModel receivingItemModel){
        String result = null;
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(receivingItemModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static ReceivingOrderModel jsonToOrderModel(String receivingOrderModel_json){
        ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
        try{
            Gson gson = GsonUtil.fromStringJson();
            receivingOrderModel = gson.fromJson(receivingOrderModel_json,ReceivingOrderModel.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return receivingOrderModel;
    }

}
