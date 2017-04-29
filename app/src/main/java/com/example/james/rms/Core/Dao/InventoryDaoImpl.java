package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.ServePath.InventoryServePath;
import com.example.james.rms.Core.ServePath.ReceivingServePath;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
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
    public InventoryModel save(InventoryModel inventory) {
        return null;
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
}


