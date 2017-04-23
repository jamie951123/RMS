package com.example.james.rms.Receiving;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.SearchObject.ReceivingSearchObject;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by james on 26/2/2017.
 */

public class ReceivingCombine {

    public String combine_partyId(String partyId) {
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

}
