package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 16/4/2017.
 */

public class UserProfileServePath {

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

    public static String serve_findByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getLogin_findByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findbyFacebookId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getLogin_findByFacebookId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_save(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getLogin_save();
        String serve_path = serve+path;
        return serve_path;
    }


}
