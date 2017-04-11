package com.example.james.rms.ProductPool.Service;

import android.util.Log;

import com.example.james.rms.Core.Product.Dao.ProductDao;
import com.example.james.rms.Core.Product.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Product.Model.ProductInsertModel;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.NetWork.HttpPostAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by james on 5/2/2017.
 */

public interface ProductService {

    public List<ProductModel> findAllFromUserProfile(String json);

    public ProductInsertModel insertProduct(String json);
}
