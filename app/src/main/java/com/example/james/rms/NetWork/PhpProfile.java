package com.example.james.rms.NetWork;

/**
 * Created by James on 21/1/2017.
 */

public class PhpProfile {
    //Serve
    public static String serve                                    = "http://123.203.117.130:1123/";
    //GET
    final static String getUserProfile                             = "LOGIN/getUserProfile.php";

    //POST
    final static String postCheckLogin                             = "LOGIN/checkLogin.php";
    final static String findProductPoolByPartyId                   = "PRODUCT/findProductPoolByPartyId.php";
    final static String findReceivingItembyPartyId                 = "RECEIVING/findReceivingItembyPartyId.php";
    final static String findReceivingOrderByPartyId                = "RECEIVING/findReceivingOrderByPartyId.php";
    final static String findReceivingOrderByPartyIdAndCreateDate   = "Receiving/findReceivingOrderByPartyIdAndCreateDate.php";
    //Insert
    final static String insertProduct                              = "PRODUCT/insert_product.php";
    final static String insertReceivingOrder                       = "Receiving/insert_receivingOrder.php";
    final static String insertReceivingItem                        = "Receiving/insert_receivingItem.php";

    public static String getServe() {
        return serve;
    }

    public static void setServe(String serve) {
        PhpProfile.serve = serve;
    }

    public static String getInsertReceivingItem() {
        return insertReceivingItem;
    }

    public static String getFindReceivingOrderByPartyIdAndCreateDate() {
        return findReceivingOrderByPartyIdAndCreateDate;
    }

    public static String getGetUserProfile() {
        return getUserProfile;
    }

    public static String getPostCheckLogin() {
        return postCheckLogin;
    }

    public static String getFindProductPoolByPartyId() {
        return findProductPoolByPartyId;
    }

    public static String getFindReceivingItembyPartyId() {
        return findReceivingItembyPartyId;
    }

    public static String getFindReceivingOrderByPartyId() {
        return findReceivingOrderByPartyId;
    }

    public static String getInsertProduct() {
        return insertProduct;
    }

    public static String getInsertReceivingOrder() {
        return insertReceivingOrder;
    }
}
