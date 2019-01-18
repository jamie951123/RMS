package com.example.james.rms.core.dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.DeliveryOrderModel;
import com.example.james.rms.core.model.NetworkModel;
import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.core.serve_path.DeliveryOrderServePath;
import com.example.james.rms.network.HttpGetAsync;
import com.example.james.rms.network.HttpPostAsync;
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

public class DeliveryOrderDaoImpl extends NetworkModel implements DeliveryOrderDao {

    public DeliveryOrderDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<DeliveryOrderModel> findAll() {
        Log.d("asd:","[DeliveryOrderDao]-findAll(Request--JSON) ");
        String result = "";
        List<DeliveryOrderModel> deliveryOrderModels = new ArrayList<>();
        try {
            result = new HttpGetAsync(this).execute(DeliveryOrderServePath.INSTANCE.serve_findAll()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-findAll(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<DeliveryOrderModel>>() {}.getType();
            deliveryOrderModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryOrderModel]-findAll(Gson): "+deliveryOrderModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryOrderModels;
    }

    @Override
    public List<DeliveryOrderModel> findByPartyIdAndStatus(String deliverySearchObject) {
        Log.d("asd:","[DeliveryOrderModel]-findByPartyIdAndStatus(Request--JSON)  :" + deliverySearchObject);
        String result = "";
        List<DeliveryOrderModel> deliveryOrderModels = new ArrayList<>();
        try {
            result = new HttpPostAsync(this).execute(DeliveryOrderServePath.INSTANCE.serve_findByPartyIdAndStatus(),deliverySearchObject).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-findByPartyIdAndStatus(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<DeliveryOrderModel>>() {}.getType();
            deliveryOrderModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryOrderModel]-findByPartyIdAndStatus(Gson): "+deliveryOrderModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryOrderModels;
    }

    @Override
    public List<DeliveryOrderModel> findByOrderIdAndStatus(String deliverySearchObject) {
        Log.d("asd:","[DeliveryOrderModel]-findByOrderIdAndStatus(Request--JSON)  :" + deliverySearchObject);
        String result = "";
        List<DeliveryOrderModel> deliveryOrderModels = new ArrayList<>();
        try {
            result = new HttpPostAsync(this).execute(DeliveryOrderServePath.INSTANCE.serve_findByOrderIdAndStatus(),deliverySearchObject).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-findByOrderIdAndStatus(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<DeliveryOrderModel>>() {}.getType();
            deliveryOrderModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryOrderModel]-findByOrderIdAndStatus(Gson): "+deliveryOrderModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryOrderModels;
    }

    @Override
    public DeliveryOrderModel save(String deliveryOrderModel_json) {
        Log.d("asd:","[DeliveryOrderModel]-save(Request--JSON)  :" + deliveryOrderModel_json);
        String result = "";
        DeliveryOrderModel deliveryOrderModel = new DeliveryOrderModel();
        try {
            result = new HttpPostAsync(this).execute(DeliveryOrderServePath.INSTANCE.serve_save(),deliveryOrderModel_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-save(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            deliveryOrderModel = gson.fromJson(result,DeliveryOrderModel.class);
            Log.d("asd","[DeliveryOrderModel]-save(Gson): "+deliveryOrderModel);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryOrderModel;
    }

    @Override
    public DeliveryOrderModel saveOrderAndItem(String deliveryOrderModel_json) {
        Log.d("asd:","[DeliveryOrderModel]-saveOrderAndItem(Request--JSON)  :" + deliveryOrderModel_json);
        String result = "";
        DeliveryOrderModel deliveryOrderModel = new DeliveryOrderModel();
        try {
            result = new HttpPostAsync(this).execute(DeliveryOrderServePath.INSTANCE.serve_saveOrderAndItem(),deliveryOrderModel_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-saveOrderAndItem(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            deliveryOrderModel = gson.fromJson(result,DeliveryOrderModel.class);
            Log.d("asd","[DeliveryOrderModel]-saveOrderAndItem(Gson): "+deliveryOrderModel);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryOrderModel;
    }

    @Override
    public ResponseMessage delete(String DeliveryOrderModel) {
        Log.d("asd:","[DeliveryOrderModel]-delete(Request--JSON)  :" + DeliveryOrderModel);
        String result = "";
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            result = new HttpPostAsync(this).execute(DeliveryOrderServePath.INSTANCE.serve_delete(),DeliveryOrderModel).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-delete(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            responseMessage = gson.fromJson(result,ResponseMessage.class);
            Log.d("asd","[DeliveryOrderModel]-delete(Gson): "+responseMessage);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return responseMessage;
    }
}
