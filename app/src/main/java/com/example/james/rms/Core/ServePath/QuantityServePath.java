package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by jamie on 2017/4/23.
 */

public class QuantityServePath {

    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getQuantity_findAll();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getQuantity_findByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_quantityDelete(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getQuantity_delete();
        String serve_path = serve+path;
        return serve_path;
    }


}
