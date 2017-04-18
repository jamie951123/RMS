package com.example.james.rms.Receiving;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Receiving.Model.ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;
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

    public String combine_partyIdAndCreateDate(String partyId, String createDate) {
        String result="";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("partyId", partyId);
            jsonObject.put("createDate", createDate);
            jsonArray.put(jsonObject);
            result = jsonArray.toString();
        }catch (JSONException e){

        }
        return result;
    }
    public String combine_AddReceivingOrder(ReceivingOrderModel receivingOrderModel){
        String partyId        = receivingOrderModel.getPartyId();
        String receivingDate  = receivingOrderModel.getReceivingDate();
        String createDate     = receivingOrderModel.getCreateDate();
        String remark         = receivingOrderModel.getRemark();
        String status         = receivingOrderModel.getStatus();
        Integer actualQty     = receivingOrderModel.getActualQty();
        Integer estimateQty   = receivingOrderModel.getEstimateQty();
        Integer itemQty       = receivingOrderModel.getItemQty();
        String result="";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("partyId", partyId);
            jsonObject.put("receivingDate", receivingDate);
            jsonObject.put("createDate", createDate);
            jsonObject.put("remark", remark);
            jsonObject.put("status", status);
            jsonObject.put("actualQty", actualQty);
            jsonObject.put("estimateQty", estimateQty);
            jsonObject.put("itemQty", itemQty);
            jsonArray.put(jsonObject);
            result = jsonArray.toString();
        }catch (JSONException e){

        }
        return result;
    }

    public String combine_AddReceivingItem(List<ReceivingItemModel> receivingItemModel) {
        String result = "";
        JSONArray jsonArray = new JSONArray();
        for (ReceivingItemModel item : receivingItemModel) {
            JSONObject jsonObject           = new JSONObject();
            Long productId                  = item.getProductId();
            String itemStatus               = item.getItemStatus();
            Date itemCreateDate             = item.getItemCreateDate();
            Date itemReceivingDate          = item.getItemReceivingDate();
            BigDecimal itemGrossWeight      = item.getItemGrossWeight();
            String itemGrossWeightUnit      = item.getItemGrossWeightUnit();
            Integer itemQty                 = item.getItemQty();
            String itemQtyUnit              = item.getItemQtyUnit();
            String itemRemark               = item.getItemRemark();
            String partyId                  = item.getPartyId();
            Long orderId                    = item.getOrderId();
            try {
                jsonObject.put("productId", productId);
                jsonObject.put("status", itemStatus);
                jsonObject.put("createDate", itemCreateDate);
                jsonObject.put("receivingDate", itemReceivingDate);
                jsonObject.put("grossWeight", itemGrossWeight);
                jsonObject.put("grossWeightUnit", itemGrossWeightUnit);
                jsonObject.put("itemQty", itemQty);
                jsonObject.put("itemQtyUnit", itemQtyUnit);
                jsonObject.put("remark", itemRemark);
                jsonObject.put("partyId", partyId);
                jsonObject.put("orderId", orderId);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {

            }
        }
        result = jsonArray.toString();
        return result;
    }

}
