package com.example.james.rms.ProductPool.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/18.
 */

public class ProductExpandListAdapter extends MyExpandableListAdapter<ProductModel> {


    public ProductExpandListAdapter(Context context, List<ProductModel> dataArrayList) {
        super(context, dataArrayList);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        ProductModel productModel = getGroup(groupPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.product_expandablelist_group, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.productCode.setText(productModel.getProductCode());
        holder.productName.setText(productModel.getProductName());
        holder.createDate.setText(ObjectUtil.dateToString(productModel.getCreateDate()));
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ProductModel productModel = getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.product_expandablelist_item, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.descriptionEN.setText(productModel.getProductDescriptionEN());
        holder.descriptionCH.setText(productModel.getProductDescriptionCH());
        holder.closeDate.setText(ObjectUtil.dateToString(productModel.getCloseDate()));

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1 ;
    }

    @Override
    public boolean productCodeMatch(ProductModel productModel, String string) {
        boolean result = productModel.getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ProductModel productModel, String value) {

        return  ObjectUtil.isNullEmpty(productModel.getProductName()) ? false: productModel.getProductName().toUpperCase().contains(value.toUpperCase());
    }

    @Override
    public boolean receivingRemarkMatch(ProductModel productModel, String string) {
        return false;
    }


    static class ChildHolder {
        @BindView(R.id.product_status)
        TextView status;
        @BindView(R.id.product_descriptionEN)
        TextView descriptionEN;
        @BindView(R.id.product_descriptionCH)
        TextView descriptionCH;
        @BindView(R.id.product_closeDate)
        TextView closeDate;

        public ChildHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    static class GroupHolder {
        @BindView(R.id.product_productCode)
        TextView productCode;
        @BindView(R.id.product_productName)
        TextView productName;
        @BindView(R.id.product_createDate)
        TextView createDate;
//        @BindView(R.id.product_product_image)
        de.hdodenhof.circleimageview.CircleImageView productImage;
        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}