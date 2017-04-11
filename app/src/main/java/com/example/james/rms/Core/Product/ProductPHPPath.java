package com.example.james.rms.Core.Product;

import com.example.james.rms.NetWork.PhpProfile;

/**
 * Created by james on 26/3/2017.
 */

public class ProductPHPPath {

    public static String findProductPoolByPartyId(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getFindProductPoolByPartyId();
        String serve_path = serve+path;
        return serve_path;
    }

    public static String insertProduct(){
        String serve = PhpProfile.getServe();
        String path = PhpProfile.getInsertProduct();
        String serve_path = serve+path;
        return serve_path;
    }
}
