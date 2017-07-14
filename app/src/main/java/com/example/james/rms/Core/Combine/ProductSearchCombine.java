package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.Core.SearchObject.ProductSearchObject;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class ProductSearchCombine extends HomeCombine<ProductSearchObject> {

    public ProductSearchCombine(Class<ProductSearchObject> classType) {
        super(classType);
    }

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
}
