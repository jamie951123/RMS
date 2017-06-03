package com.example.james.rms.Core.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.james.rms.Operation.OperationContainer;
import com.example.james.rms.ProductPool.Adapter.ProductExpandListAdapter;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/6/3.
 */

public class CnGridAdapter extends BaseAdapter {

    Context context;
    private List<ProductExpandListAdapter.GridComponent> gridComponent = new ArrayList<>();

    public CnGridAdapter(Context context, List<ProductExpandListAdapter.GridComponent> gridComponent) {
        this.gridComponent = gridComponent;
        this.context = context;
    }
    @Override
    public int getCount() {
        return gridComponent.size();
    }

    @Override
    public Object getItem(int position) {
        return gridComponent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(context).inflate(R.layout.common_grid_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.image.setImageDrawable(gridComponent.get(position).getImage());
        viewHolder.label.setText(gridComponent.get(position).getLabel());

        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.cn_grid_image)
        ImageView image;
        @BindView(R.id.cn_grid_label)
        TextView label;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
