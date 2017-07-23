//package com.example.james.rms.Main.Receiving.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.james.rms.CommonProfile.MyAdapter.MyBaseAdapter;
//import com.example.james.rms.CommonProfile.Util.ObjectUtil;
//import com.example.james.rms.Core.Model.ReceivingOrderModel;
//import com.example.james.rms.R;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by james on 1/3/2017.
// */
//
//public class ReceivingOrderListAdapter extends MyBaseAdapter<ReceivingOrderModel> {
//
//    public ReceivingOrderListAdapter(Context context, List<ReceivingOrderModel> dataArrayList) {
//        super(context, dataArrayList);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(viewHolder != null){
//            viewHolder = (ViewHolder)convertView.getTag();
//        }else{
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.receiving_order_item,parent,false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//        viewHolder.receivingOrder_orderId.setText(ObjectUtil.longToString(getItem(position).getOrderId()));
//        viewHolder.receivingOrder_status.setText(getItem(position).getStatus());
//        viewHolder.receivingOrder_date.setText(ObjectUtil.dateToString(getItem(position).getReceivingDate()));
//        viewHolder.receivingOrder_createDate.setText(ObjectUtil.dateToString(getItem(position).getCreateDate()));
//        viewHolder.receivingOrder_closeDate.setText(ObjectUtil.dateToString(getItem(position).getCloseDate()));
//        viewHolder.receivingOrder_remark.setText(getItem(position).getRemark());
//        viewHolder.receivingOrder_actualQty.setText(ObjectUtil.intToString(getItem(position).getActualQty()));
//        viewHolder.receivingOrder_estimateQty.setText(ObjectUtil.intToString(getItem(position).getEstimateQty()));
//        return convertView;
//    }
//
//    @Override
//    public boolean productCodeMatch(ReceivingOrderModel receivingOrderModel, String string) {
//        return false;
//    }
//
//    @Override
//    public boolean productNameMatch(ReceivingOrderModel receivingOrderModel, String string) {
//        return false;
//    }
//
//    @Override
//    public boolean receivingRemarkMatch(ReceivingOrderModel receivingOrderModel, String string) {
//        return  ObjectUtil.isNullEmpty(receivingOrderModel.getRemark()) ? false : receivingOrderModel.getRemark().toUpperCase().contains(string.toUpperCase());
//    }
//
//    static class ViewHolder{
//        @BindView(R.id.receivingOrder_orderId)
//        TextView receivingOrder_orderId;
//        @BindView(R.id.receivingOrder_status)
//        TextView receivingOrder_status;
//        @BindView(R.id.receivingOrder_date)
//        TextView receivingOrder_date;
//        @BindView(R.id.receivingOrder_createDate)
//        TextView receivingOrder_createDate;
//        @BindView(R.id.receivingOrder_closeDate)
//        TextView receivingOrder_closeDate;
//        @BindView(R.id.receivingOrder_remark)
//        TextView receivingOrder_remark;
//        @BindView(R.id.receivingOrder_actualQty)
//        TextView receivingOrder_actualQty;
//        @BindView(R.id.receivingOrder_estimateQty)
//        TextView receivingOrder_estimateQty;
//
//
//
//        public ViewHolder(View view) {
//            ButterKnife.bind(this,view);
//        }
//    }
//}
