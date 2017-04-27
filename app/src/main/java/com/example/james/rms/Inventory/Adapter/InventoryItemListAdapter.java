package com.example.james.rms.Inventory.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Inventory.model.RecyclerItemModel;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingItemListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        viewHolder.textView.setText(getItem(position).getInventoryId().toString());
        return convertView;
    }

    @Override
    public boolean productCodeMatch(InventoryModel inventoryModel, String string) {
        return false;
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
        @BindView(R.id.inv_text)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
