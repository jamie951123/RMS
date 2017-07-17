package com.example.james.rms.Inventory.Tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.Swipe.SwipeUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Dao.InventoryDao;
import com.example.james.rms.Core.Dao.InventoryDaoImpl;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.SearchObject.SearchCombine;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Inventory.Adapter.InventoryItemListAdapter;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/4/27.
 */

public class Inventory_Item extends MyBaseFragment implements ViewPagerListener,SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener{
    @BindView(R.id.inventory_item_listview)
    ListView listView;
    @BindView(R.id.inventory_swipe)
    SwipeRefreshLayout laySwipe;

    List<InventoryModel> inventoryModels;

    private InventoryItemListAdapter inventoryItemListAdapter;

    //Dao
    InventoryDao inventoryDao;

    //
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
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
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
        }
        listView.setAdapter(inventoryItemListAdapter);
    }

}
