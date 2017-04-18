package com.example.james.rms.Receiving.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Receiving.Model.ReceivingItemModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 26/2/2017.
 */

public class ReceivingItemListAdapter extends MyBaseAdapter<ReceivingItemModel> {

    public ReceivingItemListAdapter(Context context, List<ReceivingItemModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ViewHolder)convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_item_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.receivingItem_ProductCode.setText(getItem(position).getProductModel().getProductCode());
        viewHolder.receivingItem_ProductName.setText(getItem(position).getProductModel().getProductName());
        viewHolder.receivingItem_itemStatus.setText(getItem(position).getItemStatus());
        viewHolder.receivingItem_itemCreateDate.setText(ObjectUtil.dateToString(getItem(position).getItemCreateDate()));
        viewHolder.receivingItem_itemReceivingDate.setText(ObjectUtil.dateToString(getItem(position).getItemReceivingDate()));
        viewHolder.receivingItem_itemRemark.setText(getItem(position).getItemRemark());
        viewHolder.receivingItem_itemGrossWeight.setText(ObjectUtil.bigDecimalToString(getItem(position).getItemGrossWeight()));
        viewHolder.receivingItem_itemGrossWeightUnit.setText(getItem(position).getItemGrossWeightUnit());
        return convertView;
    }

    @Override
    public boolean productCodeMatch(ReceivingItemModel VReceivingItemModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(ReceivingItemModel VReceivingItemModel, String string) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingItemModel VReceivingItemModel, String string) {
        boolean result = VReceivingItemModel.getItemRemark().toUpperCase().contains(string.toUpperCase());
        return result;
    }


    static class ViewHolder{
        @BindView(R.id.receivingItem_ProductCode)
        TextView receivingItem_ProductCode;
        @BindView(R.id.receivingItem_ProductName)
        TextView receivingItem_ProductName;
        @BindView(R.id.receivingItem_itemStatus)
        TextView receivingItem_itemStatus;
        @BindView(R.id.receivingItem_itemCreateDate)
        TextView receivingItem_itemCreateDate;
        @BindView(R.id.receivingItem_itemReceivingDate)
        TextView receivingItem_itemReceivingDate;
        @BindView(R.id.receivingItem_itemRemark)
        TextView receivingItem_itemRemark;
        @BindView(R.id.receivingItem_itemGrossWeight)
        TextView receivingItem_itemGrossWeight;
        @BindView(R.id.receivingItem_itemGrossWeightUnit)
        TextView receivingItem_itemGrossWeightUnit;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
