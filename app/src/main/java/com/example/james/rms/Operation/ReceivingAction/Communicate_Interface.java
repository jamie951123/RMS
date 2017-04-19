package com.example.james.rms.Operation.ReceivingAction;

import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by james on 1/4/2017.
 */

public interface Communicate_Interface {

     //UI
     void putOriginalProductModels(List<ReceivingIncreaseModel> rlAllModel,List<ReceivingIncreaseModel> allModel,HashMap<Integer, Boolean> isSelected);

     void putLastestProductModel(List<ReceivingIncreaseModel> lastestModel, HashMap<Integer, Boolean> isSelected);
}
