package com.example.james.rms.Inventory.Adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by james on 16/3/2017.
 */

public class InventoryItemListAdapter  extends MyBaseAdapter<InventoryModel>{

    public InventoryItemListAdapter(Context context, List<InventoryModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InventoryItemListAdapter.ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (InventoryItemListAdapter.ViewHolder)convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inventory_item_listitem,parent,false);
            viewHolder = new InventoryItemListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        BigDecimal itemGw = getItem(position).getTotalGrossWeight();
        String itemGwUnit = getItem(position).getWeightUnit();

        Integer itemQty = getItem(position).getTotalQty();
        String itemQtyUnit = getItem(position).getQuantityUnit();

        viewHolder.productCode.setText(getItem(position).getProductCode());
        viewHolder.productName.setText(getItem(position).getProductName());
        viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.inventory));
        viewHolder.qty.setText(itemQty==null?"":itemQty.toString()+itemQtyUnit);
        viewHolder.gw.setText(itemGw==null?"":itemGw.toString()+itemGwUnit);
        String item_title = getContext().getString(R.string.label_storage) +"("+(position+1)+")";
        viewHolder.title.setText(item_title);
        return convertView;
    }

    @Override
    public boolean productCodeMatch(InventoryModel inventoryModel, String string) {
        if(ObjectUtil.isNullEmpty(inventoryModel.getProductCode()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = inventoryModel.getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(InventoryModel inventoryModel, String string) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(InventoryModel inventoryModel, String string) {
        return false;
    }

    static class ViewHolder{
        @BindView(R.id.inventory_item_icon)
        CircleImageView icon;
        @BindView(R.id.inventory_item_productCode)
        TextView productCode;
        @BindView(R.id.inventory_item_productName)
        TextView productName;
        @BindView(R.id.inventory_item_qty)
        TextView qty;
        @BindView(R.id.inventory_item_gw)
        TextView gw;
        @BindView(R.id.inventory_item_title)
        TextView title;


        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
