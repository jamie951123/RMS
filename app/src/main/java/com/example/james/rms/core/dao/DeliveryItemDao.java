package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.DeliveryItemModel;
import com.example.james.rms.core.model.ResponseMessage;

import java.util.List;

/**
 * Created by Jamie on 15/6/2017.
 */

public interface DeliveryItemDao {
//    Find
    List<DeliveryItemModel> findAll();
    List<DeliveryItemModel> findByPartyIdAndStatus(String deliveryItemSearch_json);
    List<DeliveryItemModel> findByDeliveryItemIdAndStatus();

//    Save
    DeliveryItemModel save (String deliveryItemModel);
    List<DeliveryItemModel> saves (String deliveryItemModels);

//    Delete
    ResponseMessage delete (String deliveryItemModel);
}
