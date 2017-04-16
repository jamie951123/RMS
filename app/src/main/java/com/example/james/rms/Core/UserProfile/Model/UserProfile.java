package com.example.james.rms.Core.UserProfile.Model;

import java.util.Date;

/**
 * Created by Jamie on 16/4/2017.
 */

public class UserProfile{
    private Long userProfileId ;
    private String username ;
    private String password ;
    private String partyId ;
    private String status ;
    private Date createDate ;
    private Date closeDate ;

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

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
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
                ", closeDate=" + closeDate +
                '}';
    }
}
