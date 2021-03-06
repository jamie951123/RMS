package com.example.james.rms.Main.Delivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.MyAdapter.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.DeliveryItemCombine;
import com.example.james.rms.Core.Combine.DeliveryOrderCombine;
import com.example.james.rms.Core.Dao.DeliveryItemDao;
import com.example.james.rms.Core.Dao.DeliveryItemDaoImpl;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Main.Delivery.DeliveryContainer;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Main.Inventory.Tab.InventoryContainer;
import com.example.james.rms.Operation.DeliveryAction.DeliveryIncrease;
import com.example.james.rms.R;
import com.github.siyamed.shapeimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JamesL on 6/29/2017.
 */

public class DeliveryItemExpandListAdapter extends MyExpandableListAdapter<DeliveryItemModel> {

    private DeliveryOrderModel deliveryOrderModel;
    //Dao
    private DeliveryItemDao deliveryItemDao;

    public DeliveryItemExpandListAdapter(Context context, DeliveryOrderModel deliveryOrderModel) {
        super(context, deliveryOrderModel.getDeliveryItem());
        this.deliveryOrderModel = deliveryOrderModel;
        deliveryItemDao = new DeliveryItemDaoImpl((AppCompatActivity)context);
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder viewHolder;
        final DeliveryItemModel deliveryItemModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.delivery_item_expendablelist_group, parent, false);
            viewHolder = new GroupHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
        viewHolder.deliveryItem_ProductCode.setText(deliveryItemModel.getReceivingItem().getProduct().getProductCode());
        viewHolder.deliveryItem_ProductName.setText(deliveryItemModel.getReceivingItem().getProduct().getProductName());
        viewHolder.deliveryItem_itemStockOutDate.setText(ObjectUtil.dateToString_OnlyDate(deliveryItemModel.getItemStockOutDate()));
        String w = ObjectUtil.bigDecimalToString(deliveryItemModel.getItemGrossWeight());
        String w_unit =  deliveryItemModel.getReceivingItem().getProduct().getWeightprofile()==null?"":deliveryItemModel.getReceivingItem().getProduct().getWeightprofile().getWeightUnit();;
        if(ObjectUtil.isNotNullEmpty(w)){
            viewHolder.deliveryItem_itemGrossWeight.setText(w);
            viewHolder.deliveryItem_itemGrossWeightUnit.setText(w_unit);
        }else{
            viewHolder.weight_empty.setText(getContext().getString(R.string.label_empty_value));
        }
        String qty = ObjectUtil.intToString(deliveryItemModel.getItemQty());
        String qty_unit = deliveryItemModel.getReceivingItem().getProduct().getQuantityProfile()==null?"":deliveryItemModel.getReceivingItem().getProduct().getQuantityProfile().getQuantityUnit();
        if(ObjectUtil.isNotNullEmpty(qty)) {
            viewHolder.deliveryItem_itemQuantity.setText(qty);
            viewHolder.deliveryItem_itemQuantityUnit.setText(qty_unit);
        }else{
            viewHolder.qty_empty.setText(getContext().getString(R.string.label_empty_value));
        }

        viewHolder.delivery_item_linear_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Log.d("asd","[DeliveryItemExpandListAdapter] : " + deliveryOrder_json);
            }
        });

        viewHolder.delivery_item_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryItemCombine deliveryItemCombine = new DeliveryItemCombine(DeliveryItemModel.class);
                String json = deliveryItemCombine.modelToJson(deliveryItemModel);
                ResponseMessage responseMessage = deliveryItemDao.delete(json);
                if (responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())) {
                    getFilteredData().remove(groupPosition);
                    notifyDataSetChanged();
                    refresh(DeliveryContainer.class.getCanonicalName(),R.layout.delivery_order);
                    refresh(InventoryContainer.class.getCanonicalName(),R.layout.inventort_item);
                }
                Log.d("asd", "[ReceivingItemExpandListAdapter]-responseMessage : " + responseMessage);
            }
        });
        return convertView;
    }


    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder viewHolder;
        DeliveryItemModel deliveryItemModel = getChild(groupPosition,childPosition);
        if(convertView == null){
            convertView = getLayoutInflater().inflate(R.layout.delivery_item_expendablelist_item, parent, false);
            viewHolder = new ChildHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ChildHolder) convertView.getTag();
        }
        viewHolder.deliveryItem_itemStatus.setText(deliveryItemModel.getItemStatus());
        viewHolder.deliveryItem_itemCreateDate.setText(ObjectUtil.dateToString(deliveryItemModel.getItemCreateDate()));
        viewHolder.deliveryItem_itemRemark.setText(deliveryItemModel.getItemRemark());
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }


    @Override
    public boolean productCodeMatch(DeliveryItemModel deliveryItemModel, String string) {
        if(ObjectUtil.isNullEmpty(deliveryItemModel.getReceivingItem().getProduct().getProductCode()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = deliveryItemModel.getReceivingItem().getProduct().getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(DeliveryItemModel deliveryItemModel, String string,int position) {
        boolean isMatch = false;
        if(deliveryItemModel.getReceivingItem() != null && deliveryItemModel.getReceivingItem().getProduct() != null && ObjectUtil.isNotNullEmpty(deliveryItemModel.getReceivingItem().getProduct().getProductName())){
            isMatch = deliveryItemModel.getReceivingItem().getProduct().getProductName().contains(string.toUpperCase());
        }
        return isMatch;
    }

    @Override
    public boolean receivingRemarkMatch(DeliveryItemModel deliveryItemModel, String string) {
        return false;
    }

    static class GroupHolder {
        @BindView(R.id.deliveryItem_image)
        RoundedImageView imageView;
        @BindView(R.id.deliveryItem_ProductCode)
        TextView deliveryItem_ProductCode;
        @BindView(R.id.deliveryItem_ProductName)
        TextView deliveryItem_ProductName;
        @BindView(R.id.deliveryItem_itemStockOutDate)
        TextView deliveryItem_itemStockOutDate;
        @BindView(R.id.deliveryItem_itemGrossWeight)
        TextView deliveryItem_itemGrossWeight;
        @BindView(R.id.deliveryItem_weight_empty)
        TextView weight_empty;
        @BindView(R.id.deliveryItem_itemGrossWeightUnit)
        TextView deliveryItem_itemGrossWeightUnit;
        @BindView(R.id.deliveryItem_qty_empty)
        TextView qty_empty;
        @BindView(R.id.deliveryItem_itemQuantity)
        TextView deliveryItem_itemQuantity;
        @BindView(R.id.deliveryItem_itemQuantityUnit)
        TextView deliveryItem_itemQuantityUnit;
        @BindView(R.id.delivery_item_linear_edit)
        LinearLayout delivery_item_linear_edit;
        @BindView(R.id.delivery_item_linear_delete)
        LinearLayout delivery_item_linear_delete;


        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.deliveryItem_itemStatus)
        TextView deliveryItem_itemStatus;
        @BindView(R.id.deliveryItem_itemCreateDate)
        TextView deliveryItem_itemCreateDate;
        @BindView(R.id.deliveryItem_itemRemark)
        TextView deliveryItem_itemRemark;

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

//End Class
}
