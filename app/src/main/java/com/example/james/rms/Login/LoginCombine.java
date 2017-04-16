package com.example.james.rms.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by james on 31/1/2017.
 */

public class LoginCombine {

    public String combine_loginValue(String username ,String password) {
        String result="";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            result = jsonObject.toString();
        }catch (JSONException e){

        }
        return result;
    }
}
