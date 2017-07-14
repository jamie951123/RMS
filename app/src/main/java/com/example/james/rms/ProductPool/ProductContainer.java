package com.example.james.rms.ProductPool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.Controller.MyViewPager;
import com.example.james.rms.ProductPool.Adapter.ProductContainer_Adapter;
import com.example.james.rms.ProductPool.Tab.ProductDetail;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 15/2/2017.
 */

public class ProductContainer extends MyBaseFragment{
    @BindView(R.id.product_container_pages)
    MyViewPager pager;

    private List<Fragment> fragments = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_container, container, false);
        ButterKnife.bind(this, rootView);
        fragments.add(new ProductDetail());
//        fragments.add(new ProductAction());
        ProductContainer_Adapter productContainer_adapter =new ProductContainer_Adapter(getFragmentManager(), fragments);
        pager.setAdapter(productContainer_adapter);
//        pager.setPagingEnabled(false);
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Fragment fragment = fragments.get(pager.getCurrentItem());
        try{
            MyBaseFragment myBaseFragment = (MyBaseFragment)fragment;
            return myBaseFragment.onQueryTextChange(newText);
        }catch (ClassCastException e){
            return false;
        }
    }

    @Override
    public void transfersViewPager(int rid, List models) {
        Fragment fragment = fragments.get(pager.getCurrentItem()+1);
        switch (rid){
            case R.id.product_action:
                MyBaseFragment myBaseFragment = (MyBaseFragment) fragment;
                myBaseFragment.transferViewPager(rid,models);
                pager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void transferViewPager(int rid, Object models) {

    }
}
