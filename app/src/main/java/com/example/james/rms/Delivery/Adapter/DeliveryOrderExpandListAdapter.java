package com.example.james.rms.Delivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.GlideApp;
import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.DeliveryOrderCombine;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Operation.DeliveryAction.DeliveryIncrease;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 16/6/2017.
 */

public class DeliveryOrderExpandListAdapter extends MyExpandableListAdapter<DeliveryOrderModel> {

    public DeliveryOrderExpandListAdapter(Context context, List<DeliveryOrderModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        final DeliveryOrderModel deliveryOrderModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_order_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.deliveryOrder_orderId.setText(ObjectUtil.longToString(deliveryOrderModel.getOrderId()));
        holder.deliveryOrder_date.setText(ObjectUtil.dateToString_OnlyDate(deliveryOrderModel.getStockOutDate()));
        holder.deliveryOrder_itemQty.setText(ObjectUtil.intToString(deliveryOrderModel.getItemQty()));
        holder.deliveryOrder_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.output));
//        GlideApp.with(getContext())
//                .load(R.drawable.output)
//                .error(R.drawable.question_purple)
//                .placeholder(R.drawable.question_purple)
//                .fitCenter()
//                .into(holder.deliveryOrder_image);

        holder.deliveryOrder_linear_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), DeliveryIncrease.class);
                DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);
                String deliveryOrder_json = deliveryOrderCombine.modelToJson(deliveryOrderModel);
                intent.putExtra(StartActivityForResultKey.deliveryOrderModel,deliveryOrder_json);
                getContext().startActivity(intent);
                Log.d("asd","deliveryOrder_linear_edit");
            }
        });

        holder.deliveryOrder_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);

                try {
                    String delete_json = deliveryOrderCombine.modelToJson(getGroup(groupPosition));
                    DeliveryOrderDao deliveryOrderDao = new DeliveryOrderDaoImpl();
                    ResponseMessage responseMessage = deliveryOrderDao.delete(delete_json);
                    if (responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())) {
                        getFilteredData().remove(groupPosition);
                        notifyDataSetChanged();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.d("asd","deliveryOrder_linear_delete");
            }
        });

        holder.deliveryOrder_linear_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext() instanceof NavigationController){
                    Long order_orderId = getGroup(groupPosition).getOrderId();
                    if(order_orderId ==null) return;

                    if(getFilteredData().get(groupPosition).getDeliveryItem() != null) {
                        NavigationController controller = (NavigationController) getContext();
                        ViewPagerListener viewPagerListener = (ViewPagerListener) controller;
                        viewPagerListener.transferViewPager(R.id.delivery_item,getFilteredData().get(groupPosition));
                    }
                }
                Log.d("asd","deliveryOrder_linear_detail");
            }
        });
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        DeliveryOrderModel deliveryOrderModel = getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_order_expendablelist_item, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.deliveryOrder_status.setText(deliveryOrderModel.getStatus());
        holder.deliveryOrder_status.setText(ObjectUtil.dateToString(deliveryOrderModel.getCreateDate()));
        holder.deliveryOrder_closeDate.setText(ObjectUtil.dateToString(deliveryOrderModel.getCloseDate()));
        holder.deliveryOrder_remark.setText(deliveryOrderModel.getRemark());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
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
        if(ObjectUtil.isNullEmpty(deliveryOrderModel.getRemark()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        return  ObjectUtil.isNullEmpty(deliveryOrderModel.getRemark()) ? false : deliveryOrderModel.getRemark().toUpperCase().contains(string.toUpperCase());
    }

    static class GroupHolder {
        @BindView(R.id.deliveryOrder_orderId)
        TextView deliveryOrder_orderId;
        @BindView(R.id.deliveryOrder_date)
        TextView deliveryOrder_date;
        @BindView(R.id.deliveryOrder_itemQty)
        TextView deliveryOrder_itemQty;
        @BindView(R.id.deliveryOrder_linear_detail)
        LinearLayout deliveryOrder_linear_detail;
        @BindView(R.id.deliveryOrder_linear_delete)
        LinearLayout deliveryOrder_linear_delete;
        @BindView(R.id.deliveryOrder_linear_edit)
        LinearLayout deliveryOrder_linear_edit;
        @BindView(R.id.deliveryOrder_image)
        com.github.siyamed.shapeimageview.RoundedImageView deliveryOrder_image;

        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.deliveryOrder_status)
        TextView deliveryOrder_status;
        @BindView(R.id.deliveryOrder_createDate)
        TextView receivingOrder_createDate;
        @BindView(R.id.deliveryOrder_closeDate)
        TextView deliveryOrder_closeDate;
        @BindView(R.id.deliveryOrder_remark)
        TextView deliveryOrder_remark;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
