package com.example.james.rms.core.model;

import java.util.Date;
import java.util.List;

/**
 * Created by jamie on 2017/4/23.
 */

public class WeightProfileModel {

    private Long weightId;

    private String partyId;

    private String weightUnit;

    private Date createDate;

    private String createBy;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private Status status;

    private List<ProductModel> product;

    public Long getWeightId() {
        return weightId;
    }

    public void setWeightId(Long weightId) {
        this.weightId = weightId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WeightProfileModel{" +
                "weightId=" + weightId +
                ", partyId='" + partyId + '\'' +
                ", weightUnit='" + weightUnit + '\'' +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", status=" + status +
                ", product=" + product +
                '}';
    }

    public List<ProductModel> getProduct() {
        return product;
    }

    public void setProduct(List<ProductModel> product) {
        this.product = product;
    }

}
