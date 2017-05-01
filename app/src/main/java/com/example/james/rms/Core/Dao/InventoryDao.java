package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.InventoryModel;

import java.util.List;

/**
 * Created by jamie on 2017/4/27.
 */

public interface InventoryDao {

    List<InventoryModel> findByPartyId(String json);

    InventoryModel save (String json);

    List<InventoryModel> saves(String json);

    List<InventoryModel> findByPartyIdAndStatus(String json);
}
