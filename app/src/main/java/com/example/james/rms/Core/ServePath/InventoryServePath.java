package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by jamie on 2017/5/1.
 */

public class InventoryServePath {

    public static String serve_findByPartyIdAndStatus(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventorySum_findByPartyIdAndStatus();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByPartyIdAndStatusOrderByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventorySum_findByPartyIdAndStatusOrderByProductId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String serve_findByPartyIdAndStatusOrderByProductIdAsc(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInventory_findByPartyIdAndStatusOrderByProductIdAsc();
        String serve_path = serve+path;
        return serve_path;
    }


}
