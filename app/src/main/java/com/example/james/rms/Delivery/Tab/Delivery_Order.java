package com.example.james.rms.Delivery.Tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Swipe.SwipeUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Combine.DeliveryOrderSearchCombine;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Delivery.Adapter.DeliveryOrderExpandListAdapter;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class Delivery_Order extends MyBaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener {

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
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.login_information);
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
            deliveryOrderExpandListAdapter.filterByRemark("");
        }else {
            deliveryOrderExpandListAdapter.filterByRemark(newText);
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
        }
    }
}
