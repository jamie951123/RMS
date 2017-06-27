package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryOrderServePath {

    //Find
    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_findAll();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByPartyIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_findByPartyIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByOrderIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_findByOrderIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

    //Save
    public static String serve_save(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_save();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_saveOrderAndItem(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_saveOrderAndItem();
        String serve_path = serve+path;
        return serve_path;
    }

    //Update


    //Delete
    public static String serve_delete(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_delete();
        String serve_path = serve+path;
        return serve_path;
    }
}
