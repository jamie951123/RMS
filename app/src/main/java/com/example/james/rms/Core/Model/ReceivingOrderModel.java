package com.example.james.rms.Core.Model;

import java.util.Date;
import java.util.List;

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
    private String createBy;
    private Date closeDate;
    private Date lastModifiedDate;
    private String lastModifiedBy;
    private Integer actualQty;
    private Integer estimateQty;
    private Integer itemQty;
    private List<ReceivingItemModel> receivingItem;

    public ReceivingOrderModel newReceivingOrderModel(){
        ReceivingOrderModel r = new ReceivingOrderModel();
        r.setOrderId(this.orderId);
        r.setPartyId(this.partyId);
        r.setRemark(this.remark);
        r.setStatus(this.status);
        r.setReceivingDate(this.receivingDate);
        r.setCreateDate(this.createDate);
        r.setCreateBy(this.createBy);
        r.setCloseDate(this.closeDate);
        r.setLastModifiedDate(this.lastModifiedDate);
        r.setLastModifiedBy(this.lastModifiedBy);
        r.setActualQty(this.actualQty);
        r.setEstimateQty(this.estimateQty);
        r.setItemQty(this.itemQty);
        r.setReceivingItem(this.receivingItem);
        return r;
    }

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

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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

    public List<ReceivingItemModel> getReceivingItem() {
        return receivingItem;
    }

    public void setReceivingItem(List<ReceivingItemModel> receivingItem) {
        this.receivingItem = receivingItem;
    }

    @Override
    public String toString() {
        return "ReceivingOrderModel{" +
                "orderId=" + orderId +
                ", partyId='" + partyId + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", receivingDate=" + receivingDate +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", closeDate=" + closeDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", actualQty=" + actualQty +
                ", estimateQty=" + estimateQty +
                ", itemQty=" + itemQty +
                ", receivingItem=" + receivingItem +
                '}';
    }
}

