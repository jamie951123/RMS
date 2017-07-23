package com.example.james.rms.Main.Delivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseAdapter;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Main.Receiving.Adapter.ReceivingOrderGoodDisplayAdapter;
import com.example.james.rms.R;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 24/7/2017.
 */

public class DeliveryOrderGoodDisplayAdapter extends MyBaseAdapter<DeliveryItemModel> {

    public DeliveryOrderGoodDisplayAdapter(Context context, List<DeliveryItemModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        DeliveryItemModel deliveryItemModel = getItem(position);
        if(convertView != null){
            viewHolder = (ViewHolder)convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.delivery_order_good_display_listview,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.name.setText(deliveryItemModel.getReceivingItem().getProduct().getProductName());

        String qty = ObjectUtil.intToString(deliveryItemModel.getItemQty());
        String qty_unit = "";
        if(deliveryItemModel.getReceivingItem().getProduct().getQuantityProfile()!= null)qty_unit = deliveryItemModel.getReceivingItem().getProduct().getQuantityProfile().getQuantityUnit();
        if(ObjectUtil.isNotNullEmpty(qty)){
            viewHolder.qty.setText(ObjectUtil.intToString(deliveryItemModel.getItemQty())+qty_unit);
        }else{
            viewHolder.qty.setText(getContext().getString(R.string.label_empty_value));
        }

        String weight = ObjectUtil.bigDecimalToString(deliveryItemModel.getItemGrossWeight());
        String w_unit = "";
        if(deliveryItemModel.getReceivingItem().getProduct().getWeightprofile()!= null) w_unit = deliveryItemModel.getReceivingItem().getProduct().getWeightprofile().getWeightUnit();
        if(ObjectUtil.isNotNullEmpty(weight)){
            viewHolder.w.setText(ObjectUtil.bigDecimalToString(deliveryItemModel.getItemGrossWeight())+w_unit);
        }else{
            viewHolder.w.setText(getContext().getString(R.string.label_empty_value));
        }
        return convertView;
    }

    @Override
    public boolean productCodeMatch(DeliveryItemModel deliveryItemModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(DeliveryItemModel deliveryItemModel, String string,int position) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(DeliveryItemModel deliveryItemModel, String string) {
        return false;
    }

    class ViewHolder{
        @BindView(R.id.do_goods_display_name)
        TextView name;
        @BindView(R.id.do_goods_display_qty)
        TextView qty;
        @BindView(R.id.do_goods_display_w)
        TextView w;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
