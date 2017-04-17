package com.example.james.rms.ProductPool;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.google.gson.Gson;

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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("partyId", partyId);
            result = jsonObject.toString();
        }catch (JSONException e){

        }
        return result;
    }

    public String combine_AddProduct(String productCode,String productName,
                                     String descriptionCN, String descriptionEN, String remark,
                                     Date createDate,String partyId){
        String result="";
        ProductModel productModel = new ProductModel();
        productModel.setProductCode(productCode);
        productModel.setProductName(productName);
        productModel.setProductDescriptionCH(descriptionCN);
        productModel.setProductDescriptionEN(descriptionEN);
        productModel.setRemark(remark);
        productModel.setCreateDate(createDate);
        productModel.setPartyId(partyId);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productCode",productCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson = GsonUtil.toJson();
        result = gson.toJson(productModel);
        return result;
    }
}
