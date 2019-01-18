package com.example.james.rms.main.inventory.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.common.adapter.MyBaseFragment;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/4/28.
 */

public class InventoryItem2Frag extends MyBaseFragment {
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
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object models) {

    }

    @Override
    public void refresh(RefreshModel refreshModel) {

    }
}
