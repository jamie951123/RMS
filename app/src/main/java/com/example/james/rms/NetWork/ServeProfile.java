package com.example.james.rms.NetWork;

/**
 * Created by Jamie on 16/4/2017.
 */

public class ServeProfile {
    public static String serve                                    = "http://123.203.117.130:1123/";

    //Login
    final static String login_findAll                             = "/rms/login/findAll";
    final static String login_checklogin                          = "/rms/login/checklogin";
    //Product
    final static String product_findAll                            = "/rms/product/findAll";
    final static String product_findByPartyId                     = "/rms/product/findByPartyId";
    final static String product_insert                             = "/rms/product/insertProduct";
    //Receiving


    public static String getProduct_findAll() {
        return product_findAll;
    }

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

    public static String getProduct_findByPartyId() {
        return product_findByPartyId;
    }

    public static String getProduct_insert() {
        return product_insert;
    }
}
