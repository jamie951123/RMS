package com.example.james.rms.core.model;

import java.util.Date;

/**
 * Created by Jamie on 16/4/2017.
 */

public class UserProfile{

    private Long userProfileId;

    private String username;

    private String password;

    private String partyId;

    private String status;

    private Date createDate;

    private String createBy;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private Date closeDate;

    private Facebook facebook;

    private SettingModel setting;

    public SettingModel getSetting() {
        return setting;
    }

    public void setSetting(SettingModel setting) {
        this.setting = setting;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "UserProfile{" +
                "userProfileId=" + userProfileId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", partyId='" + partyId + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", closeDate=" + closeDate +
                ", facebook=" + facebook +
                ", setting=" + setting +
                '}';
    }
}
