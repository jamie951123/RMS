package com.example.james.rms.Main.ProductPool.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.MyAdapter.MyExpandableListAdapter;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.ProductCombine;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Operation.ProductAction.ProductIncrease;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;
import com.example.james.rms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/18.
 */

public class ProductExpandListAdapter extends MyExpandableListAdapter<ProductModel> {

    private ProductDao productDao;

    public ProductExpandListAdapter(Context context, List<ProductModel> dataArrayList) {
        super(context, dataArrayList);
        productDao = new ProductDaoImpl((AppCompatActivity)context);
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
        String qtyUnit="";
        String weightUnit="";
        if(productModel.getQuantityProfile() != null && ObjectUtil.isNotNullEmpty(productModel.getQuantityProfile().getQuantityUnit()))qtyUnit = productModel.getQuantityProfile().getQuantityUnit();
        if(productModel.getWeightprofile() != null && ObjectUtil.isNotNullEmpty(productModel.getWeightprofile().getWeightUnit()))weightUnit = productModel.getWeightprofile().getWeightUnit();

        holder.qty_unit.setText(qtyUnit);
        holder.weight_unit.setText(weightUnit);
//        GlideApp.with(getContext())
//                .load(R.drawable.mailbox_black)
//                .error(R.drawable.question_purple)
//                .placeholder(R.drawable.question_purple)
//                .fitCenter()
//                .into(holder.productImage);

        holder.linear_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movementRecord_str = CommonFactory.movementFactory_str(NavigationController.class.getCanonicalName(),ReceivingIncrease.class.getCanonicalName(),R.id.nav_all_product);

                ProductCombine productCombine = new ProductCombine(ProductModel.class);
                String editModel = productCombine.modelToJson(getFilteredData().get(groupPosition));
                Intent intent = new Intent();
                intent.setClass(getContext(), ProductIncrease.class);
                intent.putExtra(StartActivityForResultKey.productModel,editModel);
                intent.putExtra(StartActivityForResultKey.movementRecord,movementRecord_str);

                getContext().startActivity(intent);
                Log.d("asd",editModel);
            }
        });

        holder.linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCombine productCombine = new ProductCombine(ProductModel.class);
                String productModel = productCombine.modelToJson(getFilteredData().get(groupPosition));
                ResponseMessage responseMessage = productDao.delete(productModel);
                if(responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())){
                    getFilteredData().remove(groupPosition);
                    notifyDataSetChanged();
                }
                Log.d("asd","[Product]-responseMessage : " +responseMessage);

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
        holder.status.setText(productModel.getStatus());
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
    public boolean productNameMatch(ProductModel productModel, String string,int position) {
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
        @BindView(R.id.product_qty_unit)
        TextView qty_unit;
        @BindView(R.id.product_weight_unit)
        TextView weight_unit;
        @BindView(R.id.product_product_image)
        com.github.siyamed.shapeimageview.RoundedImageView productImage;
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