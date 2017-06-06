package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by jamie on 2017/4/17.
 */

public class ProductServerPath {

public static String serve_findAll(){
    String serve = ServeProfile.getServe();
    String path = ServeProfile.getProduct_findAll();
    String serve_path = serve+path;
    return serve_path;
}

    public static String serve_findByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_findByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_insertProduct(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_insert();
        String serve_path = serve+path;
        return serve_path;
    }
    public static String serve_updateWeightIdNullByWeightIdAndPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_updateWeightIdNullByWeightIdAndPartyId();
        String serve_path = serve+path;
        return serve_path;
    }
    public static String serve_updateQuantityIdNullByQuantityIdAndPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_updateQuantityIdNullByQuantityIdAndPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_findByProductId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_updateQuantityIdAndWeightIdNullByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_updateQuantityIdAndWeightIdNullByProductId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_deleteByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_deleteByProductId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_delete(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getProduct_delete();
        String serve_path = serve+path;
        return serve_path;
    }

}
