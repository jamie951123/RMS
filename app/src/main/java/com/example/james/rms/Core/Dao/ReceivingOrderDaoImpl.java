package com.example.james.rms.Core.Dao;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.ServePath.ReceivingOrderServePath;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingOrderDaoImpl extends NetworkModel implements ReceivingOrderDao {


    public ReceivingOrderDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<ReceivingOrderModel> findByPartyId(String json) {
        String result = "";
        Log.d("asd","[ReceivingOrderModel]-findByPartyId(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync(this).execute(ReceivingOrderServePath.INSTANCE.findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingOrderModel]-findByPartyId-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        List<ReceivingOrderModel> receivingOrderModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ReceivingOrderModel>>() {}.getType();
            receivingOrderModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingOrderModels == null){
            Log.d("asd","[ReceivingOrderModel]-findByPartyId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingOrderModel]-findByPartyId(Response)  :" + receivingOrderModels.toString());
        return receivingOrderModels;
    }

    @Override
    public List<ReceivingOrderModel> findByPartyIdAndStatus(String json) {
        String result = "";
        Log.d("asd","[ReceivingOrderModel]-findByPartyIdAndStatus(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync(this).execute(ReceivingOrderServePath.INSTANCE.findByPartyIdAndStatus(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingOrderModel]-findByPartyIdAndStatus-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        List<ReceivingOrderModel> receivingOrderModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ReceivingOrderModel>>() {}.getType();
            receivingOrderModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingOrderModels == null){
            Log.d("asd","[ReceivingOrderModel]-findByPartyIdAndStatus(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingOrderModel]-findByPartyIdAndStatus(Response)  :" + receivingOrderModels.toString());
        return receivingOrderModels;
    }

    @Override
    public List<ReceivingOrderModel> findByOrderIdAndStatus(String json) {
        String result = "";
        Log.d("asd","[ReceivingOrderModel]-findByOrderIdAndStatus(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync(this).execute(ReceivingOrderServePath.INSTANCE.findByOrderIdAndStatus(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingOrderModel]-findByOrderIdAndStatus-[Response(String)] : "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        List<ReceivingOrderModel> receivingOrderModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ReceivingOrderModel>>() {}.getType();
            receivingOrderModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingOrderModels == null){
            Log.d("asd","[ReceivingOrderModel]-findByOrderIdAndStatus(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingOrderModel]-findByOrderIdAndStatus(Response)  :" + receivingOrderModels.toString());
        return receivingOrderModels;
    }

    @Override
    public ReceivingOrderModel save(String json) {
        String result = "";
        Log.d("asd", "[ReceivingOrderModel]- save(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync(this).execute(ReceivingOrderServePath.INSTANCE.save(), json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd", "[ReceivingOrderModel]-save(Response-[String]):" + result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
        try {
            Gson gson = GsonUtil.fromJson();
            receivingOrderModel = gson.fromJson(result, ReceivingOrderModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (receivingOrderModel == null) {
            Log.d("asd", "[ReceivingOrderModel]-save(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd", "[ReceivingOrderModel]-save(Response--GSON):" + result);
        return receivingOrderModel;
    }

//    @Override
//    public List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json) {
//        String result = "";
//        Log.d("asd","findReceivingOrderByPartyIdAndCreateDate(Request--JSON) :" + json);
//        try {
//            result = new HttpPostAsync().execute(ReceivingOrderServePath.findOrderIdByPartyIdAndCreateDate(),json).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        Log.d("asd:","[ReceivingOrderModel]-findReceivingOrderByPartyIdAndCreateDate-[Response(String)]: :"+result);
//        List<ReceivingOrderModel> receivingOrderModel = receivingSplit.convertReceivingOrder(result);
//        Log.d("asd","findReceivingOrderByPartyIdAndCreateDate(Response) :" + receivingOrderModel.toString());
//        return receivingOrderModel;
//    }


    @Override
    public ReceivingOrderModel saveOrderAndItem(String json) {
        Log.d("asd","[ReceivingOrderModel]-saveOrderAndItem(Request--JSON) :" + json);
        String result ="";
        try{
            result = new HttpPostAsync(this).execute(ReceivingOrderServePath.INSTANCE.saveOrderAndItem(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd", "[ReceivingOrderModel]-saveOrderAndItem(Response-[String]):" + result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
        try {
            Gson gson = GsonUtil.fromJson();
            receivingOrderModel = gson.fromJson(json, ReceivingOrderModel.class);
            Log.d("asd","[ReceivingOrderModel]-saveOrderAndItem(Response):" + result);
            if (receivingOrderModel == null) {
                Log.d("asd", "[ReceivingOrderModel]-saveOrderAndItem(Response) [Error] : Serve have not response anything");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("asd", "[ReceivingOrderModel]-saveOrderAndItem(Response-[Gson]):" + receivingOrderModel);
        return receivingOrderModel;
    }

    @Override
    public ResponseMessage delete(String receivingOrder_json) {
        Log.d("asd:","[ReceivingOrderModel]-delete-[Request (JSON)]: :"+receivingOrder_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ReceivingOrderServePath.INSTANCE.delete(),receivingOrder_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingOrderModel]-delete-[Response(String)]: :"+result);
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
