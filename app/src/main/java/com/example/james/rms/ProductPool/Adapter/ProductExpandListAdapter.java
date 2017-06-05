package com.example.james.rms.ProductPool.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Core.Adapter.CnGridAdapter;
import com.example.james.rms.Core.Dao.InventoryDao;
import com.example.james.rms.Core.Dao.InventoryDaoImpl;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.SearchObject.ProductSearchObject;
import com.example.james.rms.Core.SearchObject.SearchCombine;
import com.example.james.rms.Operation.ProductAction.ProductIncrease;
import com.example.james.rms.ProductPool.ProductCombine;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.Date;
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
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
        holder.productImage.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
        holder.linear_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editModel = ProductCombine.modelToJson(getFilteredData().get(groupPosition));
                Intent intent = new Intent();
                intent.setClass(getContext(), ProductIncrease.class);
                intent.putExtra("ProductIncrease",editModel);
                getContext().startActivity(intent);
                Log.d("asd",editModel);
            }
        });

        holder.linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productModel = ProductCombine.modelToJson(getFilteredData().get(groupPosition));
                InventoryDao inventoryDao = new InventoryDaoImpl();
                ResponseMessage responseMessage = inventoryDao.deleteByProductId(productModel);
                Log.d("asd","responseMessage : " +responseMessage);
            }
        });
//        ArrayList<GridComponent> gridList = new ArrayList<>();
//        GridComponent g = new GridComponent();
//        g.setLabel("a");
//        g.setImage(ContextCompat.getDrawable(getContext(),R.drawable.mailbox_black));
//        gridList.add(g);
//        gridList.add(g);
//        CnGridAdapter c = new CnGridAdapter(getContext(),gridList);
//        holder.gridView.setNumColumns(2);
//        holder.gridView.setAdapter(c);

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
        if(ObjectUtil.isNullEmpty(productModel.getProductCode()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        boolean result = productModel.getProductCode().toUpperCase().contains(string.toUpperCase());
        return result;
    }

    @Override
    public boolean productNameMatch(ProductModel productModel, String string) {
        if(ObjectUtil.isNullEmpty(productModel.getProductName()) && ObjectUtil.isNullEmpty(string)){
            return true;
        }
        return  ObjectUtil.isNullEmpty(productModel.getProductName()) ? false: productModel.getProductName().toUpperCase().contains(string.toUpperCase());
    }

    @Override
    public boolean receivingRemarkMatch(ProductModel productModel, String string) {
        return false;
    }

    public class GridComponent{
        String label;
        Drawable image;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }
    }

    static class GroupHolder {
        @BindView(R.id.product_productCode)
        TextView productCode;
        @BindView(R.id.product_productName)
        TextView productName;
        @BindView(R.id.product_createDate)
        TextView createDate;
        @BindView(R.id.product_product_image)
        de.hdodenhof.circleimageview.CircleImageView productImage;
        @BindView(R.id.product_linear_edit)
        LinearLayout linear_edit;
        @BindView(R.id.product_linear_delete)
        LinearLayout linear_delete;
        //        @BindView(R.id.product_gridview)
//        GridView gridView;
        public GroupHolder(View view){
            ButterKnife.bind(this,view);
        }
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


}