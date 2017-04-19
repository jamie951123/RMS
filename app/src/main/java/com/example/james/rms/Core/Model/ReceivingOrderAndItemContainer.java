package com.example.james.rms.Core.Model;

import java.util.List;

/**
 * Created by jamie on 2017/4/20.
 */

public class ReceivingOrderAndItemContainer{
    private ReceivingOrderModel receivingOrderModel;
    private List<ReceivingItemModel> receivingItemModelList;

    public ReceivingOrderAndItemContainer() {
    }

    public ReceivingOrderAndItemContainer(ReceivingOrderModel receivingOrderModel, List<ReceivingItemModel> receivingItemModelList) {
        this.receivingOrderModel = receivingOrderModel;
        this.receivingItemModelList = receivingItemModelList;
    }

    public ReceivingOrderModel getReceivingOrderModel() {
        return receivingOrderModel;
    }

    public void setReceivingOrderModel(ReceivingOrderModel receivingOrderModel) {
        this.receivingOrderModel = receivingOrderModel;
    }

    public List<ReceivingItemModel> getReceivingItemModelList() {
        return receivingItemModelList;
    }

    public void setReceivingItemModelList(List<ReceivingItemModel> receivingItemModelList) {
        this.receivingItemModelList = receivingItemModelList;
    }

    @Override
    public String toString() {
        return "ReceivingOrderAndItemContainer{" +
                "receivingOrderModel=" + receivingOrderModel +
                ", receivingItemModelList=" + receivingItemModelList +
                '}';
    }
}
