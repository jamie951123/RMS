package com.example.james.rms.Core.Product.Model;

/**
 * Created by james on 26/3/2017.
 */

public class ProductModel {
    String productId;
    String productCode;
    String productName;
    String partyId;
    String status;
    String createDate;
    String closeDate;
    String remark;
    String productDescriptionEN;
    String productDescriptionCH;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
