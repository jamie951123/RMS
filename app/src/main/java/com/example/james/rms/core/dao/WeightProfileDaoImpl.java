package com.example.james.rms.core.dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.NetworkModel;
import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.core.model.WeightProfileModel;
import com.example.james.rms.core.serve_path.WeightServePath;
import com.example.james.rms.network.HttpGetAsync;
import com.example.james.rms.network.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamie on 2017/4/23.
 */

public class WeightProfileDaoImpl extends NetworkModel implements WeightProfileDao {

    public WeightProfileDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<WeightProfileModel> findAll() {
        String url = WeightServePath.INSTANCE.serve_findAll();
        String result="";
        try {
            result = new HttpGetAsync(this).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[WeightProfileModel]-findAll(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
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
            result = new HttpPostAsync(this).execute(WeightServePath.INSTANCE.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[WeightProfileModel]-findByPartyId-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
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
            result = new HttpPostAsync(this).execute(WeightServePath.INSTANCE.serve_delete(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[WeightProfileModel]-delete-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
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

    @Override
    public WeightProfileModel save(String json) {
        Log.d("asd","[WeightProfileModel]-save(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(WeightServePath.INSTANCE.serve_save(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[WeightProfileModel]-save-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        WeightProfileModel weightProfileModel = new WeightProfileModel();
        try{
            Gson gson = GsonUtil.fromJson();
            weightProfileModel = gson.fromJson(result,WeightProfileModel.class);
            Log.d("asd:","[WeightProfileModel]-save-[Response(Gson)] : "+weightProfileModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(weightProfileModel == null){
            Log.d("asd","[WeightProfileModel]-save(Response) [Error] : Serve have not response anything");
            return null;
        }
        return weightProfileModel;
    }


}
