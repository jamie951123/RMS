package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryItemServePath {

    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_item_findAll();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByOrderIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_item_findByOrderIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

}