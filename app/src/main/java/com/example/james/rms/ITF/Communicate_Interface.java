package com.example.james.rms.ITF;

import com.example.james.rms.Core.Model.ExpandableSelectedModel;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by james on 1/4/2017.
 */

public interface Communicate_Interface<T>{

     //UI
     void putOriginalProductModels(List<T> item_original, List<T> item_latest, ExpandableSelectedModel expandableSelectModel);

     void putLatestProductModel(List<T> item_listview, ExpandableSelectedModel expandableSelectModel);
}
