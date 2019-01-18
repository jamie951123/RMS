package com.example.james.rms.core.search_object;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.Status;
import com.google.gson.Gson;

/**
 * Created by jamie on 2017/6/4.
 */

public class SearchCombine {

    public static String combine_partyId(String partyId) {
        HomeSearchObject homeSearchObject = new HomeSearchObject();
        homeSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(homeSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String combine_partyIdAndStatus(String partyId, Status status){
        HomeSearchObject homeSearchObject = new HomeSearchObject();
        homeSearchObject.setPartyId(partyId);
        homeSearchObject.setStatus(status);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(homeSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String combine_id(Long id){
        HomeSearchObject homeSearchObject = new HomeSearchObject();
        homeSearchObject.setId(id);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(homeSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
