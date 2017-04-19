package com.example.james.rms.ProductPool.Service;

import com.example.james.rms.Core.Model.ProductModel;

import java.util.List;

/**
 * Created by james on 5/2/2017.
 */

public interface ProductService {

     List<ProductModel> findAll();

     List<ProductModel> findByPartyId(String json);

     ProductModel insertProduct(String json);
}
