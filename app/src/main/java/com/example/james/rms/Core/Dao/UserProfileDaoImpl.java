package com.example.james.rms.Core.Dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.LoginModel;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.Core.Model.UserProfile;
import com.example.james.rms.Core.SearchObject.UserProfileSearchObject;
import com.example.james.rms.Core.ServePath.UserProfileServePath;
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

public class UserProfileDaoImpl extends NetworkModel implements UserProfileDao {

    public UserProfileDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<UserProfile> findAll() {
        String url = UserProfileServePath.INSTANCE.serve_findAll();
        String result="";
        List<UserProfile> userProfile = new ArrayList<>();
        try {
            result = new HttpGetAsync(this).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[UserProfile]-findAll(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<UserProfile>>() {}.getType();
            userProfile = gson.fromJson(result,listType);
            Log.d("asd","[UserProfile]-findAll(Gson): "+userProfile);
        }catch (Exception e){
            Log.d("asd","[UserProfile]-findAll(Gson)-[Error]-[json to Gson Error] : ");
            e.printStackTrace();
        }
        return userProfile;
    }

    @Override
    public UserProfile findByPartyId(String userProfileSearchObject_json) {
        String url = UserProfileServePath.INSTANCE.serve_findByPartyId();
        Log.d("asd","[UserProfile]-findByPartyId--Request(JSON) :"+ userProfileSearchObject_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(url,userProfileSearchObject_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[UserProfile]-findByPartyId(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        UserProfile userProfile = new UserProfile();
        try{
            Gson gson = GsonUtil.fromJson();
            userProfile = gson.fromJson(result,UserProfile.class);
            Log.d("asd","[UserProfile]-findByPartyId(Gson): "+userProfile);
        }catch (Exception e){
            Log.d("asd","[UserProfile]-findByPartyId(Gson)-[Error]-[json to Gson Error] : ");
            e.printStackTrace();
        }
        return userProfile;
    }

    @Override
    public UserProfile findByFacebookId(String userProfileSearchObject_json) {
        String url = UserProfileServePath.INSTANCE.serve_findbyFacebookId();
        Log.d("asd","[UserProfile]-findByFacebookId--Request(JSON) :"+ userProfileSearchObject_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(url,userProfileSearchObject_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[UserProfile]-findByFacebookId(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        UserProfile userProfile = new UserProfile();
        try{
            Gson gson = GsonUtil.fromJson();
            userProfile = gson.fromJson(result,UserProfile.class);
            Log.d("asd","[UserProfile]-findByFacebookId(Gson): "+userProfile);
        }catch (Exception e){
            Log.d("asd","[UserProfile]-findByFacebookId(Gson)-[Error]-[json to Gson Error] : ");
            e.printStackTrace();
        }
        return userProfile;
    }

    @Override
    public LoginModel checkLogin(String userProfile_json) {
        Log.d("asd","[UserProfile]-checkLogin--Request(JSON) :"+ userProfile_json);
        String result = "";
        result = "";
        try {
            result = new HttpPostAsync(this).execute(UserProfileServePath.INSTANCE.serve_checkLogin(),userProfile_json).get();
//    new HttpPostAsyncUI(this).execute(UserProfileServePath.serve_checkLogin(),userProfile_json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("asd","[UserProfile]-checkLogin(Response) :"+ result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        LoginModel loginModel = new LoginModel();
        try {
            Gson gson = GsonUtil.fromJson();
            loginModel = gson.fromJson(result,LoginModel.class);
            Log.d("asd","[UserProfile]-checkLogin(Split) : "+loginModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginModel;
    }

    @Override
    public UserProfile save(String userProfile_json) {
        String url = UserProfileServePath.INSTANCE.serve_save();

        Log.d("asd","[UserProfile]-save--Request(JSON) :"+ userProfile_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(url,userProfile_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[UserProfile]-save(Response) :"+ result);

        UserProfile userProfile = new UserProfile();
        try{
            Gson gson = GsonUtil.fromJson();
            userProfile = gson.fromJson(result,UserProfile.class);
            Log.d("asd","[UserProfile]-save(Gson): "+userProfile);
        }catch (Exception e){
            Log.d("asd","[UserProfile]-save(Gson)-[Error]-[json to Gson Error] : ");
            e.printStackTrace();
        }
        return userProfile;
    }

}
