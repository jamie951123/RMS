package com.example.james.rms.Operation.Service;


import com.example.james.rms.Core.Model.ReceivingOrderAndItemContainer;
import com.example.james.rms.Core.Dao.ReceivingDao;
import com.example.james.rms.Core.Dao.ReceivingDaoImpl;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by Jamie on 5/4/2017.
 */

public class ReceivingIncreaseServiceImpl implements ReceivingIncreaseService {
    ReceivingDao receivingDao = new ReceivingDaoImpl();

    @Override
    public List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json) {
//        return receivingDao.findReceivingOrderByPartyIdAndCreateDate(json);
        return null;
    }

    @Override
    public ReceivingOrderModel insertIntoReceivingOrder(String json) {
        return receivingDao.insertIntoReceivingOrder(json);
    }

    @Override
    public List<ReceivingItemModel> insertIntoReceivingItem(String json) {
        return receivingDao.insertIntoReceivingItem(json);
    }

    @Override
    public List<ReceivingOrderAndItemContainer> saveOrderAndItem(String json) {
        return receivingDao.saveOrderAndItem(json);
    }

}
