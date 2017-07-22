package com.example.james.rms.Core.Model;

import com.example.james.rms.Core.SearchObject.HomeSearchObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryItemModel {

    private Long deliveryItemId;

    private String itemStatus;

    private Long orderId;

    private String partyId;

    private Date itemStockOutDate;

    private Date itemCreateDate;

    private String itemCreateBy;

    private Date itemLastModifiedDate;

    private String itemLastModifiedBy;

    private Date itemCloseDate;

    private String itemRemark;

    private BigDecimal itemGrossWeight;

    private Integer itemQty;

    private Long receivingId;

    private DeliveryOrderModel deliveryOrder;

    private ReceivingItemModel receivingItem;

    public DeliveryItemModel newDeliveryItemModel(){
        DeliveryItemModel deliveryItemModel = new DeliveryItemModel();
        deliveryItemModel.deliveryItemId = this.deliveryItemId;
        deliveryItemModel.itemStatus = this.itemStatus;
        deliveryItemModel.orderId = this.orderId;
        deliveryItemModel.partyId = this.partyId;
        deliveryItemModel.itemStockOutDate = this.itemStockOutDate;
        deliveryItemModel.itemLastModifiedDate = this.itemLastModifiedDate;
        deliveryItemModel.itemLastModifiedBy = this.itemLastModifiedBy;
        deliveryItemModel.itemCloseDate = this.itemCloseDate;
        deliveryItemModel.itemRemark = this.itemRemark;
        deliveryItemModel.itemQty = this.itemQty;
        deliveryItemModel.itemGrossWeight = this.itemGrossWeight;
        deliveryItemModel.receivingId = this.receivingId;
        deliveryItemModel.deliveryOrder = this.deliveryOrder;
        deliveryItemModel.receivingItem = this.receivingItem;

        return deliveryItemModel;
    }

    public Long getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(Long deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Date getItemStockOutDate() {
        return itemStockOutDate;
    }

    public void setItemStockOutDate(Date itemStockOutDate) {
        this.itemStockOutDate = itemStockOutDate;
    }

    public Date getItemCreateDate() {
        return itemCreateDate;
    }

    public void setItemCreateDate(Date itemCreateDate) {
        this.itemCreateDate = itemCreateDate;
    }

    public String getItemCreateBy() {
        return itemCreateBy;
    }

    public void setItemCreateBy(String itemCreateBy) {
        this.itemCreateBy = itemCreateBy;
    }

    public Date getItemLastModifiedDate() {
        return itemLastModifiedDate;
    }

    public void setItemLastModifiedDate(Date itemLastModifiedDate) {
        this.itemLastModifiedDate = itemLastModifiedDate;
    }

    public String getItemLastModifiedBy() {
        return itemLastModifiedBy;
    }

    public void setItemLastModifiedBy(String itemLastModifiedBy) {
        this.itemLastModifiedBy = itemLastModifiedBy;
    }

    public Date getItemCloseDate() {
        return itemCloseDate;
    }

    public void setItemCloseDate(Date itemCloseDate) {
        this.itemCloseDate = itemCloseDate;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
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

    public Long getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Long receivingId) {
        this.receivingId = receivingId;
    }

    public DeliveryOrderModel getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrderModel deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public ReceivingItemModel getReceivingItem() {
        return receivingItem;
    }

    public void setReceivingItem(ReceivingItemModel receivingItem) {
        this.receivingItem = receivingItem;
    }

    @Override
    public String toString() {
        return "DeliveryItemModel{" +
                "deliveryItemId=" + deliveryItemId +
                ", itemStatus='" + itemStatus + '\'' +
                ", orderId=" + orderId +
                ", partyId='" + partyId + '\'' +
                ", itemStockOutDate=" + itemStockOutDate +
                ", itemCreateDate=" + itemCreateDate +
                ", itemCreateBy='" + itemCreateBy + '\'' +
                ", itemLastModifiedDate=" + itemLastModifiedDate +
                ", itemLastModifiedBy='" + itemLastModifiedBy + '\'' +
                ", itemCloseDate=" + itemCloseDate +
                ", itemRemark='" + itemRemark + '\'' +
                ", itemGrossWeight=" + itemGrossWeight +
                ", itemQty=" + itemQty +
                ", receivingId=" + receivingId +
                ", receivingItem=" + receivingItem +
                '}';
    }
}