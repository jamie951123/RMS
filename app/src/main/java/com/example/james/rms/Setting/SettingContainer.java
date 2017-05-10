package com.example.james.rms.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.daimajia.swipe.util.Attributes;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/3.
 */

public class SettingContainer extends AppCompatActivity {

    @BindView(R.id.setting_wlistview)
    ListView wlistView;
    @BindView(R.id.setting_qlistview)
    ListView qlistView;
    @BindView(R.id.setting_toolbar)
    Toolbar toolbar;

    private SettingWeightListAdapter wAdapter;
    private SettingQuantityListAdapter qAdapter;

    private List<WeightProfileModel> weightProfileModelList = new ArrayList<>();
    private List<QuantityProfileModel> quantityProfileModelList = new ArrayList<>();
    private String common_partyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        setUpActionBar();
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        String partyId = partyIdPreferences.getPreferences_PartyId().get("partyId");
        common_partyId = SettingCombine.combine_partyId(partyId);

        //weight
        WeightProfileDao weightProfileDao = new WeightProfileDaoImpl();
        weightProfileModelList = weightProfileDao.findByPartyId(common_partyId);
        wAdapter = new SettingWeightListAdapter(this,weightProfileModelList);
        wlistView.setAdapter(wAdapter);
        wAdapter.setMode(Attributes.Mode.Single);

        //quantity
        QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();
        quantityProfileModelList = quantityProfileDao.findByPartyId(common_partyId);
        qAdapter = new SettingQuantityListAdapter(this,quantityProfileModelList);
        qlistView.setAdapter(qAdapter);
        qAdapter.setMode(Attributes.Mode.Single);

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

}
