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
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.TranslateModel.QuantityDialogModel;
import com.example.james.rms.ITF.ConnectQuantityDialogListener;
import com.example.james.rms.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/4/22.
 */

public class QuantityDialog extends DialogFragment implements View.OnClickListener,ConnectQuantityDialogListener {

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

    private List<String> units = new ArrayList<>();
    private QuantityDialogModel quantityDialogModel;
    @Override
    public void fromReceivingIncreaseListAdapter(QuantityDialogModel quantityDialogModel) {
        this.quantityDialogModel = quantityDialogModel;
        units = new ArrayList<>();
        Log.d("asd", "QuantityDialog : " + this.quantityDialogModel.toString());
        if (quantityDialogModel != null) {
            if (quantityDialogModel.getKey().equalsIgnoreCase("QTY")) {
                units = getQTYUnit(quantityDialogModel.getQuantityProfileModels(),quantityDialogModel.getQtyUnit());
                if (quantityDialogModel.getQty() != null)
                    numEdit.setText(quantityDialogModel.getQty().toString());
            } else if (quantityDialogModel.getKey().equalsIgnoreCase("GW")) {
                units = getGWUnit(quantityDialogModel.getWeightProfileModelList(),quantityDialogModel.getGrossWeightUnit());
                if (quantityDialogModel.getGrossWeight() != null)
                    numEdit.setText(quantityDialogModel.getGrossWeight().toString());

            }
        }
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
        List<String> unit = new ArrayList<>();
        if(ObjectUtil.isNotNullEmpty(qtyUnit)){
            unit.add(qtyUnit);
        }
        for(QuantityProfileModel item: quantityProfileModels){
            if(item.getQuantityUnit().equalsIgnoreCase(qtyUnit)){
                continue;
            }
            unit.add(item.getQuantityUnit());
        }
        return unit;
    }

    public interface ConnectDialogToAdapter{
        void fromQuantityDialog(HashMap<String,Object> map);
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

    private List<String> getUnit() {
        List<String> units = new ArrayList<>();
        units.add("A");
        units.add("B");
        return units;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quantity_dialog_unit:
                List<String> units = getUnit();
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
                String unti = dialog_spinner.getSelectedItem().toString();
                Log.d("asd","num :" +num);
                Log.d("asd","unti :" +unti);
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
        }
    }

}
