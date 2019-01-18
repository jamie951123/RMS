package com.example.james.rms.main.receiving.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.james.rms.common.CommonFactory;
import com.example.james.rms.common.util.ListViewUtil;
import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.adapter.MyExpandableListAdapter;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.common.ResponseStatus;
import com.example.james.rms.common.StartActivityForResultKey;
import com.example.james.rms.controller.NavigationAct;
import com.example.james.rms.core.combine.ReceivingOrderCombine;
import com.example.james.rms.core.dao.ReceivingOrderDao;
import com.example.james.rms.core.dao.ReceivingOrderDaoImpl;
import com.example.james.rms.core.model.ReceivingItemModel;
import com.example.james.rms.core.model.ReceivingOrderModel;
import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.main.delivery.DeliveryContainerFrag;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.main.inventory.tab.InventoryContainerFrag;
import com.example.james.rms.operation.receiving_action.ReceivingIncreaseAct;
import com.example.james.rms.R;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/24.
 */

public class ReceivingOrderExpandListAdapter extends MyExpandableListAdapter<ReceivingOrderModel> {
    //Dao
    private ReceivingOrderDao receivingOrderDao;

    public ReceivingOrderExpandListAdapter(Context context, List<ReceivingOrderModel> dataArrayList) {
        super(context, dataArrayList);
        receivingOrderDao = new ReceivingOrderDaoImpl((AppCompatActivity)context);
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        final ReceivingOrderModel receivingOrderModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_order_expendablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
//        holder.receivingOrder_orderId.setText(ObjectUtil.longToString(receivingOrderModel.getOrderId()));
        holder.receivingOrder_date.setText(ObjectUtil.dateToString_OnlyDate(receivingOrderModel.getReceivingDate()));
//        holder.receivingOrder_actualQty.setText(ObjectUtil.intToString(receivingOrderModel.getActualQty()));
//        holder.receivingOrder_estimateQty.setText(ObjectUtil.intToString(receivingOrderModel.getEstimateQty()));
        holder.receivingOrder_itemQty.setText(ObjectUtil.intToString(receivingOrderModel.getReceivingItem()==null?0:receivingOrderModel.getReceivingItem().size()));
        holder.receivingOrder_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.input));

        //
        ReceivingOrderGoodDisplayAdapter receivingOrderGoodDisplayAdapter = new ReceivingOrderGoodDisplayAdapter(getContext(),receivingOrderModel.getReceivingItem());
        holder.goods_display_listview.setAdapter(receivingOrderGoodDisplayAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(holder.goods_display_listview);
        holder.goods_display_listview.setFocusable(false);
        if(receivingOrderModel.getReceivingItem() == null || (receivingOrderModel.getReceivingItem() != null && receivingOrderModel.getReceivingItem().isEmpty())){
            holder.goods_header_linear.setVisibility(View.GONE);
        }
//        convertView.
//        GlideApp.with(getContext())
//                .load(R.drawable.input)
//                .error(R.drawable.question_purple)
//                .placeholder(R.drawable.question_purple)
//                .fitCenter()
//                .into(holder.receivingOrder_image);

        holder.receivingOrder_linear_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceivingOrderCombine receivingOrderCombine = new ReceivingOrderCombine(ReceivingOrderModel.class);
                String receivingOrder_json = receivingOrderCombine.modelToJson(receivingOrderModel);
                if(ObjectUtil.isNotNullEmpty(receivingOrder_json)) {
                    //MovementRecord
                    String movementRecord_str = CommonFactory.movementFactory_str(NavigationAct.class.getCanonicalName(), ReceivingIncreaseAct.class.getCanonicalName(),R.id.nav_receiving);
                    //
                    Intent intent = new Intent();
                    intent.setClass(getContext(), ReceivingIncreaseAct.class);
                    intent.putExtra(StartActivityForResultKey.receivingOrderModel,receivingOrder_json);
                    intent.putExtra(StartActivityForResultKey.movementRecord,movementRecord_str);
                    getContext().startActivity(intent);
                }
            }
        });
        holder.receivingOrder_linear_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext() instanceof NavigationAct){
                    Long order_orderId = receivingOrderModel.getOrderId();
                    if(order_orderId ==null) return;

                    if(getFilteredData().get(groupPosition).getReceivingItem() != null) {
                        NavigationAct controller = (NavigationAct) getContext();
                        ViewPagerListener viewPagerListener = (ViewPagerListener) controller;
                        viewPagerListener.transferViewPager(R.id.receiving_item, getFilteredData().get(groupPosition));
                    }
                }

            }
        });

        holder.receivingOrder_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceivingOrderModel receivingOrderModel = getGroup(groupPosition);
                try{
                    Gson gson = GsonUtil.toJson();
                    String receivingOrder_json =gson.toJson(receivingOrderModel,ReceivingOrderModel.class);
                    ResponseMessage responseMessage = receivingOrderDao.delete(receivingOrder_json);
                    if(responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())){
                        getFilteredData().remove(groupPosition);
                        notifyDataSetChanged();
                        refresh(DeliveryContainerFrag.class.getCanonicalName(),R.layout.delivery_order);
                        refresh(InventoryContainerFrag.class.getCanonicalName(),R.layout.inventort_item);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.v("asd","receivingOrder_linear_delete");
            }
        });
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
    public boolean productNameMatch(ReceivingOrderModel receivingOrderModel, String string,int position) {
        boolean isMatch = false;
        if(receivingOrderModel.getReceivingItem() != null ){
            for(ReceivingItemModel item : receivingOrderModel.getReceivingItem()){
                if(item.getProduct().getProductName().contains(string.toUpperCase())){
                    isMatch = true;
                    if(isMatch)break;
                }
            }
        }
//        if(receivingOrderModel.getReceivingItem()!=null && !receivingOrderModel.getReceivingItem().isEmpty()){
//            isMatch = receivingOrderModel.getReceivingItem().get(position).getProduct().getProductName().contains(string.toUpperCase());
//        }
        return isMatch;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingOrderModel receivingOrderModel, String string) {
        if(ObjectUtil.isNullEmpty(receivingOrderModel.getRemark()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        return  ObjectUtil.isNullEmpty(receivingOrderModel.getRemark()) ? false : receivingOrderModel.getRemark().toUpperCase().contains(string.toUpperCase());
    }

    static class GroupHolder {
        @BindView(R.id.receivingOrder_goods_hearder_linear)
        LinearLayout goods_header_linear;
        @BindView(R.id.receivingOrder_card_view)
        CardView cardView;
        //        @BindView(R.id.receivingOrder_image)
//        TextView receivingOrder_image;
//        @BindView(R.id.receivingOrder_orderId)
//        TextView receivingOrder_orderId;
        @BindView(R.id.receivingOrder_date)
        TextView receivingOrder_date;
        @BindView(R.id.receivingOrder_itemQty)
        TextView receivingOrder_itemQty;
//        @BindView(R.id.receivingOrder_actualQty)
//        TextView receivingOrder_actualQty;
//        @BindView(R.id.receivingOrder_estimateQty)
//        TextView receivingOrder_estimateQty;
        @BindView(R.id.receivingOrder_goods_display_listview)
        ListView goods_display_listview;
        @BindView(R.id.receivingOrder_linear_detail)
        LinearLayout receivingOrder_linear_detail;
        @BindView(R.id.receivingOrder_linear_delete)
        LinearLayout receivingOrder_linear_delete;
        @BindView(R.id.receivingOrder_linear_edit)
        LinearLayout receivingOrder_linear_edit;
        @BindView(R.id.receivingOrder_image)
        com.github.siyamed.shapeimageview.RoundedImageView receivingOrder_image;

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

    private void refresh(String className,int rid){
        ViewPagerListener viewPagerListener = (NavigationAct)getContext();
        RefreshModel refreshModel = new RefreshModel();
        refreshModel.setClassName(className);
        refreshModel.setRid(rid);
        viewPagerListener.refresh(refreshModel);
    }
}
