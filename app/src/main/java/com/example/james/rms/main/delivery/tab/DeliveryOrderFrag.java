package com.example.james.rms.main.delivery.tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.common.Library.AnimatedExpandableListView;
import com.example.james.rms.common.util.ListViewUtil;
import com.example.james.rms.common.adapter.MyBaseFragment;
import com.example.james.rms.common.SharePreferences.MyPreferences;
import com.example.james.rms.constant.PreferencesKey;
import com.example.james.rms.common.util.SwipeUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.combine.DeliveryOrderSearchCombine;
import com.example.james.rms.core.dao.DeliveryOrderDao;
import com.example.james.rms.core.dao.DeliveryOrderDaoImpl;
import com.example.james.rms.core.model.DeliveryOrderModel;
import com.example.james.rms.core.model.Status;
import com.example.james.rms.main.delivery.adapter.DeliveryOrderExpandListAdapter;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryOrderFrag extends MyBaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener {

    @BindView(R.id.de_order_listview)
    AnimatedExpandableListView listView;
    @BindView(R.id.delivery_order_swipe)
    SwipeRefreshLayout laySwipe;

    //Model
    private List<DeliveryOrderModel> deliveryOrderModels;

    //Adapter
    private DeliveryOrderExpandListAdapter deliveryOrderExpandListAdapter;

    //Dao
    private DeliveryOrderDao deliveryOrderDao;

    //MyPreferences
    private MyPreferences myPreferences;
    private String combine_partyIdAndStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delivery_order,container , false);
        ButterKnife.bind(this,rootView);
        //Dao
        deliveryOrderDao = new DeliveryOrderDaoImpl((AppCompatActivity) getActivity());
        //SetUp
        laySwipe.setOnRefreshListener(this);
        SwipeUtil.setColor(laySwipe);
        //Preferences
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.INSTANCE.getLogin_information());
        String partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //
        //partyId
        combine_partyIdAndStatus = DeliveryOrderSearchCombine.combine_partyIdAndStatus(partyId, Status.PROGRESS);

        getData();
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
            deliveryOrderExpandListAdapter.filterByProductName("");
        }else {
            deliveryOrderExpandListAdapter.filterByProductName(newText);
        }
        return true;
    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object model) {

    }

    @Override
    public void refresh(RefreshModel refreshModel) {
        getData();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ListViewUtil.detectTop(event,listView,laySwipe);
        return false;
    }

    @Override
    public void onRefresh() {
        laySwipe.setRefreshing(true);
        getData();
        laySwipe.setRefreshing(false);

    }

    public void getData(){
        deliveryOrderModels = deliveryOrderDao.findByPartyIdAndStatus(combine_partyIdAndStatus);
        if(deliveryOrderModels != null) {
            deliveryOrderExpandListAdapter = new DeliveryOrderExpandListAdapter(getActivity(), deliveryOrderModels);
            listView.setAdapter(deliveryOrderExpandListAdapter);
            listView.setGroupIndicator(null);
            listView.setOnTouchListener(this);
        }
    }

//    private List<DeliveryOrderModel> sumDeliveryItem(List<DeliveryOrderModel> deliveryOrderModels){
//
//        List<DeliveryOrderModel> newOrderModels = new ArrayList<>();
//
//        for(DeliveryOrderModel orderModel :deliveryOrderModels) {
//            DeliveryOrderModel newDeliveryOrderModel = orderModel.newDeliveryOrderModel();
//
//            List<DeliveryItemModel> newItemModels = new ArrayList<>();
//            LinkedHashMap<Long, DeliveryItemModel> hashMap = new LinkedHashMap<>();
//
//            List<DeliveryItemModel> dItem = orderModel.getDeliveryItem();
//            for (int i = 0; i < dItem.size(); i++) {
//                DeliveryItemModel deliveryItemModels = dItem.get(i).newDeliveryItemModel();
//                Long productId = deliveryItemModels.getReceivingItem().getProductId();
//                Integer latest_qty = deliveryItemModels.getItemQty() == null ? 0 :deliveryItemModels.getItemQty();
//                BigDecimal latest_w = deliveryItemModels.getItemGrossWeight() == null ? new BigDecimal(0) : deliveryItemModels.getItemGrossWeight();
//
//                if (hashMap.containsKey(productId)) {
//                    Integer orginal_qty = hashMap.get(productId).getItemQty();
//                    BigDecimal orginal_w = hashMap.get(productId).getItemGrossWeight();
//                    //QTY
//                    if (orginal_qty == null) {
//                        hashMap.get(productId).setItemQty(latest_qty);
//                    } else {
//                        hashMap.get(productId).setItemQty(orginal_qty + latest_qty);
//                    }
//
//                    //W
//                    if (orginal_w == null) {
//                        hashMap.get(productId).setItemGrossWeight(latest_w);
//                    } else {
//                        hashMap.get(productId).setItemGrossWeight(orginal_w.add(latest_w));
//                    }
//                } else {
//                    hashMap.put(productId, deliveryItemModels);
//                }
//            }
//            Log.v("asd", "[DeliveryItemFrag]-[sumDeliveryItem]-[LinkHashMap] :" + hashMap);
//            for (Map.Entry<Long, DeliveryItemModel> entry : hashMap.entrySet()) {
//                Long productId = entry.getKey();
//                DeliveryItemModel d = entry.getValue();
//                newItemModels.add(d);
//            }
//            newDeliveryOrderModel.setDeliveryItem(newItemModels);
//            newOrderModels.add(newDeliveryOrderModel);
//        }
//        return newOrderModels;
//    }

}