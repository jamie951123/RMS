package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.QuantityProfileModel;

import java.util.List;

/**
 * Created by jamie on 2017/4/23.
 */

public interface QuantityProfileDao {
    List<QuantityProfileModel> findAll();
    List<QuantityProfileModel> findByPartyId(String partyId);
}
