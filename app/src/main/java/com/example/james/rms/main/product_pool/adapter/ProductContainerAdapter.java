package com.example.james.rms.main.product_pool.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.james.rms.common.adapter.MyPagerAdapter;

import java.util.List;

/**
 * Created by james on 14/2/2017.
 */

public class ProductContainerAdapter extends MyPagerAdapter {

    public ProductContainerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }
}
