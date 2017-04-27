package com.example.james.rms.Core.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventoryModel {
    private Long inventoryId;

    private String partyId;

    private Long productId;

    private Date createDate;

    private Date closeDate;

    private Date stockOutDate;

    private Date stockInDate;

    private Status status;

    private BigDecimal GrossWeight;

    private String GrossWeightUnit;

    private Integer itemQty;

    private String qtyUnit;

    private ProductModel Product;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getStockOutDate() {
        return stockOutDate;
    }

    public void setStockOutDate(Date stockOutDate) {
        this.stockOutDate = stockOutDate;
    }

    public Date getStockInDate() {
        return stockInDate;
    }

    public void setStockInDate(Date stockInDate) {
        this.stockInDate = stockInDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getGrossWeight() {
        return GrossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        GrossWeight = grossWeight;
    }

    public String getGrossWeightUnit() {
        return GrossWeightUnit;
    }

    public void setGrossWeightUnit(String grossWeightUnit) {
        GrossWeightUnit = grossWeightUnit;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public ProductModel getProduct() {
        return Product;
    }

    public void setProduct(ProductModel product) {
        Product = product;
    }

    @Override
    public String toString() {
        return "InventoryModel{" +
                "inventoryId=" + inventoryId +
                ", partyId='" + partyId + '\'' +
                ", productId=" + productId +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                ", stockOutDate=" + stockOutDate +
                ", stockInDate=" + stockInDate +
                ", status=" + status +
                ", GrossWeight=" + GrossWeight +
                ", GrossWeightUnit='" + GrossWeightUnit + '\'' +
                ", itemQty=" + itemQty +
                ", qtyUnit='" + qtyUnit + '\'' +
                ", Product=" + Product +
                '}';
    }

}
