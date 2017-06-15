package com.example.james.rms.CommonProfile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Filter;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.ITF.RmsCompareListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamie on 2017/5/21.
 */

public abstract class MyExpandableListAdapter<T> extends AnimatedExpandableListView.AnimatedExpandableListAdapter implements RmsCompareListener<T> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<T> originalData;
    private List<T> filteredData ;

    private boolean isFiltering = false;

    private MyExpandableListAdapter.ProductNameFilter productNameFilter;
    private MyExpandableListAdapter.RemarkFilter remarkFilter;
    private MyExpandableListAdapter.ProductCodeFilter productCodeFilter;

    public MyExpandableListAdapter(Context context, List<T> dataArrayList) {
        this.context = context;
        this.originalData = dataArrayList;
        this.filteredData = dataArrayList;
        this.layoutInflater = layoutInflater.from(context);
        productNameFilter = new MyExpandableListAdapter.ProductNameFilter();
        remarkFilter = new MyExpandableListAdapter.RemarkFilter();
        productCodeFilter = new MyExpandableListAdapter.ProductCodeFilter();
    }

    public class ProductCodeFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            isFiltering = constraint!=null;
            List<T>list = new ArrayList<>();
            if(isFiltering){
                for (int i=0; i<originalData.size(); i++){
                    T item = originalData.get(i);
                    boolean isFilterValue = productCodeMatch(item,constraint.toString());
                    if(isFilterValue) list.add(item);
                }
            }else {
                list.addAll(originalData);
            }
            results.values = list;
            results.count = list.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<T>) results.values;
            notifyDataSetChanged();
        }
    }

    public class ProductNameFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence string) {
            FilterResults results = new FilterResults();
            isFiltering = string!=null;
            List<T> list = new ArrayList<>();
            if(isFiltering){
                for (int i = 0; i < originalData.size(); i++) {
                    T item = originalData.get(i);
                    boolean isFilterValue = productNameMatch(item,string.toString());
                    if (isFilterValue) list.add(item);
                }
            }else {
                list.addAll(originalData);
            }
            results.values = list;
            results.count = list.size();
            return results;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<T>) results.values;
            notifyDataSetChanged();
        }
    }

    public class RemarkFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence string) {
            FilterResults results = new FilterResults();
            isFiltering = string!=null;
            List<T> list = new ArrayList<>();
            if(isFiltering){
                for (int i = 0; i < originalData.size(); i++) {
                    T item = originalData.get(i);
                    boolean isFilterValue = receivingRemarkMatch(item,string.toString());
                    if (isFilterValue)list.add(item);
                }
            }else {
                list.addAll(originalData);
            }
            results.values = list;
            results.count = list.size();
            return results;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d("asd","RL Orgin :" +filteredData);
            filteredData = (ArrayList<T>) results.values;
            Log.d("asd","RL results :" +results);
            Log.d("asd","RL filteredData :" +filteredData);
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupCount() {
        return filteredData.size();
    }

    @Override
    public T getGroup(int groupPosition) {
        return filteredData.get(groupPosition);
    }

    @Override
    public T getChild(int groupPosition, int childPosition) {
        return filteredData.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public Context getContext() {
        return context;
    }

    public void filterByProductName(String string){
        productNameFilter.filter(string);
    }
    public void filterByRemark(String remark){
        remarkFilter.filter(remark);
    }
    public void filterByProductCode(String code){
        productCodeFilter.filter(code);
    }

    public List<T> getOriginalData() {
        return originalData;
    }

    public List<T> getFilteredData() {
        return filteredData;
    }
}
