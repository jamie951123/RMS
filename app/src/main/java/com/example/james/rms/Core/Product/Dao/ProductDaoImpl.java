package com.example.james.rms.Core.Product.Dao;

import android.util.Log;

import com.example.james.rms.Core.Product.Model.ProductInsertModel;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.Core.Product.ProductPHPPath;
import com.example.james.rms.NetWork.HttpPostAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by james on 26/3/2017.
 */

public class ProductDaoImpl implements ProductDao {

    ProductSplit productSplit = new ProductSplit();
    @Override
    public List<ProductModel> findAllFromUserProfile(String json) {
        Log.d("asd:","request_findAllFromUserProfile: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(ProductPHPPath.findProductPoolByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","response_findAllFromUserProfile: :"+result);
        List<ProductModel> productModels = productSplit.convertProductPool(result);
        return productModels;
    }

    public ProductInsertModel insertProduct(String json){
        Log.d("asd:","request_insertProduct: :"+json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(ProductPHPPath.insertProduct(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","response_insertProduct: :"+result);
        ProductInsertModel productInsertModel = productSplit.convertProductInsertResponse(result);

        return productInsertModel;
    }
}
