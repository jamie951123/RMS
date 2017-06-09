package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingOrderDao {

    List<ReceivingOrderModel> findReceivingOrderByPartyId(String json);

    ReceivingOrderModel insertIntoReceivingOrder(String json);

    ReceivingOrderModel saveOrderAndItem(String json);
}
