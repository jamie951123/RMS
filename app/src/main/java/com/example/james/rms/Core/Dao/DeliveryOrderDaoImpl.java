package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.ServePath.DeliveryOrderServePath;
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

public class DeliveryOrderDaoImpl implements DeliveryOrderDao {


    @Override
    public List<DeliveryOrderModel> findAll() {
        Log.d("asd:","[DeliveryOrderDao]-findAll(Request--JSON) ");
        String result = "";
        List<DeliveryOrderModel> deliveryOrderModels = new ArrayList<>();
        try {
            result = new HttpGetAsync().execute(DeliveryOrderServePath.serve_findAll()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[DeliveryOrderModel]-findAll(Response--String): :"+result);
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<DeliveryOrderModel>>() {}.getType();
            deliveryOrderModels = gson.fromJson(result,listType);
            Log.d("asd","[DeliveryOrderModel]-findAll(Gson): "+deliveryOrderModels);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return deliveryOrderModels;
    }

    @Override
    public List<DeliveryOrderModel> findByOrderIdAndStatus(String deliverySearchObject) {
        return null;
    }
}
