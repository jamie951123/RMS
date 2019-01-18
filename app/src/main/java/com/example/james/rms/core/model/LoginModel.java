package com.example.james.rms.core.model;



/**
 * Created by james on 5/2/2017.
 */

public class LoginModel {
    private UserProfile userProfile;
    private String loginStatus;
    private String loginMessage;

    public LoginModel() {
        UserProfile u = new UserProfile();
        this.userProfile = u;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "userProfile=" + userProfile +
                ", loginStatus='" + loginStatus + '\'' +
                ", loginMessage='" + loginMessage + '\'' +
                '}';
    }
}
