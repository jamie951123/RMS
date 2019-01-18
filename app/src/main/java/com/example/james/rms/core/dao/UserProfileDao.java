package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.LoginModel;
import com.example.james.rms.core.model.UserProfile;

import java.util.List;

/**
 * Created by Jamie on 16/4/2017.
 */

public interface UserProfileDao {
//    Find
    List<UserProfile> findAll();
    UserProfile findByPartyId(String userProfileSearchObject_json);
    UserProfile findByFacebookId(String userProfileSearchObject_json);
    LoginModel checkLogin(String userProfile_json);

//    Save
    UserProfile save(String userProfile_json);

}
