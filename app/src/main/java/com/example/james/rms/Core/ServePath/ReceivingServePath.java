package com.example.james.rms.Core.ServePath;

import com.example.james.rms.NetWork.ServeProfile;

/**
 * Created by james on 26/3/2017.
 */
public class ReceivingServePath {

    public static String findReceivingOrderByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getOrderFindByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String findReceivingItemByPartyId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getItemFindByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String insertToReceivingOrder(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInsertReceivingOrder();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String insertToReceivingItem(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getInsertReceivingItem();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String saveOrderAndItem(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getSaveOrderAndItem();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String item_deleteByProductId(){
        String serve = ServeProfile.getServe();
        String path = ServeProfile.getItem_deleteByProductId();
        String serve_path = serve+path;
        return serve_path;
    }


//    public static String findOrderIdByPartyIdAndCreateDate(){
//        String serve = PhpProfile.getServe();
//        String path = PhpProfile.getFindReceivingOrderByPartyIdAndCreateDate();
//        String serve_path = serve+path;
//        return serve_path;
//    }

}