package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.ServePath.QuantityServePath;
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

public class QuantityProfileDaoImpl implements QuantityProfileDao {
    @Override
    public List<QuantityProfileModel> findAll() {
        String url = QuantityServePath.serve_findAll();
        String result="";
        try {
            result = new HttpGetAsync().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[QuantityProfileModel]-findAll(Response): "+result);
        List<QuantityProfileModel> quantityProfileModels = new ArrayList<>();
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<QuantityProfileModel>>() {}.getType();
            quantityProfileModels = gson.fromJson(result,listType);
            Log.d("asd","[QuantityProfileModel]-findAll(Gson): "+quantityProfileModels);
        }catch (Exception e){

        }
        return quantityProfileModels;
    }

    @Override
    public List<QuantityProfileModel> findByPartyId(String json) {
        Log.d("asd","[QuantityProfileModel]-findByPartyId(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(QuantityServePath.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[QuantityProfileModel]-findByPartyId-[Response(String)] : "+result);
        List<QuantityProfileModel> quantityProfileModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<QuantityProfileModel>>() {}.getType();
            quantityProfileModels = gson.fromJson(result,listType);
            Log.d("asd:","[QuantityProfileModel]-findByPartyId-[Response(Gson)] : "+result);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(quantityProfileModels == null){
            Log.d("asd","[QuantityProfileModel]-findByPartyId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[QuantityProfileModel]-findByPartyId(Response) :" + quantityProfileModels.toString());
        return quantityProfileModels;
    }

    @Override
    public ResponseMessage delete(String json) {
        Log.d("asd","[QuantityProfileModel]-delete(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(QuantityServePath.serve_quantityDelete(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[QuantityProfileModel]-delete-[Response(String)] : "+result);
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            Gson gson = GsonUtil.fromJson();
            responseMessage = gson.fromJson(result,ResponseMessage.class);
            Log.d("asd:","[QuantityProfileModel]-delete-[Response(Gson)] : "+responseMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(responseMessage == null){
            Log.d("asd","[QuantityProfileModel]-delete(Response) [Error] : Serve have not response anything");
            return null;
        }
        return responseMessage;
    }
}
