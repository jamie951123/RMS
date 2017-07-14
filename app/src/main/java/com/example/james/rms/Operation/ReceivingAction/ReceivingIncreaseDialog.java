package com.example.james.rms.Operation.ReceivingAction;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.ITF.Communicate_Interface;
import com.example.james.rms.Operation.Adapter.ReceivingIncreaseDialogListAdapter;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 27/3/2017.
 */

public class ReceivingIncreaseDialog extends DialogFragment implements AdapterView.OnItemClickListener,Communicate_Interface<ReceivingItemModel>,
        View.OnClickListener{
    @BindView(R.id.receiving_increase_dialog_listview)
    ListView listView;
    @BindView(R.id.receiving_increase_dialog_cancel)
    Button cancel;
    @BindView(R.id.receiving_increase_dialog_submit)
    Button submit;

    //
    private List<ReceivingItemModel>  item_original;
    private List<ReceivingItemModel>  item_latest;
    //
    private ExpandableSelectedModel expandableSelectModel;
    //
    ReceivingIncreaseDialogListAdapter receivingDialogListAdapter;

    public ReceivingIncreaseDialog() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.receiving_increase_dialog, container);
        ButterKnife.bind(this,view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setCanceledOnTouchOutside(false);
        receivingDialogListAdapter = new ReceivingIncreaseDialogListAdapter(getActivity(),item_latest,expandableSelectModel);
        listView.setAdapter(receivingDialogListAdapter);
        listView.setOnItemClickListener(this);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReceivingIncreaseDialogListAdapter.ViewHolder viewHolder= (ReceivingIncreaseDialogListAdapter.ViewHolder)view.getTag();
        viewHolder.receiving_increase_dialog_item_checkbox.toggle();
        receivingDialogListAdapter.getIsSelected().put(item_latest.get(position).getProductId(),viewHolder.receiving_increase_dialog_item_checkbox.isChecked());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.receiving_increase_dialog_submit:
                Log.d("asd","[ReceivingIncreaseDialog]--[click]-[Submit]");
                List<ReceivingItemModel> receivingItemModels = getNewProductModel(expandableSelectModel.getIsItemSelected());
                Log.d("asd","[ReceivingIncreaseDialog]--[Submit]-[receivingItemModels] :" + receivingItemModels);
                ReceivingIncrease receivingIncrease = (ReceivingIncrease)getActivity();
                Communicate_Interface communicateInterface = receivingIncrease;
                communicateInterface.putLatestProductModel(receivingItemModels,expandableSelectModel);
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
            case R.id.receiving_increase_dialog_cancel:
                Log.d("asd","[ReceivingIncreaseDialog]--[click]-[Cancel]");
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
        }
    }
    public List<ReceivingItemModel> getNewProductModel(LinkedHashMap<Long, Boolean> checkBoxList){
        List<ReceivingItemModel> item_listview = new ArrayList<>();

        //original
        HashMap<Long,ReceivingItemModel> original_map = new LinkedHashMap<>();
        for(ReceivingItemModel item : item_original){
            original_map.put(item.getProductId(),item);
        }
        Log.d("asd","[ReceivingIncreaseDialog]--[Submit]-[getNewProductModel] -[original_map]:" +original_map);
        //lastest
        HashMap<Long,ReceivingItemModel> lastest_map = new LinkedHashMap<>();
        for(ReceivingItemModel item : item_latest){
            lastest_map.put(item.getProductId(),item);
        }
        Log.d("asd","[ReceivingIncreaseDialog]--[Submit]-[getNewProductModel]-[lastest_map] :" +lastest_map);

        //
        int position = 0;
        for(Map.Entry<Long,Boolean> entry: checkBoxList.entrySet()){
            Long key    = entry.getKey();
            Boolean value = entry.getValue();
            if(value){
                item_listview.add(lastest_map.get(key));
            }else{
                item_latest.set(position,original_map.get(key).newReceivingItemModel());
                //clear vlaue
            }
            position++;
        }
        return item_listview;
    }


    @Override
    public void putOriginalProductModels(List<ReceivingItemModel> item_original, List<ReceivingItemModel> item_latest, ExpandableSelectedModel expandableSelectModel) {
        this.item_original = item_original;
        this.item_latest = item_latest;
        this.expandableSelectModel = expandableSelectModel;
//        if(expandableSelectModel.getCount_dialogBox() == 1) {
//            this.onlyOriginalClicked = new LinkedHashMap<>(DeepCopyUtil.copyLinkedHashMap_Long_Boolean(expandableSelectModel.getIsItemSelected()));
//        }
//
//        if(this.item_original.get(0) == item_original.get(0)){
//            Log.d("asd","Same");
//        }else{
//            Log.d("asd","Different");
//
//        }
        Log.d("asd","[ReceivingIncreaseDialog]--[item_original] :" + item_original);
        Log.d("asd","[ReceivingIncreaseDialog]--[item_latest] :" + item_latest);
        Log.d("asd","[ReceivingIncreaseDialog]--[expandableSelectModel] :" + expandableSelectModel);
    }

    @Override
    public void putLatestProductModel(List<ReceivingItemModel> item_listview, ExpandableSelectedModel expandableSelectModel) {

    }
}
