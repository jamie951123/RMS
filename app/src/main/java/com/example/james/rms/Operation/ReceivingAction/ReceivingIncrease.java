package com.example.james.rms.Operation.ReceivingAction;

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
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;
import com.example.james.rms.Operation.Adapter.ReceivingIncreaseListAdapter;
import com.example.james.rms.Operation.Model.ReceivingIncreaseModel;
import com.example.james.rms.Operation.Service.ReceivingIncreaseService;
import com.example.james.rms.Operation.Service.ReceivingIncreaseServiceImpl;
import com.example.james.rms.ProductPool.ProductCombine;
import com.example.james.rms.ProductPool.Service.ProductService;
import com.example.james.rms.ProductPool.Service.ProductServiceImpl;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.ReceivingCombine;
import com.github.clans.fab.FloatingActionButton;

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
        Communicate_Interface,MyDatePicker.MyDatePickerService {
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

    private ProductService productService = new ProductServiceImpl();
    //
    private ProductCombine productCombine = new ProductCombine();
    //
    private HashMap<Integer, Boolean> isSelected;

    private List<ReceivingIncreaseModel>  rlAllmodel;
    private List<ReceivingIncreaseModel>  rlLastestmodel;
    private List<ReceivingIncreaseModel> listviewLastestModel;
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
        String combine_partyId = productCombine.combine_partyId(common_partyId);
        List<ProductModel> allModel = productService.findByPartyId(combine_partyId);
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
        String receivingDate    = datePicker_btn.getText().toString();
        String createDate       = ObjectUtil.dateToString(new Date());
        String orderJson        = insertReceivingOrder(itemSize,receivingDate,createDate);
//        Date receivingDate_date = ObjectUtil.stringToDate_onlyDate(receivingDate);
//        if(ObjectUtil.isNotNullEmpty(orderJson)){
//            ReceivingIncreaseService receivingIncreaseService = new ReceivingIncreaseServiceImpl();
//            receivingIncreaseService.insertIntoReceivingOrder(orderJson);
//            ReceivingCombine receivingCombine = new ReceivingCombine();
//            String partyIdAndCreateDateJSON = receivingCombine.combine_partyIdAndCreateDate(common_partyId,createDate);
//            List<ReceivingOrderModel> receivingOrderModel = receivingIncreaseService.findReceivingOrderByPartyIdAndCreateDate(partyIdAndCreateDateJSON);
//            String orderId = receivingOrderModel.get(0).getOrderId();
//            String itemJson = insertReceivingItem(receivingDate,orderId,createDate);
//            receivingIncreaseService.insertIntoReceivingItem(itemJson);
//
//            Log.d("asd","orderId :" +receivingOrderModel.get(0).getOrderId());
//            Log.d("asd","itemJson :" +itemJson);
//        }
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
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ClassicDialog classicDialog = new ClassicDialog(this,getString(R.string.previous));
        classicDialog.showBackPrevious();

    }

//    public String insertReceivingItem(Date receivingDate,Long rlOrderId,Date createDate){
//        String json = null;
//        List<ReceivingItemModel> receivingItemModels = new ArrayList<>();
//        for(ReceivingIncreaseModel item : listviewLastestModel ){
//            ReceivingItemModel receivingItemModel = new ReceivingItemModel();
//            Long productId              = item.getProductModel().getProductId();
//            Date itemCreateDate         = createDate;
//            Date itemReceivingDate      = receivingDate;
//            BigDecimal itemGrossWeight = item.getGrossWeight();
//            String itemGrossWeightUnit = item.getGrossWeightUnit();
//            Integer itemQty            = item.getQty();
//            String itemQtyUnit         = item.getQtyUnit();
//            String itemRemark          = item.getRemark();
//            String partyId             = common_partyId;
//            String orderId             = rlOrderId;
//            receivingItemModel.setProductId(productId);
//            receivingItemModel.setItemCreateDate(itemCreateDate);
//            receivingItemModel.setItemReceivingDate(itemReceivingDate);
//            receivingItemModel.setItemGrossWeight(itemGrossWeight);
//            receivingItemModel.setItemGrossWeightUnit(itemGrossWeightUnit);
//            receivingItemModel.setItemQty(itemQty);
//            receivingItemModel.setItemQtyUnit(itemQtyUnit);
//            receivingItemModel.setItemRemark(itemRemark);
//            receivingItemModel.setPartyId(partyId);
//            receivingItemModel.setOrderId(orderId);
//            receivingItemModels.add(receivingItemModel);
//        }
//        ReceivingCombine receivingCombine = new ReceivingCombine();
//        json = receivingCombine.combine_AddReceivingItem(receivingItemModels);
//        return json;
//    }
    public String insertReceivingOrder(int itemSize,String receivingDate,String createDate){
        String json = null;
        if(ObjectUtil.isNotNullEmpty(receivingDate) && ObjectUtil.isNotNullEmpty(common_partyId)) {
            ReceivingOrderModel receivingOrderModel = new ReceivingOrderModel();
            receivingOrderModel.setPartyId(common_partyId);
            receivingOrderModel.setReceivingDate(receivingDate);
            receivingOrderModel.setItemQty(itemSize);
            receivingOrderModel.setCreateDate(createDate);
            ReceivingCombine receivingCombine = new ReceivingCombine();
            json = receivingCombine.combine_AddReceivingOrder(receivingOrderModel);
        }else{
            List<String> missingField = new ArrayList<>();
            missingField.add(getString(R.string.label_receivingDate));
            ClassicDialog classicDialog = new ClassicDialog(this);
            classicDialog.showMissingField(missingField);
        }
        return json;
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
                    ClassicDialog classicDialog = new ClassicDialog(v.getContext(),getString(R.string.previous));
                    classicDialog.showBackPrevious();
                }
            });
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
    public void putOriginalProductModels(List<ReceivingIncreaseModel> rlAllModel, List<ReceivingIncreaseModel> allModel, HashMap<Integer, Boolean> isSelected) {

    }

    @Override
    public void putLastestProductModel(List<ReceivingIncreaseModel> lastestModel, HashMap<Integer, Boolean> isSelected) {
        this.isSelected           = isSelected;
        this.listviewLastestModel = lastestModel;
        ReceivingIncreaseListAdapter receivingIncreaseListAdapter = new ReceivingIncreaseListAdapter(this,listviewLastestModel);
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
}
