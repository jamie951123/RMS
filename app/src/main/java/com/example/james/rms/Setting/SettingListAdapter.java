package com.example.james.rms.Setting;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/4.
 */

public class SettingListAdapter  extends BaseSwipeAdapter {
    private Context mContext;
    private List<WeightProfileModel> list;

    public SettingListAdapter(Context mContext, List<WeightProfileModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
      return R.id.setting_swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.setting_swipe_listitem, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.setting_front_image));
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.setting_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        ViewHolder viewHolder = new ViewHolder(convertView);
        //front
        viewHolder.front_image.setImageDrawable(ContextCompat.getDrawable(this.mContext,R.drawable.industrial_scales_color));
        viewHolder.front_unit.setText(getItem(position).getWeightUnit());
        viewHolder.behind_edit_unit.setText(getItem(position).getWeightUnit());
        viewHolder.behind_image.setImageDrawable(ContextCompat.getDrawable(this.mContext,R.drawable.pencil_color));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public WeightProfileModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        //front
        @BindView(R.id.setting_front_image)
        de.hdodenhof.circleimageview.CircleImageView front_image;
        @BindView(R.id.setting_front_unit)
        TextView front_unit;
        //behind
        @BindView(R.id.setting_edit_unit)
        EditText behind_edit_unit;
        @BindView(R.id.setting_behind_image)
        de.hdodenhof.circleimageview.CircleImageView behind_image;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
