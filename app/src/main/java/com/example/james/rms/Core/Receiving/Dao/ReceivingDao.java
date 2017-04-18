package com.example.james.rms.Core.Receiving.Dao;

import com.example.james.rms.Core.Receiving.Model.ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingDao {

    List<ReceivingOrderModel> findReceivingOrderByPartyId(String json);

//    List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json);

    List<ReceivingItemModel> findReceivingItemByPartyId(String json);

    String insertIntoReceivingOrder(String json);

    String insertIntoReceivingItem(String json);
}
