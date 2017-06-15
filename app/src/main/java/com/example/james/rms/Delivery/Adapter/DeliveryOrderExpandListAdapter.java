package com.example.james.rms.Delivery.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.Core.Model.DeliveryOrderModel;

import java.util.List;

/**
 * Created by Jamie on 16/6/2017.
 */

public class DeliveryOrderExpandListAdapter extends MyExpandableListAdapter<DeliveryOrderModel> {

    public DeliveryOrderExpandListAdapter(Context context, List<DeliveryOrderModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean productCodeMatch(DeliveryOrderModel deliveryOrderModel, String string) {
        return false;
    }

    @Override
    public boolean productNameMatch(DeliveryOrderModel deliveryOrderModel, String string) {
        return false;
    }

    @Override
    public boolean receivingRemarkMatch(DeliveryOrderModel deliveryOrderModel, String string) {
        return false;
    }
}
