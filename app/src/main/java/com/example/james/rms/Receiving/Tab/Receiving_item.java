package com.example.james.rms.Receiving.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.Graphics.GenericChat;
import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Receiving.Model.ReceivingItemModel;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingItemListAdapter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Receiving_item extends MyBaseFragment {

    @BindView(R.id.receiving_item_listView)
    ListView listView;

    @BindView(R.id.receivingItem_HorbarChart)
    HorizontalBarChart horizontalBarChart;

    private List<ReceivingItemModel> receivingItemModels;
    private ReceivingItemListAdapter receivingItemListAdapter;

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
            receivingItemListAdapter.filterByRemark("");
        }else {
            receivingItemListAdapter.filterByRemark(newText);
        }
        return true;
    }


    @Override
    public void transferViewPager(int rid, List models) {
        receivingItemModels = new ArrayList<>(models);
        receivingItemListAdapter = new ReceivingItemListAdapter(getActivity(), receivingItemModels);
        listView.setAdapter(receivingItemListAdapter);
        listView.setDivider(null);

        GenericChat genericChat = new GenericChat();
        genericChat.horizontalBarChart(getActivity(),horizontalBarChart,getCharDate(),getLabel());

    }

    public List<BarEntry> getCharDate(){
        List<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i< receivingItemModels.size(); i++){
            ReceivingItemModel receivingItemModel = receivingItemModels.get(i);
            BarEntry barEntry = new BarEntry (i*spaceForBar,
                    receivingItemModel.getItemGrossWeight().intValueExact(), receivingItemModel.getProductModel().getProductCode());
            barEntries.add(barEntry);
        }
        return barEntries;
    }

    public List<String> getLabel(){
        List<String> theName = new ArrayList<>();
        for(int i = 0; i< receivingItemModels.size(); i++){
            ReceivingItemModel receivingItemModel = receivingItemModels.get(i);
            theName.add(receivingItemModel.getProductModel().getProductCode());
        }
        return theName;
    }
}
