package com.example.james.rms.Login.Service;

import com.example.james.rms.Core.UserProfile.Dao.UserProfileDao;
import com.example.james.rms.Core.UserProfile.Dao.UserProfileDaoImpl;
import com.example.james.rms.Core.UserProfile.LoginServePath;
import com.example.james.rms.Core.UserProfile.Model.LoginModel;
import com.example.james.rms.Core.UserProfile.Model.UserProfile;

import java.util.List;

/**
 * Created by Jamie on 16/4/2017.
 */

public class LoginServiceImpl implements LoginService {

    UserProfileDao userProfileDao = new UserProfileDaoImpl();

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }

    @Override
    public List<LoginModel> findByPartyId() {
        return null;
    }

    @Override
    public LoginModel checkLogin(String json) {
        String url_checkLogin = LoginServePath.serve_checkLogin();
        return userProfileDao.checkLogin(json,url_checkLogin);
    }
}
