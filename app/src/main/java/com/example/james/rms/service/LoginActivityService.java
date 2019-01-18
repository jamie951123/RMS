package com.example.james.rms.service;

import com.example.james.rms.core.model.Facebook;

/**
 * Created by jamie on 26/7/2017.
 */

public interface LoginActivityService {
    void autoLogin();
    void fb_Login(Facebook facebook);
    void buttonLogin(String username, String password);
}
