package com.example.james.rms.Core.Receiving.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Receiving.Model.V_ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingSplit {
    public List<V_ReceivingItemModel> convertReceivingItem(String result){
        List<V_ReceivingItemModel> VReceivingItemModelList = new ArrayList<>();
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        try {
            jsonArray = new JSONArray(result);
            for(int i=0; i<jsonArray.length(); i++){
                V_ReceivingItemModel VReceivingItemModel = new V_ReceivingItemModel();
                jsonObject = jsonArray.getJSONObject(i);
                String receivingID = jsonObject.getString("receivingID");
                String productId = jsonObject.getString("productId");
                String status = jsonObject.getString("itemStatus");
                String createDate = jsonObject.getString("itemCreateDate");
                String receivingDate = jsonObject.getString("itemReceivingDate");
                BigDecimal grossWeight = ObjectUtil.stringToBigDecimal(jsonObject.getString("itemGrossWeight"));
                String grossWeightUnit = jsonObject.getString("itemGrossWeightUnit");
                String remark = jsonObject.getString("itemRemark");
                String partyId = jsonObject.getString("partyId");
                String orderId = jsonObject.getString("orderId");
                String productCode = jsonObject.getString("productCode");
                String productName = jsonObject.getString("productName");

                VReceivingItemModel.setReceivingID(receivingID);
                VReceivingItemModel.setProductId(productId);
                VReceivingItemModel.setItemStatus(status);
                VReceivingItemModel.setItemCreateDate(createDate);
                VReceivingItemModel.setItemGrossWeight(grossWeight);
                VReceivingItemModel.setItemGrossWeightUnit(grossWeightUnit);
                VReceivingItemModel.setItemRemark(remark);
                VReceivingItemModel.setPartyId(partyId);
                VReceivingItemModel.setItemReceivingDate(receivingDate);
                VReceivingItemModel.setOrderId(orderId);
                VReceivingItemModel.setProductCode(productCode);
                VReceivingItemModel.setProductName(productName);
                VReceivingItemModelList.add(VReceivingItemModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("asd","convertReceivingItem :" + VReceivingItemModelList);
        return VReceivingItemModelList;
    }

    public List<ReceivingOrderModel> convertReceivingOrder(String result){
        List<ReceivingOrderModel> receivingModelList = new ArrayList<>();
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        try {
            jsonArray = new JSONArray(result);
            for(int i=0; i<jsonArray.length(); i++){
                ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
                jsonObject = jsonArray.getJSONObject(i);
                String orderId          = jsonObject.getString("orderId");
                String partyId          = jsonObject.getString("partyId");
                String receivingDate    = jsonObject.getString("receivingDate");
                String remark           = jsonObject.getString("remark");
                String status           = jsonObject.getString("status");
                String createDate       = jsonObject.getString("createDate");
                String closeDate        = jsonObject.getString("closeDate");
                Integer actualQty       = ObjectUtil.stringToInteger(jsonObject.getString("actualQty"));
                Integer estimateQty     = ObjectUtil.stringToInteger(jsonObject.getString("estimateQty"));
                Integer itemQty         = ObjectUtil.stringToInteger(jsonObject.getString("itemQty"));

                receivingOrderModel.setOrderId(orderId);
                receivingOrderModel.setPartyId(partyId);
                receivingOrderModel.setReceivingDate(receivingDate);
                receivingOrderModel.setRemark(remark);
                receivingOrderModel.setStatus(status);
                receivingOrderModel.setCreateDate(createDate);
                receivingOrderModel.setCloseDate(closeDate);
                receivingOrderModel.setActualQty(actualQty);
                receivingOrderModel.setEstimateQty(estimateQty);
                receivingOrderModel.setItemQty(itemQty);
                receivingModelList.add(receivingOrderModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("asd","convertReceivingOrder :" +receivingModelList);
        return receivingModelList;
    }


}
