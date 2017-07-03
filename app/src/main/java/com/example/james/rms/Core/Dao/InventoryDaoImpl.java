package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.ServePath.InventoryServePath;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamie on 2017/5/1.
 */

public class InventoryDaoImpl implements InventoryDao {
    @Override
    public List<InventoryModel> findByPartyIdAndStatus(String json) {
        Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_findByPartyIdAndStatus(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventorySum]-findByPartyIdAndStatus-[Response(Gson)] : "+ inventoryModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventoryModels == null){
            Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Response) :" + inventoryModels.toString());
        return inventoryModels;
    }

    @Override
    public List<InventoryModel> findByPartyIdAndStatusOrderByProductIdAsc(String json) {
        Log.d("asd","[InventorySum]-findByPartyIdAndStatusOrderByProductIdAsc(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventoryServePath.serve_findByPartyIdAndStatusOrderByProductIdAsc(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventorySum]-findByPartyIdAndStatusOrderByProductIdAsc-[Response(Gson)] : "+ inventoryModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventoryModels == null){
            Log.d("asd","[InventorySum]-findByPartyIdAndStatusOrderByProductIdAsc(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventorySum]-findByPartyIdAndStatusOrderByProductIdAsc(Response) :" + inventoryModels.toString());
        return inventoryModels;
    }

}
