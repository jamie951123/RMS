package com.example.james.rms.Core.Dao;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.ServePath.ReceivingItemServePath;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamie on 2017/6/10.
 */

public class ReceivingItemDaoImpl extends NetworkModel implements ReceivingItemDao {


    public ReceivingItemDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<ReceivingItemModel> findReceivingItemByPartyId(String json) {
        Log.d("asd","[ReceivingItemModel]-findReceivingItemByPartyId(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ReceivingItemServePath.findReceivingItemByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingItemModel]-findReceivingItemByPartyId-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

        List<ReceivingItemModel> receivingItemModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ReceivingItemModel>>() {}.getType();
            receivingItemModels = gson.fromJson(result,listType);
            Log.d("asd:","[ReceivingItemModel]-findReceivingItemByPartyId-[Response(Gson)] : "+result);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingItemModels == null){
            Log.d("asd","[ReceivingItemModel]-findReceivingItemByPartyId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingItemModel]-findReceivingItemByPartyId(Response) :" + receivingItemModels.toString());
        return receivingItemModels;
    }

    @Override
    public List<ReceivingItemModel> saves(String json) {
        String result ="";
        Log.d("asd","[ReceivingItemModel]-insertIntoReceivingItem(Request--JSON) :" + json);
        try{
            result = new HttpPostAsync(this).execute(ReceivingItemServePath.saves(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[ReceivingOrderModel]-insertIntoReceivingItem(Response-[String]):" + result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        List<ReceivingItemModel> receivingItemModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ReceivingItemModel>>() {}.getType();
            receivingItemModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingItemModels == null && receivingItemModels.isEmpty()){
            Log.d("asd","[ReceivingOrderModel]-insertIntoReceivingOrder(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingItemModel]-insertIntoReceivingItem(Response):" + result);
        return receivingItemModels;
    }

    @Override
    public ResponseMessage delete(String receivingItem_json) {
        Log.d("asd:","[ReceivingItemModel]-delete-[Request (JSON)]: :"+receivingItem_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ReceivingItemServePath.delete(),receivingItem_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingItemModel]-delete-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        if(ObjectUtil.isNotNullEmpty(result)){
            ResponseMessage responseMessage = new ResponseMessage();
            try {
                Gson gson = GsonUtil.fromJson();
                responseMessage = gson.fromJson(result,responseMessage.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseMessage;
        }
        return null;
    }
}
