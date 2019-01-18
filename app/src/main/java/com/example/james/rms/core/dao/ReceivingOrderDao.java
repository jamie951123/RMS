package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.ReceivingOrderModel;
import com.example.james.rms.core.model.ResponseMessage;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingOrderDao {

    //Find
    List<ReceivingOrderModel> findByPartyId(String json);
    List<ReceivingOrderModel> findByPartyIdAndStatus(String json);

    List<ReceivingOrderModel> findByOrderIdAndStatus(String json);
    //Save
    ReceivingOrderModel save(String json);

    ReceivingOrderModel saveOrderAndItem(String json);

    //Delete
    ResponseMessage delete(String json);
}
