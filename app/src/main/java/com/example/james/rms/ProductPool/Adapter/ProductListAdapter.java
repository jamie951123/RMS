package com.example.james.rms.ProductPool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyBaseAdapter;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 31/1/2017.
 */

public class ProductListAdapter extends MyBaseAdapter<ProductModel>{

    public ProductListAdapter(Context context, List <ProductModel>dataArrayList) {
        super(context,dataArrayList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_detail_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.product_productName.setText(getItem(position).getProductName());
        viewHolder.product_productCode.setText(getItem(position).getProductCode());
        viewHolder.product_descriptionEN.setText(getItem(position).getProductDescriptionEN());
        viewHolder.product_descriptionCH.setText(getItem(position).getProductDescriptionCH());
        viewHolder.product_createDate.setText(getItem(position).getCreateDate());
        return convertView;
    }

    @Override
    public boolean productCodeMatch(ProductModel productModel, String string) {
        boolean result = productModel.getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ProductModel productModel, String value) {
        boolean result = productModel.getProductName().toUpperCase().contains(value.toUpperCase());
        return result;
    }

    @Override
    public boolean receivingRemarkMatch(ProductModel productModel, String string) {
        return false;
    }


    static class ViewHolder{
        @BindView(R.id.product_productCode)
        TextView product_productCode;
        @BindView(R.id.product_productName)
        TextView product_productName;
        @BindView(R.id.product_descriptionEN)
        TextView product_descriptionEN;
        @BindView(R.id.product_createDate)
        TextView product_createDate;
        @BindView(R.id.product_descriptionCH)
        TextView product_descriptionCH;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}