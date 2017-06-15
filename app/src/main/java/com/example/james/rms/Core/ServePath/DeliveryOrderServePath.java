package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryOrderServePath {

    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_findAll();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByOrderIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_order_findByOrderIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

}
