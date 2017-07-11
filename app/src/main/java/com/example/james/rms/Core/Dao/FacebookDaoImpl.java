package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.Facebook;
import com.example.james.rms.Core.ServePath.FacebookServerPath;
import com.example.james.rms.NetWork.HttpGetAsync;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jamie on 11/7/2017.
 */

public class FacebookDaoImpl implements FacebookDao {

    @Override
    public List<Facebook> findAll() {
        String url = FacebookServerPath.serve_findAll();
        String result = "";
        try {
            result = new HttpGetAsync().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[Facebook]-findAll(Response): "+result);
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
        String url = FacebookServerPath.serve_findbyFacebookId();
        Log.d("asd","[Facebook]-findByFacebookId--Request(JSON) :"+ facebookSearch_json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(url,facebookSearch_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[Facebook]-findByFacebookId(Response): "+result);
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
        String url = FacebookServerPath.serve_countFacebookId();
        Log.d("asd","[Facebook]-countFacebookId--Request(JSON) :"+ facebookSearch_json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(url,facebookSearch_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[Facebook]-countFacebookId(Response): "+result);
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
