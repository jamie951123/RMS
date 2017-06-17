package com.example.james.rms.Delivery.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Combine.DeliveryOrderCombine;
import com.example.james.rms.Core.Combine.DeliveryOrderSearchCombine;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.SearchObject.DeliveryOrderSearchObject;
import com.example.james.rms.Delivery.Adapter.DeliveryOrderExpandListAdapter;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class Delivery_Order extends MyBaseFragment {

    @BindView(R.id.de_order_listview)
    AnimatedExpandableListView listView;

    //Interface
    private DeliveryOrderDao deliveryOrderDao = new DeliveryOrderDaoImpl();

    //Model
    private List<DeliveryOrderModel> deliveryOrderModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delivery_order,container , false);
        ButterKnife.bind(this,rootView);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        String combine_partyIdAndStatus = DeliveryOrderSearchCombine.combine_partyIdAndStatus(partyId, Status.PROGRESS);

        deliveryOrderModels = deliveryOrderDao.findByPartyIdAndStatus(combine_partyIdAndStatus);

        DeliveryOrderExpandListAdapter deliveryOrderExpandListAdapter = new DeliveryOrderExpandListAdapter(getActivity(),deliveryOrderModels);
        listView.setAdapter(deliveryOrderExpandListAdapter);
//        pager.setPagingEnabled(false);
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void transfersViewPager(int rid, List models) {

    }

    @Override
    public void transferViewPager(int rid, Object model) {

    }
}
