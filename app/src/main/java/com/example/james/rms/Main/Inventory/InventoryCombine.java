package com.example.james.rms.Main.Inventory;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.SearchObject.InventorySearchObject;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by jamie on 2017/4/28.
 */

public class InventoryCombine {

    public static String combine_partyId(String partyId) {
        InventorySearchObject inventorySearchObject = new InventorySearchObject();
        inventorySearchObject.setPartyId(partyId);
        String result = convertToGson(inventorySearchObject);
        return result;
    }

    public static String combine_partyIdAndStatus(String partyId, Status status){
        InventorySearchObject inventorySearchObject = new InventorySearchObject();
        inventorySearchObject.setPartyId(partyId);
        inventorySearchObject.setStatus(status);
        String result = convertToGson(inventorySearchObject);
        return result;
    }
public static String convertToGson(InventorySearchObject inventorySearchObject){
    String result="";
    try{
        Gson gson = GsonUtil.toJson();
        result = gson.toJson(inventorySearchObject);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}

    public static String inventoryToGson(InventoryModel inventoryList){
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(inventoryList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
}
    public static String inventorysToGson(List<InventoryModel> inventoryLists){
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(inventoryLists);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
