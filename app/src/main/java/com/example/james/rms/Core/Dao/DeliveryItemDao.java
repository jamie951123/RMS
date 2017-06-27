package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.DeliveryItemModel;

import java.util.List;

/**
 * Created by Jamie on 15/6/2017.
 */

public interface DeliveryItemDao {
//    Find
    List<DeliveryItemModel> findAll();
    List<DeliveryItemModel> findByDeliveryItemIdAndStatus();

//    Save
    DeliveryItemModel save (String deliveryItemModel);
    List<DeliveryItemModel> saves (String deliveryItemModels);

//    Delete
}
