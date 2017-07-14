package com.example.james.rms.CommonProfile.Util;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Core.Combine.MovementRecordCombine;
import com.example.james.rms.Core.Model.MovementRecord;

/**
 * Created by jamie on 2017/4/23.
 */

public class ActivityUtil {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static String movementFactory_str(String orginalClass,String tragetClass,int exist_fragment){
        MovementRecord movementRecord = new MovementRecord();
        movementRecord.setOriginalClass_string(orginalClass);
        movementRecord.setTargetClass_string(tragetClass);
        movementRecord.setExist_fragment(exist_fragment);

        MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);
        String movementRecord_str = movementRecordCombine.modelToJson(movementRecord);
        return  movementRecord_str;
    }
}
