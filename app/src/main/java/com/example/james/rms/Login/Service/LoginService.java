package com.example.james.rms.Login.Service;

import com.example.james.rms.Core.Model.LoginModel;
import com.example.james.rms.Core.Model.UserProfile;

import java.util.List;

/**
 * Created by Jamie on 16/4/2017.
 */

public interface LoginService {
    List<UserProfile> findAll();
    List<LoginModel> findByPartyId();
    LoginModel checkLogin(String json);
}
