package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.GlideApp;
import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.R;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryDialogExpandableAdapter extends MyExpandableListAdapter<ReceivingOrderModel> {

    // 用來控制CheckBox的選中狀況
    private static LinkedHashMap<Long, Boolean> isSelected;

    public DeliveryDialogExpandableAdapter(Context context, List<ReceivingOrderModel> dataArrayList, LinkedHashMap<Long, Boolean> isSelected) {
        super(context, dataArrayList);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        final ReceivingOrderModel receivingOrderModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_order_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.receivingOrder_orderId.setText(ObjectUtil.longToString(getGroup(groupPosition).getOrderId()));
        holder.receivingOrder_date.setText(ObjectUtil.dateToString_OnlyDate(getGroup(groupPosition).getReceivingDate()));
        GlideApp.with(getContext())
                .load(R.drawable.input)
                .error(R.drawable.question_purple)
                .placeholder(R.drawable.question_purple)
                .fitCenter()
                .into(holder.receivingOrder_image);
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder viewHolder;
        List<ReceivingItemModel> receivingItemModels = getChild(groupPosition,childPosition).getReceivingItem();
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_item_expendablelist_group, parent, false);
            viewHolder = new ChildHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getReceivingItem().size();
    }

    @Override
    public boolean productCodeMatch(ReceivingOrderModel receivingOrderModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(ReceivingOrderModel receivingOrderModel, String string) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingOrderModel receivingOrderModel, String string) {
        return false;
    }

    static class GroupHolder {
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_orderId)
        TextView receivingOrder_orderId;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_date)
        TextView receivingOrder_date;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_image)
        com.github.siyamed.shapeimageview.RoundedImageView receivingOrder_image;

        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.delivery_order_increase_receivingItem_ProductCode)
        TextView delivery_order_increase_receivingItem_ProductCode;
        @BindView(R.id.delivery_order_increase_receivingItem_ProductName)
        TextView delivery_order_increase_receivingItem_ProductName;
        @BindView(R.id.delivery_order_increase_receivingItem_itemReceivingDate)
        TextView delivery_order_increase_receivingItem_itemReceivingDate;
        @BindView(R.id.delivery_order_increase_receivingItem_itemGrossWeight)
        TextView delivery_order_increase_receivingItem_itemGrossWeight;
        @BindView(R.id.delivery_order_increase_receivingItem_itemGrossWeightUnit)
        TextView delivery_order_increase_receivingItem_itemGrossWeightUnit;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
