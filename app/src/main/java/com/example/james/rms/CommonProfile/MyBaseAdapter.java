package com.example.james.rms.CommonProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.Filter;

import com.example.james.rms.ITF.RmsCompareListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 31/1/2017.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter implements RmsCompareListener<T> {
    private List<T> originalData ;
    private List<T> filteredData ;
    private boolean isFiltering = false;
    private LayoutInflater layoutInflater;
    private Context context;

    //
    private ProductNameFilter productNameFilter;
    private RemarkFilter remarkFilter;
    private ProductCodeFilter productCodeFilter;

    public MyBaseAdapter(Context context, List<T> dataArrayList){
        this.context = context;
        this.originalData = new ArrayList<>(dataArrayList);
        this.filteredData = new ArrayList<>(dataArrayList);
        layoutInflater = layoutInflater.from(context);
        productNameFilter = new ProductNameFilter();
        remarkFilter = new RemarkFilter();
        productCodeFilter = new ProductCodeFilter();
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
            filteredData = (ArrayList<T>) results.values;
            notifyDataSetChanged();
        }
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public T getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
}
