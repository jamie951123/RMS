package com.example.james.rms.CommonProfile.DialogBox;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Operation.DeliveryAction.DeliveryIncrease;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jamie on 9/4/2017.
 */

public class MyDatePicker extends DialogFragment  implements DatePickerDialog.OnDateSetListener{

    private int yy;
    private int mm;
    private int dd;
//    private String tag = null;
    public interface MyDatePickerService{
         void passDateToReceivingIncrease(String date_str ,Date date);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        yy = calendar.get(Calendar.YEAR);
        mm = calendar.get(Calendar.MONTH);
        dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//        tag = null;
//        if(datePickerDialog.getDatePicker().getTag() != null) {
//            tag = String.valueOf(datePickerDialog.getDatePicker().getTag());
//        }
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String lastDateTime = setDateFormat(year,month+1,day);
        Date date = ObjectUtil.stringToDate_onlyDate(lastDateTime);
        MyDatePickerService myDatePickerService;
        String className = getActivity().getClass().getSimpleName();
        switch (className){
            case StartActivityForResultKey.receivingIncrease:
                myDatePickerService = (ReceivingIncrease) getActivity();
                myDatePickerService.passDateToReceivingIncrease(lastDateTime, date);
                break;
            case StartActivityForResultKey.deliveryIncrease:
                myDatePickerService = (DeliveryIncrease) getActivity();
                myDatePickerService.passDateToReceivingIncrease(lastDateTime, date);
                break;

        }
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

//    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            view.setMinDate(System.currentTimeMillis());
//            view.setMinDate(System.currentTimeMillis() - 1000);
//            String lastdatetime = setDateFormat(year,monthOfYear+1,dayOfMonth);
//            Date date = ObjectUtil.stringToDate_onlyDate(lastdatetime);
//            MyDatePickerService myDatePickerService = (ReceivingIncrease)getActivity();
//            myDatePickerService.passDateToReceivingIncrease(lastdatetime,date);
//        }
//    };

}
