package com.example.james.rms.Operation.ReceivingAction;

import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import java.util.HashMap;
import java.util.List;

/**
 * Created by james on 1/4/2017.
 */

public interface Communicate_Interface {

     //UI
     void putOriginalProductModels(List<ReceivingItemModel> item_original, List<ReceivingItemModel> item_latest, HashMap<Integer, Boolean> isSelected);

     void putLatestProductModel(List<ReceivingItemModel> item_latest, HashMap<Integer, Boolean> isSelected);
}
