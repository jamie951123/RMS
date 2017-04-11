package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;
import com.example.james.rms.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 1/4/2017.
 */

public class ReceivingIncreaseDialogListAdapter extends MyBaseAdapter<ReceivingIncreaseModel> {

    // 用來控制CheckBox的選中狀況
    private static HashMap<Integer, Boolean> isSelected;
    private static List<ReceivingIncreaseModel> dataArrayList;

    public ReceivingIncreaseDialogListAdapter(Context context, List<ReceivingIncreaseModel> dataArrayList, HashMap<Integer, Boolean> isSelected) {
        super(context,dataArrayList);
        this.dataArrayList = dataArrayList;
        this.isSelected = isSelected;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReceivingIncreaseDialogListAdapter.ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ReceivingIncreaseDialogListAdapter.ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_increase_dialog_list_item,parent,false);
            viewHolder = new ReceivingIncreaseDialogListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.receiving_increase_dialog_item_productCode.setText(getItem(position).getProductModel().getProductName());
        viewHolder.receiving_increase_dialog_item_productName.setText(getItem(position).getProductModel().getProductCode());
        viewHolder.receiving_increase_dialog_item_checkbox.setChecked(getIsSelected().get(position));
        return convertView;
    }

    @Override
    public boolean productCodeMatch(ReceivingIncreaseModel productModel, String string) {
        boolean result = productModel.getProductModel().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ReceivingIncreaseModel productModel, String value) {
        boolean result = productModel.getProductModel().getProductName().toUpperCase().contains(value.toUpperCase());
        return result;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingIncreaseModel productModel, String string) {
        return false;
    }


    public static class ViewHolder{
        @BindView(R.id.receiving_increase_dialog_item_productCode)
        public TextView receiving_increase_dialog_item_productCode;
        @BindView(R.id.receiving_increase_dialog_item_productName)
        public TextView receiving_increase_dialog_item_productName;
        @BindView(R.id.receiving_increase_dialog_item_checkbox)
        public android.support.v7.widget.AppCompatCheckBox receiving_increase_dialog_item_checkbox;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ReceivingIncreaseDialogListAdapter.isSelected = isSelected;
    }

}