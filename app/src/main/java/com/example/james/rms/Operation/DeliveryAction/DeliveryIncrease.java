package com.example.james.rms.Operation.DeliveryAction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.MyDatePicker;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.DeliveryOrderCombine;
import com.example.james.rms.Core.Combine.DeliveryOrderSearchCombine;
import com.example.james.rms.Core.Combine.ReceivingOrderCombine;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.Communicate_Interface;
import com.example.james.rms.ITF.NumberDialogListener;
import com.example.james.rms.Operation.Adapter.DeliveryIncreaseItemExpandableAdapter;
import com.example.james.rms.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.james.rms.R.id.delivery_increase_datePicker;
import static com.example.james.rms.R.id.delivery_increase_fab;
/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryIncrease extends AppCompatActivity implements View.OnClickListener,MyDatePicker.MyDatePickerService,
        Communicate_Interface<ReceivingOrderModel>,NumberDialogListener,Cloneable {

    @BindView(R.id.delivery_increase_toolbar)
    Toolbar toolbar;
    @BindView(R.id.delivery_increase_remark)
    EditText remark_edit;
    @BindView(R.id.delivery_increase_fab)
    FloatingActionButton fab;
    @BindView(R.id.delivery_increase_toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.delivery_increase_datePicker)
    TextView datePicker;
    @BindView(R.id.delivery_increase_listview)
    AnimatedExpandableListView listView;

    //
    private String common_partyId;
    //
    private ExpandableSelectedModel expandableSelectedModel = new ExpandableSelectedModel();
    //
    private List<ReceivingOrderModel>  order_original;
    private List<ReceivingOrderModel>  order_latest;
    private List<ReceivingOrderModel>  order_listview;
    private DeliveryOrderModel deliveryOrderModel;
    //
    private DeliveryIncreaseItemExpandableAdapter deliveryIncreaseItemExpandableAdapter;
    //
    private LinkedHashMap<Long,DeliveryItemModel> orginalMapByReceivingItemId = new LinkedHashMap<>();
    private LinkedHashMap<Long,DeliveryItemModel> latestMapByReceivingItemId = new LinkedHashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_increase);
        ButterKnife.bind(this);
        datePicker.setOnClickListener(this);
        fab.setOnClickListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpToolbar();
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        common_partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //Combine
        String combine_partyIdAndstatus = DeliveryOrderSearchCombine.combine_partyIdAndStatus(common_partyId,Status.PROGRESS);
        //
        ReceivingOrderDao receivingOrderDao = new ReceivingOrderDaoImpl();
        List<ReceivingOrderModel> receivingOrderModels = receivingOrderDao.findByPartyIdAndStatus(combine_partyIdAndstatus);
        Log.d("asd","receivingOrderModels :" + receivingOrderModels);

        //Creat Original ReceivingOrder
        order_original = new ArrayList<>();
        for(ReceivingOrderModel r : receivingOrderModels){
            ReceivingOrderModel rOrder =r.newReceivingOrderModel();
            List<ReceivingItemModel> rItem = new ArrayList<>();
            for(ReceivingItemModel i : rOrder.getReceivingItem()){
                rItem.add(i.newReceivingItemModel());
            }
            rOrder.setReceivingItem(rItem);
            order_original.add(rOrder);
        }
        //Last ReceivingOrder
        order_latest = new ArrayList<>(receivingOrderModels);
        //Listview ReceivingOrder
        order_listview = new ArrayList<>();
        //order and child checkbox setup
        setOriginalCheckbox(order_original);
        //new DeliveryOrder
        deliveryOrderModel = new DeliveryOrderModel();
//        Intent
        String deliveryOrder_json = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                deliveryOrder_json = null;
            } else {
                deliveryOrder_json = extras.getString(StartActivityForResultKey.deliveryOrderModel);
            }
        }

        //Edit
        if (ObjectUtil.isNotNullEmpty(deliveryOrder_json)) {
            DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);
//            deliveryOrderModel = new DeliveryOrderModel();
            deliveryOrderModel = deliveryOrderCombine.jsonToModel(deliveryOrder_json);
            toolbar_title.setText(R.string.title_edit_delivery);
            getDeliveryModelMapWhenEdit(deliveryOrderModel.getDeliveryItem());
            Log.d("asd","deliveryOrder_json : " + deliveryOrder_json);
            Log.d("asd","orginalMapByReceivingItemId : " + orginalMapByReceivingItemId);
            for(ReceivingOrderModel order :order_latest){
                List<ReceivingItemModel> newItems = new ArrayList<>();
                    for(ReceivingItemModel item: order.getReceivingItem()) {
                        if (orginalMapByReceivingItemId.containsKey(item.getReceivingId())) {
                            newItems.add(item);
                        }
                    }
                    if(!newItems.isEmpty()){
                        order.setReceivingItem(newItems);
                        order_listview.add(order);
                    }
            }
            setLatestCheckBox(order_listview);
//            SetDeliveryOrder Field
            setAllField(deliveryOrderModel);
        }
        deliveryIncreaseItemExpandableAdapter = new DeliveryIncreaseItemExpandableAdapter(this,order_listview,orginalMapByReceivingItemId,listView);
        listView.setAdapter(deliveryIncreaseItemExpandableAdapter);
        setUpListView(order_listview);
        Log.v("asd","DeliveryIncrease-[order_original] :" + order_original);
    }

    private void setAllField(DeliveryOrderModel deliveryOrderModel) {
        remark_edit.setText(ObjectUtil.isNullEmpty(deliveryOrderModel.getRemark())?"":deliveryOrderModel.getRemark());
        datePicker.setText(ObjectUtil.dateToString_OnlyDate(deliveryOrderModel.getStockOutDate()));
    }


    //    WHen Edit
    private void getDeliveryModelMapWhenEdit(List<DeliveryItemModel> itemModels){
        orginalMapByReceivingItemId = new LinkedHashMap<>();
        latestMapByReceivingItemId = new LinkedHashMap<>();
        for(DeliveryItemModel item : itemModels){
            Long receivingItemId = item.getReceivingId();
            orginalMapByReceivingItemId.put(receivingItemId,item);
        }
        latestMapByReceivingItemId = orginalMapByReceivingItemId;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case delivery_increase_fab:
                createDialogBox();
                break;
            case delivery_increase_datePicker:
                showDatePicker();
                break;
        }
    }

    public void createDialogBox(){
        DeliveryIncreaseDialog deliveryIncreaseDialog = new DeliveryIncreaseDialog();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("delivery_increase");
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        deliveryIncreaseDialog.show(fm,"delivery_increase");
        Communicate_Interface communicateInterface = deliveryIncreaseDialog;
        communicateInterface.putOriginalProductModels(order_original,null,expandableSelectedModel);
        Toast.makeText(this,"DeliveryIncrease",Toast.LENGTH_SHORT).show();
    }

    private void showDatePicker(){
//        int mYear, mMonth, mDay;
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
        DialogFragment newFragment = new MyDatePicker();
        newFragment.show(getSupportFragmentManager(), StartActivityForResultKey.deliveryIncrease);
    }

    //Edit
    public void setLatestCheckBox(List<ReceivingOrderModel>  receivingOrderModel){
        for (int i=0; i<receivingOrderModel.size();i++){
            List<ReceivingItemModel> receivingItemModels = receivingOrderModel.get(i).getReceivingItem();
            for(int j=0; j< receivingItemModels.size();j++){
                Long existReceivingItemId = receivingItemModels.get(j).getReceivingId();
                if(expandableSelectedModel.getIsItemSelected().containsKey(existReceivingItemId))
                    expandableSelectedModel.getIsItemSelected().put(existReceivingItemId,true);
            }
        }
    }
    //Create
    public void setOriginalCheckbox(List<ReceivingOrderModel>  receivingOrderModel){
        LinkedHashMap<Long,Boolean> isOrderSelected = new LinkedHashMap<>();
        LinkedHashMap<Long,Boolean> isItemSelected = new LinkedHashMap<>();

        for (int i=0; i<receivingOrderModel.size();i++){
            isOrderSelected.put(receivingOrderModel.get(i).getOrderId(),false);

            List<ReceivingItemModel> receivingItemModels = receivingOrderModel.get(i).getReceivingItem();
            for(int j=0; j< receivingItemModels.size();j++){
                isItemSelected.put(receivingItemModels.get(j).getReceivingId(),false);
            }
            expandableSelectedModel.setIsOrderSelected(isOrderSelected);
            expandableSelectedModel.setIsItemSelected(isItemSelected);

        }
    }

    private void setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setNavigationIcon(R.drawable.back_white);
            toolbar_title.setText(R.string.delivery_increase_title);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassicDialog classicDialog = new ClassicDialog(v.getContext());
                    classicDialog.showBackPrevious(getString(R.string.previous));
                }
            });
        }
    }

//    Save
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        DeliveryOrderModel deliveryOrderModel = new DeliveryOrderModel();
        List<DeliveryItemModel> deliveryItemModels = new ArrayList<>();
        int itemCount = 0;
        Date createDate = new Date();
        for(Map.Entry<Long,DeliveryItemModel> entry : latestMapByReceivingItemId.entrySet()){
            DeliveryItemModel item = entry.getValue();
            item.setItemCreateDate(createDate);
            item.setItemStockOutDate(ObjectUtil.stringToDate_onlyDate(datePicker.getText().toString()));
            deliveryItemModels.add(item);
            itemCount++;
        }
//        deliveryOrderModel.setOrderId(new Long(6));
        deliveryOrderModel.setItemQty(itemCount);
        deliveryOrderModel.setRemark(remark_edit.getText().toString());
        deliveryOrderModel.setStockOutDate(ObjectUtil.stringToDate_onlyDate(datePicker.getText().toString()));
        deliveryOrderModel.setCreateDate(createDate);
        deliveryOrderModel.setStatus(Status.PROGRESS.name());
        deliveryOrderModel.setPartyId(common_partyId);
        deliveryOrderModel.setCreateBy(common_partyId);
        deliveryOrderModel.setDeliveryItem(deliveryItemModels);

        DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);
        String deliveryOrder_json = deliveryOrderCombine.modelToJson(deliveryOrderModel);

        DeliveryOrderDao deliveryOrderDao = new DeliveryOrderDaoImpl();
        DeliveryOrderModel saveResult = deliveryOrderDao.saveOrderAndItem(deliveryOrder_json);

        if (saveResult != null) {
            Intent intent = new Intent();
            intent.putExtra("NavigationController", StartActivityForResultKey.navDelivery);
            intent.setClass(this, NavigationController.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(menuItem);
    }
    @Override
    public void passDateToReceivingIncrease(String date_str, Date date) {
        datePicker.setText(date_str);
        Log.v("asd","passDateToReceivingIncrease :" +date_str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delivery_increase_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ClassicDialog classicDialog = new ClassicDialog(this);
        classicDialog.showBackPrevious(getString(R.string.previous));
    }

    @Override
    public void putOriginalProductModels(List<ReceivingOrderModel> item_original, List<ReceivingOrderModel> item_latest, ExpandableSelectedModel expandableSelectModel) {

    }

    @Override
    public void putLatestProductModel(List<ReceivingOrderModel> item_listview, ExpandableSelectedModel expandableSelectModel) {
        //item_listview from  Dialog
        Log.v("asd","[DeliveryIncrease]-[ListView_Status]-[List<ReceivingOrderModel>]-[item_listview] :" + item_listview);
        Log.v("asd","[DeliveryIncrease]-[ListView_Status]-[ExpandableSelectedModel] :" + expandableSelectModel);
        getDeliveryModelMap(item_listview);
        deliveryIncreaseItemExpandableAdapter = new DeliveryIncreaseItemExpandableAdapter(this,item_listview,latestMapByReceivingItemId,listView);
        listView.setAdapter(deliveryIncreaseItemExpandableAdapter);
        setUpListView(item_listview);
    }

    //When From Dialogs submit
    private  void getDeliveryModelMap(List<ReceivingOrderModel> item_listview){
        //clear deliveryitem Qty and Gw value when added in the past.
        latestMapByReceivingItemId = new LinkedHashMap<>();
        for(ReceivingOrderModel order : item_listview){
            List<ReceivingItemModel> items  = order.getReceivingItem();
            for(ReceivingItemModel item : items){
                if(orginalMapByReceivingItemId.containsKey(item.getReceivingId())) {
                    latestMapByReceivingItemId.put(item.getReceivingId(), orginalMapByReceivingItemId.get(item.getReceivingId()));
                }else{
                    latestMapByReceivingItemId.put(item.getReceivingId(), item.newDeliveryItemModel());
                }
            }
        }
        orginalMapByReceivingItemId = latestMapByReceivingItemId;
    }
    private void setUpListView(List<ReceivingOrderModel> item_listview){
        listView.setGroupIndicator(null);
        listView.setChildIndicator(null);
        listView.setDivider(ContextCompat.getDrawable(this,R.color.black1F1F1F));
        listView.setChildDivider(ContextCompat.getDrawable(this,R.color.transperent_color));
        listView.setDividerHeight(5);
        for(int i=0; i<item_listview.size();i++){
            listView.expandGroup(i);

        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }

    @Override
    public void from(NumberDialogModel numberDialogModel, Object o) {
        //Nothins to do
        return;
    }

    @Override
    public void returnData(NumberDialogModel numberDialogModel) {
        switch (numberDialogModel.getKey()){
            case KeyModel.qty:
                latestMapByReceivingItemId.get(numberDialogModel.getId()).setItemQty(numberDialogModel.getQty());
                break;
            case KeyModel.gw:
                latestMapByReceivingItemId.get(numberDialogModel.getId()).setItemGrossWeight(numberDialogModel.getGrossWeight());
                break;
        }
        Log.d("asd","[NumberDialog]-->[DeliveryIncrease]-[order_latest] :" + orginalMapByReceivingItemId);
        deliveryIncreaseItemExpandableAdapter.notifyDataSetChanged();
    }
}
