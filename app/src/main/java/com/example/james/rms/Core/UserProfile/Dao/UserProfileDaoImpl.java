package com.example.james.rms.Core.UserProfile.Dao;

import android.util.Log;

import com.example.james.rms.Core.UserProfile.LoginServePath;
import com.example.james.rms.Core.UserProfile.Model.LoginModel;
import com.example.james.rms.Core.UserProfile.Model.UserProfile;
import com.example.james.rms.NetWork.HttpGetAsync;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jamie on 16/4/2017.
 */

public class UserProfileDaoImpl implements UserProfileDao {

    @Override
    public List<UserProfile> findAll() {
        String url = LoginServePath.serve_findAll();
        String result="";
        List<UserProfile> userProfile = new ArrayList<>();
        try {
            result = new HttpGetAsync().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","findAll(Response): "+result);
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<UserProfile>>() {}.getType();
            userProfile = gson.fromJson(result,listType);
            Log.d("asd","findAll(Gson): "+userProfile);
        }catch (Exception e){

        }
        return userProfile;
    }

    @Override
    public List<LoginModel> findByPartyId() {
        return null;
    }

    @Override
    public LoginModel checkLogin(String json,String url) {
        Log.d("asd","checkLogin--Request(JSON) :"+ json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(url,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","checkLogin(Response) :"+ result);

        LoginModel loginModel = new LoginModel();
        try {
            Gson gson = new Gson();
            loginModel = gson.fromJson(result,LoginModel.class);
            Log.d("asd","checkLogin(Split) : "+loginModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginModel;
    }


}
