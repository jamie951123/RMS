package com.example.james.rms.Core.TranslateModel;

import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jamie on 2017/4/24.
 */

public class QuantityDialogModel {
    String key;
    BigDecimal grossWeight;
    String grossWeightUnit;
    Integer qty;
    String qtyUnit;
    List<WeightProfileModel> weightProfileModelList;
    List<QuantityProfileModel> quantityProfileModels;

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

    public List<WeightProfileModel> getWeightProfileModelList() {
        return weightProfileModelList;
    }

    public void setWeightProfileModelList(List<WeightProfileModel> weightProfileModelList) {
        this.weightProfileModelList = weightProfileModelList;
    }

    public List<QuantityProfileModel> getQuantityProfileModels() {
        return quantityProfileModels;
    }

    public void setQuantityProfileModels(List<QuantityProfileModel> quantityProfileModels) {
        this.quantityProfileModels = quantityProfileModels;
    }

    @Override
    public String toString() {
        return "QuantityDialogModel{" +
                "key='" + key + '\'' +
                ", grossWeight=" + grossWeight +
                ", grossWeightUnit='" + grossWeightUnit + '\'' +
                ", qty=" + qty +
                ", qtyUnit='" + qtyUnit + '\'' +
                ", weightProfileModelList=" + weightProfileModelList +
                ", quantityProfileModels=" + quantityProfileModels +
                '}';
    }
}
