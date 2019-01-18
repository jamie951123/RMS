package com.example.james.rms.main.product_pool.tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.james.rms.common.Library.AnimatedExpandableListView;
import com.example.james.rms.common.util.ListViewUtil;
import com.example.james.rms.common.adapter.MyBaseFragment;
import com.example.james.rms.common.SharePreferences.MyPreferences;
import com.example.james.rms.constant.PreferencesKey;
import com.example.james.rms.common.util.SwipeUtil;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.core.dao.ProductDao;
import com.example.james.rms.core.dao.ProductDaoImpl;
import com.example.james.rms.core.model.ProductModel;
import com.example.james.rms.core.search_object.SearchCombine;
import com.example.james.rms.ITF.model.RefreshModel;
import com.example.james.rms.main.product_pool.adapter.ProductExpandListAdapter;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 15/2/2017.
 */

public class ProductDetailFrag extends MyBaseFragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener {

    @BindView(R.id.product_listView)
    AnimatedExpandableListView listView;
    @BindView(R.id.product_swipe)
    SwipeRefreshLayout laySwipe;

    private ProductExpandListAdapter productExpandListAdapter ;

    //Dao
    private ProductDao productDao;
    //MyPreferences
    private MyPreferences myPreferences;
    private String combine_partyId;
    private String combine_partyIdAndStatus;
    private String partyId;

    private List<ProductModel> productModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail,container , false);
        ButterKnife.bind(this,rootView);
        //Dao
        productDao = new ProductDaoImpl((AppCompatActivity)getActivity());
        //SetUp
        laySwipe.setOnRefreshListener(this);
        SwipeUtil.setColor(laySwipe);
        //
        //Preferences
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.INSTANCE.getLogin_information());
        partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        //HttpOK
        combine_partyId = SearchCombine.combine_partyId(partyId);
        getData();

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent();
//        intent = intent.setClass(getActivity(), ProductIncrease.class);
//        startActivity(intent);
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
    public void onRefresh() {
        laySwipe.setRefreshing(true);
        getData();
        laySwipe.setRefreshing(false);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ListViewUtil.detectTop(event,listView,laySwipe);
        return false;
    }

    public void getData(){
        productModels = productDao.findByPartyId(combine_partyId);
        //ListView
        if(productModels != null){
            productExpandListAdapter = new ProductExpandListAdapter(getActivity(),productModels);
            listView.setAdapter(productExpandListAdapter);
            listView.setOnItemClickListener(this);
            listView.setGroupIndicator(null);
            listView.setOnTouchListener(this);
        }
    }
}
