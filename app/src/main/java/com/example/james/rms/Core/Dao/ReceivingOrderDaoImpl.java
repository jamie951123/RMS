package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
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

public class ReceivingOrderDaoImpl implements ReceivingOrderDao {

    @Override
    public List<ReceivingOrderModel> findReceivingOrderByPartyId(String json) {
        String result = "";
        Log.d("asd","[ReceivingOrderModel]-findReceivingOrderByPartyId(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync().execute(ReceivingOrderServePath.findReceivingOrderByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingOrderModel]-findReceivingOrderByPartyId-[Response(String)] : "+result);
        List<ReceivingOrderModel> receivingOrderModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ReceivingOrderModel>>() {}.getType();
            receivingOrderModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingOrderModels == null){
            Log.d("asd","[ReceivingOrderModel]-findReceivingOrderByPartyId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingOrderModel]-findReceivingOrderByPartyId(Response)  :" + receivingOrderModels.toString());
        return receivingOrderModels;
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
    public ReceivingOrderModel insertIntoReceivingOrder(String json) {
        String result = "";
        Log.d("asd", "[ReceivingOrderModel]- insertIntoReceivingOrder(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync().execute(ReceivingOrderServePath.save(), json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd", "[ReceivingOrderModel]-insertIntoReceivingOrder(Response-[String]):" + result);
        ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
        try {
            Gson gson = GsonUtil.fromJson();
            receivingOrderModel = gson.fromJson(result, ReceivingOrderModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (receivingOrderModel == null) {
            Log.d("asd", "[ReceivingOrderModel]-insertIntoReceivingOrder(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd", "[ReceivingOrderModel]-insertIntoReceivingOrder(Response--GSON):" + result);
        return receivingOrderModel;
    }

    @Override
    public ReceivingOrderModel saveOrderAndItem(String json) {
        Log.d("asd","[ReceivingOrderModel]-saveOrderAndItem(Request--JSON) :" + json);
        String result ="";
        try{
            result = new HttpPostAsync().execute(ReceivingOrderServePath.saveOrderAndItem(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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
}
