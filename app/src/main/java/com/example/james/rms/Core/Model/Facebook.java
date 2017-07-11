package com.example.james.rms.Core.Model;

/**
 * Created by Jamie on 11/7/2017.
 */

public class Facebook {

    private String facebookId;
    private UserProfile userProfile;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "Facebook{" +
                "facebookId='" + facebookId + '\'' +
                ", userProfile=" + userProfile +
                '}';
    }
}
