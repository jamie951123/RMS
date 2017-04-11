package com.example.james.rms.ProductPool;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by james on 5/2/2017.
 */

public class ProductCombine {

    public String combine_partyId(String partyId) {
        String result="";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("partyId", partyId);
            jsonArray.put(jsonObject);
            result = jsonArray.toString();
        }catch (JSONException e){

        }
        Log.v("asd" , "combine_partyId : " + result);
        return result;
    }

    public String combine_AddProduct(String productCode,String productName,
                                     String descriptionCN, String descriptionEN, String remark,
                                     String createDate,String partyId){
        String result="";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productCode",productCode);
            jsonObject.put("productName",productName);
            jsonObject.put("productDescriptionCH",descriptionCN);
            jsonObject.put("productDescriptionEN",descriptionEN);
            jsonObject.put("remark",remark);
            jsonObject.put("createDate",createDate);;
            jsonObject.put("partyId",partyId);
            jsonArray.put(jsonObject);
            result = jsonArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("asd" , "combine_AddProduct : " + result);
        return result;
    }
}
