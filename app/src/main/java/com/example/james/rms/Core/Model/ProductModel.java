package com.example.james.rms.Core.Model;

import java.util.Date;

/**
 * Created by james on 26/3/2017.
 */

public class ProductModel {

    private Long productId;

    private String productCode;

    private String productName;

    private String partyId;

    private String status;

    private Date createDate;

    private Date closeDate;

    private String remark;

    private String productDescriptionEN;

    private String productDescriptionCH;

    public ProductModel getProductModel(){
        ProductModel items = new ProductModel();
        items.productId = productId;
        items.productCode = productCode;
        items.productName = productName;
        items.partyId = partyId;
        items.status = status;
        items.createDate = createDate;
        items.closeDate = closeDate;
        items.remark = remark;
        items.productDescriptionEN = productDescriptionEN;
        items.productDescriptionCH = productDescriptionCH;
        return items;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductDescriptionEN() {
        return productDescriptionEN;
    }

    public void setProductDescriptionEN(String productDescriptionEN) {
        this.productDescriptionEN = productDescriptionEN;
    }

    public String getProductDescriptionCH() {
        return productDescriptionCH;
    }

    public void setProductDescriptionCH(String productDescriptionCH) {
        this.productDescriptionCH = productDescriptionCH;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", partyId='" + partyId + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                ", remark='" + remark + '\'' +
                ", productDescriptionEN='" + productDescriptionEN + '\'' +
                ", productDescriptionCH='" + productDescriptionCH + '\'' +
                '}';
    }
}
