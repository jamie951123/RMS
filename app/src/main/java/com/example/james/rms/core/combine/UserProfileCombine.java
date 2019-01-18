package com.example.james.rms.core.combine;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.core.model.UserProfile;
import com.example.james.rms.core.search_object.UserProfileSearchObject;
import com.google.gson.Gson;

/**
 * Created by james on 31/1/2017.
 */

public class UserProfileCombine extends HomeCombine<UserProfile>{

    public UserProfileCombine(Class<UserProfile> classType) {
        super(classType);
    }

    public static String combine_partyId(String partyId) {
        UserProfileSearchObject userProfileSearchObject = new UserProfileSearchObject();
        userProfileSearchObject.setPartyId(partyId);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(userProfileSearchObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public static String combine_usernameAndpassword(String username, String password) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);
        userProfile.setPassword(password);
        String result="";
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(userProfile);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
