package com.example.james.rms.Core.Model;

import com.example.james.rms.Core.Model.ProductModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jamie on 9/4/2017.
 */

public class ReceivingItemModel {
    private Long receivingID;
    private Long productId;
    private Long orderId;
    private String itemStatus;
    private Date itemCreateDate;
    private Date itemReceivingDate;
    private BigDecimal itemGrossWeight;
    private Integer itemQty;
    private String itemRemark;
    private String partyId;
    private ProductModel product;

    public ReceivingItemModel() {
        this.product = new ProductModel();
    }

    @Override
    public String toString() {
        return "ReceivingItemModel{" +
                "receivingID=" + receivingID +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", itemStatus='" + itemStatus + '\'' +
                ", itemCreateDate=" + itemCreateDate +
                ", itemReceivingDate=" + itemReceivingDate +
                ", itemGrossWeight=" + itemGrossWeight +
                ", itemQty=" + itemQty +
                ", itemRemark='" + itemRemark + '\'' +
                ", partyId='" + partyId + '\'' +
                ", productModel=" + product +
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Date getItemCreateDate() {
        return itemCreateDate;
    }

    public void setItemCreateDate(Date itemCreateDate) {
        this.itemCreateDate = itemCreateDate;
    }

    public Date getItemReceivingDate() {
        return itemReceivingDate;
    }

    public void setItemReceivingDate(Date itemReceivingDate) {
        this.itemReceivingDate = itemReceivingDate;
    }

    public BigDecimal getItemGrossWeight() {
        return itemGrossWeight;
    }

    public void setItemGrossWeight(BigDecimal itemGrossWeight) {
        this.itemGrossWeight = itemGrossWeight;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
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

    public ProductModel getProductModel() {
        return product;
    }

    public void setProductModel(ProductModel product) {
        this.product = product;
    }
}
