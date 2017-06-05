package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.ServePath.InventoryServePath;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventoryDaoImpl implements InventoryDao {


    @Override
    public List<InventoryModel> findByPartyId(String json) {
        Log.d("asd","[InventoryModel]-findByPartyId(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-findByPartyId-[Response(String)] : "+result);
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventoryModel]-findByPartyId-[Response(Gson)] : "+inventoryModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventoryModels == null){
            Log.d("asd","[InventoryModel]-findByPartyId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventoryModel]-findByPartyId(Response) :" + inventoryModels.toString());
        return inventoryModels;
    }

    @Override
    public InventoryModel save(String json) {
        Log.d("asd","[InventoryModel]-save(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_insertInventory(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-save-[Response(String)]: :"+result);
        InventoryModel inventoryModel = new InventoryModel();
        try{
            Gson gson = new Gson();
            inventoryModel = gson.fromJson(result,InventoryModel.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-save-[Response(GSON)]: :"+inventoryModel);

        return inventoryModel;
    }


    @Override
    public List<InventoryModel> saves(String json) {
        Log.d("asd","[InventoryModel]-saves(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_insertInventorys(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-saves-[Response(String)]: :"+result);
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-saves-[Response(GSON)]: :"+inventoryModels);

        return inventoryModels;
    }

    @Override
    public List<InventoryModel> findByPartyIdAndStatus(String json) {
        Log.d("asd","[InventoryModel]-findByPartyIdAndStatus(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_findByPartyIdAndStatus(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-findByPartyIdAndStatus-[Response(String)] : "+result);
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventoryModel]-findByPartyIdAndStatus-[Response(Gson)] : "+inventoryModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventoryModels == null){
            Log.d("asd","[InventoryModel]-findByPartyIdAndStatus(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventoryModel]-findByPartyIdAndStatus(Response) :" + inventoryModels.toString());
        return inventoryModels;
    }

    @Override
    public ResponseMessage deleteByProductId(String product_json) {
        Log.d("asd","[InventoryModel]-saves(Request--JSON):" + product_json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_deleteByProductId(),product_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-saves-[Response(String)]: :"+result);
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


