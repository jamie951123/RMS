package com.example.james.rms.ProductPool.Tab;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Product.Dao.ProductDao;
import com.example.james.rms.Core.Product.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.ProductPool.Adapter.ProductListAdapter;
import com.example.james.rms.ProductPool.ProductCombine;
import com.example.james.rms.ProductPool.Service.ProductService;
import com.example.james.rms.ProductPool.Service.ProductServiceImpl;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 15/2/2017.
 */

public class ProductDetail extends MyBaseFragment implements AdapterView.OnItemClickListener{

    @BindView(R.id.listView)
    ListView listView;

    private ProductService productService = new ProductServiceImpl();
    //
    private ProductCombine productCombine = new ProductCombine();
    //
    private ProductListAdapter productAdapter ;

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
        List<ProductModel> productModels = productService.findByPartyId(combine_partyId);
        //ListView
        if(productModels != null){
            productAdapter = new ProductListAdapter(getActivity(),productModels);
            listView.setAdapter(productAdapter);
            listView.setOnItemClickListener(this);
            listView.setDivider(null);
        }


        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(ObjectUtil.isNullEmpty(newText)){
            productAdapter.filterByProductName("");
        }else {
            productAdapter.filterByProductName(newText);
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
