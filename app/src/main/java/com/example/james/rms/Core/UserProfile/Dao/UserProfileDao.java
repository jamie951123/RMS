package com.example.james.rms.Core.UserProfile.Dao;

import com.example.james.rms.Core.UserProfile.Model.LoginModel;
import com.example.james.rms.Core.UserProfile.Model.UserProfile;

import java.util.List;

/**
 * Created by Jamie on 16/4/2017.
 */

public interface UserProfileDao {
    List<UserProfile> findAll();
    List<LoginModel> findByPartyId();
    LoginModel checkLogin(String json,String url);
}
