package com.example.james.rms.Core.Product.Dao;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Product.Model.ProductInsertModel;
import com.example.james.rms.Core.Product.Model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public class ProductSplit {

    public List<ProductModel> convertProductPool(String result) {
        List<ProductModel> productArray = new ArrayList<>();
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        try {
            jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                ProductModel productModel = new ProductModel();
                jsonObject = jsonArray.getJSONObject(i);
                String productId = jsonObject.getString("productId");
                String productCode = jsonObject.getString("productCode");
                String productName = jsonObject.getString("productName");
                String partyId = jsonObject.getString("partyId");
                String status = jsonObject.getString("status");
                String createDate = jsonObject.getString("createDate");
                String closeDate = jsonObject.getString("closeDate");
                String remark = jsonObject.getString("remark");
                String productDescriptionEN = jsonObject.getString("productDescriptionEN");
                String productDescriptionCH = jsonObject.getString("productDescriptionCH");
                productModel.setProductId(productId);
                productModel.setProductCode(productCode);
                productModel.setProductName(productName);
                productModel.setPartyId(partyId);
                productModel.setStatus(status);
                productModel.setCreateDate(createDate);
                productModel.setCloseDate(closeDate);
                productModel.setRemark(remark);
                productModel.setProductDescriptionEN(productDescriptionEN);
                productModel.setProductDescriptionCH(productDescriptionCH);
                productArray.add(productModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productArray;
    }

    public ProductInsertModel convertProductInsertResponse(String result){
        ProductInsertModel productInsertModel = new ProductInsertModel();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(result);
            String selection = jsonObject.getString("selection");
            String insertMessage = jsonObject.getString("insertMessage");
            String missingField = jsonObject.getString("missingField");
            List<String> missingFieldArray = new ArrayList<>();
            if(ObjectUtil.isNotNullEmpty(missingField)) {
                JSONArray jsonArray = new JSONArray(missingField);
                for (int i =0; i<jsonArray.length();i++){
                    missingFieldArray.add(jsonArray.get(i).toString());
                }
            }
            productInsertModel.setSelection(selection);
            productInsertModel.setInsertMessage(insertMessage);
            productInsertModel.setMissingField(null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productInsertModel;
    }
}
