package com.example.james.rms.Core.TransferModel;

import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jamie on 2017/4/24.
 */

public class NumberDialogModel {
    Long id;
    String key;
    BigDecimal grossWeight;
    String grossWeightUnit;
    BigDecimal gwMax;
    Integer qty;
    String qtyUnit;
    Integer qtyMax;

    Integer position;
    Integer groupPosition;
    Integer childPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public BigDecimal getGwMax() {
        return gwMax;
    }

    public void setGwMax(BigDecimal gwMax) {
        this.gwMax = gwMax;
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

    public Integer getQtyMax() {
        return qtyMax;
    }

    public void setQtyMax(Integer qtyMax) {
        this.qtyMax = qtyMax;
    }

    public Integer getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(Integer groupPosition) {
        this.groupPosition = groupPosition;
    }

    public Integer getChildPosition() {
        return childPosition;
    }

    public void setChildPosition(Integer childPosition) {
        this.childPosition = childPosition;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "NumberDialogModel{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", grossWeight=" + grossWeight +
                ", grossWeightUnit='" + grossWeightUnit + '\'' +
                ", gwMax=" + gwMax +
                ", qty=" + qty +
                ", qtyUnit='" + qtyUnit + '\'' +
                ", qtyMax=" + qtyMax +
                ", position=" + position +
                ", groupPosition=" + groupPosition +
                ", childPosition=" + childPosition +
                '}';
    }
}
