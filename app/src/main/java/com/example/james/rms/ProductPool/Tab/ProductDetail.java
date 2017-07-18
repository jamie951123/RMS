package com.example.james.rms.ProductPool.Tab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Swipe.SwipeUtil;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.SearchObject.SearchCombine;
import com.example.james.rms.ProductPool.Adapter.ProductExpandListAdapter;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 15/2/2017.
 */

public class ProductDetail extends MyBaseFragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener,View.OnTouchListener {

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
        myPreferences = new MyPreferences(getActivity(), PreferencesKey.login_information);
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
