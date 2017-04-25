package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.DialogBox.NumberDialog;
import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.ConnectQuantityDialogListener;
import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by james on 27/3/2017.
 */

public class ReceivingIncreaseListAdapter extends MyBaseAdapter<ReceivingIncreaseModel> implements View.OnClickListener{
    List<WeightProfileModel> weightProfileModelList;
    List<QuantityProfileModel> quantityProfileModels;
    ReceivingIncreaseListAdapter.ViewHolder viewHolder = null;
    // 用來控制CheckBox的選中狀況
    public ReceivingIncreaseListAdapter(Context context, List<ReceivingIncreaseModel> lastestModel,List<WeightProfileModel> weightProfileModelList,List<QuantityProfileModel> quantityProfileModels) {
        super(context,lastestModel);
        this.weightProfileModelList = weightProfileModelList;
        this.quantityProfileModels = quantityProfileModels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = null;
        if(convertView != null){
            viewHolder = (ReceivingIncreaseListAdapter.ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_increase_list_item,parent,false);
            viewHolder = new ReceivingIncreaseListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        String qtyUnit = getItem(position).getQtyUnit() == null ? "": getItem(position).getQtyUnit();
        String gwUnit  = getItem(position).getGrossWeightUnit() == null ? "":getItem(position).getGrossWeightUnit();
        viewHolder.receiving_increase_list_item_productCode.setText(getItem(position).getProductModel().getProductName());
        viewHolder.receiving_increase_list_item_productName.setText(getItem(position).getProductModel().getProductCode());
        viewHolder.qty.setText(ObjectUtil.intToString(getItem(position).getQty()));
        viewHolder.qtyunit.setText(qtyUnit);
        viewHolder.grossweight.setText(ObjectUtil.bigDecimalToString(getItem(position).getGrossWeight()));
        viewHolder.gwunit.setText(gwUnit);

        viewHolder.qty.addTextChangedListener(textWatch(position,"qty"));
        viewHolder.grossweight.addTextChangedListener(textWatch(position,"grossweight"));

        viewHolder.qtylinear.setTag(position);
        viewHolder.qtylinear.setOnClickListener(this);
        viewHolder.gwlinear.setTag(position);
        viewHolder.gwlinear.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean productCodeMatch(ReceivingIncreaseModel receivingIncreaseModel, String string) {
        boolean result = receivingIncreaseModel.getProductModel().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ReceivingIncreaseModel receivingIncreaseModel, String value) {
        boolean result = receivingIncreaseModel.getProductModel().getProductName().toUpperCase().contains(value.toUpperCase());
        return result;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingIncreaseModel receivingIncreaseModel, String string) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.receiving_increase_qtylinear:
                createDialogBox(v,"QTY");
                break;
            case R.id.receiving_increase_gwlinear:
                createDialogBox(v,"GW");
        }
    }

    public void createDialogBox(View v,String key){
        NumberDialog numberDialog = new NumberDialog();
        FragmentManager fm =  ((AppCompatActivity) this.getContext()).getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(key);
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        ConnectQuantityDialogListener listener = numberDialog;
        NumberDialogModel numberDialogModel = new NumberDialogModel();
        numberDialogModel.setKey(key);
        Integer position = (Integer)v.getTag();
        Log.d("asd","ReceivingIcreaseListAdapter---getItemPosition : " + position);
        numberDialogModel.setPosition(position);
        if(key.equalsIgnoreCase("QTY")){
            Integer qty = getItem(position).getQty();
            String qtyUnit = getItem(position).getQtyUnit();
            numberDialogModel.setQty(qty);
            numberDialogModel.setQtyUnit(qtyUnit);
            numberDialogModel.setQuantityProfileModels(this.quantityProfileModels);
        }else if(key.equalsIgnoreCase("GW")){
            BigDecimal gw = getItem(position).getGrossWeight();
            String gwUnit = getItem(position).getGrossWeightUnit();
            numberDialogModel.setGrossWeight(gw);
            numberDialogModel.setGrossWeightUnit(gwUnit);
            numberDialogModel.setWeightProfileModelList(this.weightProfileModelList);
        }
        listener.fromReceivingIncreaseListAdapter(numberDialogModel);
        numberDialog.show(fm,key);
    }

    public static class ViewHolder{
        @BindView(R.id.receiving_increase_list_item_productCode)
        TextView receiving_increase_list_item_productCode;
        @BindView(R.id.receiving_increase_list_item_productName)
        TextView receiving_increase_list_item_productName;
        @BindView(R.id.receiving_increase_list_item_qty)
        TextView qty;
        @BindView(R.id.receiving_increase_list_item_grossweigth)
        TextView grossweight;
        @BindView(R.id.receiving_increase_list_item_qtyunit)
        TextView qtyunit;
        @BindView(R.id.receiving_increase_list_item_gwunit)
        TextView gwunit;
        @BindView(R.id.receiving_increase_qtylinear)
        LinearLayout qtylinear;
        @BindView(R.id.receiving_increase_gwlinear)
        LinearLayout gwlinear;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public TextWatcher textWatch(final int position,final String fieldId){
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
                if(s != null && s.length()>0) {
                    if("qty".equals(fieldId)){
                        getItem(position).setQty(Integer.parseInt(s.toString()));
                        Log.v("asd", "[qty][onTextChanged]" + getItem(position).getQty());
                    }
                    if("grossweight".equals(fieldId)){
                        getItem(position).setGrossWeight(new BigDecimal(s.toString()));
                        Log.v("asd", "[grossweight][onTextChanged]" + getItem(position).getGrossWeight());
                    }

                }

            }

        };
        return watcher;
    }

}