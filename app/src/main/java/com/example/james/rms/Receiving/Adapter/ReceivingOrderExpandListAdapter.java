package com.example.james.rms.Receiving.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/24.
 */

public class ReceivingOrderExpandListAdapter extends MyExpandableListAdapter<ReceivingOrderModel> {

    public ReceivingOrderExpandListAdapter(Context context, List<ReceivingOrderModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        ReceivingOrderModel receivingOrderModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_order_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.receivingOrder_orderId.setText(ObjectUtil.longToString(receivingOrderModel.getOrderId()));
        holder.receivingOrder_date.setText(ObjectUtil.dateToString(receivingOrderModel.getReceivingDate()));
        holder.receivingOrder_actualQty.setText(ObjectUtil.intToString(receivingOrderModel.getActualQty()));
        holder.receivingOrder_estimateQty.setText(ObjectUtil.intToString(receivingOrderModel.getEstimateQty()));
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ReceivingOrderModel receivingOrderModel = getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_order_expendablelist_item, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.receivingOrder_status.setText(receivingOrderModel.getStatus());
        holder.receivingOrder_createDate.setText(ObjectUtil.dateToString(receivingOrderModel.getCreateDate()));
        holder.receivingOrder_closeDate.setText(ObjectUtil.dateToString(receivingOrderModel.getCloseDate()));
        holder.receivingOrder_remark.setText(receivingOrderModel.getRemark());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1 ;
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
        return  ObjectUtil.isNullEmpty(receivingOrderModel.getRemark()) ? false : receivingOrderModel.getRemark().toUpperCase().contains(string.toUpperCase());
    }

    static class GroupHolder {
        @BindView(R.id.receivingOrder_orderId)
        TextView receivingOrder_orderId;
        @BindView(R.id.receivingOrder_date)
        TextView receivingOrder_date;
        @BindView(R.id.receivingOrder_actualQty)
        TextView receivingOrder_actualQty;
        @BindView(R.id.receivingOrder_estimateQty)
        TextView receivingOrder_estimateQty;

        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.receivingOrder_status)
        TextView receivingOrder_status;
        @BindView(R.id.receivingOrder_createDate)
        TextView receivingOrder_createDate;
        @BindView(R.id.receivingOrder_closeDate)
        TextView receivingOrder_closeDate;
        @BindView(R.id.receivingOrder_remark)
        TextView receivingOrder_remark;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
