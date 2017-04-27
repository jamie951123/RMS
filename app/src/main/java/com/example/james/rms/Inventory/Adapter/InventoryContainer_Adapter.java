package com.example.james.rms.Inventory.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.james.rms.CommonProfile.MyPagerAdapter;

import java.util.List;

/**
 * Created by jamie on 2017/4/28.
 */

public class InventoryContainer_Adapter extends MyPagerAdapter {
    public InventoryContainer_Adapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }
}
