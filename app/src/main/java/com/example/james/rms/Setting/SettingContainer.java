package com.example.james.rms.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.daimajia.swipe.util.Attributes;
import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.Service.ClassicDialogService;
import com.example.james.rms.CommonProfile.Listview.ListViewGrowthUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
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
import com.example.james.rms.Core.SearchObject.SettingSearchObject;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * Created by jamie on 2017/5/3.
 */

public class SettingContainer extends AppCompatActivity implements View.OnClickListener,ClassicDialogService {

    @BindView(R.id.setting_wlistview)
    ListView wlistView;
    @BindView(R.id.setting_qlistview)
    ListView qlistView;
    @BindView(R.id.setting_toolbar)
    Toolbar toolbar;
    @BindView(R.id.waddbtn)
    Button waddbtn;
    @BindView(R.id.qaddbtn)
    Button qaddbtn;

    private SettingWeightListAdapter wAdapter;
    private SettingQuantityListAdapter qAdapter;

    private List<WeightProfileModel> weightProfileModelList = new ArrayList<>();
    private List<QuantityProfileModel> quantityProfileModelList = new ArrayList<>();
    private String partyId;
    private String common_partyId;

    // Dao
    private WeightProfileDao weightProfileDao;
    private QuantityProfileDao quantityProfileDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        //Dao
        weightProfileDao = new WeightProfileDaoImpl(this);
        quantityProfileDao = new QuantityProfileDaoImpl(this);
        //
        setUpActionBar();
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        partyId = partyIdPreferences.getPreferences_PartyId().get("partyId");
        common_partyId = SettingSearchCombine.combine_partyId(partyId);

        //weight
        weightProfileModelList = weightProfileDao.findByPartyId(common_partyId);
        if(weightProfileModelList != null) {
            wAdapter = new SettingWeightListAdapter(this, weightProfileModelList, wlistView, partyId);
            wlistView.setAdapter(wAdapter);
            wAdapter.setMode(Attributes.Mode.Single);
            waddbtn.setOnClickListener(this);
            ListViewGrowthUtil.setListViewHeightBasedOnChildren(wlistView);
        }
        //quantity
        quantityProfileModelList = quantityProfileDao.findByPartyId(common_partyId);
        if(quantityProfileModelList != null) {
            qAdapter = new SettingQuantityListAdapter(this, quantityProfileModelList, qlistView, partyId);
            qlistView.setAdapter(qAdapter);
            qAdapter.setMode(Attributes.Mode.Single);
            qaddbtn.setOnClickListener(this);
            ListViewGrowthUtil.setListViewHeightBasedOnChildren(qlistView);
        }
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.setting);
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
        ListViewGrowthUtil.setListViewHeightBasedOnChildren(wlistView);
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
        ListViewGrowthUtil.setListViewHeightBasedOnChildren(qlistView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
