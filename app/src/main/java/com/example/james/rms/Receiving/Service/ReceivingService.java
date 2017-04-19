package com.example.james.rms.Receiving.Service;


import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingService {

     List<ReceivingOrderModel> findReceivingOrderByPartyId(String json);

     List<ReceivingItemModel> findReceivingItemByPartyId(String json);
}
