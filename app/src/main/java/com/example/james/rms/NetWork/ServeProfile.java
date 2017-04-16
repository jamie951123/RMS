package com.example.james.rms.NetWork;

/**
 * Created by Jamie on 16/4/2017.
 */

public class ServeProfile {
    public static String serve                                    = "http://123.203.117.130:1123/";

    //Product
    final static String login_findAll                             = "/rms/login/findAll";
    final static String login_checklogin                          = "/rms/login/checklogin";

    //Receiving


    public static String getServe() {
        return serve;
    }

    public static void setServe(String serve) {
        ServeProfile.serve = serve;
    }

    public static String getLogin_findAll() {
        return login_findAll;
    }

    public static String getLogin_checklogin() {
        return login_checklogin;
    }
}
