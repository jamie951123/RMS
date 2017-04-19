package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 16/4/2017.
 */

public class LoginServePath {

    public static String serve_checkLogin(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getLogin_checklogin();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getLogin_findAll();
        String serve_path = serve+path;
        return serve_path;
    }
}
