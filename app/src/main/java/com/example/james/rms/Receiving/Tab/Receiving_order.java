package com.example.james.rms.Receiving.Tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Swipe.SwipeUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.ITF.Model.RefreshModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingOrderExpandListAdapter;
import com.example.james.rms.Core.Combine.ReceivingOrderCombine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Receiving_order extends MyBaseFragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener {

    @BindView(R.id.receiving_order_listView)
    AnimatedExpandableListView listView;
    @BindView(R.id.receiving_order_swipe)
    SwipeRefreshLayout laySwipe;
    //Adapter
    private ReceivingOrderExpandListAdapter receivingOrderExpandListAdapter;

    //Model
    List<ReceivingOrderModel> receivingOrderModels;
    //    List<ReceivingItemModel> receivingItemModels;

    //Dao
    private ReceivingOrderDao receivingOrderDao;

    //MyPreferences
    private MyPreferences myPreferences;
    private String combine_partyId;
    private String combine_partyIdAndStatus;
    private String partyId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receiving_order,container , false);
        ButterKnife.bind(this,rootView);
        //Dao
        receivingOrderDao = new ReceivingOrderDaoImpl((AppCompatActivity)getContext());
        //SetUp
        laySwipe.setOnRefreshListener(this);
        SwipeUtil.setColor(laySwipe);
        //Preferences
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.login_information);
        partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        combine_partyId = ReceivingOrderCombine.combine_partyId(partyId);

        //HttpOK
        getData();
        return rootView;
    }

    //SearchView
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(ObjectUtil.isNullEmpty(newText)){
            receivingOrderExpandListAdapter.filterByRemark("");
        }else {
            receivingOrderExpandListAdapter.filterByRemark(newText);
        }
        return true;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Long order_orderId = receivingOrderModels.get(position).getOrderId();
        if(order_orderId ==null) return true;

        if(receivingOrderModels.get(position).getReceivingItem() != null) {
            NavigationController controller = (NavigationController) getContext();
            ViewPagerListener viewPagerListener = (ViewPagerListener) controller;
            viewPagerListener.transferViewPager(R.id.receiving_item, receivingOrderModels.get(position));
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object models) {

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
        receivingOrderModels = receivingOrderDao.findByPartyId(combine_partyId);
        if(receivingOrderModels != null) {
            receivingOrderExpandListAdapter = new ReceivingOrderExpandListAdapter(getActivity(), receivingOrderModels);
            listView.setAdapter(receivingOrderExpandListAdapter);
            listView.setOnItemLongClickListener(this);
            listView.setOnTouchListener(this);
            listView.setGroupIndicator(null);
        }
    }

}
