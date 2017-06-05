package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.ResponseMessage;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ProductDao {
    List<ProductModel> findAll();

    ProductModel findByProductId(String json);

    List<ProductModel> findByPartyId(String json);

    ProductModel insertProduct(String json);

    Integer updateWeightIdNullByWeightIdAndPartyId(String json);

    Integer updateQuantityIdNullByQuantityIdAndPartyId(String json);

    Integer updateQuantityIdAndWeightIdNullByProductId(String json);

    ResponseMessage deleteByProductId(String json);

}
