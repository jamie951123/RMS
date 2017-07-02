package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by jamie on 2017/6/10.
 */

public class ReceivingItemServePath {
    public static String findReceivingItemByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_findByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String save(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_save();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String saves(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_save();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String deleteByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_deleteByProductId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String deleteByOrderId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_deleteByOrderId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String delete(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_delete();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String deletes(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getRe_item_deletes();
        String serve_path = serve+path;
        return serve_path;
    }

}
