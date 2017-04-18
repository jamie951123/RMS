package com.example.james.rms.Receiving.Service;

import com.example.james.rms.Core.Receiving.Dao.ReceivingDao;
import com.example.james.rms.Core.Receiving.Dao.ReceivingDaoImpl;
import com.example.james.rms.Core.Receiving.Model.ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingServiceImpl implements ReceivingService {
    ReceivingDao receivingDao = new ReceivingDaoImpl();
    @Override
    public List<ReceivingItemModel> findReceivingItemByPartyId(String json) {
        return receivingDao.findReceivingItemByPartyId(json);
    }

    @Override
    public List<ReceivingOrderModel> findReceivingOrderByPartyId(String json) {
        return receivingDao.findReceivingOrderByPartyId(json);
    }
}
