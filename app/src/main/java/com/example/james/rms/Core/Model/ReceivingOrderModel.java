package com.example.james.rms.Core.Model;

import java.util.Date;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingOrderModel {
    private Long orderId;
    private String partyId;
    private String remark;
    private String status;
    private Date receivingDate;
    private Date createDate;
    private Date closeDate;
    private Integer actualQty;
    private Integer estimateQty;
    private Integer itemQty;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
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

    @Override
    public String toString() {
        return "ReceivingOrderModel{" +
                "orderId=" + orderId +
                ", partyId='" + partyId + '\'' +
                ", receivingDate='" + receivingDate + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                ", actualQty=" + actualQty +
                ", estimateQty=" + estimateQty +
                ", itemQty=" + itemQty +
                '}';
    }
}

