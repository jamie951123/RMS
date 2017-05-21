package com.example.james.rms.ITF;

import java.util.List;

/**
 * Created by james on 12/3/2017.
 */

public interface ViewPagerListener<T> {
    void transferViewPager(int rid,List<T> models);
}
