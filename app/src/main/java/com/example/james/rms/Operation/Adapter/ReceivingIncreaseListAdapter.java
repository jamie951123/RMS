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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.DialogBox.NumberDialog;
import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.NumberDialogListener;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by james on 27/3/2017.
 */

public class ReceivingIncreaseListAdapter extends MyBaseAdapter<ReceivingItemModel> implements View.OnClickListener{

    // 用來控制CheckBox的選中狀況
    public ReceivingIncreaseListAdapter(Context context, List<ReceivingItemModel> item_listview) {
        super(context,item_listview);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (ReceivingIncreaseListAdapter.ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_increase_list_item,parent,false);
            viewHolder = new ReceivingIncreaseListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        if(getItem(position) == null){
            Log.d("asd","[ReceivingIncreaseListAdapter]-[ReceivingItemModel]-[Error] : ReceivingItemModel is null");
            return convertView;
        }
        String qtyUnit = getItem(position).getProduct().getQuantityProfile() == null?"":getItem(position).getProduct().getQuantityProfile().getQuantityUnit();
        String gwUnit  = getItem(position).getProduct().getWeightprofile() == null?"": getItem(position).getProduct().getWeightprofile().getWeightUnit();
        viewHolder.receiving_increase_list_item_productCode.setText(getItem(position).getProduct().getProductCode());
        viewHolder.receiving_increase_list_item_productName.setText(getItem(position).getProduct().getProductName());
        viewHolder.qty.setText(ObjectUtil.intToString(getItem(position).getItemQty()));
        viewHolder.qtyunit.setText(qtyUnit);
        viewHolder.grossweight.setText(ObjectUtil.bigDecimalToString(getItem(position).getItemGrossWeight()));
        viewHolder.gwunit.setText(gwUnit);
        viewHolder.itemremark.setText(getItem(position).getItemRemark());

        viewHolder.itemremark.addTextChangedListener(textWatch(position, KeyModel.remark));
        viewHolder.qty.addTextChangedListener(textWatch(position, KeyModel.qty));
        viewHolder.grossweight.addTextChangedListener(textWatch(position, KeyModel.gw));

        viewHolder.qtylinear.setTag(position);
        viewHolder.qtylinear.setOnClickListener(this);
        viewHolder.gwlinear.setTag(position);
        viewHolder.gwlinear.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean productCodeMatch(ReceivingItemModel receivingItemModel, String string) {
        boolean result = receivingItemModel.getProduct().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ReceivingItemModel receivingItemModel, String value) {
        boolean result = receivingItemModel.getProduct().getProductName().toUpperCase().contains(value.toUpperCase());
        return result;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingItemModel receivingItemModel, String string) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.receiving_increase_qtylinear:
                createDialogBox(v, KeyModel.qty);
                break;
            case R.id.receiving_increase_gwlinear:
                createDialogBox(v, KeyModel.gw);
        }
    }

    public void createDialogBox(View v,String key){
        NumberDialog numberDialog = new NumberDialog();
        FragmentManager fm =  ((AppCompatActivity) this.getContext()).getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(key);
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        NumberDialogListener listener = numberDialog;
        NumberDialogModel numberDialogModel = new NumberDialogModel();
        Integer position = (Integer)v.getTag();
        Log.d("asd","ReceivingIcreaseListAdapter---getItemPosition : " + position);
        numberDialogModel.setPosition(position);
        numberDialogModel.setKey(key);
        if(key.equalsIgnoreCase(KeyModel.qty)){
            Integer qty = getItem(position).getItemQty();
            String qtyUnit = getItem(position).getProduct().getQuantityProfile() == null?"":getItem(position).getProduct().getQuantityProfile().getQuantityUnit();
            numberDialogModel.setQty(qty);
            numberDialogModel.setQtyUnit(qtyUnit);
            numberDialogModel.setQtyMax(null);
        }else if(key.equalsIgnoreCase(KeyModel.gw)){
            BigDecimal gw = getItem(position).getItemGrossWeight();
            String gwUnit  = getItem(position).getProduct().getWeightprofile() == null?"": getItem(position).getProduct().getWeightprofile().getWeightUnit();
            numberDialogModel.setGrossWeight(gw);
            numberDialogModel.setGrossWeightUnit(gwUnit);
            numberDialogModel.setGwMax(null);
        }
        listener.from(numberDialogModel,getContext());
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
        @BindView(R.id.receiving_increase_itemremark)
        EditText itemremark;

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
                    switch (fieldId){
                        case KeyModel.qty:
                            getItem(position).setItemQty(Integer.parseInt(s.toString()));
                            Log.v("asd", "[qty][onTextChanged]" + getItem(position).getItemQty());
                            break;
                        case KeyModel.gw:
                            getItem(position).setItemGrossWeight(new BigDecimal(s.toString()));
                            Log.v("asd", "[grossweight][onTextChanged]" + getItem(position).getItemGrossWeight());
                            break;
                        case KeyModel.remark:
                            getItem(position).setItemRemark(s.toString());
                            break;
                    }
                }

            }

        };
        return watcher;
    }

}