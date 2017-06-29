package com.example.james.rms.Core.Model;

import com.example.james.rms.Delivery.Tab.Delivery_Item;

import java.util.Date;
import java.util.List;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryOrderModel {

    private Long orderId;

    private String partyId;

    private String remark;

    private String status;

    private Date stockOutDate;

    private Date createDate;

    private String createBy;

    private Date closeDate;

    private Integer itemQty;

    private String doNo;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private List<DeliveryItemModel> deliveryItem;

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

    public Date getStockOutDate() {
        return stockOutDate;
    }

    public void setStockOutDate(Date stockOutDate) {
        this.stockOutDate = stockOutDate;
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

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public String getDoNo() {
        return doNo;
    }

    public void setDoNo(String doNo) {
        this.doNo = doNo;
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

    public List<DeliveryItemModel> getDeliveryItem() {
        return deliveryItem;
    }

    public void setDeliveryItem(List<DeliveryItemModel> deliveryItem) {
        this.deliveryItem = deliveryItem;
    }

    @Override
    public String toString() {
        return "DeliveryOrderModel{" +
                "orderId=" + orderId +
                ", partyId='" + partyId + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", stockOutDate=" + stockOutDate +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", closeDate=" + closeDate +
                ", itemQty=" + itemQty +
                ", doNo='" + doNo + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", deliveryItem=" + deliveryItem +
                '}';
    }
}
