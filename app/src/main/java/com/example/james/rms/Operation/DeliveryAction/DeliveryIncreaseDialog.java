package com.example.james.rms.Operation.DeliveryAction;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Operation.Adapter.DeliveryDialogExpandableAdapter;
import com.example.james.rms.Operation.ReceivingAction.Communicate_Interface;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryIncreaseDialog extends DialogFragment implements Communicate_Interface<DeliveryOrderModel>,AdapterView.OnItemClickListener,
        View.OnClickListener{

    @BindView(R.id.delivery_increase_dialog_listview)
    AnimatedExpandableListView listView;
    @BindView(R.id.delivery_increase_dialog_cancel)
    Button cancel;
    @BindView(R.id.delivery_increase_dialog_submit)
    Button submit;

    //
    private List<DeliveryOrderModel>  item_original;
    private List<DeliveryOrderModel>  item_latest;
    //
    private LinkedHashMap<Long, Boolean> isItemSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delivery_increase_dialog, container);
        ButterKnife.bind(this,view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);

        DeliveryDialogExpandableAdapter deliveryDialogExpandableAdapter = new DeliveryDialogExpandableAdapter(getActivity(),item_original,isItemSelected);
        listView.setAdapter(deliveryDialogExpandableAdapter);
        return view;
    }


    @Override
    public void putOriginalProductModels(List<DeliveryOrderModel> item_original, List<DeliveryOrderModel> item_latest, LinkedHashMap<Long, Boolean> isSelected) {
        this.item_original = item_original;
        this.item_latest = item_latest;
        this.isItemSelected = isSelected;
    }

    @Override
    public void putLatestProductModel(List<DeliveryOrderModel> item_listview, LinkedHashMap<Long, Boolean> isSelected) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delivery_increase_dialog_submit:
                LinkedHashMap<Long, Boolean> checkMap = isItemSelected;
//                List<DeliveryOrderModel> deliveryOrderModels = getNewProductModel(checkMap);
                List<DeliveryOrderModel> deliveryOrderModels = new ArrayList<>();

                DeliveryIncrease deliveryIncrease = (DeliveryIncrease)getActivity();
                Communicate_Interface communicateInterface = deliveryIncrease;
                communicateInterface.putLatestProductModel(deliveryOrderModels,checkMap);
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
            case R.id.delivery_increase_dialog_cancel:
                if (getDialog().isShowing()){
                    getDialog().dismiss();
                }
                break;
        }
    }
}
