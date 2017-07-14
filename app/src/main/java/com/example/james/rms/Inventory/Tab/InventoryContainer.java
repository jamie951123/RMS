package com.example.james.rms.Inventory.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.Controller.MyViewPager;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Inventory.Adapter.InventoryContainer_Adapter;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 16/3/2017.
 */

public class InventoryContainer extends MyBaseFragment implements ViewPagerListener{

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
        Fragment fragment = fragments.get(pager.getCurrentItem());
        try{
            MyBaseFragment myBaseFragment = (MyBaseFragment)fragment;
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
        switch (rid){
//            case R.id.inventory_item:
//                Log.d("asd","[InventoryContainer] --Nav -> InventoryContainer -> Inventory_Item");
//                fragments.add(new Inventory_Item());
//                fragments.add(new InventoryItem2());
//                ViewPagerListener viewPagerListener = (MyBaseFragment)fragments.get(0);
//                viewPagerListener.transferViewPager(rid,models);
//                break;

        }
    }

    @Override
    public void transferViewPager(int rid, Object models) {

    }
}
