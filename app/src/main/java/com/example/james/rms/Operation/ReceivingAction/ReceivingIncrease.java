package com.example.james.rms.Operation.ReceivingAction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.MyDatePicker;
import com.example.james.rms.CommonProfile.DialogBox.NumberDialog;
import com.example.james.rms.CommonProfile.GsonUtil;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.Operation.Adapter.ReceivingIncreaseListAdapter;
import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.ReceivingCombine;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.james.rms.R.id.receiving_increase_datePicker;
import static com.example.james.rms.R.id.receiving_increase_fab;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingIncrease extends AppCompatActivity implements View.OnClickListener,
        Communicate_Interface,MyDatePicker.MyDatePickerService,
        NumberDialog.QDtoReceivingIncrease{
    @BindView(R.id.receiving_increase_fab)
    FloatingActionButton fab_btn;
    @BindView(R.id.receiving_increase_listview)
    ListView listView;
    @BindView(R.id.receiving_increase_toolbar)
    Toolbar toolbar;
    @BindView(R.id.receiving_increase_remark)
    EditText remark_edit;
    @BindView(receiving_increase_datePicker)
    TextView datePicker_btn;

    private ProductDao productDao = new ProductDaoImpl();
    //
    private WeightProfileDao weightProfileDao = new WeightProfileDaoImpl();
    //
    private QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();
    //
    private ReceivingOrderDao receivingOrderDao = new ReceivingOrderDaoImpl();
    //
    private HashMap<Integer, Boolean> isSelected;

    private List<ReceivingIncreaseModel>  rlAllmodel;
    private List<ReceivingIncreaseModel>  rlLastestmodel;
    private List<ReceivingIncreaseModel> listviewLastestModel;
    private List<WeightProfileModel> weightProfileModelList;
    private List<QuantityProfileModel> quantityProfileModelList;
    String common_partyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiving_increase);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setUpToolbar();
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        common_partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //HttpOK
        String combine_partyId = ReceivingCombine.combine_partyId(common_partyId);
        List<ProductModel> allModel = productDao.findByPartyId(combine_partyId);
        weightProfileModelList = weightProfileDao.findByPartyId(combine_partyId);
        quantityProfileModelList  = quantityProfileDao.findByPartyId(combine_partyId);
        //
        rlLastestmodel = modelConvert(allModel);
        rlAllmodel     = modelConvert(allModel);
        listviewLastestModel = new ArrayList<>();
        setCheckbox();
        fab_btn.setOnClickListener(this);
        datePicker_btn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.receiving_increase_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.d("asd","rlLastestmodel Size :" +listviewLastestModel.size());
        Log.d("asd","rlLastestmodel :" +listviewLastestModel.toString());
        int itemSize = listviewLastestModel.size();
        Date receivingDate              = ObjectUtil.stringToDate_onlyDate(datePicker_btn.getText().toString());
        Date createDate                 = new Date();
        String orderRemark              = remark_edit.getText().toString();
        ReceivingOrderModel order       = getReceivingOrder(itemSize,receivingDate,createDate,orderRemark);
        List<ReceivingItemModel> item   = getReceivingItem(receivingDate,createDate);
        order.setReceivingItem(item);
        if(receivingDate == null){
            List<String> missingField = new ArrayList<>();
            missingField.add(getString(R.string.label_receivingDate));
            ClassicDialog classicDialog = new ClassicDialog(this);
            classicDialog.showMissingField(missingField);
            return super.onOptionsItemSelected(menuItem);
        }
        if(order != null){
            String result_json = null;
            try {
                Gson gson = GsonUtil.toJson();
                result_json = gson.toJson(order);
            }catch (Exception e){
                e.printStackTrace();
                return super.onOptionsItemSelected(menuItem);
            }
            ReceivingOrderModel saveResult = receivingOrderDao.saveOrderAndItem(result_json);
            if(saveResult != null){
                Intent intent = new Intent();
                intent.putExtra("NavigationController", StartActivityForResultKey.navReceiving);
                intent.setClass(this, NavigationController.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case receiving_increase_fab:
                createDialogBox();
                break;
            case receiving_increase_datePicker:
                showDatePicker();
                break;
        }
    }

    public void createDialogBox(){
        ReceivingIncreaseDialog receivingIncreaseDialog = new ReceivingIncreaseDialog();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("receiving_increase");
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        receivingIncreaseDialog.show(fm,"receiving_increase");
        Communicate_Interface communicateInterface = receivingIncreaseDialog;
        communicateInterface.putOriginalProductModels(rlAllmodel,rlLastestmodel,isSelected);
        Toast.makeText(this,"ReceivingIncrease",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ClassicDialog classicDialog = new ClassicDialog(this);
        classicDialog.showBackPrevious(getString(R.string.previous));

    }

    public  List<ReceivingItemModel> getReceivingItem(Date receivingDate,Date createDate){
        List<ReceivingItemModel> receivingItemModels = new ArrayList<>();
        for(ReceivingIncreaseModel item : listviewLastestModel ){
            ReceivingItemModel receivingItemModel = new ReceivingItemModel();
            Long productId              = item.getProductModel().getProductId();
            Date itemCreateDate         = createDate;
            Date itemReceivingDate      = receivingDate;
            BigDecimal itemGrossWeight = item.getGrossWeight();
//            String itemGrossWeightUnit = item.getGrossWeightUnit();
            Integer itemQty            = item.getQty();
//            String itemQtyUnit         = item.getQtyUnit();
            String itemRemark          = item.getRemark();
            String partyId             = common_partyId;
            receivingItemModel.setProductId(productId);
            receivingItemModel.setItemCreateDate(itemCreateDate);
            receivingItemModel.setItemReceivingDate(itemReceivingDate);
            receivingItemModel.setItemGrossWeight(itemGrossWeight);
            receivingItemModel.setItemQty(itemQty);
            receivingItemModel.setItemRemark(itemRemark);
            receivingItemModel.setPartyId(partyId);
            receivingItemModel.setItemStatus(Status.PROGRESS.name());
            receivingItemModels.add(receivingItemModel);
        }
        return receivingItemModels;
    }
    public ReceivingOrderModel getReceivingOrder(int itemSize,Date receivingDate,Date createDate,String orderRemark){
        if( createDate != null && receivingDate != null && ObjectUtil.isNotNullEmpty(common_partyId)) {
            ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
            receivingOrderModel.setPartyId(common_partyId);
            receivingOrderModel.setReceivingDate(receivingDate);
            receivingOrderModel.setItemQty(itemSize);
            receivingOrderModel.setCreateDate(createDate);
            receivingOrderModel.setStatus(Status.PROGRESS.name());
            receivingOrderModel.setRemark(orderRemark);
            return receivingOrderModel;
        }
            return null;
    }
    private void showDatePicker(){
//        int mYear, mMonth, mDay;
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
        DialogFragment newFragment = new MyDatePicker();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }
    private void setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setNavigationIcon(R.drawable.back_white);
            getSupportActionBar().setTitle(getString(R.string.receiving_increase_title));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassicDialog classicDialog = new ClassicDialog(v.getContext());
                    classicDialog.showBackPrevious(getString(R.string.previous));
                }
            });
        }
    }
    @Override
    public void putOriginalProductModels(List<ReceivingIncreaseModel> rlAllModel, List<ReceivingIncreaseModel> allModel, HashMap<Integer, Boolean> isSelected) {

    }

    @Override
    public void putLastestProductModel(List<ReceivingIncreaseModel> lastestModel, HashMap<Integer, Boolean> isSelected) {
        this.isSelected           = isSelected;
        this.listviewLastestModel = lastestModel;
        ReceivingIncreaseListAdapter receivingIncreaseListAdapter = new ReceivingIncreaseListAdapter(this,listviewLastestModel,weightProfileModelList,quantityProfileModelList);
        listView.setAdapter(receivingIncreaseListAdapter);
        Log.v("asd","lastestModel :" +lastestModel.toString());
    }


    public HashMap<Integer, Boolean> setCheckbox(){
        isSelected = new HashMap<>();
        for (int i=0; i<rlAllmodel.size();i++){
            isSelected.put(i,false);
        }
        return isSelected;
    }

    public List<ReceivingIncreaseModel> modelConvert(List<ProductModel> productModels){
        List<ReceivingIncreaseModel> receivingIncreaseModels = new ArrayList<>();
        if(productModels != null){
            for(ProductModel items : productModels){
                if(items == null){
                    Log.v("asd","ReceivingIncrease--ConvertModel have null empty");
                    continue;
                }
                ReceivingIncreaseModel receivingIncreaseModel = new ReceivingIncreaseModel(items);
                receivingIncreaseModels.add(receivingIncreaseModel);
            }
        }
        return receivingIncreaseModels;
    }

    @Override
    public void passDateToReceivingIncrease(String date_str,Date date) {
        datePicker_btn.setText(date_str);
        Log.v("asd","passDateToReceivingIncrease :" +date_str);
    }

    @Override
    public void returnData(NumberDialogModel numberDialogModel) {
        switch (numberDialogModel.getKey()){
            case KeyModel.qty:
                this.listviewLastestModel.get(numberDialogModel.getPosition()).setQty(numberDialogModel.getQty());
                this.listviewLastestModel.get(numberDialogModel.getPosition()).setQtyUnit(numberDialogModel.getQtyUnit());
                break;
            case KeyModel.gw:
                this.listviewLastestModel.get(numberDialogModel.getPosition()).setGrossWeight(numberDialogModel.getGrossWeight());
                this.listviewLastestModel.get(numberDialogModel.getPosition()).setGrossWeightUnit(numberDialogModel.getGrossWeightUnit());
        }
        if(listView != null){
            listView.invalidateViews();
        }
    }
}
