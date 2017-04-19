package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ReceivingOrderAndItemContainer;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingDao {

    List<ReceivingOrderModel> findReceivingOrderByPartyId(String json);

//    List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json);

    List<ReceivingItemModel> findReceivingItemByPartyId(String json);

    ReceivingOrderModel insertIntoReceivingOrder(String json);

    List<ReceivingItemModel> insertIntoReceivingItem(String json);

    List<ReceivingOrderAndItemContainer> saveOrderAndItem(String json);
}
