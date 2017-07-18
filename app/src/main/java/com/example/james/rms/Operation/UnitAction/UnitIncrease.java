package com.example.james.rms.Operation.UnitAction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.daimajia.swipe.util.Attributes;
import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.Service.ClassicDialogService;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.Core.Combine.QuantityProfileCombine;
import com.example.james.rms.Core.Combine.SettingSearchCombine;
import com.example.james.rms.Core.Combine.WeightProfileCombine;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Operation.Adapter.UnitQuantityListAdapter;
import com.example.james.rms.Operation.Adapter.UnitWeightListAdapter;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * Created by jamie on 2017/5/3.
 */

public class UnitIncrease extends AppCompatActivity implements View.OnClickListener,ClassicDialogService {

    @BindView(R.id.wlistview)
    ListView wlistView;
    @BindView(R.id.qlistview)
    ListView qlistView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.waddbtn)
    Button waddbtn;
    @BindView(R.id.qaddbtn)
    Button qaddbtn;

    private UnitWeightListAdapter wAdapter;
    private UnitQuantityListAdapter qAdapter;

    private List<WeightProfileModel> weightProfileModelList = new ArrayList<>();
    private List<QuantityProfileModel> quantityProfileModelList = new ArrayList<>();
    //MyPreferences
    private MyPreferences myPreferences;
    private String combine_partyId;
    private String combine_partyIdAndStatus;
    private String partyId;

    // Dao
    private WeightProfileDao weightProfileDao;
    private QuantityProfileDao quantityProfileDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_increase);
        ButterKnife.bind(this);
        //Dao
        weightProfileDao = new WeightProfileDaoImpl(this);
        quantityProfileDao = new QuantityProfileDaoImpl(this);
        //
        setUpActionBar();
        //Preferences
        myPreferences = new MyPreferences(this, PreferencesKey.login_information);
        partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        combine_partyId = SettingSearchCombine.combine_partyId(partyId);

        //weight
        weightProfileModelList = weightProfileDao.findByPartyId(combine_partyId);
        if(weightProfileModelList != null) {
            wAdapter = new UnitWeightListAdapter(this, weightProfileModelList, wlistView, partyId);
            wlistView.setAdapter(wAdapter);
            wAdapter.setMode(Attributes.Mode.Single);
            waddbtn.setOnClickListener(this);
            ListViewUtil.setListViewHeightBasedOnChildren(wlistView);
        }
        //quantity
        quantityProfileModelList = quantityProfileDao.findByPartyId(combine_partyId);
        if(quantityProfileModelList != null) {
            qAdapter = new UnitQuantityListAdapter(this, quantityProfileModelList, qlistView, partyId);
            qlistView.setAdapter(qAdapter);
            qAdapter.setMode(Attributes.Mode.Single);
            qaddbtn.setOnClickListener(this);
            ListViewUtil.setListViewHeightBasedOnChildren(qlistView);
        }
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_unit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
//        ClassicDialog classicDialog = new ClassicDialog(this);
        switch (v.getId()){
            case R.id.waddbtn:
                ClassicDialog.showInputBox(this,getString(R.string.add_weight_unit),null,null, KeyModel.gw,partyId);
                break;

            case R.id.qaddbtn:
                ClassicDialog.showInputBox(this,getString(R.string.add_weight_unit),null,null, KeyModel.qty,partyId);
                break;
        }
    }

    @Override
    public void settingPagesWeight(WeightProfileModel weightProfileModel) {
        WeightProfileCombine weightProfileCombine = new WeightProfileCombine(WeightProfileModel.class);
        String json = weightProfileCombine.modelToJson(weightProfileModel);
        //service
        weightProfileModel = weightProfileDao.save(json);
        //
        if(weightProfileModel != null) {
            weightProfileModelList.add(weightProfileModel);
            wAdapter.notifyDataSetChanged();
//            Toast.makeText(this,weightProfileModel.toString(),Toast.LENGTH_SHORT).show();
        }
        ListViewUtil.setListViewHeightBasedOnChildren(wlistView);
        ListViewUtil.setListViewHeightBasedOnChildren(wlistView);
    }

    @Override
    public void settingPagesQty(QuantityProfileModel quantityProfileModel) {
        QuantityProfileCombine quantityProfileCombine = new QuantityProfileCombine(QuantityProfileModel.class);
        String json = quantityProfileCombine.modelToJson(quantityProfileModel);
        //service
        quantityProfileModel = quantityProfileDao.save(json);
        //
        if(quantityProfileModel != null){
            quantityProfileModelList.add(quantityProfileModel);
            qAdapter.notifyDataSetChanged();
//            Toast.makeText(this,quantityProfileModel.toString(),Toast.LENGTH_SHORT).show();
        }
        ListViewUtil.setListViewHeightBasedOnChildren(qlistView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
