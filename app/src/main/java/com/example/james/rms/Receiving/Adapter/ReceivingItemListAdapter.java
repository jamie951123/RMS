package com.example.james.rms.Receiving.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.Core.Receiving.Model.V_ReceivingItemModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 26/2/2017.
 */

public class ReceivingItemListAdapter extends MyBaseAdapter<V_ReceivingItemModel> {

    public ReceivingItemListAdapter(Context context, List<V_ReceivingItemModel> dataArrayList) {
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
        viewHolder.receivingItem_ProductCode.setText(getItem(position).getProductCode().toString());
        viewHolder.receivingItem_ProductName.setText(getItem(position).getProductName().toString());
        viewHolder.receivingItem_itemStatus.setText(getItem(position).getItemStatus().toString());
        viewHolder.receivingItem_itemCreateDate.setText(getItem(position).getItemCreateDate().toString());
        viewHolder.receivingItem_itemReceivingDate.setText(getItem(position).getItemReceivingDate().toString());
        viewHolder.receivingItem_itemRemark.setText(getItem(position).getItemRemark().toString());
        viewHolder.receivingItem_itemGrossWeight.setText(getItem(position).getItemGrossWeight().toString());
        viewHolder.receivingItem_itemGrossWeightUnit.setText(getItem(position).getItemGrossWeightUnit().toString());
        return convertView;
    }

    @Override
    public boolean productCodeMatch(V_ReceivingItemModel VReceivingItemModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(V_ReceivingItemModel VReceivingItemModel, String string) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(V_ReceivingItemModel VReceivingItemModel, String string) {
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
