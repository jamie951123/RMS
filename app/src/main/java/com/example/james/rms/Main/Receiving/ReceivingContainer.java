package com.example.james.rms.Main.Receiving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.Controller.CommunicateService.NavToRL;
import com.example.james.rms.Controller.MyViewPager;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.R;
import com.example.james.rms.Main.Receiving.Adapter.ReceivingContainer_Adapter;
import com.example.james.rms.Main.Receiving.Tab.Receiving_item;
import com.example.james.rms.Main.Receiving.Tab.Receiving_order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceivingContainer extends MyBaseFragment implements NavToRL {

    @BindView(R.id.receiving_container_pages)
    MyViewPager pager;

    private List<Fragment> fragments = new ArrayList<>();
    ReceivingContainer_Adapter receivingContainer_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receiving_container,container , false);
        ButterKnife.bind(this, rootView);
        fragments.add(new Receiving_order());
        fragments.add(new Receiving_item());
        receivingContainer_adapter = new ReceivingContainer_Adapter(getFragmentManager(),fragments);
        pager.setAdapter(receivingContainer_adapter);
        pager.setPagingEnabled(false);
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

    }

    @Override
    public void transferViewPager(int rid, Object model) {
        Fragment fragment = fragments.get(pager.getCurrentItem()+1);
        switch (rid){
            case R.id.receiving_item:
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
            case R.layout.receiving_order:
                fragment = fragments.get(0);
                break;
            case R.layout.receiving_item:
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
