package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.GlideApp;
import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.R;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryDialogExpandableAdapter extends MyExpandableListAdapter<DeliveryOrderModel> {

    // 用來控制CheckBox的選中狀況
    private static LinkedHashMap<Long, Boolean> isSelected;

    public DeliveryDialogExpandableAdapter(Context context, List<DeliveryOrderModel> dataArrayList, LinkedHashMap<Long, Boolean> isSelected) {
        super(context, dataArrayList);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        final DeliveryOrderModel deliveryOrderModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_dialog, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.receivingOrder_orderId.setText(ObjectUtil.longToString(getGroup(groupPosition).getOrderId()));
        holder.receivingOrder_date.setText(ObjectUtil.dateToString_OnlyDate(getGroup(groupPosition).getStockOutDate()));
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
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getDeliveryItem().size();
    }

    @Override
    public boolean productCodeMatch(DeliveryOrderModel deliveryOrderModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(DeliveryOrderModel deliveryOrderModel, String string) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(DeliveryOrderModel deliveryOrderModel, String string) {
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

}
