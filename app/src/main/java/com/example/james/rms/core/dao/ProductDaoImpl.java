package com.example.james.rms.core.dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.NetworkModel;
import com.example.james.rms.core.model.ProductModel;
import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.core.serve_path.ProductServerPath;
import com.example.james.rms.network.HttpGetAsync;
import com.example.james.rms.network.HttpPostAsync;
import com.example.james.rms.network.ProductImagePostAsync;
import com.example.james.rms.network.RetrofitUtil;
import com.example.james.rms.retrofit.ProductApi;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by james on 26/3/2017.
 */

public class ProductDaoImpl extends NetworkModel implements ProductDao {

    private ProductApi productApi;

    public ProductDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        productApi = RetrofitUtil.getDefaultRetrofit().create(ProductApi.class);
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

    public ProductModel save(String json, File file){
        Log.d("asd:","[ProductModel]-insertProduct-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync(this).execute(ProductServerPath.INSTANCE.serve_insertProduct(),json).get();

            String imageResult = new ProductImagePostAsync(file).execute().get();
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
            Gson gson = GsonUtil.fromJson();
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
