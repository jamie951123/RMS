package com.example.james.rms.CommonProfile.DialogBox;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.ConnectQuantityDialogListener;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by jamie on 2017/4/22.
 */

public class NumberDialog extends DialogFragment implements View.OnClickListener,ConnectQuantityDialogListener,EditText.OnFocusChangeListener  {

    //    @BindView(R.id.quantity_dialog_unit)
//    android.support.v7.widget.AppCompatSpinner dialog_spinner;
    @BindView(R.id.quantity_dialog_unit)
    Button quantity_dialog_unit;
    @BindView(R.id.quantity_title)
    TextView title;
    @BindView(R.id.quantity_dialog_close)
    Button close;
    @BindView(R.id.quantity_dialog_choose)
    Button choice;
    @BindView(R.id.quantity_dialog_number)
    EditText numEdit;
    @BindView(R.id.quantity_dialog_max)
    TextView max;
    private String dialog_title;
    private NumberDialogModel numberDialogModel;

    public interface QDtoReceivingIncrease{
        void returnData(NumberDialogModel numberDialogModel);
    }
    @Override
    public void from(NumberDialogModel numberDialogModel) {
        this.numberDialogModel = numberDialogModel;
        Log.d("asd", "To-[NumberDialog ]-- (NumberDialogModel)--From[ReceivingIncreaseListAdapter]" + this.numberDialogModel.toString());
        if (numberDialogModel != null) {
            if (numberDialogModel.getKey().equalsIgnoreCase(KeyModel.qty)) {
                handlerQty();
            } else if (numberDialogModel.getKey().equalsIgnoreCase(KeyModel.gw)) {
                handlerGw();
            }
        }
    }

    private void handlerQty(){
        dialog_title = "Quantity";
    }

    private void handlerGw(){
        dialog_title = "Weight";
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quantity_dialog_layout, container);
        ButterKnife.bind(this, view);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        close.setOnClickListener(this);
        choice.setOnClickListener(this);
        numEdit.setOnFocusChangeListener(this);
        title.setText(dialog_title);
        if(numberDialogModel !=null ){
            switch (numberDialogModel.getKey()){
                case KeyModel.qty :
                    //Left
                    numEdit.setText(ObjectUtil.intToString(numberDialogModel.getQty()));
                    max.setText(ObjectUtil.intToString(numberDialogModel.getQtyMax()));
                    //Right
                    quantity_dialog_unit.setText(numberDialogModel.getQtyUnit());
                    quantity_dialog_unit.setCompoundDrawablesWithIntrinsicBounds( R.drawable.industrial_scales_color, 0, 0, 0);
                    numEdit.addTextChangedListener(textWatch(0, KeyModel.qty));
                    break;
                case KeyModel.gw :
                    //Left
                    numEdit.setText(ObjectUtil.bigDecimalToString(numberDialogModel.getGrossWeight()));
                    max.setText(ObjectUtil.bigDecimalToString(numberDialogModel.getGwMax()));
                    //Right
                    quantity_dialog_unit.setText(numberDialogModel.getGrossWeightUnit());
                    quantity_dialog_unit.setCompoundDrawablesWithIntrinsicBounds( R.drawable.box_color, 0, 0, 0);
                    numEdit.addTextChangedListener(textWatch(0, KeyModel.gw));
                    break;
            }
        }
        return view;
    }

    public TextWatcher textWatch(final int position, final String fieldId){
        TextWatcher watcher = new android.text.TextWatcher(){


            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s != null && s.length()>0 && ObjectUtil.isNumeric(s.toString())) {
                    switch (fieldId){
                        case KeyModel.qty:
                            Integer qty = Integer.parseInt(s.toString());
                             if(numberDialogModel.getQtyMax() != null && numberDialogModel.getQtyMax() < qty){
                                 numberDialogModel.setQty(numberDialogModel.getQtyMax());
                                 numEdit.setText(ObjectUtil.intToString(numberDialogModel.getQty()));
                            }else{
                                 numberDialogModel.setQty(qty);
                            }
                            break;
                        case KeyModel.gw:
                            BigDecimal bigDecimal = new BigDecimal(s.toString());
                            if(numberDialogModel.getGwMax() != null && numberDialogModel.getGwMax().compareTo(bigDecimal) ==-1 ){
                                numberDialogModel.setGrossWeight(numberDialogModel.getGrossWeight());
                                numEdit.setText(ObjectUtil.bigDecimalToString(numberDialogModel.getGrossWeight()));
                            }else{
                                numberDialogModel.setGrossWeight(bigDecimal);
                            }
                            break;
                    }
                    int pos = numEdit.getText().length();
                    numEdit.setSelection(pos);
                }

            }

        };
        return watcher;
    }
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quantity_dialog_unit:
                break;
            case R.id.quantity_dialog_close:
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
            case R.id.quantity_dialog_choose:
                Integer num = ObjectUtil.stringToInteger(numEdit.getText().toString());
                Log.d("asd","[NumberDialog]-{Click Choose}-num :" +num);
                if(num != null) {
                    switch (this.numberDialogModel.getKey()) {
                        case KeyModel.qty:
                            this.numberDialogModel.setQty(num);
                            break;
                        case KeyModel.gw:
                            this.numberDialogModel.setGrossWeight(new BigDecimal(num));
                            break;

                    }
                }
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                ReceivingIncrease receivingIncrease = (ReceivingIncrease)getActivity();
                QDtoReceivingIncrease qDtoReceivingIncrease = receivingIncrease;
                qDtoReceivingIncrease.returnData(this.numberDialogModel);
                break;
        }
    }

}
