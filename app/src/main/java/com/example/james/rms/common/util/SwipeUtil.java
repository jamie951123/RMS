package com.example.james.rms.common.util;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Jamie on 17/7/2017.
 */

public class SwipeUtil {

    public static void setColor(SwipeRefreshLayout laySwipe){
        laySwipe.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
    }
}
