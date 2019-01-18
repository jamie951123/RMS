package com.example.james.rms.main.inventory.tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.james.rms.common.util.ListViewUtil;
import com.example.james.rms.common.adapter.MyBaseFragment;
import com.example.james.rms.common.SharePreferences.MyPreferences;
import com.example.james.rms.constant.PreferencesKey;
import com.example.james.rms.common.util.SwipeUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.dao.InventoryDao;
import com.example.james.rms.core.dao.InventoryDaoImpl;
import com.example.james.rms.core.model.InventoryModel;
import com.example.james.rms.core.model.Status;
import com.example.james.rms.core.search_object.SearchCombine;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.main.inventory.adapter.InventoryItemListAdapter;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventoryItemFrag extends MyBaseFragment implements ViewPagerListener,SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener{
    @BindView(R.id.inventory_item_listview)
    ListView listView;
    @BindView(R.id.inventory_swipe)
    SwipeRefreshLayout laySwipe;

    List<InventoryModel> inventoryModels;

    private InventoryItemListAdapter inventoryItemListAdapter;

    //Dao
    InventoryDao inventoryDao;

    //MyPreferences
    private MyPreferences myPreferences;
    private String combine_partyIdAndStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventort_item,container , false);
        ButterKnife.bind(this,rootView);
        //Dao
        inventoryDao = new InventoryDaoImpl((AppCompatActivity) getActivity());
        //SetUp
        laySwipe.setOnRefreshListener(this);
        SwipeUtil.setColor(laySwipe);
        //Preferences
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.INSTANCE.getLogin_information());
        String partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        combine_partyIdAndStatus = SearchCombine.combine_partyIdAndStatus(partyId, Status.PROGRESS);
        getData();
        return rootView;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(ObjectUtil.isNullEmpty(newText)){
            inventoryItemListAdapter.filterByProductCode("");
        }else {
            inventoryItemListAdapter.filterByProductCode(newText);
        }
        return true;
    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object models) {

    }

    @Override
    public void refresh(RefreshModel refreshModel) {
        getData();
    }

    @Override
    public void onRefresh() {
        laySwipe.setRefreshing(true);
        getData();
        laySwipe.setRefreshing(false);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ListViewUtil.detectTop(event,listView,laySwipe);
        return false;
    }
    public void getData(){
        //Service Inventory
        inventoryModels = inventoryDao.findByPartyIdAndStatusOrderByProductIdAsc(combine_partyIdAndStatus);
        // this is data fro recycler view
        if(inventoryModels != null) {
            inventoryItemListAdapter = new InventoryItemListAdapter(getActivity(), inventoryModels);
            listView.setAdapter(inventoryItemListAdapter);
        }
    }

}
