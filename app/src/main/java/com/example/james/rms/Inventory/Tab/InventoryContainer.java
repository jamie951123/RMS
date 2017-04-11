package com.example.james.rms.Inventory.Tab;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.Inventory.Tab.Adapter.InventoryItemListAdapter;
import com.example.james.rms.Inventory.Tab.model.RecyclerItemModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 16/3/2017.
 */

public class InventoryContainer extends MyBaseFragment {

    @BindView(R.id.inventory_recycler)
    RecyclerView recycler;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventory_container,container , false);
        ButterKnife.bind(this,rootView);

        // this is data fro recycler view
        RecyclerItemModel itemsData[] = { new RecyclerItemModel("Help",R.drawable.circle_image),
                new RecyclerItemModel("Delete",R.drawable.add_white),
                new RecyclerItemModel("Cloud",R.drawable.fab_add),
                new RecyclerItemModel("Favorite",R.drawable.fingerprint_white),
                new RecyclerItemModel("Like",R.drawable.add_white),
                new RecyclerItemModel("Rating",R.drawable.fab_add)};

        // 2. set layoutManger
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 3. create an adapter
        InventoryItemListAdapter mAdapter = new InventoryItemListAdapter(itemsData);
        // 4. set adapter
        recycler.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recycler.setItemAnimator(new DefaultItemAnimator());
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
