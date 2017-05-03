package com.example.james.rms.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daimajia.swipe.util.Attributes;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
//        List<String> data = datasetting();
//        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        List<WeightProfileModel> w = wSetting();
        wAdapter = new SettingListAdapter(this,w);
        wlistView.setAdapter(wAdapter);
        wAdapter.setMode(Attributes.Mode.Single);


    }

    private List<QuantityProfileModel> qSetting() {
        List<QuantityProfileModel> data = new ArrayList<>();
        QuantityProfileModel w = new QuantityProfileModel();
        w.setQuantityUnit("KG");
        w.setQuantityUnit("G");
        data.add(w);
        return data;
    }

    private List<WeightProfileModel> wSetting() {
        List<WeightProfileModel> data = new ArrayList<>();
        WeightProfileModel w = new WeightProfileModel();
        w.setWeightUnit("G");
        data.add( w);
        data.add( w);
        return data;
    }

}
