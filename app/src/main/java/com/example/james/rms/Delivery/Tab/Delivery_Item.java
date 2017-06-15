package com.example.james.rms.Delivery.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Combine.DeliveryItemCombine;
import com.example.james.rms.Core.Dao.DeliveryItemDao;
import com.example.james.rms.Core.Dao.DeliveryItemDaoImpl;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/6/2017.
 */

public class Delivery_Item extends MyBaseFragment{

    @BindView(R.id.de_item_listview)
    ListView listView;

    //Interface
    private DeliveryItemDao deliveryItemDao = new DeliveryItemDaoImpl();

    //Model
    private List<DeliveryItemModel> deliveryItemModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delivery_item,container , false);
        ButterKnife.bind(this,rootView);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        String combine_partyId = DeliveryItemCombine.combine_partyId(partyId);

        deliveryItemModels = deliveryItemDao.findAll();

        List<String> test = new ArrayList<>();
        for(DeliveryItemModel model : deliveryItemModels){
            test.add(model.getPartyId());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,test);
        listView.setAdapter(arrayAdapter);
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
