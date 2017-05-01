package com.example.james.rms.Core.Dao;

import android.util.Log;

import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.Core.Model.ProductModel;
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

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<ProductModel> findAll() {
        Log.d("asd:","[ProductModel]-findAll(Request--JSON) :");
        String result = "";
        List<ProductModel> products = new ArrayList<>();
        try {
            result = new HttpGetAsync().execute(ProductServerPath.serve_findAll()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-findAll(Response--String): :"+result);
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
    public List<ProductModel> findByPartyId(String json) {
        Log.d("asd:","[ProductModel]-findByPartyId(Request--JSON)  :" +json);
        String result = "";
        List<ProductModel> products = new ArrayList<>();
        try {
            result = new HttpPostAsync().execute(ProductServerPath.serve_findByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","[ProductModel]-findByPartyId(Response-String): "+result);
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

    public ProductModel insertProduct(String json){
        Log.d("asd:","[ProductModel]-insertProduct-[Request (JSON)]: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(ProductServerPath.insertProduct(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ProductModel]-insertProduct-[Response(String)]: :"+result);

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
}
