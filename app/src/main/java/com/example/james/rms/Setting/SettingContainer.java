package com.example.james.rms.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.Service.ClassicDialogService;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/3.
 */

public class SettingContainer extends AppCompatActivity implements View.OnClickListener,ClassicDialogService{

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        setUpActionBar();
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        partyId = partyIdPreferences.getPreferences_PartyId().get("partyId");
        common_partyId = SettingCombine.combine_partyId(partyId);

        //weight
        WeightProfileDao weightProfileDao = new WeightProfileDaoImpl();
        weightProfileModelList = weightProfileDao.findByPartyId(common_partyId);
        wAdapter = new SettingWeightListAdapter(this,weightProfileModelList);
        wlistView.setAdapter(wAdapter);
        wAdapter.setMode(Attributes.Mode.Single);
        waddbtn.setOnClickListener(this);
        //quantity
        QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();
        quantityProfileModelList = quantityProfileDao.findByPartyId(common_partyId);
        qAdapter = new SettingQuantityListAdapter(this,quantityProfileModelList);
        qlistView.setAdapter(qAdapter);
        qAdapter.setMode(Attributes.Mode.Single);
        qaddbtn.setOnClickListener(this);
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
        ClassicDialog classicDialog = new ClassicDialog(this);
        switch (v.getId()){
            case R.id.waddbtn:
                classicDialog.showInputBox(getString(R.string.add_weight_unit),null,null, KeyModel.gw);
                break;

            case R.id.qaddbtn:
                classicDialog.showInputBox(getString(R.string.add_weight_unit),null,null, KeyModel.qty);
                break;
        }
    }

    @Override
    public void settingPagesWeight(String value) {
        WeightProfileModel weightProfileModel = new WeightProfileModel();
        weightProfileModel.setCreateDate(new Date());
        weightProfileModel.setPartyId(partyId);
        weightProfileModel.setWeightUnit(value);
        String json = SettingCombine.gsonWeightProfile(weightProfileModel);
        //service
        WeightProfileDao weightProfileDao = new WeightProfileDaoImpl();
        weightProfileModel = weightProfileDao.save(json);
        //
        if(weightProfileModel != null) {
            weightProfileModelList.add(weightProfileModel);
            wAdapter.notifyDataSetChanged();
//            Toast.makeText(this,weightProfileModel.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void settingPagesQty(String value) {
        QuantityProfileModel quantityProfileModel = new QuantityProfileModel();
        quantityProfileModel.setCreateDate(new Date());
        quantityProfileModel.setQuantityUnit(value);
        quantityProfileModel.setPartyId(partyId);
        String json = SettingCombine.gsonQuantityProfile(quantityProfileModel);
        //service
        QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();
        quantityProfileModel = quantityProfileDao.save(json);
        //
        if(quantityProfileModel != null){
            quantityProfileModelList.add(quantityProfileModel);
            qAdapter.notifyDataSetChanged();
//            Toast.makeText(this,quantityProfileModel.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
