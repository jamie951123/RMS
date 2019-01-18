package com.example.james.rms.core.dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.Facebook;
import com.example.james.rms.core.model.NetworkModel;
import com.example.james.rms.core.serve_path.FacebookServerPath;
import com.example.james.rms.network.HttpGetAsync;
import com.example.james.rms.network.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jamie on 11/7/2017.
 */

public class FacebookDaoImpl extends NetworkModel implements FacebookDao {

    public FacebookDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<Facebook> findAll() {
        String url = FacebookServerPath.INSTANCE.serve_findAll();
        String result = "";
        try {
            result = new HttpGetAsync(this).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[Facebook]-findAll(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        List<Facebook> facebooks = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<Facebook>>() {}.getType();
            facebooks = gson.fromJson(result,listType);
            Log.d("asd","[Facebook]-findByFacebookId(Gson): "+facebooks);
        }catch (Exception e){
            Log.d("asd","[Facebook]-findByFacebookId(Gson)-[Error]-[json to Gson Error] : ");
            e.printStackTrace();
        }
        return facebooks;
    }

    @Override
    public Facebook findByFacebookId(String facebookSearch_json) {
        String url = FacebookServerPath.INSTANCE.serve_findbyFacebookId();
        Log.d("asd","[Facebook]-findByFacebookId--Request(JSON) :"+ facebookSearch_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(url,facebookSearch_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[Facebook]-findByFacebookId(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        Facebook facebook = new Facebook();
        try{
            Gson gson = GsonUtil.fromJson();
            facebook = gson.fromJson(result,Facebook.class);
            Log.d("asd","[Facebook]-findByFacebookId(Gson): "+facebook);
        }catch (Exception e){
            Log.d("asd","[Facebook]-findByFacebookId(Gson)-[Error]-[json to Gson Error] : ");
            e.printStackTrace();
        }
        return facebook;
    }

    @Override
    public Integer countFacebookId(String facebookSearch_json) {
        String url = FacebookServerPath.INSTANCE.serve_countFacebookId();
        Log.d("asd","[Facebook]-countFacebookId--Request(JSON) :"+ facebookSearch_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(url,facebookSearch_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[Facebook]-countFacebookId(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        if(ObjectUtil.isNotNullEmpty(result)){
            return ObjectUtil.stringToInteger(result);
        }else{
            return null;
        }
//        Facebook facebook = new Facebook();
//        try{
//            Gson gson = GsonUtil.fromJson();
//            facebook = gson.fromJson(result,Facebook.class);
//            Log.d("asd","[Facebook]-countFacebookId(Gson): "+facebook);
//        }catch (Exception e){
//            Log.d("asd","[Facebook]-countFacebookId(Gson)-[Error]-[json to Gson Error] : ");
//            e.printStackTrace();
//        }
    }
}
