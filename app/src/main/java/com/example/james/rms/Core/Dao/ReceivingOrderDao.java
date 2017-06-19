package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingOrderDao {

    //Find
    List<ReceivingOrderModel> findByPartyId(String json);
    List<ReceivingOrderModel> findByOrderIdAndStatus(String json);
    //Save
    ReceivingOrderModel save(String json);

    ReceivingOrderModel saveOrderAndItem(String json);

    //Delete
    ResponseMessage delete(String json);
}
