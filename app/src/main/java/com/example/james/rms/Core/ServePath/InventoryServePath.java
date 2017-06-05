package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventoryServePath {

    public static String serve_findAll(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_findAll();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_findByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByPartyIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_findByPartyIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_insertInventory(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_insertInventory();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_insertInventorys(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_insertInventorys();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_deleteByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_deleteByProductId();
        String serve_path = serve+path;
        return serve_path;
    }


}
