package com.example.james.rms.Main.Receiving.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.ReceivingOrderCombine;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Main.Delivery.DeliveryContainer;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Main.Inventory.Tab.InventoryContainer;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;
import com.example.james.rms.R;
import com.google.gson.Gson;

import java.util.ArrayList;
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
                    String movementRecord_str = CommonFactory.movementFactory_str(NavigationController.class.getCanonicalName(),ReceivingIncrease.class.getCanonicalName(),R.id.nav_receiving);
                    //
                    Intent intent = new Intent();
                    intent.setClass(getContext(), ReceivingIncrease.class);
                    intent.putExtra(StartActivityForResultKey.receivingOrderModel,receivingOrder_json);
                    intent.putExtra(StartActivityForResultKey.movementRecord,movementRecord_str);
                    getContext().startActivity(intent);
                }
            }
        });
        holder.receivingOrder_linear_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext() instanceof NavigationController){
                    Long order_orderId = receivingOrderModel.getOrderId();
                    if(order_orderId ==null) return;

                    if(getFilteredData().get(groupPosition).getReceivingItem() != null) {
                        NavigationController controller = (NavigationController) getContext();
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
                        refresh(DeliveryContainer.class.getCanonicalName(),R.layout.delivery_order);
                        refresh(InventoryContainer.class.getCanonicalName(),R.layout.inventort_item);
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
        ViewPagerListener viewPagerListener = (NavigationController)getContext();
        RefreshModel refreshModel = new RefreshModel();
        refreshModel.setClassName(className);
        refreshModel.setRid(rid);
        viewPagerListener.refresh(refreshModel);
    }
}
