package com.example.james.rms.Receiving.Tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Adapter.ReceivingOrderExpandListAdapter;
import com.example.james.rms.Core.Combine.ReceivingOrderCombine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Receiving_order extends MyBaseFragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    @BindView(R.id.receiving_order_listView)
    AnimatedExpandableListView listView;

    //Adapter
    private ReceivingOrderExpandListAdapter receivingOrderExpandListAdapter;

    //Model
    List<ReceivingOrderModel> receivingOrderModels;
    //    List<ReceivingItemModel> receivingItemModels;

    //Dao
    private ReceivingOrderDao receivingOrderDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receiving_order,container , false);
        ButterKnife.bind(this,rootView);
        //Dao
        receivingOrderDao = new ReceivingOrderDaoImpl((AppCompatActivity)getContext());
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        String combine_partyId = ReceivingOrderCombine.combine_partyId(partyId);

        //HttpOK
        receivingOrderModels = receivingOrderDao.findByPartyId(combine_partyId);
//        receivingItemModels = receivingItemDao.findReceivingItemByPartyId(combine_partyId);

        //listView
        if(receivingOrderModels != null) {
            receivingOrderExpandListAdapter = new ReceivingOrderExpandListAdapter(getActivity(), receivingOrderModels);
            listView.setAdapter(receivingOrderExpandListAdapter);
            listView.setOnItemLongClickListener(this);
            listView.setGroupIndicator(null);
//            listView.setDivider(null);
        }
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
}
