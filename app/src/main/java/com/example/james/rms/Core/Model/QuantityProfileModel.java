package com.example.james.rms.Core.Model;

import java.util.Date;

/**
 * Created by jamie on 2017/4/23.
 */

public class QuantityProfileModel {
    private Long profileId;

    private String partyId;

    private String quantityUnit;

    private Date creteDate;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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

    public Date getCreteDate() {
        return creteDate;
    }

    public void setCreteDate(Date creteDate) {
        this.creteDate = creteDate;
    }

    @Override
    public String toString() {
        return "QuantityProfileModel{" +
                "profileId=" + profileId +
                ", partyId='" + partyId + '\'' +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", creteDate=" + creteDate +
                '}';
    }
}
