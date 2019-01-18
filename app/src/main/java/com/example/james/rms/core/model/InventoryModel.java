package com.example.james.rms.core.model;

import java.math.BigDecimal;

/**
 * Created by jamie on 2017/5/1.
 */

public class InventoryModel {

    private Long productId;
    private String partyId;
    private Status status;
    private Long weightId;
    private Long quantityId;
    private String productCode;
    private String productName;
    private BigDecimal totalGrossWeight;
    private String weightUnit;
    private Integer totalQty;
    private String quantityUnit;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public BigDecimal getTotalGrossWeight() {
        return totalGrossWeight;
    }

    public void setTotalGrossWeight(BigDecimal totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    @Override
    public String toString() {
        return "InventoryModel{" +
                "productId=" + productId +
                ", partyId='" + partyId + '\'' +
                ", status=" + status +
                ", weightId=" + weightId +
                ", quantityId=" + quantityId +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", totalGrossWeight=" + totalGrossWeight +
                ", weightUnit='" + weightUnit + '\'' +
                ", totalQty=" + totalQty +
                ", quantityUnit='" + quantityUnit + '\'' +
                '}';
    }
}
