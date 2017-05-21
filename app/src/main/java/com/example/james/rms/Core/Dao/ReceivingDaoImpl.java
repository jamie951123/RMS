package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderAndItemContainer;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.ServePath.ReceivingServePath;
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

public class ReceivingDaoImpl implements ReceivingDao {

    @Override
    public List<ReceivingItemModel> findReceivingItemByPartyId(String json) {
        Log.d("asd","[ReceivingItemModel]-findReceivingItemByPartyId(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(ReceivingServePath.findReceivingItemByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ReceivingItemModel]-findReceivingItemByPartyId-[Response(String)] : "+result);
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
    public List<ReceivingOrderModel> findReceivingOrderByPartyId(String json) {
        String result = "";
        Log.d("asd","[ReceivingOrderModel]-findReceivingOrderByPartyId(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync().execute(ReceivingServePath.findReceivingOrderByPartyId(),json).get();
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
//            result = new HttpPostAsync().execute(ReceivingServePath.findOrderIdByPartyIdAndCreateDate(),json).get();
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
        String result ="";
        Log.d("asd", "[ReceivingOrderModel]- insertIntoReceivingOrder(Request--JSON) :" + json);
        try{
            result = new HttpPostAsync().execute(ReceivingServePath.insertToReceivingOrder(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[ReceivingOrderModel]-insertIntoReceivingOrder(Response-[String]):" + result);
        ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
        try{
            Gson gson = GsonUtil.fromJson();
            receivingOrderModel = gson.fromJson(result,ReceivingOrderModel.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(receivingOrderModel == null){
            Log.d("asd","[ReceivingOrderModel]-insertIntoReceivingOrder(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ReceivingOrderModel]-insertIntoReceivingOrder(Response--GSON):" + result);
        return receivingOrderModel;
    }

    @Override
    public List<ReceivingItemModel> insertIntoReceivingItem(String json) {
        String result ="";
        Log.d("asd","[ReceivingItemModel]-insertIntoReceivingItem(Request--JSON) :" + json);
        try{
            result = new HttpPostAsync().execute(ReceivingServePath.insertToReceivingItem(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[ReceivingOrderModel]-insertIntoReceivingItem(Response-[String]):" + result);
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
    public List<ReceivingOrderAndItemContainer> saveOrderAndItem(String json) {
        Log.d("asd","[ReceivingOrderAndItemContainer]-saveOrderAndItem(Request--JSON) :" + json);
        String result ="";
        try{
            result = new HttpPostAsync().execute(ReceivingServePath.saveOrderAndItem(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ReceivingOrderAndItemContainer container = new ReceivingOrderAndItemContainer();
        try {
            Gson gson = GsonUtil.fromJson();
            container = gson.fromJson(json, ReceivingOrderAndItemContainer.class);
            Log.d("asd", "[ReceivingOrderAndItemContainer]-saveOrderAndItem(Response-[Gson]):" + container);

            if (container != null) {
                Log.d("asd", "[ReceivingOrderAndItemContainer]-saveOrderAndItem(Response) [Error] : Serve have not response anything");
                return null;
            }
        }catch (Exception e){

        }
        Log.d("asd","[ReceivingOrderAndItemContainer]-saveOrderAndItem(Response):" + result);
        return null;
    }
}
