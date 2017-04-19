package com.example.james.rms.ProductPool.Service;

import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Model.ProductModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<ProductModel> findAll() {
        return null;
    }

    @Override
    public List<ProductModel> findByPartyId(String json) {
        return productDao.findByPartyId(json);
    }

    @Override
    public ProductModel insertProduct(String json) {
        return productDao.insertProduct(json);
    }
}
