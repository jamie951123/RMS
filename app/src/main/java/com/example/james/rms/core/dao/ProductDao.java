package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.ProductModel;
import com.example.james.rms.core.model.ResponseMessage;

import java.io.File;
import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ProductDao {

    //Find
    List<ProductModel> findAll();

    ProductModel findByProductId(String json);

    List<ProductModel> findByPartyId(String json);

    //Save
    ProductModel save(String json, File file);

    //Update
    Integer updateWeightIdNullByWeightIdAndPartyId(String json);

    Integer updateQuantityIdNullByQuantityIdAndPartyId(String json);

    Integer updateQuantityIdAndWeightIdNullByProductId(String json);

    //Delete
    ResponseMessage deleteByProductId(String json);

    ResponseMessage delete(String json);

}
