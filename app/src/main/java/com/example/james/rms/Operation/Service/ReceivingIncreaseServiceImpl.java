package com.example.james.rms.Operation.Service;


import com.example.james.rms.Core.Receiving.Dao.ReceivingDao;
import com.example.james.rms.Core.Receiving.Dao.ReceivingDaoImpl;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by Jamie on 5/4/2017.
 */

public class ReceivingIncreaseServiceImpl implements ReceivingIncreaseService {
    ReceivingDao receivingDao = new ReceivingDaoImpl();

    @Override
    public List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json) {
        return receivingDao.findReceivingOrderByPartyIdAndCreateDate(json);
    }

    @Override
    public String insertIntoReceivingOrder(String json) {
        return receivingDao.insertIntoReceivingOrder(json);
    }

    @Override
    public String insertIntoReceivingItem(String json) {
        return receivingDao.insertIntoReceivingItem(json);
    }
}
