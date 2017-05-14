package com.example.james.rms.Core.Dao;

import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.Model.WeightProfileModel;

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
