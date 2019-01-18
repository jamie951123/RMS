package com.example.james.rms.main.inventory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.james.rms.common.adapter.MyPagerAdapter;

import java.util.List;

/**
 * Created by jamie on 2017/4/28.
 */

public class InventoryContainerAdapter extends MyPagerAdapter {
    public InventoryContainerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }
}
