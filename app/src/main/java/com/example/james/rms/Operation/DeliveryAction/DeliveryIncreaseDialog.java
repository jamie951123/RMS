package com.example.james.rms.Operation.DeliveryAction;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Operation.Adapter.DeliveryDialogExpandableAdapter;
import com.example.james.rms.ITF.Communicate_Interface;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryIncreaseDialog extends DialogFragment implements Communicate_Interface<ReceivingOrderModel>,AdapterView.OnItemClickListener,
        View.OnClickListener{

    @BindView(R.id.delivery_increase_dialog_listview)
    AnimatedExpandableListView listView;
    @BindView(R.id.delivery_increase_dialog_cancel)
    Button cancel;
    @BindView(R.id.delivery_increase_dialog_submit)
    Button submit;

    //
    private List<ReceivingOrderModel>  item_original;
    private List<ReceivingOrderModel>  item_latest;
    //
    private ExpandableSelectedModel expandableSelectedModel;

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

        DeliveryDialogExpandableAdapter deliveryDialogExpandableAdapter = new DeliveryDialogExpandableAdapter(getActivity(),item_original,expandableSelectedModel,listView);
        listView.setAdapter(deliveryDialogExpandableAdapter);
        listView.setGroupIndicator(null);
        listView.setChildIndicator(null);
        listView.setDivider(ContextCompat.getDrawable(getActivity(),R.color.black1F1F1F));
        listView.setChildDivider(ContextCompat.getDrawable(getActivity(),R.color.transperent_color));
        listView.setDividerHeight(5);
        for(int i=0; i<item_original.size();i++){
            listView.expandGroup(i);

        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
//        listView.expandGroup()
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delivery_increase_dialog_submit:

                List<ReceivingOrderModel> deliveryOrderModels = new ArrayList<>();

                DeliveryIncrease deliveryIncrease = (DeliveryIncrease)getActivity();
                Communicate_Interface communicateInterface = deliveryIncrease;
                communicateInterface.putLatestProductModel(deliveryOrderModels,expandableSelectedModel);
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


    @Override
    public void putOriginalProductModels(List<ReceivingOrderModel> item_original, List<ReceivingOrderModel> item_latest, ExpandableSelectedModel expandableSelectModel) {
        this.item_original = item_original;
        this.item_latest = item_latest;
        this.expandableSelectedModel = expandableSelectModel;
    }

    @Override
    public void putLatestProductModel(List<ReceivingOrderModel> item_listview, ExpandableSelectedModel expandableSelectModel) {

    }
}
