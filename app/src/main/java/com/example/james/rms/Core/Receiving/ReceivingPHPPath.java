package com.example.james.rms.Core.Receiving;

import com.example.james.rms.NetWork.PhpProfile;

/**
 * Created by james on 26/3/2017.
 */
public class ReceivingPHPPath {

    public static String findReceivingOrderByPartyId(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getFindReceivingOrderByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String findReceivingItemByPartyId(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getFindReceivingItembyPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String insertToReceivingOrder(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getInsertReceivingOrder();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String insertToReceivingItem(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getInsertReceivingItem();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String findOrderIdByPartyIdAndCreateDate(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getFindReceivingOrderByPartyIdAndCreateDate();
        String serve_path = serve+path;
        return serve_path;
    }

}