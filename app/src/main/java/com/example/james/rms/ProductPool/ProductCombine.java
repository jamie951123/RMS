package com.example.james.rms.ProductPool;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.SearchObject.ProductSearchObject;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by james on 5/2/2017.
 */

public class ProductCombine {

    public static String combine_partyId(String partyId) {
        ProductSearchObject productSearchObject = new ProductSearchObject();
        productSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(productSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String combine_AddProduct(String productCode,String productName,
                                     String descriptionCN, String descriptionEN, String remark,
                                     Date createDate,String partyId,Long weightId,Long quantityId){
        String result="";
        ProductModel productModel = new ProductModel();
        productModel.setProductCode(productCode);
        productModel.setProductName(productName);
        productModel.setProductDescriptionCH(descriptionCN);
        productModel.setProductDescriptionEN(descriptionEN);
        productModel.setRemark(remark);
        productModel.setCreateDate(createDate);
        productModel.setPartyId(partyId);
        productModel.setStatus(Status.PROGRESS.name());
        productModel.setWeightId(weightId);
        productModel.setQuantityId(quantityId);
        Gson gson = GsonUtil.toJson();
        result = gson.toJson(productModel);
        return result;
    }

    public static String modelToJson(ProductModel productModel){
        String result = null;
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(productModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static ProductModel jsonToModel(String json){
        ProductModel productModel = new ProductModel();
        try{
            Gson gson = GsonUtil.fromStringJson();
            productModel = gson.fromJson(json,ProductModel.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productModel;
    }
}
