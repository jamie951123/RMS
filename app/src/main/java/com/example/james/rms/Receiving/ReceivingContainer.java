package com.example.james.rms.Receiving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.Controller.CommunicateService.NavToRL;
import com.example.james.rms.Controller.MyViewPager;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingContainer_Adapter;
import com.example.james.rms.Receiving.Tab.Receiving_item;
import com.example.james.rms.Receiving.Tab.Receiving_order;

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
    public boolean changeCurrentPage() {
        if(pager.getCurrentItem() ==0){
            return false;
        }
        pager.setCurrentItem(0);
        return true;
    }
}
