package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by james on 27/3/2017.
 */

public class ReceivingIncreaseListAdapter extends MyBaseAdapter<ReceivingIncreaseModel> {
    // 用來控制CheckBox的選中狀況
    public ReceivingIncreaseListAdapter(Context context, List<ReceivingIncreaseModel> lastestModel) {
        super(context,lastestModel);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReceivingIncreaseListAdapter.ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ReceivingIncreaseListAdapter.ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_increase_list_item,parent,false);
            viewHolder = new ReceivingIncreaseListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.receiving_increase_list_item_productCode.setText(getItem(position).getProductModel().getProductName());
        viewHolder.receiving_increase_list_item_productName.setText(getItem(position).getProductModel().getProductCode());
        viewHolder.qtyEditText.setText(ObjectUtil.intToString(getItem(position).getQty()));
        viewHolder.grossweightEditText.setText(ObjectUtil.bigDecimalToString(getItem(position).getGrossWeight()));
        viewHolder.qtyEditText.addTextChangedListener(textWatch(position,"qty"));
        viewHolder.grossweightEditText.addTextChangedListener(textWatch(position,"grossweight"));
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


    public static class ViewHolder{
        @BindView(R.id.receiving_increase_list_item_productCode)
        TextView receiving_increase_list_item_productCode;
        @BindView(R.id.receiving_increase_list_item_productName)
        TextView receiving_increase_list_item_productName;
        @BindView(R.id.receiving_increase_list_item_edittext_qty)
        EditText qtyEditText;
        @BindView(R.id.receiving_increase_list_item_edittext_grossweigth)
        EditText grossweightEditText;
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