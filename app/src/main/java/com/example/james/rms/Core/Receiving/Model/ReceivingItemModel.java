package com.example.james.rms.Core.Receiving.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jamie on 9/4/2017.
 */

public class ReceivingItemModel {
    private Long receivingID;
    private Long productId;
    private String itemStatus;
    private String itemCreateDate;
    private String itemReceivingDate;
    private BigDecimal itemGrossWeight;
    private String itemGrossWeightUnit;
    private Integer itemQty;
    private String itemQtyUnit;
    private String itemRemark;
    private String partyId;
    private String orderId;

    @Override
    public String toString() {
        return "ReceivingItemModel{" +
                "receivingID='" + receivingID + '\'' +
                ", productId='" + productId + '\'' +
                ", itemStatus='" + itemStatus + '\'' +
                ", itemCreateDate='" + itemCreateDate + '\'' +
                ", itemReceivingDate='" + itemReceivingDate + '\'' +
                ", itemGrossWeight=" + itemGrossWeight +
                ", itemGrossWeightUnit='" + itemGrossWeightUnit + '\'' +
                ", itemQty=" + itemQty +
                ", itemQtyUnit='" + itemQtyUnit + '\'' +
                ", itemRemark='" + itemRemark + '\'' +
                ", partyId='" + partyId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Long getReceivingID() {
        return receivingID;
    }

    public void setReceivingID(Long receivingID) {
        this.receivingID = receivingID;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemCreateDate() {
        return itemCreateDate;
    }

    public void setItemCreateDate(String itemCreateDate) {
        this.itemCreateDate = itemCreateDate;
    }

    public String getItemReceivingDate() {
        return itemReceivingDate;
    }

    public void setItemReceivingDate(String itemReceivingDate) {
        this.itemReceivingDate = itemReceivingDate;
    }

    public BigDecimal getItemGrossWeight() {
        return itemGrossWeight;
    }

    public void setItemGrossWeight(BigDecimal itemGrossWeight) {
        this.itemGrossWeight = itemGrossWeight;
    }

    public String getItemGrossWeightUnit() {
        return itemGrossWeightUnit;
    }

    public void setItemGrossWeightUnit(String itemGrossWeightUnit) {
        this.itemGrossWeightUnit = itemGrossWeightUnit;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemQtyUnit() {
        return itemQtyUnit;
    }

    public void setItemQtyUnit(String itemQtyUnit) {
        this.itemQtyUnit = itemQtyUnit;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
