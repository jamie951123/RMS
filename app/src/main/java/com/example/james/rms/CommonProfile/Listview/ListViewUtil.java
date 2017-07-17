package com.example.james.rms.CommonProfile.Listview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by jamie on 2017/5/15.
 */

public class ListViewUtil {

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void detectTop(MotionEvent event,ListView listView,SwipeRefreshLayout laySwipe){
        if(listView == null || listView.getChildAt(0) == null){
            return;
        }
        View mView = listView.getChildAt(0);
        int top = mView.getTop();

        switch(event.getAction()){

            case MotionEvent.ACTION_MOVE:
                // see if it top is at Zero, and first visible position is at 0
                if(top == 0 && listView.getFirstVisiblePosition() == 0){
                    laySwipe.setEnabled(true);
                }else{
                    laySwipe.setEnabled(false);
                }
        }
    }
}
