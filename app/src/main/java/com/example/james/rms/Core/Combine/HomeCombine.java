package com.example.james.rms.Core.Combine;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.google.gson.Gson;

/**
 * Created by Jamie on 15/6/2017.
 */

public class HomeCombine<T> {

    Class<T> classType;

    public HomeCombine(Class<T> classType){
        this.classType = classType;
    }

    public String modelToJson(T model){
        String result = null;
        try{
            Gson gson = GsonUtil.toJson();
            result = gson.toJson(model);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public T jsonToModel(String json){
        T model = null;
        try{
            Gson gson = GsonUtil.fromStringJson();
            model = gson.fromJson(json,classType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

}
