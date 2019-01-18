package com.example.james.rms.common.graphic;

import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * Created by Jamie on 24/7/2017.
 */

public class BarChatModel {
    private List<String> weight_name;
    private List<BarEntry> weight_value;
    private List<String> qty_name;
    private List<BarEntry> qty_value;

    public List<String> getWeight_name() {
        return weight_name;
    }

    public void setWeight_name(List<String> weight_name) {
        this.weight_name = weight_name;
    }

    public List<BarEntry> getWeight_value() {
        return weight_value;
    }

    public void setWeight_value(List<BarEntry> weight_value) {
        this.weight_value = weight_value;
    }

    public List<String> getQty_name() {
        return qty_name;
    }

    public void setQty_name(List<String> qty_name) {
        this.qty_name = qty_name;
    }

    public List<BarEntry> getQty_value() {
        return qty_value;
    }

    public void setQty_value(List<BarEntry> qty_value) {
        this.qty_value = qty_value;
    }
}
