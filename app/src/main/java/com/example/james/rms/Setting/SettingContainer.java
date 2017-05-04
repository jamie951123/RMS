package com.example.james.rms.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daimajia.swipe.util.Attributes;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
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

    private SettingListAdapter wAdapter;

    private List<WeightProfileModel> weightProfileModelList;
    private List<QuantityProfileModel> quantityProfileModelList;
    private String common_partyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        String partyId = partyIdPreferences.getPreferences_PartyId().get("partyId");
        //
        common_partyId = SettingCombine.combine_partyId(partyId);
        WeightProfileDao weightProfileDao = new WeightProfileDaoImpl();
        List<WeightProfileModel> weightProfileModelList = weightProfileDao.findByPartyId(common_partyId);
        wAdapter = new SettingListAdapter(this,weightProfileModelList);
        wlistView.setAdapter(wAdapter);
        wAdapter.setMode(Attributes.Mode.Single);


    }

//    private List<QuantityProfileModel> qSetting() {
//        List<QuantityProfileModel> data = new ArrayList<>();
//        QuantityProfileModel w = new QuantityProfileModel();
//        w.setQuantityUnit("KG");
//        w.setQuantityUnit("G");
//        data.add(w);
//        return data;
//    }
//
//    private List<WeightProfileModel> wSetting() {
//        List<WeightProfileModel> data = new ArrayList<>();
//        WeightProfileModel w = new WeightProfileModel();
//        w.setWeightUnit("G");
//        data.add( w);
//        data.add( w);
//        return data;
//    }

}