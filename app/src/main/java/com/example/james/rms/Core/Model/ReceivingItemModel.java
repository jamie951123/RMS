package com.example.james.rms.Core.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jamie on 9/4/2017.
 */

public class ReceivingItemModel {

    private Long receivingId;

    private Long productId;

    private String itemStatus;

    private Long orderId;

    private String partyId;

    private Date itemCreateDate;

    private String itemCreateBy;

    private Date itemReceivingDate;

    private Date itemLastModifiedDate;

    private String itemLModifiedBy;

    private BigDecimal itemGrossWeight;

    private Integer itemQty;

    private String itemRemark;

    private ProductModel product;

    public DeliveryItemModel newDeliveryItemModel(){
        DeliveryItemModel deliveryItemModel = new DeliveryItemModel();
        deliveryItemModel.setReceivingId(this.receivingId);
        deliveryItemModel.setPartyId(this.partyId);
        deliveryItemModel.setItemCreateBy(this.itemCreateBy);
        deliveryItemModel.setItemCreateDate(new Date());
        deliveryItemModel.setItemStatus(Status.PROGRESS.name());
        return deliveryItemModel;
    }
    public ReceivingItemModel newReceivingItemModel(){
        ReceivingItemModel receivingItemModel = new ReceivingItemModel();
        receivingItemModel.receivingId = this.receivingId;
        receivingItemModel.productId = this.productId;
        receivingItemModel.itemStatus = this.itemStatus;
        receivingItemModel.orderId = this.orderId;
        receivingItemModel.partyId = this.partyId;
        receivingItemModel.itemCreateBy = this.itemCreateBy;
        receivingItemModel.itemReceivingDate = this.itemReceivingDate;
        receivingItemModel.itemLastModifiedDate = this.itemLastModifiedDate;
        receivingItemModel.itemLModifiedBy = this.itemLModifiedBy;
        receivingItemModel.itemGrossWeight = this.itemGrossWeight;
        receivingItemModel.itemQty = this.itemQty;
        receivingItemModel.itemRemark = this.itemRemark;
        receivingItemModel.product = this.product;
        return receivingItemModel;
    }

    public Long getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Long receivingId) {
        this.receivingId = receivingId;
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

    public Date getItemReceivingDate() {
        return itemReceivingDate;
    }

    public void setItemReceivingDate(Date itemReceivingDate) {
        this.itemReceivingDate = itemReceivingDate;
    }

    public Date getItemLastModifiedDate() {
        return itemLastModifiedDate;
    }

    public void setItemLastModifiedDate(Date itemLastModifiedDate) {
        this.itemLastModifiedDate = itemLastModifiedDate;
    }

    public String getItemLModifiedBy() {
        return itemLModifiedBy;
    }

    public void setItemLModifiedBy(String itemLModifiedBy) {
        this.itemLModifiedBy = itemLModifiedBy;
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

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ReceivingItemModel{" +
                "receivingId=" + receivingId +
                ", productId=" + productId +
                ", itemStatus='" + itemStatus + '\'' +
                ", orderId=" + orderId +
                ", partyId='" + partyId + '\'' +
                ", itemCreateDate=" + itemCreateDate +
                ", itemCreateBy='" + itemCreateBy + '\'' +
                ", itemReceivingDate=" + itemReceivingDate +
                ", itemLastModifiedDate=" + itemLastModifiedDate +
                ", itemLModifiedBy='" + itemLModifiedBy + '\'' +
                ", itemGrossWeight=" + itemGrossWeight +
                ", itemQty=" + itemQty +
                ", itemRemark='" + itemRemark + '\'' +
                ", product=" + product +
                '}';
    }
}
