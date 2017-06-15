package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.DeliveryOrderModel;

import java.util.List;

/**
 * Created by Jamie on 15/6/2017.
 */

public interface DeliveryOrderDao {
    List<DeliveryOrderModel> findAll();
    List<DeliveryOrderModel> findByOrderIdAndStatus(String deliverySearchObject);
}
