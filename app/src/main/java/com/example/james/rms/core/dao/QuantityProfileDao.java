package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.QuantityProfileModel;
import com.example.james.rms.core.model.ResponseMessage;

import java.util.List;

/**
 * Created by jamie on 2017/4/23.
 */

public interface QuantityProfileDao {
    List<QuantityProfileModel> findAll();
    List<QuantityProfileModel> findByPartyId(String partyId);
    ResponseMessage delete(String quantityProfile);
    QuantityProfileModel save(String json);
}
