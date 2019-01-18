package com.example.james.rms.main.receiving.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.common.graphic.GenericChart;
import com.example.james.rms.common.graphic.BarChatModel;
import com.example.james.rms.common.Library.AnimatedExpandableListView;
import com.example.james.rms.common.adapter.MyBaseFragment;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.model.ReceivingItemModel;
import com.example.james.rms.core.model.ReceivingOrderModel;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.R;
import com.example.james.rms.main.receiving.adapter.ReceivingItemExpandListAdapter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceivingItemFrag extends MyBaseFragment {

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
            receivingItemExpandListAdapter.filterByProductName("");
        }else {
            receivingItemExpandListAdapter.filterByProductName(newText);
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

        GenericChart genericChart = new GenericChart();
        genericChart.horizontalBarChart(getActivity(),horizontalBarChart,getCharDate());

    }

    @Override
    public void refresh(RefreshModel refreshModel) {

    }

    public BarChatModel getCharDate(){
        List<String> w_theName = new ArrayList<>();
        List<String> qty_theName = new ArrayList<>();

        List<BarEntry> w_barEntries = new ArrayList<>();
        List<BarEntry> qty_barEntries = new ArrayList<>();

        for(int i = 0; i< receivingItemModels.size(); i++){
            ReceivingItemModel receivingItemModel = receivingItemModels.get(i);
            String productName = receivingItemModel.getProduct() == null ? "" : receivingItemModel.getProduct().getProductName();
            String w_uit = "";
            String qty_unit="";
            if(receivingItemModel.getProduct().getWeightprofile() != null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getWeightprofile().getWeightUnit())){
                w_uit = "("+receivingItemModel.getProduct().getWeightprofile().getWeightUnit()+")";
            }
            if(receivingItemModel.getProduct().getQuantityProfile() != null && ObjectUtil.isNotNullEmpty(receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit())){
                qty_unit = "("+receivingItemModel.getProduct().getQuantityProfile().getQuantityUnit()+")";
            }


                //Weight
            BarEntry w_barEntry = new BarEntry (i*spaceForBar,
                    receivingItemModel.getItemGrossWeight() == null ? 0 : receivingItemModel.getItemGrossWeight().intValueExact());
            w_barEntries.add(w_barEntry);

            //QTY
            BarEntry qty_barEntry = new BarEntry (i*spaceForBar,
                    receivingItemModel.getItemQty() == null ? 0 : receivingItemModel.getItemQty().intValue());
            qty_barEntries.add(qty_barEntry);
//            w_theName.add(productName+w_uit);
//            qty_theName.add(productName+qty_unit);
            qty_theName.add(productName);
            w_theName.add(productName);


        }
        BarChatModel barChatModel = new BarChatModel();
        barChatModel.setWeight_name(w_theName);
        barChatModel.setWeight_value(w_barEntries);
        barChatModel.setQty_name(qty_theName);
        barChatModel.setQty_value(qty_barEntries);
        return barChatModel;
    }

//    public List<String> getLabel(){
//        List<String> theName = new ArrayList<>();
//        for(int i = 0; i< receivingItemModels.size(); i++){
//            ReceivingItemModel receivingItemModel = receivingItemModels.get(i);
//            theName.add(receivingItemModel.getProduct() == null ? "":ObjectUtil.isNullEmpty(receivingItemModel.getProduct().getProductCode()) ? "":receivingItemModel.getProduct().getProductCode());
//        }
//        return theName;
//    }


}
