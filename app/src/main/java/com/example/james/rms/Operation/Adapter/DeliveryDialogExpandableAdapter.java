package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
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
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_dialog_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        if (isExpanded)
            convertView.setPadding(0, 0, 0, 0);
        else{
            convertView.setPadding(0, 0, 0, 20);
        }
        holder.receivingOrder_orderId.setText(ObjectUtil.longToString(receivingOrderModel.getOrderId()));
        holder.receivingOrder_date.setText(ObjectUtil.dateToString_OnlyDate(receivingOrderModel.getReceivingDate()));
        holder.receivingOrder_itemQty.setText(ObjectUtil.intToString(receivingOrderModel.getItemQty()));
        GlideApp.with(getContext())
                .load(R.drawable.input)
                .error(R.drawable.question_purple)
                .placeholder(R.drawable.question_purple)
                .fitCenter()
                .into(holder.receivingOrder_image);
        holder.group_checkbox.setChecked(true);
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ReceivingItemModel receivingItemModel = getGroup(groupPosition).getReceivingItem().get(childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_dialog_expendablelist_item, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        if (childPosition == getGroup(groupPosition).getReceivingItem().size() - 1) {
            convertView.setPadding(0, 0, 0, 20);
        } else {
            convertView.setPadding(0, 0, 0, 0);
        }

        GlideApp.with(getContext())
                .load(R.drawable.mailbox_black)
                .error(R.drawable.question_purple)
                .placeholder(R.drawable.question_purple)
                .fitCenter()
                .into(holder.delivery_item_increase_receivingItem_image);
        holder.delivery_item_increase_receivingItem_ProductCode.setText(receivingItemModel.getProduct().getProductCode());
        holder.delivery_item_increase_receivingItem_ProductName.setText(receivingItemModel.getProduct().getProductName());
        holder.delivery_item_increase_receivingItem_itemReceivingDate.setText(ObjectUtil.dateToString_OnlyDate(receivingItemModel.getItemReceivingDate()));
        holder.delivery_item_increase_receivingItem_itemGrossWeight.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight()));
        holder.delivery_item_increase_receivingItem_itemGrossWeight_unit.setText(receivingItemModel.getProduct().getWeightprofile()==null?"":receivingItemModel.getProduct().getWeightprofile().getWeightUnit());
        holder.delivery_item_increase_receivingItem_itemQuantity.setText(receivingItemModel.getProduct().getQuantityProfile()==null?"":receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit());
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
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_itemQty)
        TextView receivingOrder_itemQty;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_image)
        com.github.siyamed.shapeimageview.RoundedImageView receivingOrder_image;
        @BindView(R.id.delivery_order_increase_dialog_checkbox)
        android.support.v7.widget.AppCompatCheckBox group_checkbox;


        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.delivery_item_increase_receivingItem_image)
        com.github.siyamed.shapeimageview.RoundedImageView delivery_item_increase_receivingItem_image;
        @BindView(R.id.delivery_item_increase_receivingItem_ProductCode)
        TextView delivery_item_increase_receivingItem_ProductCode;
        @BindView(R.id.delivery_item_increase_receivingItem_ProductName)
        TextView delivery_item_increase_receivingItem_ProductName;
        @BindView(R.id.delivery_item_increase_receivingItem_itemReceivingDate)
        TextView delivery_item_increase_receivingItem_itemReceivingDate;
        @BindView(R.id.delivery_item_increase_receivingItem_itemGrossWeight)
        TextView delivery_item_increase_receivingItem_itemGrossWeight;
        @BindView(R.id.delivery_item_increase_receivingItem_itemGrossWeight_unit)
        TextView delivery_item_increase_receivingItem_itemGrossWeight_unit;
        @BindView(R.id.delivery_item_increase_receivingItem_itemQuantity)
        TextView delivery_item_increase_receivingItem_itemQuantity;
        @BindView(R.id.delivery_item_increase_receivingItem_itemQuantity_unit)
        TextView delivery_item_increase_receivingItem_itemQuantity_unit;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
