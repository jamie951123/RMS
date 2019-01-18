package com.example.james.rms.core.model;

import java.util.LinkedHashMap;

/**
 * Created by Jamie on 20/6/2017.
 */

public class ExpandableSelectedModel {
    private LinkedHashMap<Long, Boolean> isOrderSelected;
    private LinkedHashMap<Long, Boolean> orginal_isOrderSelected;
    private LinkedHashMap<Long, Boolean> orginal_isItemSelected;
    private LinkedHashMap<Long, Boolean> isItemSelected;

    private int count_dialogBox = 0;

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

    public int getCount_dialogBox() {
        return count_dialogBox;
    }

    public void setCount_dialogBox(int count_dialogBox) {
        this.count_dialogBox = count_dialogBox;
    }

    public LinkedHashMap<Long, Boolean> getOrginal_isOrderSelected() {
        return orginal_isOrderSelected;
    }

    public void setOrginal_isOrderSelected(LinkedHashMap<Long, Boolean> orginal_isOrderSelected) {
        this.orginal_isOrderSelected = orginal_isOrderSelected;
    }

    public LinkedHashMap<Long, Boolean> getOrginal_isItemSelected() {
        return orginal_isItemSelected;
    }

    public void setOrginal_isItemSelected(LinkedHashMap<Long, Boolean> orginal_isItemSelected) {
        this.orginal_isItemSelected = orginal_isItemSelected;
    }

    @Override
    public String toString() {
        return "ExpandableSelectedModel{" +
                "isOrderSelected=" + isOrderSelected +
                ", orginal_isOrderSelected=" + orginal_isOrderSelected +
                ", orginal_isItemSelected=" + orginal_isItemSelected +
                ", isItemSelected=" + isItemSelected +
                ", count_dialogBox=" + count_dialogBox +
                '}';
    }
}
