package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JamesL on 6/23/2017.
 */

public class DeliveryIncreaseItemExpandableAdapter extends MyExpandableListAdapter<ReceivingOrderModel> {

    private AnimatedExpandableListView listView;


    public DeliveryIncreaseItemExpandableAdapter(Context context, List<ReceivingOrderModel> dataArrayList,AnimatedExpandableListView listView) {
        super(context, dataArrayList);
        this.listView = listView;
    }

    @Override
    public View getGroupView(final int groupPosition,final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        final ReceivingOrderModel receivingOrderModel = getGroup(groupPosition);
        if(convertView == null){
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_item_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        if (isExpanded) {
            holder.image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.input));
            convertView.setPadding(0, 0, 0, 0);
            if (receivingOrderModel.getReceivingItem() == null || receivingOrderModel.getReceivingItem().isEmpty()) {
                convertView.setPadding(0, 0, 0, 20);
            }
        }else{
            holder.image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.drop_down_black));

            convertView.setPadding(0, 0, 0, 20);
        }

        holder.orderId.setText(ObjectUtil.longToString(receivingOrderModel.getOrderId()));
        holder.date.setText(ObjectUtil.dateToString_OnlyDate(receivingOrderModel.getReceivingDate()));
        holder.image_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("asd","isExpanded :" + isExpanded);
                if (isExpanded) {
                    listView.collapseGroup(groupPosition);
                }else{
                    listView.expandGroup(groupPosition);

                }
            }
        });
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ReceivingItemModel receivingItemModel = getGroup(groupPosition).getReceivingItem().get(childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_item_expendablelist_child, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
        holder.productCode.setText(receivingItemModel.getProduct().getProductCode());
        holder.productName.setText(receivingItemModel.getProduct().getProductName());
//        holder.qtylinear
//        holder.qty.setText(receivingItemModel.getIte);
        holder.totalQty.setText(ObjectUtil.intToString(receivingItemModel.getItemQty()));
//        holder.gwlinear
//        holder.gw.setText();
        holder.totalGW.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight()));

        if(receivingItemModel.getProduct().getQuantityProfile() !=null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit())){
            holder.qty_unit.setText(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit());
        }

        if(receivingItemModel.getProduct().getWeightprofile() !=null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getWeightprofile().getWeightUnit())){
            holder.gw_unit.setText(receivingItemModel.getProduct().getWeightprofile().getWeightUnit());
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

    public static class GroupHolder {
        @BindView(R.id.delivery_increase_item_group_orderId)
        public TextView orderId;
        @BindView(R.id.delivery_increase_item_group_date)
        public TextView date;
        @BindView(R.id.delivery_increase_item_group_image_linear)
        public LinearLayout image_linear;
        @BindView(R.id.delivery_increase_item_group_image)
        public com.github.siyamed.shapeimageview.RoundedImageView image;

        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public static class ChildHolder {
        @BindView(R.id.delivery_increase_item_child_image_linear)
        public LinearLayout image_linear;
        @BindView(R.id.delivery_increase_item_child_image)
        public com.github.siyamed.shapeimageview.RoundedImageView image;
        @BindView(R.id.delivery_increase_item_child_ProductCode)
        public TextView productCode;
        @BindView(R.id.delivery_increase_item_child_ProductName)
        public TextView productName;
        @BindView(R.id.delivery_increase_item_child_qtylinear)
        public LinearLayout qtylinear;
        @BindView(R.id.delivery_increase_item_child_qty)
        public TextView qty;
        @BindView(R.id.delivery_item_increase_totalQty)
        public TextView totalQty;
        @BindView(R.id.delivery_item_increase_qty_unit)
        public TextView qty_unit;
        @BindView(R.id.delivery_increase_item_child_gwlinear)
        public LinearLayout gwlinear;
        @BindView(R.id.delivery_increase_item_child_gw)
        public TextView gw;
        @BindView(R.id.delivery_increase_item_child_totalGW)
        public TextView totalGW;
        @BindView(R.id.delivery_increase_item_child_gw_unit)
        public TextView gw_unit;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
