package com.example.james.rms.Setting;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.example.james.rms.CommonProfile.Util.ActivityUtil;
import com.example.james.rms.CommonProfile.Listview.ListViewUtil;
import com.example.james.rms.CommonProfile.MyAdapter.MyBaseSwipeAdapter;
import com.example.james.rms.CommonProfile.ResponseStatus;
import com.example.james.rms.Core.Combine.WeightProfileCombine;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.ResponseMessage;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.R;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamie on 2017/5/4.
 */

public class SettingWeightListAdapter extends MyBaseSwipeAdapter<WeightProfileModel> {

    private ListView listView;
    private String partyId;
    //Dao
    private WeightProfileDao weightProfileDao;

    public SettingWeightListAdapter(Context mContext, List<WeightProfileModel> list, ListView listView,String partyId) {
        this.mContext = mContext;
        this.list = list;
        this.listView = listView;
        this.partyId = partyId;
        weightProfileDao = new WeightProfileDaoImpl((AppCompatActivity)mContext);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
      return R.id.setting_weight_swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = LayoutInflater.from(getmContext()).inflate(R.layout.setting_weight_swipe_listitem, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.setting_weight_front_image));
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(getmContext(), "weight DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.setting_weight_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeightProfileModel w = getItem(position);
                WeightProfileCombine weightProfileCombine = new WeightProfileCombine(WeightProfileModel.class);
                String gson = weightProfileCombine.modelToJson(w);

//                ProductDao productDao = new ProductDaoImpl();
//                Integer deleteCount = productDao.updateWeightIdNullByWeightIdAndPartyId(gson);
//                if(deleteCount != null){
                    ResponseMessage responseMessage = weightProfileDao.delete(gson);
                    if(responseMessage != null && ResponseStatus.getSuccessful().equalsIgnoreCase(responseMessage.getMessage_status())){
                        getList().remove(position);
                        notifyDataSetChanged();
                        ListViewUtil.setListViewHeightBasedOnChildren(listView);

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
        final ViewHolder viewHolder = new ViewHolder(convertView);
        //front
        viewHolder.front_image.setImageDrawable(ContextCompat.getDrawable(getmContext(),R.drawable.industrial_scales_color));
        viewHolder.front_unit.setText(getItem(position).getWeightUnit());
        viewHolder.behind_edit_unit.setText(getItem(position).getWeightUnit());
        viewHolder.behind_image.setImageDrawable(ContextCompat.getDrawable(getmContext(),R.drawable.pencil_color));
        viewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastestUnit = viewHolder.behind_edit_unit.getText().toString();
                WeightProfileModel weightProfileModel = getItem(position);
                weightProfileModel.setWeightUnit(lastestUnit);
                weightProfileModel.setLastModifiedBy(partyId);
                weightProfileModel.setLastModifiedDate(new Date());
                weightProfileModel.setStatus(Status.PROGRESS);
                WeightProfileCombine weightProfileCombine = new WeightProfileCombine(WeightProfileModel.class);
                String json = weightProfileCombine.modelToJson(weightProfileModel);
                //Service
                weightProfileModel = weightProfileDao.save(json);
                if(weightProfileModel != null){
                    viewHolder.front_unit.setText(lastestUnit);
                    ((SwipeLayout)(listView.getChildAt(position - listView.getFirstVisiblePosition()))).close(true);
                    ActivityUtil.hideSoftKeyboard((Activity)getmContext());
                    Log.d("asd","[SettingWeightListAdapter]-[save]-[Successful] : " + weightProfileModel);
                }else{
                    Log.d("asd","[SettingWeightListAdapter]-[save] -[Fail] : " + weightProfileModel);
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
        @BindView(R.id.setting_weight_front_image)
        de.hdodenhof.circleimageview.CircleImageView front_image;
        @BindView(R.id.setting_weight_front_unit)
        TextView front_unit;
        //behind
        @BindView(R.id.setting_weight_edit_unit)
        EditText behind_edit_unit;
        @BindView(R.id.setting_weight_behind_image)
        de.hdodenhof.circleimageview.CircleImageView behind_image;
        @BindView(R.id.setting_weight_save)
        Button save;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
