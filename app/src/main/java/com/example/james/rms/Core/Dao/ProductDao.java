package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ProductModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ProductDao {
    List<ProductModel> findAll();

    List<ProductModel> findByPartyId(String json);

    ProductModel insertProduct(String json);

    Integer updateWeightIdNullByWeightIdAndPartyId(String json);
        }
