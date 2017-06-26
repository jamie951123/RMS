package com.example.james.rms.Operation.DeliveryAction;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Operation.Adapter.DeliveryIncreaseDialogExpandableAdapter;
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

public class DeliveryIncreaseDialog extends DialogFragment implements Communicate_Interface<ReceivingOrderModel>,
        View.OnClickListener, ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

    @BindView(R.id.delivery_increase_dialog_listview)
    AnimatedExpandableListView listView;
    @BindView(R.id.delivery_increase_dialog_cancel)
    Button cancel;
    @BindView(R.id.delivery_increase_dialog_submit)
    Button submit;

    //
    private List<ReceivingOrderModel> item_original;
    private List<ReceivingOrderModel> item_latest;
    //
    private ExpandableSelectedModel expandableSelectedModel;

    //
    private DeliveryIncreaseDialogExpandableAdapter deliveryIncreaseDialogExpandableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delivery_increase_dialog, container);
        ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);

        deliveryIncreaseDialogExpandableAdapter = new DeliveryIncreaseDialogExpandableAdapter(getActivity(), item_latest, expandableSelectedModel, listView);
        listView.setAdapter(deliveryIncreaseDialogExpandableAdapter);
        listView.setGroupIndicator(null);
        listView.setChildIndicator(null);
        listView.setDivider(ContextCompat.getDrawable(getActivity(), R.color.black1F1F1F));
        listView.setChildDivider(ContextCompat.getDrawable(getActivity(), R.color.transperent_color));
        listView.setDividerHeight(5);
        for (int i = 0; i < item_latest.size(); i++) {
            listView.expandGroup(i);

        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delivery_increase_dialog_submit:

                List<ReceivingOrderModel> listview_model = getSelectedReceiving(expandableSelectedModel);
                DeliveryIncrease deliveryIncrease = (DeliveryIncrease) getActivity();
                Communicate_Interface communicateInterface = deliveryIncrease;
                communicateInterface.putLatestProductModel(listview_model, expandableSelectedModel);
                if (getDialog().isShowing()) {
                    getDialog().dismiss();
                }
                break;
            case R.id.delivery_increase_dialog_cancel:
                if (getDialog().isShowing()) {
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

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        DeliveryIncreaseDialogExpandableAdapter.ChildHolder childHolder = (DeliveryIncreaseDialogExpandableAdapter.ChildHolder) view.getTag();
        childHolder.child_checkbox.toggle();
        ReceivingItemModel receivingItemModel = item_latest.get(i).getReceivingItem().get(i1);
        deliveryIncreaseDialogExpandableAdapter.getExpandableSelectedModel().getIsItemSelected().put(receivingItemModel.getReceivingId(), childHolder.child_checkbox.isChecked());
//        Log.d("asd","onChildClick --i :" + i + "---i1 :" + i1 + "----isChecked :" + childHolder.child_checkbox.isChecked());
        return true;
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
        DeliveryIncreaseDialogExpandableAdapter.GroupHolder groupHolder = (DeliveryIncreaseDialogExpandableAdapter.GroupHolder) view.getTag();
        groupHolder.group_checkbox.toggle();
        deliveryIncreaseDialogExpandableAdapter.getExpandableSelectedModel().getIsOrderSelected().put(item_latest.get(i).getOrderId(), groupHolder.group_checkbox.isChecked());
//        Log.d("asd","onGroupClick --i :" + i + "----isChecked :" + groupHolder.group_checkbox.isChecked());
        return true;
    }

    public List<ReceivingOrderModel> getSelectedReceiving(ExpandableSelectedModel expandableSelectedModel) {

//        InnerClassModel innerClassModel = new InnerClassModel();
//        LinkedHashMap<Long, ReceivingOrderModel> orderMap = new LinkedHashMap<>();
//        LinkedHashMap<Long, DeliveryItemModel> deliveryItemMapByReceivingItemId = new LinkedHashMap<>();

        List<ReceivingOrderModel> newModel = new ArrayList<>();
        boolean isSelected;
        for (ReceivingOrderModel order : this.item_latest) {
            isSelected = false;
            List<ReceivingItemModel> itemModel = new ArrayList<>();
            for (ReceivingItemModel item : order.getReceivingItem()) {
                if (expandableSelectedModel.getIsItemSelected().containsKey(item.getReceivingId()) && expandableSelectedModel.getIsItemSelected().get(item.getReceivingId())) {
                    itemModel.add(item.newReceivingItemModel());
                    isSelected = true;
//                    deliveryItemMapByReceivingItemId.put(item.getOrderId(), item.newDeliveryItemModel());
                }
            }
            if (isSelected) {
                order.setReceivingItem(itemModel);
                newModel.add(order);
            }
        }

//        innerClassModel.setReceivingOrderModels(newModel);
//        innerClassModel.setDeliveryMapByReceivingItemId(deliveryItemMapByReceivingItemId);
        return newModel;
    }

//    private static class InnerClassModel{
//        List<ReceivingOrderModel> ReceivingOrderModels;
//        LinkedHashMap<Long, DeliveryItemModel> deliveryMapByReceivingItemId;
//
//        public List<ReceivingOrderModel> getReceivingOrderModels() {
//            return ReceivingOrderModels;
//        }
//
//        public void setReceivingOrderModels(List<ReceivingOrderModel> receivingOrderModels) {
//            ReceivingOrderModels = receivingOrderModels;
//        }
//
//        public LinkedHashMap<Long, DeliveryItemModel> getDeliveryMapByReceivingItemId() {
//            return deliveryMapByReceivingItemId;
//        }
//
//        public void setDeliveryMapByReceivingItemId(LinkedHashMap<Long, DeliveryItemModel> deliveryMapByReceivingItemId) {
//            this.deliveryMapByReceivingItemId = deliveryMapByReceivingItemId;
//        }
//    }
}
