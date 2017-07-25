package com.example.james.rms.Core.Model;

/**
 * Created by jamie on 26/7/2017.
 */

public class SettingModel {

    private Long id;
    private Long userProfileId;
    private Integer dateRange;
    private String language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public Integer getDateRange() {
        return dateRange;
    }

    public void setDateRange(Integer dateRange) {
        this.dateRange = dateRange;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
