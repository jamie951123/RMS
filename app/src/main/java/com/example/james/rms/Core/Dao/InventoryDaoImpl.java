package com.example.james.rms.Core.Dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.NetworkModel;
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

public class InventoryDaoImpl extends NetworkModel implements InventoryDao {


    public InventoryDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<InventoryModel> findByPartyIdAndStatus(String json) {
        Log.d("asd","[InventoryModel]-findByPartyIdAndStatus(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(InventoryServePath.INSTANCE.serve_findByPartyIdAndStatus(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-findByPartyIdAndStatus(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventoryModel]-findByPartyIdAndStatus-[Response(Gson)] : "+ inventoryModels);
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
    public List<InventoryModel> findByPartyIdAndStatusOrderByProductIdAsc(String json) {
        Log.d("asd","[InventoryModel]-findByPartyIdAndStatusOrderByProductIdAsc(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(InventoryServePath.INSTANCE.serve_findByPartyIdAndStatusOrderByProductIdAsc(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[InventoryModel]-findByPartyIdAndStatusOrderByProductIdAsc(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        List<InventoryModel> inventoryModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventoryModel>>() {}.getType();
            inventoryModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventoryModel]-findByPartyIdAndStatusOrderByProductIdAsc-[Response(Gson)] : "+ inventoryModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventoryModels == null){
            Log.d("asd","[InventoryModel]-findByPartyIdAndStatusOrderByProductIdAsc(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventoryModel]-findByPartyIdAndStatusOrderByProductIdAsc(Response) :" + inventoryModels.toString());
        return inventoryModels;
    }

}
