package com.example.james.rms.Receiving.Service;



import com.example.james.rms.Core.Receiving.Model.V_ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public interface ReceivingService {

     List<V_ReceivingItemModel> findReceivingItemByPartyId(String json);

     List<ReceivingOrderModel> findReceivingOrderByPartyId(String json);

}
