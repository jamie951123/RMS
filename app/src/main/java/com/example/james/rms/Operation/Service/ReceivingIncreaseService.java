package com.example.james.rms.Operation.Service;

import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by Jamie on 6/4/2017.
 */

public interface ReceivingIncreaseService {
    //Query

    List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json);

    String insertIntoReceivingOrder(String json);

    String insertIntoReceivingItem(String json);

}
