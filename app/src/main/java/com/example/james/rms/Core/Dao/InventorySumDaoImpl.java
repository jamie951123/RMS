package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.InventorySumModel;
import com.example.james.rms.Core.ServePath.InventorySumServePath;
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

public class InventorySumDaoImpl implements InventorySumDao {
    @Override
    public List<InventorySumModel> findByPartyIdAndStatus(String json) {
        Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventorySumServePath.serve_findByPartyIdAndStatus(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<InventorySumModel> inventorySumModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventorySumModel>>() {}.getType();
            inventorySumModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventorySum]-findByPartyIdAndStatus-[Response(Gson)] : "+ inventorySumModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventorySumModels == null){
            Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Response) :" + inventorySumModels.toString());
        return inventorySumModels;
    }

    @Override
    public List<InventorySumModel> findByPartyIdAndStatusOrderByProductId(String json) {
        Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(InventorySumServePath.serve_findByPartyIdAndStatusOrderByProductId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<InventorySumModel> inventorySumModels = new ArrayList<>();
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<InventorySumModel>>() {}.getType();
            inventorySumModels = gson.fromJson(result,listType);
            Log.d("asd:","[InventorySum]-findByPartyIdAndStatus-[Response(Gson)] : "+ inventorySumModels);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(inventorySumModels == null){
            Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[InventorySum]-findByPartyIdAndStatus(Response) :" + inventorySumModels.toString());
        return inventorySumModels;
    }
}
