package com.example.james.rms.ITF;

import android.content.Context;

import com.example.james.rms.Activity;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;

/**
 * Created by jamie on 2017/4/24.
 */

public interface NumberDialogListener {
        void from(NumberDialogModel numberDialogModel,Object o);
        void returnData(NumberDialogModel numberDialogModel);
}
