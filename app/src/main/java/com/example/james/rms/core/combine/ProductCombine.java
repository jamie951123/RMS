package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.ProductModel;
import com.example.james.rms.core.model.Status;
import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by james on 5/2/2017.
 */

public class ProductCombine extends HomeCombine<ProductModel>{

    public ProductCombine(Class<ProductModel> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        ProductModel productModel = new ProductModel();
        productModel.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(productModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String combine_AddProduct(Long productId,String productCode,String productName,
                                     String descriptionCN, String descriptionEN, String remark,
                                     Date createDate,String partyId,Long weightId,Long quantityId){
        String result="";
        ProductModel productModel = new ProductModel();
        productModel.setProductId(productId);
        productModel.setProductCode(productCode);
        productModel.setProductName(productName);
        productModel.setProductDescriptionCH(descriptionCN);
        productModel.setProductDescriptionEN(descriptionEN);
        productModel.setRemark(remark);
        productModel.setCreateDate(createDate);
        productModel.setPartyId(partyId);
        productModel.setCreateBy(partyId);
        productModel.setStatus(Status.PROGRESS.name());
        productModel.setWeightId(weightId);
        productModel.setQuantityId(quantityId);
        Gson gson = GsonUtil.toJson();
        result = gson.toJson(productModel);
        return result;
    }
}
