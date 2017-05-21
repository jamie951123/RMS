package com.example.james.rms.ProductPool.Tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.ProductPool.Adapter.ProductExpandListAdapter;
import com.example.james.rms.ProductPool.ProductCombine;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 15/2/2017.
 */

public class ProductDetail extends MyBaseFragment implements AdapterView.OnItemClickListener{

    @BindView(R.id.product_listView)
    AnimatedExpandableListView listView;

    private ProductDao productDao = new ProductDaoImpl();
    //
    private ProductCombine productCombine = new ProductCombine();
    //
    private ProductExpandListAdapter productExpandListAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail,container , false);
        ButterKnife.bind(this,rootView);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
        String partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //HttpOK
        String combine_partyId = productCombine.combine_partyId(partyId);
        List<ProductModel> productModels = productDao.findByPartyId(combine_partyId);
        //ListView
        if(productModels != null){
            productExpandListAdapter = new ProductExpandListAdapter(getActivity(),productModels);
            listView.setAdapter(productExpandListAdapter);
            listView.setOnItemClickListener(this);
//            listView.setDivider(null);
        }

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });


        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(ObjectUtil.isNullEmpty(newText)){
            productExpandListAdapter.filterByProductName("");
        }else {
            productExpandListAdapter.filterByProductName(newText);
        }
        return true;
    }

    @Override
    public void transferViewPager(int rid, List models) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent();
//        intent = intent.setClass(getActivity(), ProductIncrease.class);
//        startActivity(intent);
    }
}
