package com.example.james.rms.main.delivery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.common.adapter.MyBaseFragment;
import com.example.james.rms.controller.NavBackPressed;
import com.example.james.rms.controller.MyViewPager;
import com.example.james.rms.main.delivery.adapter.DeliveryContainerAdapter;
import com.example.james.rms.main.delivery.tab.DeliveryItemFrag;
import com.example.james.rms.main.delivery.tab.DeliveryOrderFrag;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryContainerFrag extends MyBaseFragment implements NavBackPressed {

    @BindView(R.id.delivery_container_pages)
    MyViewPager pager;

    private List<Fragment> fragments = new ArrayList<>();
    private DeliveryContainerAdapter deliveryContainer_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delivery_container,container , false);
        ButterKnife.bind(this, rootView);
        fragments.add(new DeliveryOrderFrag());
        fragments.add(new DeliveryItemFrag());
        deliveryContainer_adapter = new DeliveryContainerAdapter(getFragmentManager(),fragments);
        pager.setAdapter(deliveryContainer_adapter);
        pager.setPagingEnabled(false);
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        MyBaseFragment myBaseFragment = (MyBaseFragment)fragments.get(pager.getCurrentItem());
        try{
            return myBaseFragment.onQueryTextChange(newText);
        }catch (ClassCastException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object model) {
        Fragment fragment = fragments.get(pager.getCurrentItem()+1);
        switch (rid){
            case R.id.delivery_item:
                MyBaseFragment myBaseFragment = (MyBaseFragment) fragment;
                myBaseFragment.transferViewPager(rid,model);
                pager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void refresh(RefreshModel refreshModel) {
        Fragment fragment = null;
        switch (refreshModel.getRid()){
            case R.layout.delivery_order:
                fragment = fragments.get(0);
                break;
            case R.layout.delivery_item:
                fragment = fragments.get(1);
                break;
        }
        if(fragment != null){
            MyBaseFragment myBaseFragment = (MyBaseFragment) fragment;
            myBaseFragment.refresh(refreshModel);
        }
    }

    @Override
    public boolean changeCurrentPage() {
        if(pager.getCurrentItem() ==0){
            return false;
        }
        pager.setCurrentItem(0);
        return true;
    }
}
