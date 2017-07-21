package com.example.james.rms.Operation.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.DialogBox.NumberDialog;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyAdapter.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.NumberDialogListener;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JamesL on 6/23/2017.
 */

public class DeliveryIncreaseItemExpandableAdapter extends MyExpandableListAdapter<ReceivingOrderModel> {

    private AnimatedExpandableListView listView;
    private LinkedHashMap<Long,DeliveryItemModel> mapByReceivingItemId;

    public DeliveryIncreaseItemExpandableAdapter(Context context, List<ReceivingOrderModel> dataArrayList,LinkedHashMap<Long,DeliveryItemModel> mapByReceivingItemId, AnimatedExpandableListView listView) {
        super(context, dataArrayList);
        this.listView = listView;
        this.mapByReceivingItemId = mapByReceivingItemId;
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
    public View getRealChildView(final int groupPosition,final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
//        ReceivingItemModel receivingItemModel = getGroup(groupPosition).getDeliveryOrder().get(groupPosition).getDeliveryItem().get(childPosition).getReceivingItem();
        final ReceivingItemModel receivingItemModel = getGroup(groupPosition).getReceivingItem().get(childPosition);
        long receivingId = receivingItemModel.getReceivingId();
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_increase_item_expendablelist_child, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

//        mapByReceivingItemId
        holder.qty.setText(ObjectUtil.intToString(mapByReceivingItemId.get(receivingId).getItemQty()));
        holder.gw.setText(ObjectUtil.bigDecimalToString(mapByReceivingItemId.get(receivingId).getItemGrossWeight()));
        holder.remark.setText(mapByReceivingItemId.get(receivingId).getItemRemark());

//        receivingItemModel
        holder.image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
        holder.productCode.setText(receivingItemModel.getProduct().getProductCode());
        holder.productName.setText(receivingItemModel.getProduct().getProductName());
        holder.qtylinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogBox(KeyModel.qty,receivingItemModel,groupPosition,childPosition);
            }
        });

        holder.totalQty.setText(ObjectUtil.intToString(receivingItemModel.getOutStandingQty()));
        holder.gwlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogBox(KeyModel.gw,receivingItemModel,groupPosition,childPosition);

            }
        });
        holder.totalGW.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getOutStandingWeight()));

        if(receivingItemModel.getProduct().getQuantityProfile() !=null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit())){
            holder.qty_unit.setText(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit());
        }

        if(receivingItemModel.getProduct().getWeightprofile() !=null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getWeightprofile().getWeightUnit())){
            holder.gw_unit.setText(receivingItemModel.getProduct().getWeightprofile().getWeightUnit());
        }

        holder.remark.addTextChangedListener(textWatch(groupPosition,childPosition,KeyModel.remark,receivingId));

        return convertView;
    }

    public void createDialogBox(String key,final ReceivingItemModel receivingItemModel,int groupPosition,int childPosition){
        final Long rItemId = receivingItemModel.getReceivingId();

        NumberDialog numberDialog = new NumberDialog();
        FragmentManager fm =  ((AppCompatActivity) getContext()).getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(key);
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        NumberDialogListener listener = numberDialog;
        NumberDialogModel numberDialogModel = new NumberDialogModel();
        numberDialogModel.setId(rItemId);
        numberDialogModel.setKey(key);
        numberDialogModel.setGroupPosition(groupPosition);
        numberDialogModel.setChildPosition(childPosition);
        if(key.equalsIgnoreCase(KeyModel.qty)) {
            if (receivingItemModel.getProduct().getQuantityProfile() != null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit())) {
                numberDialogModel.setQtyUnit(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit());
            }
            numberDialogModel.setQty(mapByReceivingItemId.get(rItemId).getItemQty() == null ? 0 : mapByReceivingItemId.get(rItemId).getItemQty());
//            numberDialogModel.setQtyMax(receivingItemModel.getItemQty());
            numberDialogModel.setQtyMax(receivingItemModel.getOutStandingQty()==null?0:receivingItemModel.getOutStandingQty());
        } else if(key.equalsIgnoreCase(KeyModel.gw)){
            if (receivingItemModel.getProduct().getWeightprofile() != null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getWeightprofile().getWeightUnit())) {
                numberDialogModel.setGrossWeightUnit(receivingItemModel.getProduct().getWeightprofile().getWeightUnit());
            }
            numberDialogModel.setGrossWeight(mapByReceivingItemId.get(rItemId).getItemGrossWeight()==null?new BigDecimal(0):mapByReceivingItemId.get(rItemId).getItemGrossWeight() );
//            numberDialogModel.setGwMax(receivingItemModel.getItemGrossWeight());
            numberDialogModel.setGwMax(receivingItemModel.getOutStandingWeight()==null?new BigDecimal(0):receivingItemModel.getOutStandingWeight());
        }
        listener.from(numberDialogModel,getContext());
        numberDialog.show(fm,key);
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
        public de.hdodenhof.circleimageview.CircleImageView image;
        @BindView(R.id.delivery_increase_item_child_ProductCode)
        public TextView productCode;
        @BindView(R.id.delivery_increase_item_child_ProductName)
        public TextView productName;
        @BindView(R.id.delivery_increase_item_child_qty_linear)
        public LinearLayout qtylinear;
        @BindView(R.id.delivery_increase_item_child_qty)
        public TextView qty;
        @BindView(R.id.delivery_item_increase_totalQty)
        public TextView totalQty;
        @BindView(R.id.delivery_item_increase_qty_unit)
        public TextView qty_unit;
        @BindView(R.id.delivery_increase_item_child_gw_linear)
        public LinearLayout gwlinear;
        @BindView(R.id.delivery_increase_item_child_gw)
        public TextView gw;
        @BindView(R.id.delivery_increase_item_child_totalGW)
        public TextView totalGW;
        @BindView(R.id.delivery_increase_item_child_gw_unit)
        public TextView gw_unit;
        @BindView(R.id.delivery_increase_item_child_increase_itemremark)
        public TextView remark;
        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public TextWatcher textWatch(final int groupPosition, final int childPosition, final String fieldId, final long receivingItemId){
        TextWatcher watcher = new android.text.TextWatcher(){


            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s != null && s.length()>0) {
                    switch (fieldId){
                        case KeyModel.remark:
                            mapByReceivingItemId.get(receivingItemId).setItemRemark(s.toString());
                            break;
                    }
                }

            }

        };
        return watcher;
    }



}
