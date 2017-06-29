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

    private String createBy;

    private Date closeDate;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private String remark;

    private String productDescriptionEN;

    private String productDescriptionCH;

    private Long weightId;

    private Long quantityId;

    private WeightProfileModel weightprofile;

    private QuantityProfileModel quantityProfile;

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

    public Long getWeightId() {
        return weightId;
    }

    public void setWeightId(Long weightId) {
        this.weightId = weightId;
    }

    public Long getQuantityId() {
        return quantityId;
    }

    public void setQuantityId(Long quantityId) {
        this.quantityId = quantityId;
    }

    public WeightProfileModel getWeightprofile() {
        return weightprofile;
    }

    public void setWeightprofile(WeightProfileModel weightprofile) {
        this.weightprofile = weightprofile;
    }

    public QuantityProfileModel getQuantityProfile() {
        return quantityProfile;
    }

    public void setQuantityProfile(QuantityProfileModel quantityProfile) {
        this.quantityProfile = quantityProfile;
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
                ", createBy='" + createBy + '\'' +
                ", closeDate=" + closeDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", remark='" + remark + '\'' +
                ", productDescriptionEN='" + productDescriptionEN + '\'' +
                ", productDescriptionCH='" + productDescriptionCH + '\'' +
                ", weightId=" + weightId +
                ", quantityId=" + quantityId +
                ", weightprofile=" + weightprofile +
                ", quantityProfile=" + quantityProfile +
                '}';
    }
}
