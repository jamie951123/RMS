package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.InventorySumModel;

import java.util.List;

/**
 * Created by jamie on 2017/5/1.
 */

public interface InventorySumDao {

    List<InventorySumModel> findByPartyIdAndStatus(String json);

    List<InventorySumModel> findByPartyIdAndStatusOrderByProductId(String json);

}
