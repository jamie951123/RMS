package com.example.james.rms.Core.TransferModel;

import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jamie on 2017/4/24.
 */

public class NumberDialogModel {
    String key;
    BigDecimal grossWeight;
    String grossWeightUnit;
    Integer qty;
    String qtyUnit;
    Integer position;

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


    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "NumberDialogModel{" +
                "key='" + key + '\'' +
                ", grossWeight=" + grossWeight +
                ", grossWeightUnit='" + grossWeightUnit + '\'' +
                ", qty=" + qty +
                ", qtyUnit='" + qtyUnit + '\'' +
                ", position=" + position +
                '}';
    }
}
