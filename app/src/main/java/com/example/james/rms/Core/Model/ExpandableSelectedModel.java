package com.example.james.rms.Core.Model;

import java.util.LinkedHashMap;

/**
 * Created by Jamie on 20/6/2017.
 */

public class ExpandableSelectedModel {
    private LinkedHashMap<Long, Boolean> isOrderSelected;
    private LinkedHashMap<Long, Boolean> isItemSelected;

    public LinkedHashMap<Long, Boolean> getIsOrderSelected() {
        return isOrderSelected;
    }

    public void setIsOrderSelected(LinkedHashMap<Long, Boolean> isOrderSelected) {
        this.isOrderSelected = isOrderSelected;
    }

    public LinkedHashMap<Long, Boolean> getIsItemSelected() {
        return isItemSelected;
    }

    public void setIsItemSelected(LinkedHashMap<Long, Boolean> isItemSelected) {
        this.isItemSelected = isItemSelected;
    }

    @Override
    public String toString() {
        return "SelectedModel{" +
                "isOrderSelected=" + isOrderSelected +
                ", isItemSelected=" + isItemSelected +
                '}';
    }
}
