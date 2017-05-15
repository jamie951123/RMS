package com.example.james.rms.Core.Model;

import java.util.Date;

/**
 * Created by jamie on 2017/4/23.
 */

public class QuantityProfileModel {
    private Long quantityId;

    private String partyId;

    private String quantityUnit;

    private Date createDate;

    private Date modifyTime;

    private String modifyBy;

    public Long getQuantityId() {
        return quantityId;
    }

    public void setQuantityId(Long quantityId) {
        this.quantityId = quantityId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
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
        return "QuantityProfileModel{" +
                "quantityId=" + quantityId +
                ", partyId='" + partyId + '\'' +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", createDate=" + createDate +
                ", modifyTime=" + modifyTime +
                ", modifyBy='" + modifyBy + '\'' +
                '}';
    }
}
