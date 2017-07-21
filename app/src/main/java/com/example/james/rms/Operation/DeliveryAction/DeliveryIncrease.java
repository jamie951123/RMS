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

import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Util.DeepCopyUtil;
import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.MyDatePicker;
import com.example.james.rms.CommonProfile.Library.AnimatedExpandableListView;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.DeliveryOrderCombine;
import com.example.james.rms.Core.Combine.DeliveryOrderSearchCombine;
import com.example.james.rms.Core.Combine.MovementRecordCombine;
import com.example.james.rms.Core.Dao.DeliveryItemDao;
import com.example.james.rms.Core.Dao.DeliveryItemDaoImpl;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.MovementRecord;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.Communicate_Interface;
import com.example.james.rms.ITF.NumberDialogListener;
import com.example.james.rms.Operation.Adapter.DeliveryIncreaseItemExpandableAdapter;
import com.example.james.rms.R;
import com.facebook.FacebookSdk;
import com.github.clans.fab.FloatingActionButton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

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

    //MyPreferences
    private MyPreferences myPreferences;
    private String partyId;
    private String combine_partyIdAndstatus;
    //
    private ExpandableSelectedModel expandableSelectedModel = new ExpandableSelectedModel();
    //
    private List<ReceivingOrderModel>  order_original;
    private List<ReceivingOrderModel>  order_latest;
    private List<ReceivingOrderModel>  order_listview;
    //PutExtra
    private DeliveryOrderModel deliveryOrderModel;
    private MovementRecord movementRecord;
    //
    private DeliveryIncreaseItemExpandableAdapter deliveryIncreaseItemExpandableAdapter;
    //
    private LinkedHashMap<Long,DeliveryItemModel> orginalMapByReceivingItemId = new LinkedHashMap<>();
    private LinkedHashMap<Long,DeliveryItemModel> latestMapByReceivingItemId = new LinkedHashMap<>();
    //itemOutSatnding
    private LinkedHashMap<Long,ItemOutStanding> itemOutStandingMap = new LinkedHashMap<>();
    //Dao
    private ReceivingOrderDao receivingOrderDao;
    private DeliveryOrderDao deliveryOrderDao;
    private DeliveryItemDao deliveryItemDao;
    //Combine
    private MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.delivery_increase);
        ButterKnife.bind(this);
        //Dao
        receivingOrderDao = new ReceivingOrderDaoImpl(this);
        deliveryOrderDao = new DeliveryOrderDaoImpl(this);
        deliveryItemDao = new DeliveryItemDaoImpl(this);
        //
        datePicker.setOnClickListener(this);
        fab.setOnClickListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpToolbar();
        //Preferences
        myPreferences = new MyPreferences(this, PreferencesKey.login_information);
        partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //partyId
        //Combine
        combine_partyIdAndstatus = DeliveryOrderSearchCombine.combine_partyIdAndStatus(partyId,Status.PROGRESS);
        //Query
        List<ReceivingOrderModel> receivingOrderModels = receivingOrderDao.findByPartyIdAndStatus(combine_partyIdAndstatus);
        Log.d("asd","receivingOrderModels :" + receivingOrderModels);
        //itemMapByReceivingItemId
        getitemOutStandingMapByReceivingItemId();

        //

        //Creat Original ReceivingOrder
        order_original = new ArrayList<>();
//        boolean isContinueOutter = true;
        for(ReceivingOrderModel r : receivingOrderModels){
            ReceivingOrderModel rOrder =r.newReceivingOrderModel();
            List<ReceivingItemModel> rItem = new ArrayList<>();
            for(ReceivingItemModel i : rOrder.getReceivingItem()){
                ReceivingItemModel newReceivingItem = i.newReceivingItemModel();
                if(itemOutStandingMap.containsKey(newReceivingItem.getReceivingId())){
                    Integer outStanding_qty = null;
                    BigDecimal outStanding_w = null;
                    if(newReceivingItem.getItemQty() != null) {
                        outStanding_qty = newReceivingItem.getItemQty()-itemOutStandingMap.get(newReceivingItem.getReceivingId()).getQty();
                        newReceivingItem.setOutStandingQty(outStanding_qty);
                    }
                    if(newReceivingItem.getItemGrossWeight() != null){
                        outStanding_w = newReceivingItem.getItemGrossWeight().subtract(itemOutStandingMap.get(newReceivingItem.getReceivingId()).getWeight());
                        newReceivingItem.setOutStandingWeight(outStanding_w);
                    }
                }
                if(newReceivingItem.getOutStandingQty() == null) newReceivingItem.setOutStandingQty(newReceivingItem.getItemQty());
                if(newReceivingItem.getOutStandingWeight() == null) newReceivingItem.setOutStandingWeight(newReceivingItem.getItemGrossWeight());
                rItem.add(newReceivingItem);
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

        //new DeliveryOrder (put Extra)
        deliveryOrderModel = new DeliveryOrderModel();
        movementRecord = new MovementRecord();
        //
        String deliveryOrder_json = null;
        String movementRecord_json = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                deliveryOrder_json = null;
                movementRecord_json = null;
            } else {
                deliveryOrder_json = extras.getString(StartActivityForResultKey.deliveryOrderModel);
                movementRecord_json = extras.getString(StartActivityForResultKey.movementRecord);
            }
        }

        if(ObjectUtil.isNotNullEmpty(movementRecord_json)){
            movementRecord = movementRecordCombine.jsonToModel(movementRecord_json);
        }
        //Edit Increase
        isEditIncrease(deliveryOrder_json);

        //Set ListView
        deliveryIncreaseItemExpandableAdapter = new DeliveryIncreaseItemExpandableAdapter(this,order_listview,orginalMapByReceivingItemId,listView);
        listView.setAdapter(deliveryIncreaseItemExpandableAdapter);
        setUpListView(order_listview);
        Log.v("asd","DeliveryIncrease-[order_original] :" + order_original);
    }

    private void isEditIncrease(String deliveryOrder_json){
        //Edit
        if (ObjectUtil.isNotNullEmpty(deliveryOrder_json)) {
            DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);
            deliveryOrderModel = deliveryOrderCombine.jsonToModel(deliveryOrder_json);
            toolbar_title.setText(R.string.title_edit_delivery);
            getDeliveryModelMapWhenEdit(deliveryOrderModel.getDeliveryItem());
            Log.d("asd","deliveryOrder_json : " + deliveryOrder_json);
            Log.d("asd","orginalMapByReceivingItemId : " + orginalMapByReceivingItemId);
            for(ReceivingOrderModel order :order_original){
                ReceivingOrderModel receivingOrderModel = order.newReceivingOrderModel();
                List<ReceivingItemModel> newItems = new ArrayList<>();
                for(ReceivingItemModel item: order.newReceivingOrderModel().getReceivingItem()) {
                    if (orginalMapByReceivingItemId.containsKey(item.getReceivingId())) {
                        item.setOutStandingQty(orginalMapByReceivingItemId.get(item.getReceivingId()).getItemQty()!=null?orginalMapByReceivingItemId.get(item.getReceivingId()).getItemQty() + item.getOutStandingQty():item.getOutStandingQty() ==null?0:item.getOutStandingQty());
                        item.setOutStandingWeight(orginalMapByReceivingItemId.get(item.getReceivingId()).getItemGrossWeight()!=null?orginalMapByReceivingItemId.get(item.getReceivingId()).getItemGrossWeight().add(item.getOutStandingWeight()):item.getOutStandingWeight() ==null?new BigDecimal(0):item.getOutStandingWeight());
                        newItems.add(item);
                    }
                }
                if(!newItems.isEmpty()){
                    receivingOrderModel.setReceivingItem(newItems);
                    order_listview.add(receivingOrderModel);
                }
            }
            setLatestCheckBox(order_listview);
//            SetDeliveryOrder Field
            setAllField(deliveryOrderModel);
        }
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
        //Deep Clone CkeckBox
        expandableSelectedModel.setOrginal_isItemSelected(DeepCopyUtil.copyLinkedHashMap_Long_Boolean(expandableSelectedModel.getIsItemSelected()));
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
//                    ClassicDialog classicDialog = new ClassicDialog(v.getContext());
                    ClassicDialog.showBackPrevious(v.getContext(),getString(R.string.previous),movementRecord);
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
        Date stock_out_date = ObjectUtil.stringToDate_onlyDate(datePicker.getText().toString());
        if (stock_out_date == null) {
            List<String> missingField = new ArrayList<>();
            missingField.add(getString(R.string.label_stockoutDate));
            ClassicDialog.showMissingField(this,missingField);
            return super.onOptionsItemSelected(menuItem);
        }

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
        deliveryOrderModel.setStockOutDate(stock_out_date);
        deliveryOrderModel.setCreateDate(createDate);
        deliveryOrderModel.setStatus(Status.PROGRESS.name());
        deliveryOrderModel.setPartyId(partyId);
        deliveryOrderModel.setCreateBy(partyId);
        deliveryOrderModel.setDeliveryItem(deliveryItemModels);

        DeliveryOrderCombine deliveryOrderCombine = new DeliveryOrderCombine(DeliveryOrderModel.class);
        String deliveryOrder_json = deliveryOrderCombine.modelToJson(deliveryOrderModel);

        DeliveryOrderModel saveResult = deliveryOrderDao.saveOrderAndItem(deliveryOrder_json);

        if (saveResult != null) {
            Intent intent = new Intent();
            intent.putExtra(StartActivityForResultKey.movementRecord, movementRecordCombine.modelToJson(movementRecord));
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
//        ClassicDialog classicDialog = new ClassicDialog(this);
        ClassicDialog.showBackPrevious(this,getString(R.string.previous),movementRecord);
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
                return false;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    private void getitemOutStandingMapByReceivingItemId(){
        List<DeliveryItemModel> deliveryItemModels = deliveryItemDao.findByPartyIdAndStatus(combine_partyIdAndstatus);
        Log.d("asd","deliveryItemModels :" + deliveryItemModels);
        itemOutStandingMap = new LinkedHashMap<>();
        for(DeliveryItemModel deliveryItemModel : deliveryItemModels){
            Integer qty = deliveryItemModel.getItemQty() == null?0:deliveryItemModel.getItemQty();
            BigDecimal w =deliveryItemModel.getItemGrossWeight() == null?new BigDecimal(0):deliveryItemModel.getItemGrossWeight();

            if(itemOutStandingMap.containsKey(deliveryItemModel.getReceivingId())){
                Integer orginal_qty = itemOutStandingMap.get(deliveryItemModel.getReceivingId()).getQty();
                BigDecimal orginal_w = itemOutStandingMap.get(deliveryItemModel.getReceivingId()).getWeight();
                itemOutStandingMap.get(deliveryItemModel.getReceivingId()).setQty(orginal_qty + qty);
                itemOutStandingMap.get(deliveryItemModel.getReceivingId()).setWeight(orginal_w.add(w));
            }else{
                ItemOutStanding itemOutStanding = new ItemOutStanding();
                itemOutStanding.setQty(qty);
                itemOutStanding.setWeight(w);
                itemOutStandingMap.put(deliveryItemModel.getReceivingId(),itemOutStanding);
            }
        }
        Log.d("asd","[DeliveryIncrease]-[ItemOutStandingMap] :" + itemOutStandingMap);
    }
     private class ItemOutStanding{
       private Integer qty;
       private BigDecimal weight;

         public Integer getQty() {
             return qty;
         }

         public void setQty(Integer qty) {
             this.qty = qty;
         }

         public BigDecimal getWeight() {
             return weight;
         }

         public void setWeight(BigDecimal weight) {
             this.weight = weight;
         }

         @Override
         public String toString() {
             return "ItemOutStanding{" +
                     "qty=" + qty +
                     ", weight=" + weight +
                     '}';
         }
     }
}
