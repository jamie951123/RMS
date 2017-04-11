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

import com.example.james.rms.Operation.Adapter.ReceivingIncreaseDialogListAdapter;
import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 27/3/2017.
 */

public class ReceivingIncreaseDialog extends DialogFragment implements AdapterView.OnItemClickListener,Communicate_Interface,
        View.OnClickListener{
    @BindView(R.id.receiving_increase_dialog_listview)
    ListView listView;
    @BindView(R.id.receiving_increase_dialog_cancel)
    Button cancel;
    @BindView(R.id.receiving_increase_dialog_submit)
    Button submit;

    List<ReceivingIncreaseModel> rlAllModel = new ArrayList<>();
    List<ReceivingIncreaseModel> rlLastestModel = new ArrayList<>();

    private HashMap<Integer, Boolean> orginalIsSelected;

    private HashMap<Integer, Boolean> isSelected;

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
        receivingDialogListAdapter = new ReceivingIncreaseDialogListAdapter(getActivity(),rlLastestModel,isSelected);
        listView.setAdapter(receivingDialogListAdapter);
        listView.setOnItemClickListener(this);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("asd","position : " + position);
        ReceivingIncreaseDialogListAdapter.ViewHolder viewHolder= (ReceivingIncreaseDialogListAdapter.ViewHolder)view.getTag();
        viewHolder.receiving_increase_dialog_item_checkbox.toggle();
        receivingDialogListAdapter.getIsSelected().put(position,viewHolder.receiving_increase_dialog_item_checkbox.isChecked());
    }


    @Override
    public void onClick(View v) {
        HashMap<Integer, Boolean> checkMap = isSelected;
        List<ReceivingIncreaseModel> lastestModels = getNewProductModel(checkMap);

        switch (v.getId()){
            case R.id.receiving_increase_dialog_submit:
                ReceivingIncrease receivingIncrease = (ReceivingIncrease)getActivity();
                Communicate_Interface communicateInterface = receivingIncrease;
                communicateInterface.putLastestProductModel(lastestModels,checkMap);
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
            case R.id.receiving_increase_dialog_cancel:
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
        }
    }
    public List<ReceivingIncreaseModel> getNewProductModel(HashMap<Integer, Boolean> checkBoxList){
        Map<Integer,ReceivingIncreaseModel> nowListView = new HashMap<>();
        List<ReceivingIncreaseModel> lastModel = new ArrayList<>();
        for(Map.Entry<Integer,Boolean> entry: checkBoxList.entrySet()){
            Integer key    = entry.getKey();
            Boolean value = entry.getValue();
            if(!value){
                rlLastestModel.set(key,rlAllModel.get(key));
            }else{
                lastModel.add(rlLastestModel.get(key));
            }
//            if(value){
//                nowListView.put(key, this.rlLastestModel.get(key));
//            }
        }
        return lastModel;
    }

    @Override
    public void putOriginalProductModels(List<ReceivingIncreaseModel> rlAllModel,List<ReceivingIncreaseModel> rlLastestModel,HashMap<Integer, Boolean> isSelected) {
        this.rlAllModel = rlAllModel;
        this.rlLastestModel = rlLastestModel;
        this.isSelected = isSelected;
        this.orginalIsSelected = new HashMap<>(isSelected);
    }

    @Override
    public void putLastestProductModel(List<ReceivingIncreaseModel> lastestModel, HashMap<Integer, Boolean> isSelected) {
    }

}
