package com.example.james.rms.Inventory.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/4/28.
 */

public class InventoryItem2  extends MyBaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventory_item2,container , false);
        ButterKnife.bind(this,rootView);

        // this is data fro recycler view
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
