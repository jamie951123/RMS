package com.example.james.rms.CommonProfile.DialogBox;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

/**
 * Created by jamie on 2017/4/22.
 */

public class NumberDialog extends DialogFragment implements View.OnClickListener,ConnectQuantityDialogListener {

    @BindView(R.id.quantity_dialog_unit)
    android.support.v7.widget.AppCompatSpinner dialog_spinner;
    @BindView(R.id.quantity_title)
    TextView title;
    @BindView(R.id.quantity_dialog_close)
    Button close;
    @BindView(R.id.quantity_dialog_choose)
    Button choice;
    @BindView(R.id.quantity_dialog_number)
    EditText numEdit;

    private String dialog_title;
    private List<String> units = new ArrayList<>();
    private NumberDialogModel numberDialogModel;

    public interface QDtoReceivingIncrease{
        void returnData(NumberDialogModel numberDialogModel);
    }
    @Override
    public void fromReceivingIncreaseListAdapter(NumberDialogModel numberDialogModel) {
        this.numberDialogModel = numberDialogModel;
        units = new ArrayList<>();
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
        units = getQTYUnit(numberDialogModel.getQuantityProfileModels(), numberDialogModel.getQtyUnit());
//        if (numberDialogModel.getQty() != null)
//            numEdit.setText(numberDialogModel.getQty());
    }

    private void handlerGw(){
        dialog_title = "Weight";
        units = getGWUnit(numberDialogModel.getWeightProfileModelList(), numberDialogModel.getGrossWeightUnit());
//        if (numberDialogModel.getGrossWeight() != null)
//            numEdit.setText(numberDialogModel.getGrossWeight().toString());

    }
    private List<String> getGWUnit(List<WeightProfileModel> weightProfileModelList, String gwUnit) {
        List<String> unit = new ArrayList<>();
        if(ObjectUtil.isNotNullEmpty(gwUnit)){
            unit.add(gwUnit);
        }
        for(WeightProfileModel item: weightProfileModelList){
            if(item.getWeightUnit().equalsIgnoreCase(gwUnit)){
                continue;
            }
            unit.add(item.getWeightUnit());
        }
        return unit;
    }


    private List<String> getQTYUnit(List<QuantityProfileModel> quantityProfileModels,String qtyUnit) {
        List<String> units = new ArrayList<>();
        if(ObjectUtil.isNotNullEmpty(qtyUnit)){
            units.add(qtyUnit);
        }
        for(QuantityProfileModel item: quantityProfileModels){
            if(item.getQuantityUnit().equalsIgnoreCase(qtyUnit)){
                continue;
            }
            units.add(item.getQuantityUnit());
        }
        return units;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quantity_dialog_layout, container);
        ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        close.setOnClickListener(this);
        choice.setOnClickListener(this);
        title.setText(dialog_title);
        if(numberDialogModel !=null ){
            switch (numberDialogModel.getKey()){
                case KeyModel.qty :
                    if (numberDialogModel.getQty() != null)
                        numEdit.setText(numberDialogModel.getQty().toString());
                    break;
                case KeyModel.gw :
                    if (numberDialogModel.getGrossWeight() != null)
                        numEdit.setText(numberDialogModel.getGrossWeight().toString());
                    break;
            }
        }

        ArrayAdapter dropDownAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,units){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 0) { // we're on an even row
                    view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.grayFOF5F5));
                } else {
                    view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.grayA3C2C2));
                }
                return view;
            }
        };
        dropDownAdapter.setDropDownViewResource(R.layout.spinner_item);
        dialog_spinner.setAdapter(dropDownAdapter);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quantity_dialog_unit:
                ClassicDialog classicDialog = new ClassicDialog(getActivity());
                classicDialog.showSingleChoice(getString(R.string.label_input),units);
                break;
            case R.id.quantity_dialog_close:
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
            case R.id.quantity_dialog_choose:
                Integer num = ObjectUtil.stringToInteger(numEdit.getText().toString());
                String unti = dialog_spinner.getSelectedItem() == null ? null :dialog_spinner.getSelectedItem().toString();
                Log.d("asd","[NumberDialog]-{Click Choose}-num :" +num);
                Log.d("asd","[NumberDialog]-{Click Choose}-unti :" +unti);
                if(num != null) {
                    switch (this.numberDialogModel.getKey()) {
                        case KeyModel.qty:
                            this.numberDialogModel.setQty(num);
                            this.numberDialogModel.setQtyUnit(unti);
                            break;
                        case KeyModel.gw:
                            this.numberDialogModel.setGrossWeight(new BigDecimal(num));
                            this.numberDialogModel.setGrossWeightUnit(unti);
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
