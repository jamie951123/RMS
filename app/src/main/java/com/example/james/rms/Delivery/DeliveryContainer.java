package com.example.james.rms.Delivery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.Controller.MyViewPager;
import com.example.james.rms.Delivery.Adapter.DeliveryContainer_adapter;
import com.example.james.rms.Delivery.Tab.Delivery_Item;
import com.example.james.rms.Delivery.Tab.Delivery_Order;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryContainer extends MyBaseFragment {

    @BindView(R.id.delivery_container_pages)
    MyViewPager pager;

    private List<Fragment> fragments = new ArrayList<>();
    private DeliveryContainer_adapter deliveryContainer_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delivery_container,container , false);
        ButterKnife.bind(this, rootView);
        fragments.add(new Delivery_Order());
        fragments.add(new Delivery_Item());
        deliveryContainer_adapter = new DeliveryContainer_adapter(getFragmentManager(),fragments);
        pager.setAdapter(deliveryContainer_adapter);
//        pager.setPagingEnabled(false);
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object model) {

    }
}
