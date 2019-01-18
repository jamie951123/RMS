package com.example.james.rms.main.receiving.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.common.CommonFactory;
import com.example.james.rms.common.util.GsonUtil;
import com.example.james.rms.common.adapter.MyExpandableListAdapter;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.common.ResponseStatus;
import com.example.james.rms.common.StartActivityForResultKey;
import com.example.james.rms.controller.NavigationAct;
import com.example.james.rms.core.combine.ReceivingItemCombine;
import com.example.james.rms.core.dao.ReceivingItemDao;
import com.example.james.rms.core.dao.ReceivingItemDaoImpl;
import com.example.james.rms.core.model.ReceivingItemModel;
import com.example.james.rms.core.model.ReceivingOrderModel;
import com.example.james.rms.core.model.ResponseMessage;
import com.example.james.rms.main.delivery.DeliveryContainerFrag;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.main.inventory.tab.InventoryContainerFrag;
import com.example.james.rms.main.receiving.ReceivingFrag;
import com.example.james.rms.operation.receiving_action.ReceivingIncreaseAct;
import com.example.james.rms.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/29.
 */

public class ReceivingItemExpandListAdapter extends MyExpandableListAdapter<ReceivingItemModel> {

    private ReceivingOrderModel receivingOrderModel;
    //Dao
    ReceivingItemDao receivingItemDao;

    public ReceivingItemExpandListAdapter(Context context, ReceivingOrderModel receivingOrderModel) {
        super(context, receivingOrderModel.getReceivingItem());
        this.receivingOrderModel = receivingOrderModel;
        receivingItemDao = new ReceivingItemDaoImpl((AppCompatActivity)context);
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder viewHolder;
        ReceivingItemModel receivingItemModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_item_expendablelist_group, parent, false);
            viewHolder = new GroupHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupHolder) convertView.getTag();
        }
        if(receivingItemModel != null && receivingItemModel.getProduct() != null) {
            viewHolder.receivingItem_ProductCode.setText(receivingItemModel.getProduct().getProductCode());
            viewHolder.receivingItem_ProductName.setText(receivingItemModel.getProduct().getProductName());
            viewHolder.receivingItem_itemReceivingDate.setText(ObjectUtil.dateToString_OnlyDate(receivingItemModel.getItemReceivingDate()));
            viewHolder.receivingItem_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));

            String w = ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight());
            String w_unit =  receivingItemModel.getProduct().getWeightprofile() == null ? "" : receivingItemModel.getProduct().getWeightprofile().getWeightUnit();
            if(ObjectUtil.isNotNullEmpty(w)){
                viewHolder.receivingItem_itemGrossWeight.setText(w);
                viewHolder.receivingItem_itemGrossWeightUnit.setText(w_unit);
            }else{
                viewHolder.weight_empty.setText(getContext().getString(R.string.label_empty_value));
            }

            String qty = ObjectUtil.intToString(receivingItemModel.getItemQty());
            String qty_unit = receivingItemModel.getProduct().getQuantityProfile()==null?"":receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit();
            if(ObjectUtil.isNotNullEmpty(qty)) {
                viewHolder.receivingItem_itemQty.setText(qty);
                viewHolder.receivingItem_itemQtyUnit.setText(qty_unit);
            }else{
                viewHolder.qty_empty.setText(getContext().getString(R.string.label_empty_value));
            }

//            GlideApp.with(getContext())
//                    .load(R.drawable.mailbox_black)
//                    .error(R.drawable.question_purple)
//                    .placeholder(R.drawable.question_purple)
//                    .fitCenter()
//                    .into(viewHolder.receivingItem_image);

            viewHolder.receivingItem_linear_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String receivingOrder_json = null;
                    try{
                        Gson gson = GsonUtil.toJson();
                        receivingOrder_json = gson.toJson(receivingOrderModel,ReceivingOrderModel.class);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(ObjectUtil.isNotNullEmpty(receivingOrder_json)) {
                        //MovementRecord
                        String movementRecord_str = CommonFactory.movementFactory_str(NavigationAct.class.getCanonicalName(), ReceivingIncreaseAct.class.getCanonicalName(),R.id.nav_receiving);
                        //
                        Intent intent = new Intent();
                        intent.setClass(getContext(), ReceivingIncreaseAct.class);
                        intent.putExtra(StartActivityForResultKey.receivingOrderModel,receivingOrder_json);
                        intent.putExtra(StartActivityForResultKey.movementRecord,movementRecord_str);
                        getContext().startActivity(intent);
                        getContext().startActivity(intent);
                    }
                    Log.v("asd", "linear_edit");
                }
            });

            viewHolder.receivingItem_linear_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReceivingItemCombine receivingItemCombine = new ReceivingItemCombine(ReceivingItemModel.class);
                    String receivingItem_json = receivingItemCombine.modelToJson(getFilteredData().get(groupPosition));
                    ResponseMessage responseMessage = receivingItemDao.delete(receivingItem_json);
                    if (responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())) {
                        getFilteredData().remove(groupPosition);
                        notifyDataSetChanged();
                        refresh(ReceivingFrag.class.getCanonicalName(),R.layout.receiving_order);
                        refresh(DeliveryContainerFrag.class.getCanonicalName(),R.layout.delivery_order);
                        refresh(InventoryContainerFrag.class.getCanonicalName(),R.layout.inventort_item);
                    }
                    Log.d("asd", "[ReceivingItemExpandListAdapter]-responseMessage : " + responseMessage);
                }
            });
        }else{
            Log.d("asd","[ReceivingItemExpandListAdapter]-[ReceivingItemModel]-[Error] : ReceivingItemModel is null");
        }
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder viewHolder;
        ReceivingItemModel receivingItemModel = getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.receiving_item_expendablelist_item, parent, false);
            viewHolder = new ChildHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildHolder) convertView.getTag();
        }
        viewHolder.receivingItem_itemStatus.setText(receivingItemModel.getItemStatus());
        viewHolder.receivingItem_itemCreateDate.setText(ObjectUtil.dateToString(receivingItemModel.getItemCreateDate()));
        viewHolder.receivingItem_itemRemark.setText(receivingItemModel.getItemRemark());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1 ;
    }

    @Override
    public boolean productCodeMatch(ReceivingItemModel receivingItemModel, String string) {
        if(ObjectUtil.isNullEmpty(receivingItemModel.getProduct().getProductCode()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = receivingItemModel.getProduct().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ReceivingItemModel receivingItemModel, String string, int position) {
        if(ObjectUtil.isNullEmpty(receivingItemModel.getProduct().getProductName()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = receivingItemModel.getProduct().getProductName().toUpperCase().contains(string.toUpperCase());
        return result;
    }


    @Override
    public boolean receivingRemarkMatch(ReceivingItemModel receivingItemModel, String string) {
        if(ObjectUtil.isNullEmpty(receivingItemModel.getItemRemark()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        return  ObjectUtil.isNullEmpty(receivingItemModel.getItemRemark()) ? false : receivingItemModel.getItemRemark().toUpperCase().contains(string.toUpperCase());
    }


    static class GroupHolder {
        @BindView(R.id.receivingItem_ProductCode)
        TextView receivingItem_ProductCode;
        @BindView(R.id.receivingItem_ProductName)
        TextView receivingItem_ProductName;
        @BindView(R.id.receivingItem_itemReceivingDate)
        TextView receivingItem_itemReceivingDate;
        @BindView(R.id.receivingItem_itemGrossWeight)
        TextView receivingItem_itemGrossWeight;
        @BindView(R.id.receivingItem_weight_empty)
        TextView weight_empty;
        @BindView(R.id.receivingItem_itemGrossWeightUnit)
        TextView receivingItem_itemGrossWeightUnit;
        @BindView(R.id.receivingItem_itemQty)
        TextView receivingItem_itemQty;
        @BindView(R.id.receivingItem_qty_empty)
        TextView qty_empty;
        @BindView(R.id.receivingItem_itemQtyUnit)
        TextView receivingItem_itemQtyUnit;
        @BindView(R.id.receiving_item_linear_edit)
        LinearLayout receivingItem_linear_edit;
        @BindView(R.id.receiving_item_linear_delete)
        LinearLayout receivingItem_linear_delete;
        @BindView(R.id.receivingItem_image)
        com.github.siyamed.shapeimageview.RoundedImageView receivingItem_image;

        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.receivingItem_itemStatus)
        TextView receivingItem_itemStatus;
        @BindView(R.id.receivingItem_itemCreateDate)
        TextView receivingItem_itemCreateDate;
        @BindView(R.id.receivingItem_itemRemark)
        TextView receivingItem_itemRemark;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    private void refresh(String className ,int rid){
        ViewPagerListener viewPagerListener = (NavigationAct)getContext();
        RefreshModel refreshModel = new RefreshModel();
        refreshModel.setClassName(className);
        refreshModel.setRid(rid);
        viewPagerListener.refresh(refreshModel);
    }
}
