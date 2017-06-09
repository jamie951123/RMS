package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ReceivingItemModel;

import java.util.List;

/**
 * Created by jamie on 2017/6/10.
 */

public interface ReceivingItemDao {
    List<ReceivingItemModel> findReceivingItemByPartyId(String json);
    List<ReceivingItemModel> saves(String json);
}
