package com.example.james.rms.main.delivery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.james.rms.common.adapter.MyPagerAdapter;

import java.util.List;

/**
 * Created by Jamie on 15/6/2017.
 */

public class DeliveryContainerAdapter extends MyPagerAdapter {

    public DeliveryContainerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }
}
