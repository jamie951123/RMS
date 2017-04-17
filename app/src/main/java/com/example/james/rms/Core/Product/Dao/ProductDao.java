package com.example.james.rms.Core.Product.Dao;

import com.example.james.rms.Core.Product.Model.ProductInsertModel;
import com.example.james.rms.Core.Product.Model.ProductModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ProductDao {
    List<ProductModel> findAll();

    List<ProductModel> findByPartyId(String json);

    ProductModel insertProduct(String json);
}
