package com.example.james.rms.ITF;

import com.example.james.rms.core.transfer_model.NumberDialogModel;

/**
 * Created by jamie on 2017/4/24.
 */

public interface NumberDialogListener {
        void from(NumberDialogModel numberDialogModel,Object o);
        void returnData(NumberDialogModel numberDialogModel);
}
