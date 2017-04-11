package com.example.james.rms.Login;

import com.example.james.rms.NetWork.PhpProfile;

/**
 * Created by james on 31/1/2017.
 */

public class LoginPHPPath {


    public static String php_checkLogin(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getPostCheckLogin();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String php_userProfile(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getGetUserProfile();
        String serve_path = serve+path;
        return serve_path;
    }
}
