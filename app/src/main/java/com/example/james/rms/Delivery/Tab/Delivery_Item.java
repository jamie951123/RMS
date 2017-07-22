package com.example.james.rms.Delivery.Tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.Graphics.GenericChat;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Util.DeepCopyUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Combine.DeliveryItemCombine;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Delivery.Adapter.DeliveryItemExpandListAdapter;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        //Sum Same Product
        DeliveryOrderModel sumDeliveryOrder = copyModel(deliveryOrderModel);
        sumDeliveryOrder.setDeliveryItem(sumDeliveryItem(sumDeliveryOrder.getDeliveryItem()));
        deliveryItemModels = sumDeliveryOrder.getDeliveryItem();
        //
        deliveryItemExpandListAdapter = new DeliveryItemExpandListAdapter(getActivity(), deliveryOrderModel,sumDeliveryOrder);
        listView.setAdapter(deliveryItemExpandListAdapter);
        listView.setGroupIndicator(null);

        GenericChat genericChat = new GenericChat();
        getCharDateAndLabel();
        genericChat.horizontalBarChart(getActivity(),horizontalBarChart,barEntries,theName);
    }

    private DeliveryOrderModel copyModel(DeliveryOrderModel deliveryOrderModel){
        DeliveryOrderModel newDo = new DeliveryOrderModel();

        List<DeliveryItemModel> newItem = new ArrayList<>();
        for(DeliveryItemModel item : deliveryOrderModel.newDeliveryOrderModel().getDeliveryItem()){
            newItem.add(item.newDeliveryItemModel());
        }
        newDo.setDeliveryItem(newItem);
        return newDo;
    }
    private List<DeliveryItemModel> sumDeliveryItem(List<DeliveryItemModel> deliveryItemModels){
        List<DeliveryItemModel> deliveryItemModelList = new ArrayList<>();
        LinkedHashMap<Long,DeliveryItemModel> hashMap = new LinkedHashMap<>();
        for(int i=0; i<deliveryItemModels.size();i++){
            Long productId = deliveryItemModels.get(i).getReceivingItem().getProductId();
            Integer latest_qty = deliveryItemModels.get(i).getItemQty()==null?0:deliveryItemModels.get(i).getItemQty();
            BigDecimal latest_w = deliveryItemModels.get(i).getItemGrossWeight()==null?new BigDecimal(0):deliveryItemModels.get(i).getItemGrossWeight();

            if(hashMap.containsKey(productId)){
                Integer orginal_qty = hashMap.get(productId).getItemQty();
                BigDecimal orginal_w = hashMap.get(productId).getItemGrossWeight();
                //QTY
                if(orginal_qty == null){
                    hashMap.get(productId).setItemQty(latest_qty);
                }else{
                    hashMap.get(productId).setItemQty(orginal_qty+latest_qty);
                }

                //W
                if(orginal_w == null){
                    hashMap.get(productId).setItemGrossWeight(latest_w);
                }else{
                    hashMap.get(productId).setItemGrossWeight(orginal_w.add(latest_w));
                }
            }else{
                hashMap.put(productId,deliveryItemModels.get(i));
            }
        }
        Log.v("asd","[Delivery_Item]-[sumDeliveryItem]-[LinkHashMap] :" + hashMap);
        for (Map.Entry<Long,DeliveryItemModel> entry: hashMap.entrySet()){
            Long productId = entry.getKey();
            DeliveryItemModel d = entry.getValue();
            deliveryItemModelList.add(d);
        }
        return deliveryItemModelList;
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
