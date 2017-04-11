package com.example.james.rms.CommonProfile.DialogBox;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;
import com.example.james.rms.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jamie on 9/4/2017.
 */

public class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public interface MyDatePickerService{
         void passDateToReceivingIncrease(String date_str ,Date date);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String lastdatetime = setDateFormat(year,month,day);
        Date date = ObjectUtil.stringToDate_onlyDate(lastdatetime);
        MyDatePickerService myDatePickerService = (ReceivingIncrease)getActivity();
        myDatePickerService.passDateToReceivingIncrease(lastdatetime,date);
    }


    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        String month = String.valueOf(monthOfYear);
        String day = String.valueOf(dayOfMonth);
        if(monthOfYear < 10){

            month = "0" + String.valueOf(monthOfYear);
        }
        if(dayOfMonth < 10){

            day  = "0" + String.valueOf(dayOfMonth);
        }
        String datetime = String.valueOf(year)+ "-" + month + "-" + day;
        return datetime;
    }
}
