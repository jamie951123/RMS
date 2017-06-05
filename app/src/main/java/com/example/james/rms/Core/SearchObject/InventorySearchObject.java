package com.example.james.rms.Core.SearchObject;

import com.example.james.rms.Core.Model.Status;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventorySearchObject extends HomeSearchObject{

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "InventorySearchObject [productId=" + productId + "]";
    }

}
