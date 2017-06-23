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
import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryIncreaseDialogExpandableAdapter extends MyExpandableListAdapter<ReceivingOrderModel> {

    // 用來控制CheckBox的選中狀況
    private static ExpandableSelectedModel expandableSelectedModel;
    private AnimatedExpandableListView listView;
    public DeliveryIncreaseDialogExpandableAdapter(Context context, List<ReceivingOrderModel> dataArrayList, ExpandableSelectedModel expandableSelectedModel, AnimatedExpandableListView listView) {
        super(context, dataArrayList);
        this.listView = listView;
        this.expandableSelectedModel =expandableSelectedModel;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        final ReceivingOrderModel receivingOrderModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_dialog_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        if (isExpanded) {
            holder.receivingOrder_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.input));
            convertView.setPadding(0, 0, 0, 0);
            if (receivingOrderModel.getReceivingItem() == null || receivingOrderModel.getReceivingItem().isEmpty()) {
                convertView.setPadding(0, 0, 0, 20);
            }
        }else{
            holder.receivingOrder_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.drop_down_black));

            convertView.setPadding(0, 0, 0, 20);
        }
        holder.receivingOrder_orderId.setText(ObjectUtil.longToString(receivingOrderModel.getOrderId()));
        holder.receivingOrder_date.setText(ObjectUtil.dateToString_OnlyDate(receivingOrderModel.getReceivingDate()));
        holder.receivingOrder_itemQty.setText(ObjectUtil.intToString(receivingOrderModel.getItemQty()));
        if(expandableSelectedModel.getIsOrderSelected().containsKey(receivingOrderModel.getOrderId())){
            holder.group_checkbox.setChecked(expandableSelectedModel.getIsOrderSelected().get(receivingOrderModel.getOrderId()));
        }
//        GlideApp.with(getContext())
//                .load(R.drawable.input)
//                .error(R.drawable.question_purple)
//                .placeholder(R.drawable.question_purple)
//                .fitCenter()
//                .into(holder.receivingOrder_image);
        holder.delivery_order_increase_dialog_receivingOrder_image_linear.setOnClickListener(new View.OnClickListener() {
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
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_dialog_expendablelist_child, parent, false);
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

//        GlideApp.with(getContext())
//                .load("")
//                .error(R.drawable.question_purple)
////                .placeholder(R.drawable.question_purple)
//                .placeholder(R.drawable.mailbox_black)
//                .fitCenter()
//                .into(holder.delivery_item_increase_receivingItem_image);
        holder.delivery_item_increase_receivingItem_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
        holder.delivery_item_increase_receivingItem_ProductCode.setText(receivingItemModel.getProduct().getProductCode());
        holder.delivery_item_increase_receivingItem_ProductName.setText(receivingItemModel.getProduct().getProductName());
        holder.delivery_item_increase_receivingItem_itemReceivingDate.setText(ObjectUtil.dateToString_OnlyDate(receivingItemModel.getItemReceivingDate()));
        holder.delivery_item_increase_receivingItem_itemGrossWeight.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight()));
        holder.delivery_item_increase_receivingItem_itemGrossWeight_unit.setText(receivingItemModel.getProduct().getWeightprofile()==null?"":receivingItemModel.getProduct().getWeightprofile().getWeightUnit());
        holder.delivery_item_increase_receivingItem_itemQuantity.setText(ObjectUtil.intToString(receivingItemModel.getItemQty()));
        holder.delivery_item_increase_receivingItem_itemQuantity_unit.setText(receivingItemModel.getProduct().getQuantityProfile()==null?"":receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit());
//        holder.child_checkbox.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.blueb3ffff));
        if(expandableSelectedModel.getIsItemSelected().containsKey(receivingItemModel.getReceivingId())){
            holder.child_checkbox.setChecked(expandableSelectedModel.getIsItemSelected().get(receivingItemModel.getReceivingId()));
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
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_orderId)
        public TextView receivingOrder_orderId;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_date)
        public TextView receivingOrder_date;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_itemQty)
        public TextView receivingOrder_itemQty;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_image_linear)
        public LinearLayout delivery_order_increase_dialog_receivingOrder_image_linear;
        @BindView(R.id.delivery_order_increase_dialog_receivingOrder_image)
        public com.github.siyamed.shapeimageview.RoundedImageView receivingOrder_image;
        @BindView(R.id.delivery_order_increase_dialog_checkbox)
        public android.support.v7.widget.AppCompatCheckBox group_checkbox;


        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public static class ChildHolder {
        @BindView(R.id.delivery_item_increase_dialog_checkbox)
        public android.support.v7.widget.AppCompatCheckBox child_checkbox;
        @BindView(R.id.delivery_item_increase_receivingItem_image)
        public com.github.siyamed.shapeimageview.RoundedImageView delivery_item_increase_receivingItem_image;
        @BindView(R.id.delivery_item_increase_receivingItem_ProductCode)
        public TextView delivery_item_increase_receivingItem_ProductCode;
        @BindView(R.id.delivery_item_increase_receivingItem_ProductName)
        public TextView delivery_item_increase_receivingItem_ProductName;
        @BindView(R.id.delivery_item_increase_receivingItem_itemReceivingDate)
        public TextView delivery_item_increase_receivingItem_itemReceivingDate;
        @BindView(R.id.delivery_item_increase_receivingItem_itemGrossWeight)
        public TextView delivery_item_increase_receivingItem_itemGrossWeight;
        @BindView(R.id.delivery_item_increase_receivingItem_itemGrossWeight_unit)
        public TextView delivery_item_increase_receivingItem_itemGrossWeight_unit;
        @BindView(R.id.delivery_item_increase_receivingItem_itemQuantity)
        public TextView delivery_item_increase_receivingItem_itemQuantity;
        @BindView(R.id.delivery_item_increase_receivingItem_itemQuantity_unit)
        public TextView delivery_item_increase_receivingItem_itemQuantity_unit;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public static ExpandableSelectedModel getExpandableSelectedModel() {
        return expandableSelectedModel;
    }

    public static void setExpandableSelectedModel(ExpandableSelectedModel expandableSelectedModel) {
        DeliveryIncreaseDialogExpandableAdapter.expandableSelectedModel = expandableSelectedModel;
    }
}
