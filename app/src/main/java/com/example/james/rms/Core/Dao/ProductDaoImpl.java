package com.example.james.rms.Core.Dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.ServePath.ProductServerPath;
import com.example.james.rms.NetWork.HttpGetAsync;
import com.example.james.rms.NetWork.HttpPostAsync;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by james on 26/3/2017.
 */

public class ProductDaoImpl extends NetworkModel implements ProductDao {


    public ProductDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<ProductModel> findAll() {
        Log.d("asd:","[ProductModel]-findAll(Request--JSON) :");
        String result = "";
        List<ProductModel> products = new ArrayList<>();
        try {
            result = new HttpGetAsync(this).execute(ProductServerPath.INSTANCE.serve_findAll()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-findAll(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ProductModel>>() {}.getType();
            products = gson.fromJson(result,listType);
            Log.d("asd","[ProductModel]-findAll(Gson): "+products);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ProductModel findByProductId(String json) {
        Log.d("asd:","[ProductModel]-findByProductId(Request--JSON)  :" +json);
        String result = "";
        ProductModel product = new ProductModel();
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_findByProductId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-findByProductId(Response--String): :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            product = gson.fromJson(result,ProductModel.class);
            Log.d("asd","[ProductModel]-findByProductId(Gson): "+product);
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<ProductModel> findByPartyId(String json) {
        Log.d("asd:","[ProductModel]-findByPartyId(Request--JSON)  :" +json);
        String result = "";
        List<ProductModel> products = new ArrayList<>();
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[ProductModel]-findByPartyId(Response-String): "+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        try{
            Gson gson = GsonUtil.fromJson();
            Type listType = new TypeToken<List<ProductModel>>() {}.getType();
            products = gson.fromJson(result,listType);
            Log.d("asd","[ProductModel]-findByPartyId(Gson): "+products);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("asd","[ProductModel]-findByPartyId(Error) : Convert GsonError ");
        }

        return products;
    }

    public ProductModel save(String json){
        Log.d("asd:","[ProductModel]-insertProduct-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_insertProduct(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-insertProduct-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }

        ProductModel productModel = new ProductModel();
        try{
            Gson gson = new Gson();
            productModel = gson.fromJson(result,ProductModel.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-insertProduct-[Response(GSON)]: :"+productModel);

        return productModel;
    }

    @Override
    public Integer updateWeightIdNullByWeightIdAndPartyId(String json) {
        Log.d("asd:","[ProductModel]-updateWeightIdNullByWeightIdAndPartyId-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_updateWeightIdNullByWeightIdAndPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-updateWeightIdNullByWeightIdAndPartyId-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        if(ObjectUtil.isNotNullEmpty(result)){
            return Integer.parseInt(result);
        }
        Log.d("asd:","[ProductModel]-updateWeightIdNullByWeightIdAndPartyId-[ERROR]: -- Updata fail");
        return null;
    }

    @Override
    public Integer updateQuantityIdNullByQuantityIdAndPartyId(String json) {
        Log.d("asd:","[ProductModel]-updateQuantityIdNullByWeightIdAndPartyId-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_updateQuantityIdNullByQuantityIdAndPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-updateQuantityIdNullByWeightIdAndPartyId-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        if(ObjectUtil.isNotNullEmpty(result)){
            return Integer.parseInt(result);
        }
        Log.d("asd:","[ProductModel]-updateQuantityIdNullByWeightIdAndPartyId-[ERROR]: -- Updata fail");
        return null;
    }

    @Override
    public Integer updateQuantityIdAndWeightIdNullByProductId(String json) {
        Log.d("asd:","[ProductModel]-updateQuantityIdAndWeightIdNullByProductId-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_updateQuantityIdAndWeightIdNullByProductId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-updateQuantityIdAndWeightIdNullByProductId-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        if(ObjectUtil.isNotNullEmpty(result)){
            return Integer.parseInt(result);
        }
        Log.d("asd:","[ProductModel]-updateQuantityIdAndWeightIdNullByProductId-[ERROR]: -- Updata fail");
        return null;
    }

    @Override
    public ResponseMessage deleteByProductId(String json) {
        Log.d("asd:","[ProductModel]-deleteByProductId-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_deleteByProductId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-deleteByProductId-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
        if(ObjectUtil.isNotNullEmpty(result)){
            ResponseMessage responseMessage = new ResponseMessage();
            try {
                Gson gson = GsonUtil.fromJson();
                responseMessage = gson.fromJson(json,responseMessage.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseMessage;
        }
        return null;
    }

    @Override
    public ResponseMessage delete(String product_json) {
        Log.d("asd:","[ProductModel]-delete-[Request (JSON)]: :"+product_json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_delete(),product_json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-delete-[Response(String)]: :"+result);
        if(!ObjectUtil.isCorrectResponse(result)){
            return null;
        }
        //
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
