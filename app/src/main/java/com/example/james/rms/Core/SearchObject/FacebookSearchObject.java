package com.example.james.rms.Core.SearchObject;

/**
 * Created by Jamie on 11/7/2017.
 */

public class FacebookSearchObject  extends HomeSearchObject {
    private String facebookId;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    @Override
    public String toString() {
        return "UserProfileSearchObject [facebookId=" + facebookId + "]";
    }
}
