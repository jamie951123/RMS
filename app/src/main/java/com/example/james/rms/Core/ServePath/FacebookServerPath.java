package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 11/7/2017.
 */

public class FacebookServerPath {
    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getFacebook_findAll();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findbyFacebookId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getFacebook_findByFacebookId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_countFacebookId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getFacebook_countFacebookId();
        String serve_path = serve+path;
        return serve_path;
    }
}
