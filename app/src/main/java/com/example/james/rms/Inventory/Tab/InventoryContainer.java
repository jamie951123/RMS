package com.example.james.rms.Inventory.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.Controller.MyViewPager;
import com.example.james.rms.Inventory.Adapter.InventoryContainer_Adapter;
import com.example.james.rms.Inventory.Tab.InventoryItem2;
import com.example.james.rms.Inventory.Tab.Inventory_Item;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 16/3/2017.
 */

public class InventoryContainer extends MyBaseFragment {

    @BindView(R.id.inventory_container_pages)
    MyViewPager pager;
    private InventoryContainer_Adapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventory_container,container , false);
        ButterKnife.bind(this,rootView);
        fragments.add(new Inventory_Item());
        fragments.add(new InventoryItem2());
        adapter = new InventoryContainer_Adapter(getActivity().getSupportFragmentManager(),fragments);
        pager.setAdapter(adapter);
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
    public void transferViewPager(int rid, List models) {

    }
}
