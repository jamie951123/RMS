package com.example.james.rms.CommonProfile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.james.rms.Core.Model.WeightProfileModel;

import java.util.List;

/**
 * Created by jamie on 2017/5/7.
 */

public abstract class MyBaseSwipeAdapter<T>  extends BaseSwipeAdapter {
    public Context mContext;
    public List<T> list;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
