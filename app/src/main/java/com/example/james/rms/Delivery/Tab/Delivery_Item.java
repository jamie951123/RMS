package com.example.james.rms.Delivery.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.Graphics.GenericChat;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Combine.DeliveryItemCombine;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Delivery.Adapter.DeliveryItemExpandListAdapter;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class Delivery_Item extends MyBaseFragment{

    @BindView(R.id.de_item_listview)
    AnimatedExpandableListView listView;
    @BindView(R.id.deliveryItem_HorbarChart)
    HorizontalBarChart horizontalBarChart;

    //Model
    private DeliveryOrderModel deliveryOrderModel;
    private List<DeliveryItemModel> deliveryItemModels;

    //Adapter
    private DeliveryItemExpandListAdapter deliveryItemExpandListAdapter;

    //
    private float spaceForBar = 1f;
    private List<String> theName;
    private List<BarEntry> barEntries ;
    //Preferences
    private MyPreferences myPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delivery_item,container , false);
        ButterKnife.bind(this,rootView);
        //Preferences
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.login_information);
        String partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //
        //partyId
        String combine_partyId = DeliveryItemCombine.combine_partyId(partyId);


//        pager.setPagingEnabled(false);
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(ObjectUtil.isNullEmpty(newText)){
            deliveryItemExpandListAdapter.filterByProductCode("");
        }else {
            deliveryItemExpandListAdapter.filterByProductCode(newText);
        }
        return true;
    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object model) {
        deliveryOrderModel = (DeliveryOrderModel) model;
        deliveryItemModels = deliveryOrderModel.getDeliveryItem();

        deliveryItemExpandListAdapter = new DeliveryItemExpandListAdapter(getActivity(), deliveryOrderModel);
        listView.setAdapter(deliveryItemExpandListAdapter);
        listView.setGroupIndicator(null);

        GenericChat genericChat = new GenericChat();
        getCharDateAndLabel();
        genericChat.horizontalBarChart(getActivity(),horizontalBarChart,barEntries,theName);
    }

    @Override
    public void refresh(RefreshModel refreshModel) {

    }

    public void getCharDateAndLabel(){
        barEntries = new ArrayList<>();
        theName = new ArrayList<>();
        for(int i = 0; i< deliveryItemModels.size(); i++){
            DeliveryItemModel deliveryItemModel = deliveryItemModels.get(i);
            String label = deliveryItemModel.getReceivingItem().getProduct()==null ? "":ObjectUtil.isNullEmpty(deliveryItemModel.getReceivingItem().getProduct().getProductName()) ? "":deliveryItemModel.getReceivingItem().getProduct().getProductName();
            BarEntry barEntry = new BarEntry (i*spaceForBar,
                    deliveryItemModel.getItemGrossWeight() == null ? 0 : deliveryItemModel.getItemGrossWeight().intValueExact(), label);
            barEntries.add(barEntry);
            theName.add(label);

        }
    }

}
