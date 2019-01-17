package com.example.james.rms.Core.Dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.ServePath.DeliveryItemServePath;
import com.example.james.rms.NetWork.HttpGetAsync;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryItemDaoImpl extends NetworkModel implements DeliveryItemDao {

    public DeliveryItemDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<DeliveryItemModel> findAll() {
        Log.d("asd:","[DeliveryItemModel]-findAll(Request--JSON) ");
        String result = "";
        List<DeliveryItemModel> deliveryItemModels = new ArrayList<>();
        try {
            result = new HttpGetAsync(this).execute(DeliveryItemServePath.INSTANCE.serve_findAll()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryItemModel]-findAll(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<DeliveryItemModel>>() {}.getType();
            deliveryItemModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryItemModel]-findAll(Gson): "+deliveryItemModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryItemModels;
    }

    @Override
    public List<DeliveryItemModel> findByPartyIdAndStatus(String deliveryItemSearch_json) {
        Log.d("asd:","[DeliveryItemModel]-findByPartyIdAndStatus(Request--JSON) ");
        String result = "";
        List<DeliveryItemModel> deliveryItemModels = new ArrayList<>();
        try {
            result = new HttpPostAsync(this).execute(DeliveryItemServePath.INSTANCE.serve_findByPartyIdAndStatus(),deliveryItemSearch_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryItemModel]-findByPartyIdAndStatus(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<DeliveryItemModel>>() {}.getType();
            deliveryItemModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryItemModel]-findAll(Gson): "+deliveryItemModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryItemModels;
    }

    @Override
    public List<DeliveryItemModel> findByDeliveryItemIdAndStatus() {
        return null;
    }

    @Override
    public DeliveryItemModel save(String deliveryItemModel) {
        return null;
    }

    @Override
    public List<DeliveryItemModel> saves(String deliveryItemModels) {
        return null;
    }

    @Override
    public ResponseMessage delete(String deliveryItemModel_json) {
        Log.d("asd:","[DeliveryItemModel]-delete(Request--JSON):" + deliveryItemModel_json);
        String result = "";
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            result = new HttpPostAsync(this).execute(DeliveryItemServePath.INSTANCE.serve_delete(),deliveryItemModel_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryItemModel]-delete(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            responseMessage = gson.fromJson(result,ResponseMessage.class);
            Log.d("asd","[DeliveryItemModel]-delete(Gson): "+responseMessage);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }

        return responseMessage;
    }
}
