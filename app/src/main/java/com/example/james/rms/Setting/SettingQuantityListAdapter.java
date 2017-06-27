package com.example.james.rms.Setting;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.example.james.rms.CommonProfile.ActivityUtil;
import com.example.james.rms.CommonProfile.Listview.ListViewGrowthUtil;
import com.example.james.rms.CommonProfile.MyBaseSwipeAdapter;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.Core.Combine.QuantityProfileCombine;
import com.example.james.rms.Core.Combine.SettingSearchCombine;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.R;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/10.
 */

public class SettingQuantityListAdapter extends MyBaseSwipeAdapter<QuantityProfileModel> {

    private ListView listView;
    private String partyId;
    public SettingQuantityListAdapter(Context mContext, List<QuantityProfileModel> list,ListView listView,String partyId) {
        this.mContext = mContext;
        this.list = list;
        this.listView = listView;
        this.partyId = partyId;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.setting_qty_swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(getmContext()).inflate(R.layout.setting_qty_swipe_listitem, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.setting_qty_front_image));
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(getmContext(), "qty DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.setting_qty_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuantityProfileModel qtymodel = getItem(position);
                QuantityProfileCombine quantityProfileCombine = new QuantityProfileCombine(QuantityProfileModel.class);
                String gson = quantityProfileCombine.modelToJson(qtymodel);

//                ProductDao productDao = new ProductDaoImpl();
//                Integer deleteCount = productDao.updateQuantityIdNullByQuantityIdAndPartyId(gson);
//                if(deleteCount != null){
                    QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();
                    ResponseMessage responseMessage = quantityProfileDao.delete(gson);
                    if(responseMessage != null && responseMessage.getMessage_status().equalsIgnoreCase(ResponseStatus.getSuccessful())){
                        getList().remove(position);
                        notifyDataSetChanged();
                        ListViewGrowthUtil.setListViewHeightBasedOnChildren(listView);
                        Toast.makeText(getmContext(), responseMessage.getMessage_content(), Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    Toast.makeText(getmContext(), "Delete fail", Toast.LENGTH_SHORT).show();
//                }
                Toast.makeText(getmContext(), "deleteCount is null", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SettingQuantityListAdapter.ViewHolder viewHolder = new SettingQuantityListAdapter.ViewHolder(convertView);
        //front
        viewHolder.front_image.setImageDrawable(ContextCompat.getDrawable(getmContext(),R.drawable.box_color));
        viewHolder.front_unit.setText(getItem(position).getQuantityUnit());
        viewHolder.behind_edit_unit.setText(getItem(position).getQuantityUnit());
        viewHolder.behind_image.setImageDrawable(ContextCompat.getDrawable(getmContext(),R.drawable.pencil_color));
        viewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastestUnit = viewHolder.behind_edit_unit.getText().toString();
                QuantityProfileModel quantityProfileModel = getItem(position);
                quantityProfileModel.setQuantityUnit(lastestUnit);
                quantityProfileModel.setLastModifiedBy(partyId);
                quantityProfileModel.setLastModifiedDate(new Date());
                quantityProfileModel.setStatus(Status.PROGRESS);
                QuantityProfileCombine quantityProfileCombine = new QuantityProfileCombine(QuantityProfileModel.class);
                String json = quantityProfileCombine.modelToJson(quantityProfileModel);
                //Service
                QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();
                QuantityProfileModel result = quantityProfileDao.save(json);
                if(result != null){
                    viewHolder.front_unit.setText(lastestUnit);
                    ((SwipeLayout)(listView.getChildAt(position - listView.getFirstVisiblePosition()))).close(true);
                    ActivityUtil.hideSoftKeyboard((Activity)getmContext());
                    Log.d("asd","[SettingQuantityListAdapter]-[save]-[Successful] : " + result);
                }else{
                    Log.d("asd","[SettingQuantityListAdapter]-[save] -[Fail] : " + result);
                }

            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }
    class ViewHolder{
        //front
        @BindView(R.id.setting_qty_front_image)
        de.hdodenhof.circleimageview.CircleImageView front_image;
        @BindView(R.id.setting_qty_front_unit)
        TextView front_unit;
        //behind
        @BindView(R.id.setting_qty_edit_unit)
        EditText behind_edit_unit;
        @BindView(R.id.setting_qty_behind_image)
        de.hdodenhof.circleimageview.CircleImageView behind_image;
        @BindView(R.id.setting_qty_save)
        Button save;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

}
