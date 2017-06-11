package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ResponseMessage;

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
