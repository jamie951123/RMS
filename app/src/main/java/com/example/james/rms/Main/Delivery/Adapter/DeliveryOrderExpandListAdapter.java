package com.example.james.rms.Main.Delivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.DeliveryOrderCombine;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Main.Inventory.Tab.InventoryContainer;
import com.example.james.rms.Operation.DeliveryAction.DeliveryIncrease;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 16/6/2017.
 */

public class DeliveryOrderExpandListAdapter extends MyExpandableListAdapter<DeliveryOrderModel> {

//    Dao
    private  DeliveryOrderDao deliveryOrderDao;

    public DeliveryOrderExpandListAdapter(Context context, List<DeliveryOrderModel> deliveryOrderModels) {
        super(context, deliveryOrderModels);
        deliveryOrderDao = new DeliveryOrderDaoImpl((AppCompatActivity)context);

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
//        holder.deliveryOrder_orderId.setText(ObjectUtil.longToString(deliveryOrderModel.getOrderId()));
        holder.deliveryOrder_date.setText(ObjectUtil.dateToString_OnlyDate(deliveryOrderModel.getStockOutDate()));
        holder.deliveryOrder_itemQty.setText(ObjectUtil.intToString(deliveryOrderModel.getDeliveryItem()==null?0:deliveryOrderModel.getDeliveryItem().size()));
        holder.deliveryOrder_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.output));

        //Sum
        DeliveryOrderGoodDisplayAdapter deliveryOrderGoodDisplayAdapter = new DeliveryOrderGoodDisplayAdapter(getContext(),sumDeliveryItem(deliveryOrderModel.getDeliveryItem()));
        holder.goods_display_listview.setAdapter(deliveryOrderGoodDisplayAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(holder.goods_display_listview);
        holder.goods_display_listview.setFocusable(false);

        if(deliveryOrderModel.getDeliveryItem() == null || (deliveryOrderModel.getDeliveryItem() != null && deliveryOrderModel.getDeliveryItem().isEmpty())){
            holder.goods_header_linear.setVisibility(View.GONE);
        }
//        GlideApp.with(getContext())
//                .load(R.drawable.output)
//                .error(R.drawable.question_purple)
//                .placeholder(R.drawable.question_purple)
//                .fitCenter()
//                .into(holder.deliveryOrder_image);

        holder.deliveryOrder_linear_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Movement Record
                String movementRecord_str = CommonFactory.movementFactory_str(NavigationController.class.getCanonicalName(),DeliveryIncrease.class.getCanonicalName(),R.id.nav_stockOut);

                //
                Intent intent = new Intent();
                intent.setClass(getContext(), DeliveryIncrease.class);
                DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);
                String deliveryOrder_json = deliveryOrderCombine.modelToJson(deliveryOrderModel);
                intent.putExtra(StartActivityForResultKey.deliveryOrderModel,deliveryOrder_json);
                intent.putExtra(StartActivityForResultKey.movementRecord,movementRecord_str);
                getContext().startActivity(intent);
                Log.d("asd","deliveryOrder_linear_edit");
            }
        });

        holder.deliveryOrder_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);

                try {
                    String delete_json = deliveryOrderCombine.modelToJson(deliveryOrderModel);
                    ResponseMessage responseMessage = deliveryOrderDao.delete(delete_json);
                    if (responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())) {
                        getFilteredData().remove(groupPosition);
                        notifyDataSetChanged();
                        refresh(InventoryContainer.class.getCanonicalName(),R.layout.inventort_item);
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
                    Long order_orderId = deliveryOrderModel.getOrderId();
                    if(order_orderId ==null) return;

                    if(deliveryOrderModel.getDeliveryItem() != null) {
                        NavigationController controller = (NavigationController) getContext();
                        ViewPagerListener viewPagerListener = (ViewPagerListener) controller;
                        viewPagerListener.transferViewPager(R.id.delivery_item,deliveryOrderModel);
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
        holder.receivingOrder_createDate.setText(ObjectUtil.dateToString(deliveryOrderModel.getCreateDate()));
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
    public boolean productNameMatch(DeliveryOrderModel deliveryOrderModel, String string,int position) {
        boolean isMatch = false;
        if(deliveryOrderModel.getDeliveryItem() != null ){
            for(DeliveryItemModel deliveryItemModel : deliveryOrderModel.getDeliveryItem()){
                if(deliveryItemModel.getReceivingItem() != null && deliveryItemModel.getReceivingItem().getProduct() != null && ObjectUtil.isNotNullEmpty(deliveryItemModel.getReceivingItem().getProduct().getProductName())){
                    isMatch = deliveryItemModel.getReceivingItem().getProduct().getProductName().contains(string.toUpperCase());
                    if(isMatch)break;
                }
            }
        }
        return isMatch;
    }

    @Override
    public boolean receivingRemarkMatch(DeliveryOrderModel deliveryOrderModel, String string) {
        if(ObjectUtil.isNullEmpty(deliveryOrderModel.getRemark()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        return  ObjectUtil.isNullEmpty(deliveryOrderModel.getRemark()) ? false : deliveryOrderModel.getRemark().toUpperCase().contains(string.toUpperCase());
    }

    static class GroupHolder {
        @BindView(R.id.deliveryOrder_goods_display_listview)
        ListView goods_display_listview;
        @BindView(R.id.deliveryOrder_goods_hearder_linear)
        LinearLayout goods_header_linear;
//        @BindView(R.id.deliveryOrder_orderId)
//        TextView deliveryOrder_orderId;
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

    private void refresh(String className,int rid){
        ViewPagerListener viewPagerListener = (NavigationController)getContext();
        RefreshModel refreshModel = new RefreshModel();
        refreshModel.setClassName(className);
        refreshModel.setRid(rid);
        viewPagerListener.refresh(refreshModel);
    }

        private List<DeliveryItemModel> sumDeliveryItem(List<DeliveryItemModel> deliveryItemModels){
        List<DeliveryItemModel> deliveryItemModelList = new ArrayList<>();
        LinkedHashMap<Long,DeliveryItemModel> hashMap = new LinkedHashMap<>();
        for(int i=0; i<deliveryItemModels.size();i++){
            Long productId = deliveryItemModels.get(i).getReceivingItem().getProductId();
            Integer latest_qty = deliveryItemModels.get(i).getItemQty()==null?0:deliveryItemModels.get(i).getItemQty();
            BigDecimal latest_w = deliveryItemModels.get(i).getItemGrossWeight()==null?new BigDecimal(0):deliveryItemModels.get(i).getItemGrossWeight();

            if(hashMap.containsKey(productId)){
                Integer orginal_qty = hashMap.get(productId).getItemQty();
                BigDecimal orginal_w = hashMap.get(productId).getItemGrossWeight();
                //QTY
                if(orginal_qty == null){
                    hashMap.get(productId).setItemQty(latest_qty);
                }else{
                    hashMap.get(productId).setItemQty(orginal_qty+latest_qty);
                }

                //W
                if(orginal_w == null){
                    hashMap.get(productId).setItemGrossWeight(latest_w);
                }else{
                    hashMap.get(productId).setItemGrossWeight(orginal_w.add(latest_w));
                }
            }else{
                hashMap.put(productId,deliveryItemModels.get(i));
            }
        }
        Log.v("asd","[Delivery_Item]-[sumDeliveryItem]-[LinkHashMap] :" + hashMap);
        for (Map.Entry<Long,DeliveryItemModel> entry: hashMap.entrySet()){
            Long productId = entry.getKey();
            DeliveryItemModel d = entry.getValue();
            deliveryItemModelList.add(d);
        }
        return deliveryItemModelList;
    }

}
