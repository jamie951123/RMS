package com.example.james.rms.Receiving.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/29.
 */

public class ReceivingItemExpandListAdapter extends MyExpandableListAdapter<ReceivingItemModel> {


    public ReceivingItemExpandListAdapter(Context context, List<ReceivingItemModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder viewHolder;
        ReceivingItemModel receivingItemModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_item_expendablelist_group, parent, false);
            viewHolder = new GroupHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupHolder) convertView.getTag();
        }
        viewHolder.receivingItem_ProductCode.setText(receivingItemModel.getProductModel().getProductCode());
        viewHolder.receivingItem_ProductName.setText(receivingItemModel.getProductModel().getProductName());
        viewHolder.receivingItem_itemReceivingDate.setText(ObjectUtil.dateToString(receivingItemModel.getItemReceivingDate()));
        viewHolder.receivingItem_itemGrossWeight.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight()));
        viewHolder.receivingItem_itemGrossWeightUnit.setText(receivingItemModel.getProductModel().getWeightprofile()==null?"":receivingItemModel.getProductModel().getWeightprofile().getWeightUnit() );

        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder viewHolder;
        ReceivingItemModel receivingItemModel = getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_item_expendablelist_item, parent, false);
            viewHolder = new ChildHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildHolder) convertView.getTag();
        }
        viewHolder.receivingItem_itemStatus.setText(receivingItemModel.getItemStatus());
        viewHolder.receivingItem_itemCreateDate.setText(ObjectUtil.dateToString(receivingItemModel.getItemCreateDate()));
        viewHolder.receivingItem_itemRemark.setText(receivingItemModel.getItemRemark());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1 ;
    }

    @Override
    public boolean productCodeMatch(ReceivingItemModel receivingItemModel, String string) {
        if(ObjectUtil.isNullEmpty(receivingItemModel.getProductModel().getProductCode()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = receivingItemModel.getProductModel().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ReceivingItemModel receivingItemModel, String string) {
        if(ObjectUtil.isNullEmpty(receivingItemModel.getProductModel().getProductName()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = receivingItemModel.getProductModel().getProductName().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingItemModel receivingItemModel, String string) {
        if(ObjectUtil.isNullEmpty(receivingItemModel.getItemRemark()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        return  ObjectUtil.isNullEmpty(receivingItemModel.getItemRemark()) ? false : receivingItemModel.getItemRemark().toUpperCase().contains(string.toUpperCase());
    }


    static class GroupHolder {
        @BindView(R.id.receivingItem_ProductCode)
        TextView receivingItem_ProductCode;
        @BindView(R.id.receivingItem_ProductName)
        TextView receivingItem_ProductName;
        @BindView(R.id.receivingItem_itemReceivingDate)
        TextView receivingItem_itemReceivingDate;
        @BindView(R.id.receivingItem_itemGrossWeight)
        TextView receivingItem_itemGrossWeight;
        @BindView(R.id.receivingItem_itemGrossWeightUnit)
        TextView receivingItem_itemGrossWeightUnit;

        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.receivingItem_itemStatus)
        TextView receivingItem_itemStatus;
        @BindView(R.id.receivingItem_itemCreateDate)
        TextView receivingItem_itemCreateDate;
        @BindView(R.id.receivingItem_itemRemark)
        TextView receivingItem_itemRemark;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
