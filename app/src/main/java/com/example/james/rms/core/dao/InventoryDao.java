package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.InventoryModel;

import java.util.List;

/**
 * Created by jamie on 2017/5/1.
 */

public interface InventoryDao {

    List<InventoryModel> findByPartyIdAndStatus(String json);
    List<InventoryModel> findByPartyIdAndStatusOrderByProductIdAsc(String json);
}
