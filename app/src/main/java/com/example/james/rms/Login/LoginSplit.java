package com.example.james.rms.Login;

import android.util.Log;

import com.example.james.rms.Login.Model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by James on 21/1/2017.
 */

public class LoginSplit {

    public LoginModel conventLoginStatus(String login_Result) {
        JSONObject jsonObject = null;
        LoginModel loginModel = new LoginModel();
        Log.v("sv","conventLoginStatus : "+login_Result);
        try {

            jsonObject = new JSONObject(login_Result);
            String loginMessage = jsonObject.getString("loginMessage");
            String userProfileId = jsonObject.getString("userProfileId");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String partyId = jsonObject.getString("partyId");
            String status = jsonObject.getString("status");
            String createDate = jsonObject.getString("createDate");
            String closeDate = jsonObject.getString("closeDate");
            loginModel.setLoginMessage(loginMessage);
            loginModel.setUserProfileId(userProfileId);
            loginModel.setUsername(username);
            loginModel.setPassword(password);
            loginModel.setPartyId(partyId);
            loginModel.setStatus(status);
            loginModel.setCreateDate(createDate);
            loginModel.setCloseDate(closeDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loginModel;
    }

}
