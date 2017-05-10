package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.ServePath.WeightServePath;
import com.example.james.rms.NetWork.HttpGetAsync;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamie on 2017/4/23.
 */

public class WeightProfileDaoImpl implements WeightProfileDao {
    @Override
    public List<WeightProfileModel> findAll() {
        String url = WeightServePath.serve_findAll();
        String result="";
        try {
            result = new HttpGetAsync().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[WeightProfileModel]-findAll(Response): "+result);
        List<WeightProfileModel> weightProfileModels = new ArrayList<>();
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<WeightProfileModel>>() {}.getType();
            weightProfileModels = gson.fromJson(result,listType);
            Log.d("asd","[WeightProfileModel]-findAll(Gson): "+weightProfileModels);
        }catch (Exception e){

        }
        return weightProfileModels;
    }

    @Override
    public List<WeightProfileModel> findByPartyId(String json) {
        Log.d("asd","[WeightProfileModel]-findByPartyId(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(WeightServePath.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[WeightProfileModel]-findByPartyId-[Response(String)] : "+result);
        List<WeightProfileModel> weightProfileModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<WeightProfileModel>>() {}.getType();
            weightProfileModels = gson.fromJson(result,listType);
            Log.d("asd:","[WeightProfileModel]-findByPartyId-[Response(Gson)] : "+result);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(weightProfileModels == null){
            Log.d("asd","[WeightProfileModel]-findByPartyId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[WeightProfileModel]-findByPartyId(Response) :" + weightProfileModels.toString());
        return weightProfileModels;
    }

    @Override
    public ResponseMessage delete(String json) {
        Log.d("asd","[WeightProfileModel]-delete(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(WeightServePath.serve_delete(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[WeightProfileModel]-delete-[Response(String)] : "+result);
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            Gson gson = GsonUtil.fromJson();
            responseMessage = gson.fromJson(result,ResponseMessage.class);
            Log.d("asd:","[WeightProfileModel]-delete-[Response(Gson)] : "+responseMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(responseMessage == null){
            Log.d("asd","[WeightProfileModel]-delete(Response) [Error] : Serve have not response anything");
            return null;
        }
        return responseMessage;
    }


}
