package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.ReceivingItemModel;
import com.example.james.rms.core.model.ResponseMessage;

import java.util.List;

/**
 * Created by jamie on 2017/6/10.
 */

public interface ReceivingItemDao {
    //Find
    List<ReceivingItemModel> findReceivingItemByPartyId(String json);

    //Save
    List<ReceivingItemModel> saves(String json);

    //Delete
    ResponseMessage delete(String json);
}
