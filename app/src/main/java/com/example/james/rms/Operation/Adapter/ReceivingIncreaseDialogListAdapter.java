package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.R;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 1/4/2017.
 */

public class ReceivingIncreaseDialogListAdapter extends MyBaseAdapter<ReceivingItemModel> {

    // 用來控制CheckBox的選中狀況
    private static LinkedHashMap<Long, Boolean> isSelected;

    public ReceivingIncreaseDialogListAdapter(Context context, List<ReceivingItemModel> dataArrayList, LinkedHashMap<Long, Boolean> isSelected) {
        super(context,dataArrayList);
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
        viewHolder.receiving_increase_dialog_item_productCode.setText(getItem(position).getProduct().getProductName());
        viewHolder.receiving_increase_dialog_item_productName.setText(getItem(position).getProduct().getProductCode());
        if(getIsSelected().containsKey(getItem(position).getProductId())){
            viewHolder.receiving_increase_dialog_item_checkbox.setChecked(getIsSelected().get(getItem(position).getProductId()));
        }return convertView;
    }

    @Override
    public boolean productCodeMatch(ReceivingItemModel receivingItemModel, String string) {
        boolean result = receivingItemModel.getProduct().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ReceivingItemModel receivingItemModel, String value) {
        return ObjectUtil.isNullEmpty(receivingItemModel.getProduct().getProductName()) ? false : receivingItemModel.getProduct().getProductName().toUpperCase().contains(value.toUpperCase());
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingItemModel receivingOrderModel, String string) {
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

    public static LinkedHashMap<Long, Boolean> getIsSelected() {
        return isSelected;
    }

}