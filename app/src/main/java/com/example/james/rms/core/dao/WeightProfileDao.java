package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.core.model.WeightProfileModel;

import java.util.List;

/**
 * Created by jamie on 2017/4/23.
 */

public interface WeightProfileDao {
    List<WeightProfileModel> findAll();
    List<WeightProfileModel> findByPartyId(String partyId);
    ResponseMessage delete(String weightProfile);
    WeightProfileModel save (String json);
}
