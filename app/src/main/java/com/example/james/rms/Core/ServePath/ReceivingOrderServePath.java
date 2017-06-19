package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by james on 26/3/2017.
 */
public class ReceivingOrderServePath {

    public static String findByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_order_findByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String findByOrderIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_order_findByOrderIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String save(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_order_save();
        String serve_path = serve+path;
        return serve_path;
    }

//    public static String saves(){
//        String serve = ServeProfile.getServe();
//        String path = ServeProfile.getRe_order_saves();
//        String serve_path = serve+path;
//        return serve_path;
//    }

    public static String saveOrderAndItem(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_saveOrderAndItem();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String delete(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_order_delete();
        String serve_path = serve+path;
        return serve_path;
    }

}