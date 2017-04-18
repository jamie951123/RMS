package com.example.james.rms.Core.Receiving.Model;

import com.example.james.rms.Core.Product.Model.ProductModel;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingOrderModel {
    private String orderId;
    private String partyId;
    private String receivingDate;
    private String remark;
    private String status;
    private String createDate;
    private String closeDate;
    private Integer actualQty;
    private Integer estimateQty;
    private Integer itemQty;

    @Override
    public String toString() {
        return "ReceivingOrderModel{" +
                "orderId='" + orderId + '\'' +
                ", partyId='" + partyId + '\'' +
                ", receivingDate='" + receivingDate + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", actualQty=" + actualQty +
                ", estimateQty=" + estimateQty +
                ", itemQty=" + itemQty +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getActualQty() {
        return actualQty;
    }

    public void setActualQty(Integer actualQty) {
        this.actualQty = actualQty;
    }

    public Integer getEstimateQty() {
        return estimateQty;
    }

    public void setEstimateQty(Integer estimateQty) {
        this.estimateQty = estimateQty;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

}

