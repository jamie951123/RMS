package com.example.james.rms.common.DialogBox.service;

import com.example.james.rms.core.model.QuantityProfileModel;
import com.example.james.rms.core.model.WeightProfileModel;

/**
 * Created by jamie on 2017/5/14.
 */

public interface ClassicDialogService {
    void settingPagesWeight(WeightProfileModel weightProfileModel);
    void settingPagesQty ( QuantityProfileModel quantityProfileModel);
    
}
