package com.example.james.rms.CommonProfile.DialogBox.Service;

import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;

/**
 * Created by jamie on 2017/5/14.
 */

public interface ClassicDialogService {
    void settingPagesWeight(WeightProfileModel weightProfileModel);
    void settingPagesQty ( QuantityProfileModel quantityProfileModel);
    
}
