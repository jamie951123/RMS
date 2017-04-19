package com.example.james.rms.Operation.Model;

import com.example.james.rms.Core.Model.ProductModel;

import java.math.BigDecimal;

/**
 * Created by james on 2/4/2017.
 */

public class ReceivingIncreaseModel {
    public ProductModel productModel;
    public Integer qty;
    public String qtyUnit;
    public BigDecimal grossWeight;
    public String grossWeightUnit;
    public String remark;

    public ReceivingIncreaseModel() {
    }

    public ReceivingIncreaseModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public ReceivingIncreaseModel getReceivingIncreaseModel(ProductModel productModel){
        ReceivingIncreaseModel receivingIncreaseModel = new ReceivingIncreaseModel();
        receivingIncreaseModel.setProductModel(productModel);
        receivingIncreaseModel.setQty(qty);
        receivingIncreaseModel.setGrossWeight(grossWeight);
        return receivingIncreaseModel;
    }

    @Override
    public String toString() {
        return "ReceivingIncreaseModel{" +
                "productModel=" + productModel +
                ", qty=" + qty +
                ", qtyUnit='" + qtyUnit + '\'' +
                ", grossWeight=" + grossWeight +
                ", grossWeightUnit='" + grossWeightUnit + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getGrossWeightUnit() {
        return grossWeightUnit;
    }

    public void setGrossWeightUnit(String grossWeightUnit) {
        this.grossWeightUnit = grossWeightUnit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
