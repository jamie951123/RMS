package com.example.james.rms.common.fab_button;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.james.rms.R;
import com.github.clans.fab.FloatingActionButton;

/**
 * Created by james on 25/3/2017.
 */

public class FabCore {
    Context context;
    FloatingActionButton fabButton;

    public FabCore(Context context) {
        this.context = context;
    }

    public FloatingActionButton fabAdd(){
        fabButton = new FloatingActionButton(context);
        fabButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.add_white));
        return fabButton;
    }

    public FloatingActionButton fabReceiving(){
        fabButton = new FloatingActionButton(context);
        fabButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.close_black));
        return fabButton;
    }
}
