package com.example.james.rms.Inventory;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.SearchObject.InventorySearchObject;
import com.example.james.rms.Core.SearchObject.ReceivingSearchObject;
import com.google.gson.Gson;

/**
 * Created by jamie on 2017/4/28.
 */

public class InventoryCombine {

    public static String combine_partyId(String partyId) {
        InventorySearchObject inventorySearchObject = new InventorySearchObject();
        inventorySearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(inventorySearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
