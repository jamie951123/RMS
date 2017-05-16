package com.example.james.rms.Core.Model;

import java.util.Date;

/**
 * Created by jamie on 2017/4/23.
 */

public class WeightProfileModel {
    private Long weightId;

    private String partyId;

    private String weightUnit;

    private Date createDate;

    private Date modifyTime;

    private String modifyBy;


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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "WeightProfileModel{" +
                "weightId=" + weightId +
                ", partyId='" + partyId + '\'' +
                ", weightUnit='" + weightUnit + '\'' +
                ", createDate=" + createDate +
                ", modifyTime=" + modifyTime +
                ", modifyBy='" + modifyBy + '\'' +
                '}';
    }
}
