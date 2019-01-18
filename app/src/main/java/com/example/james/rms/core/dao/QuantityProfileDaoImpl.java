package com.example.james.rms.core.dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.NetworkModel;
import com.example.james.rms.core.model.QuantityProfileModel;
import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.core.serve_path.QuantityServePath;
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

public class QuantityProfileDaoImpl extends NetworkModel implements QuantityProfileDao {


    public QuantityProfileDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<QuantityProfileModel> findAll() {
        String url = QuantityServePath.INSTANCE.serve_findAll();
        String result="";
        try {
            result = new HttpGetAsync(this).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[QuantityProfileModel]-findAll(Response): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

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
            result = new HttpPostAsync(this).execute(QuantityServePath.INSTANCE.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[QuantityProfileModel]-findByPartyId-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

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
            result = new HttpPostAsync(this).execute(QuantityServePath.INSTANCE.serve_quantityDelete(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[QuantityProfileModel]-delete-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

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

    @Override
    public QuantityProfileModel save(String json) {
        Log.d("asd","[QuantityProfileModel]-save(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(QuantityServePath.INSTANCE.serve_save(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[QuantityProfileModel]-save-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

        QuantityProfileModel quantityProfileModel = new QuantityProfileModel();
        try{
            Gson gson = GsonUtil.fromJson();
            quantityProfileModel = gson.fromJson(result,QuantityProfileModel.class);
            Log.d("asd:","[QuantityProfileModel]-save-[Response(Gson)] : "+quantityProfileModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(quantityProfileModel == null){
            Log.d("asd","[QuantityProfileModel]-save(Response) [Error] : Serve have not response anything");
            return null;
        }
        return quantityProfileModel;
    }

//    @Override
//    public Integer updateQtyUnit(String json) {
//        Log.d("asd","[QuantityProfileModel]-updateQtyUnit(Request--JSON):" + json);
//        String result = "";
//        try {
//            result = new HttpPostAsync().execute(QuantityServePath.serve_updateQtyByQuantityIdAndPartyIdAndQtyUnit(),json).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        Log.d("asd:","[QuantityProfileModel]-updateQtyUnit-[Response(String)]: :"+result);
//        if(ObjectUtil.isNotNullEmpty(result)){
//            return Integer.parseInt(result);
//        }
//        Log.d("asd:","[QuantityProfileModel]-updateQtyUnit-[ERROR]: -- Updata fail");
//
//        return null;
//    }
}
