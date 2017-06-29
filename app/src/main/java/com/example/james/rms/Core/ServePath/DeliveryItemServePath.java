package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryItemServePath {

//    Find
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

//    Save
    public static String serve_save() {
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_item_save();
        String serve_path = serve + path;
        return serve_path;
    }

    public static String serve_saves() {
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_item_saves();
        String serve_path = serve + path;
        return serve_path;
    }

    public static String serve_delete() {
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getDe_item_delete();
        String serve_path = serve + path;
        return serve_path;
    }

}
