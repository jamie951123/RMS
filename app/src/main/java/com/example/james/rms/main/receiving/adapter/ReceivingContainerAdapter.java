package com.example.james.rms.main.receiving.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.james.rms.common.adapter.MyPagerAdapter;

import java.util.List;

/**
 * Created by james on 26/2/2017.
 */

public class ReceivingContainerAdapter extends MyPagerAdapter {
    public ReceivingContainerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }
}
