package com.example.james.rms.Core.Model;

import java.math.BigDecimal;

/**
 * Created by jamie on 2017/5/1.
 */

public class InventorySumModel {

    private String id;

    private Long productId;

    private Long weightId;

    private Long quantityId;

    private String partyId;

    private Status status;

    private BigDecimal grossWeight;

    private String weightUnit;

    private Integer qty;

    private String quantityUnit;

    private String productCode;

    private String productName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getWeightId() {
        return weightId;
    }

    public void setWeightId(Long weightId) {
        this.weightId = weightId;
    }

    public Long getQuantityId() {
        return quantityId;
    }

    public void setQuantityId(Long quantityId) {
        this.quantityId = quantityId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Override
    public String toString() {
        return "InventorySum [id=" + id + ", productId=" + productId + ", weightId=" + weightId + ", quantityId="
                + quantityId + ", partyId=" + partyId + ", status=" + status + ", grossWeight=" + grossWeight
                + ", weightUnit=" + weightUnit + ", qty=" + qty + ", quantityUnit=" + quantityUnit + ", productCode="
                + productCode + ", productName=" + productName + "]";
    }

}
