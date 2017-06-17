package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.ServePath.DeliveryItemServePath;
import com.example.james.rms.NetWork.HttpGetAsync;
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

public class DeliveryItemDaoImpl implements DeliveryItemDao {

    @Override
    public List<DeliveryItemModel> findAll() {
        Log.d("asd:","[DeliveryItemModel]-findAll(Request--JSON) ");
        String result = "";
        List<DeliveryItemModel> deliveryItemModels = new ArrayList<>();
        try {
            result = new HttpGetAsync().execute(DeliveryItemServePath.serve_findAll()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryItemModel]-findAll(Response--String): :"+result);
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<DeliveryItemModel>>() {}.getType();
            deliveryItemModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryItemModel]-findAll(Gson): "+deliveryItemModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryItemModels;
    }

    @Override
    public List<DeliveryItemModel> findByDeliveryItemIdAndStatus() {
        return null;
    }
}
