package com.example.james.rms.Main.Receiving.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 23/7/2017.
 */

public class ReceivingOrderGoodDisplayAdapter extends MyBaseAdapter<ReceivingItemModel> {


    public ReceivingOrderGoodDisplayAdapter(Context context, List<ReceivingItemModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        ReceivingItemModel receivingItemModel = getItem(position);
        if(viewHolder != null){
            viewHolder = (ViewHolder)convertView.getTag();
        }else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_order_good_display_listview, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.name.setText(receivingItemModel.getProduct().getProductName());

        String qty = ObjectUtil.intToString(receivingItemModel.getItemQty());
        String qty_unit = "";
        if(receivingItemModel.getProduct().getQuantityProfile()!= null) qty_unit = receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit();
        if(ObjectUtil.isNotNullEmpty(qty)){
            viewHolder.qty.setText(ObjectUtil.intToString(receivingItemModel.getItemQty())+qty_unit);
        }else{
            viewHolder.qty.setText(getContext().getString(R.string.label_empty_value));
        }
        String weight = ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight());
        String w_unit = "";
        if(receivingItemModel.getProduct().getWeightprofile()!= null) w_unit = receivingItemModel.getProduct().getWeightprofile().getWeightUnit();
        if(ObjectUtil.isNotNullEmpty(weight)){
            viewHolder.w.setText(ObjectUtil.bigDecimalToString(receivingItemModel.getItemGrossWeight())+w_unit);
        }else{
            viewHolder.w.setText(getContext().getString(R.string.label_empty_value));
        }
        return convertView;
    }

//    @Override
//    public boolean isEnabled(int position) {
//        return false;
//    }

    @Override
    public boolean productCodeMatch(ReceivingItemModel receivingItemModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(ReceivingItemModel receivingItemModel, String string,int position) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(ReceivingItemModel receivingItemModel, String string) {
        return false;
    }

    class ViewHolder{
        @BindView(R.id.ro_goods_display_name)
        TextView name;
        @BindView(R.id.ro_goods_display_qty)
        TextView qty;
        @BindView(R.id.ro_goods_display_w)
        TextView w;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
    }
    }
}
