package com.example.james.rms.Core.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventoryModel {

    private Long inventoryId;

    private Long productId;

    private String partyId;

    private Date createDate;

    private String createBy;

    private Date closeDate;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private Date stockOutDate;

    private Date stockInDate;

    private String status;

    private BigDecimal grossWeight;

    private Integer qty;

    private ProductModel product;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
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

    public Date getStockOutDate() {
        return stockOutDate;
    }

    public void setStockOutDate(Date stockOutDate) {
        this.stockOutDate = stockOutDate;
    }

    public Date getStockInDate() {
        return stockInDate;
    }

    public void setStockInDate(Date stockInDate) {
        this.stockInDate = stockInDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "InventoryModel{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", partyId='" + partyId + '\'' +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", closeDate=" + closeDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", stockOutDate=" + stockOutDate +
                ", stockInDate=" + stockInDate +
                ", status=" + status +
                ", grossWeight=" + grossWeight +
                ", qty=" + qty +
                ", product=" + product +
                '}';
    }
}
