package com.example.james.rms.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.james.rms.common.adapter.MyPagerAdapter;

import java.util.List;

/**
 * Created by james on 12/2/2017.
 */

public class NavPagerAdapter extends MyPagerAdapter {

    public NavPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }
}
