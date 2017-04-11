package com.example.james.rms.ProductPool.Service;

import com.example.james.rms.Core.Product.Dao.ProductDao;
import com.example.james.rms.Core.Product.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Product.Model.ProductInsertModel;
import com.example.james.rms.Core.Product.Model.ProductModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<ProductModel> findAllFromUserProfile(String json) {
        return productDao.findAllFromUserProfile(json);
    }

    @Override
    public ProductInsertModel insertProduct(String json) {
        return productDao.insertProduct(json);
    }
}
