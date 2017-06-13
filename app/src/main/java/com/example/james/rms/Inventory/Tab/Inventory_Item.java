package com.example.james.rms.Inventory.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Dao.InventorySumDao;
import com.example.james.rms.Core.Dao.InventorySumDaoImpl;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.InventorySumModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.SearchObject.SearchCombine;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Inventory.Adapter.InventoryItemListAdapter;
import com.example.james.rms.Inventory.InventoryCombine;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/4/27.
 */

public class Inventory_Item extends MyBaseFragment implements ViewPagerListener{
    @BindView(R.id.inventory_item_listview)
    ListView listView;

    List<InventoryModel> inventoryModels;
    List<InventorySumModel> inventorySumModels;

    private InventoryItemListAdapter inventoryItemListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventort_item,container , false);
        ButterKnife.bind(this,rootView);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        String combine_partyIdAndStatus = SearchCombine.combine_partyIdAndStatus(partyId, Status.PROGRESS);
        //Service Inventory
//        InventoryDao inventoryDao = new InventoryDaoImpl();
//        inventoryModels = inventoryDao.findByPartyIdAndStatus(combine_partyIdAndStatus);
        //Service InventorySum
        InventorySumDao inventorySumDao = new InventorySumDaoImpl();
        inventorySumModels = inventorySumDao.findByPartyIdAndStatusOrderByProductId(combine_partyIdAndStatus);
        // this is data fro recycler view
        if(inventorySumModels != null) {
            inventoryItemListAdapter = new InventoryItemListAdapter(getActivity(), inventorySumModels);
        }
        listView.setAdapter(inventoryItemListAdapter);
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
    public void transferViewPager(int rid, List models) {

    }
}
