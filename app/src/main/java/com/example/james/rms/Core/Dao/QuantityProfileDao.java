package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.ResponseMessage;

import java.util.List;

/**
 * Created by jamie on 2017/4/23.
 */

public interface QuantityProfileDao {
    List<QuantityProfileModel> findAll();
    List<QuantityProfileModel> findByPartyId(String partyId);
    ResponseMessage delete(String quantityProfile);
    QuantityProfileModel save(String json);
    QuantityProfileModel updateQtyUnit(String json);
}
