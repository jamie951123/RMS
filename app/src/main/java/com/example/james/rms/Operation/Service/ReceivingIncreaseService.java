package com.example.james.rms.Operation.Service;

import com.example.james.rms.Core.Model.ReceivingOrderAndItemContainer;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by Jamie on 6/4/2017.
 */

public interface ReceivingIncreaseService {
    //Query

    List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json);

    ReceivingOrderModel insertIntoReceivingOrder(String json);

    List<ReceivingItemModel> insertIntoReceivingItem(String json);

    List<ReceivingOrderAndItemContainer> saveOrderAndItem(String json);

}
