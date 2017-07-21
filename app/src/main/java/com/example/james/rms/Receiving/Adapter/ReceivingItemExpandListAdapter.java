package com.example.james.rms.Receiving.Adapter;

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
import com.example.james.rms.CommonProfile.Util.ActivityUtil;
import com.example.james.rms.CommonProfile.Util.GsonUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.ReceivingItemCombine;
import com.example.james.rms.Core.Dao.ReceivingItemDao;
import com.example.james.rms.Core.Dao.ReceivingItemDaoImpl;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;
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
            viewHolder.receivingItem_itemGrossWeight.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight()));
            viewHolder.receivingItem_itemGrossWeightUnit.setText(receivingItemModel.getProduct().getWeightprofile() == null ? "" : receivingItemModel.getProduct().getWeightprofile().getWeightUnit());
            viewHolder.receivingItem_image.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
            viewHolder.receivingItem_itemQty.setText(ObjectUtil.intToString(receivingItemModel.getItemQty()));
            viewHolder.receivingItem_itemQtyUnit.setText(receivingItemModel.getProduct().getQuantityProfile()==null?"":receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit());
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
                        String movementRecord_str = CommonFactory.movementFactory_str(NavigationController.class.getCanonicalName(),ReceivingIncrease.class.getCanonicalName(),StartActivityForResultKey.navReceiving);
                        //
                        Intent intent = new Intent();
                        intent.setClass(getContext(), ReceivingIncrease.class);
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
    public boolean productNameMatch(ReceivingItemModel receivingItemModel, String string) {
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
        @BindView(R.id.receivingItem_itemGrossWeightUnit)
        TextView receivingItem_itemGrossWeightUnit;
        @BindView(R.id.receivingItem_itemQty)
        TextView receivingItem_itemQty;
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
}
