package com.example.james.rms.Receiving.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.Graphics.GenericChat;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingItemExpandListAdapter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Receiving_item extends MyBaseFragment {

    //    @BindView(R.id.receiving_item_listView)
//    ListView listView;
    @BindView(R.id.receiving_item_listView)
    AnimatedExpandableListView listView;
    @BindView(R.id.receivingItem_HorbarChart)
    HorizontalBarChart horizontalBarChart;

    private ReceivingOrderModel receivingOrderModel;
    private List<ReceivingItemModel> receivingItemModels;
    //    private ReceivingItemListAdapter receivingItemListAdapter;
    private ReceivingItemExpandListAdapter receivingItemExpandListAdapter;

    private float spaceForBar = 1f;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receiving_item,container , false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!ObjectUtil.isNotNullEmpty(newText)){
            receivingItemExpandListAdapter.filterByProductCode("");
        }else {
            receivingItemExpandListAdapter.filterByProductCode(newText);
        }
        return true;
    }


    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object model) {
        receivingOrderModel = (ReceivingOrderModel) model;
        receivingItemModels = receivingOrderModel.getReceivingItem();

        receivingItemExpandListAdapter = new ReceivingItemExpandListAdapter(getActivity(), receivingOrderModel);
        listView.setAdapter(receivingItemExpandListAdapter);
        listView.setGroupIndicator(null);

//        listView.setDivider(null);

        GenericChat genericChat = new GenericChat();
        genericChat.horizontalBarChart(getActivity(),horizontalBarChart,getCharDate(),getLabel());

    }

    public List<BarEntry> getCharDate(){
        List<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i< receivingItemModels.size(); i++){
            ReceivingItemModel receivingItemModel = receivingItemModels.get(i);
            BarEntry barEntry = new BarEntry (i*spaceForBar,
                    receivingItemModel.getItemGrossWeight() == null ? 0 : receivingItemModel.getItemGrossWeight().intValueExact(), receivingItemModel.getProduct() == null ? "" : receivingItemModel.getProduct().getProductCode());
            barEntries.add(barEntry);
        }
        return barEntries;
    }

    public List<String> getLabel(){
        List<String> theName = new ArrayList<>();
        for(int i = 0; i< receivingItemModels.size(); i++){
            ReceivingItemModel receivingItemModel = receivingItemModels.get(i);
            theName.add(receivingItemModel.getProduct() == null ? "":ObjectUtil.isNullEmpty(receivingItemModel.getProduct().getProductCode()) ? "":receivingItemModel.getProduct().getProductCode());
        }
        return theName;
    }
}
