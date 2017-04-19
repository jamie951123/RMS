package com.example.james.rms.Receiving.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingOrderListAdapter;
import com.example.james.rms.Receiving.ReceivingCombine;
import com.example.james.rms.Receiving.Service.ReceivingService;
import com.example.james.rms.Receiving.Service.ReceivingServiceImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Receiving_order extends MyBaseFragment implements AdapterView.OnItemClickListener{

    @BindView(R.id.receiving_order_listView)
    ListView listView;

    private ReceivingCombine receivingCombine = new ReceivingCombine();
    //
    private ReceivingService receivingService = new ReceivingServiceImpl();
    //
    private ReceivingOrderListAdapter receivingOrderListAdapter;

    //Result
    List<ReceivingOrderModel> receivingOrderModels;
    List<ReceivingItemModel> receivingItemModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receiving_order,container , false);
        ButterKnife.bind(this,rootView);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        String combine_partyId = receivingCombine.combine_partyId(partyId);

        //HttpOK
        receivingOrderModels = receivingService.findReceivingOrderByPartyId(combine_partyId);
        receivingItemModels = receivingService.findReceivingItemByPartyId(combine_partyId);

        //listView
        if(receivingOrderModels != null) {
            receivingOrderListAdapter = new ReceivingOrderListAdapter(getActivity(), receivingOrderModels);
            listView.setAdapter(receivingOrderListAdapter);
            listView.setOnItemClickListener(this);
            listView.setDivider(null);
        }
        return rootView;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Long order_orderId = receivingOrderModels.get(position).getOrderId();
        if(order_orderId ==null) return;

        List<ReceivingItemModel> itemModels = new ArrayList<>();
        for(ReceivingItemModel items : receivingItemModels){
            Long item_orderId = items.getOrderId();
            if(order_orderId.equals(item_orderId))itemModels.add(items);
        }

        NavigationController controller = (NavigationController)getActivity();
        ViewPagerListener viewPagerListener = (ViewPagerListener)controller;
        viewPagerListener.transferViewPager(R.id.receiving_item,itemModels);
    }


    //SearchView
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(ObjectUtil.isNullEmpty(newText)){
            receivingOrderListAdapter.filterByRemark("");
        }else {
            receivingOrderListAdapter.filterByRemark(newText);
        }
        return true;
    }

    @Override
    public void transferViewPager(int rid, List models) {
    }


}
