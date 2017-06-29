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
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.ExpandableSelectedModel;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.TransferModel.NumberDialogModel;
import com.example.james.rms.ITF.Communicate_Interface;
import com.example.james.rms.ITF.NumberDialogListener;
import com.example.james.rms.Operation.Adapter.ReceivingIncreaseListAdapter;
import com.example.james.rms.R;
import com.example.james.rms.Core.Combine.ReceivingOrderCombine;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.james.rms.R.id.receiving_increase_datePicker;
import static com.example.james.rms.R.id.receiving_increase_fab;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingIncrease extends AppCompatActivity implements View.OnClickListener,
        Communicate_Interface<ReceivingItemModel>, MyDatePicker.MyDatePickerService,
        NumberDialogListener {
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
    @BindView(R.id.receiving_increase_toolbar_title)
    TextView receiving_increase_toolbar_title;
    private ProductDao productDao = new ProductDaoImpl();
    //
    private ReceivingOrderDao receivingOrderDao = new ReceivingOrderDaoImpl();
    //
    private ExpandableSelectedModel expandableSelectModel;

    private ReceivingOrderModel orderModel = new ReceivingOrderModel();
    private List<ReceivingItemModel> item_original;
    private List<ReceivingItemModel> item_latest;
    private List<ReceivingItemModel> item_listview;
    private String common_partyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiving_increase);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        fab_btn.setOnClickListener(this);
        datePicker_btn.setOnClickListener(this);
        setUpToolbar();
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this, "loginInformation", MODE_PRIVATE);
        common_partyId = partyIdPreferences.getPreferences_PartyId().get("partyId");
        //HttpOK
        String combine_partyId = ReceivingOrderCombine.combine_partyId(common_partyId);
        List<ProductModel> allModel = productDao.findByPartyId(combine_partyId);
        //
        item_original = new ArrayList<>(modelConvert(allModel));
        item_latest = new ArrayList<>(modelConvert(allModel));
        item_listview = new ArrayList<>();
        //
        setOriginalCheckbox(item_original);

        String receivingOrder_Json = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                receivingOrder_Json = null;
            } else {
                receivingOrder_Json = extras.getString(StartActivityForResultKey.receivingOrderModel);
            }
        }

        if (ObjectUtil.isNotNullEmpty(receivingOrder_Json)) {
            ReceivingOrderCombine receivingOrderCombine = new ReceivingOrderCombine(ReceivingOrderModel.class);
            orderModel = receivingOrderCombine.jsonToModel(receivingOrder_Json);
            receiving_increase_toolbar_title.setText(R.string.title_edit_receiving);
            setAllField(orderModel);
            return;
        }
    }

    private void setAllField(ReceivingOrderModel receivingOrderModel) {
        remark_edit.setText(receivingOrderModel.getRemark());
        if (receivingOrderModel.getReceivingDate() != null) {
            Log.v("asd", "[Receiving Increase ]-[Edit]-[ReceivingDate]:" + receivingOrderModel.getReceivingDate());
            String editRLDate = ObjectUtil.sdf_onlyDate.format(receivingOrderModel.getReceivingDate());
            setLatestCheckbox(receivingOrderModel.getReceivingItem(), expandableSelectModel.getIsItemSelected());
            datePicker_btn.setText(editRLDate);

            List<ReceivingItemModel> receivingItemModels = receivingOrderModel.getReceivingItem();
            LinkedHashMap<Long, ReceivingItemModel> item_map = new LinkedHashMap<>();
            for (ReceivingItemModel item : receivingItemModels) {
                item_map.put(item.getProductId(), item);
            }
            for (int i = 0; i < item_latest.size(); i++) {
                ReceivingItemModel r = item_latest.get(i);
                if (item_map.containsKey(r.getProductId())) {
                    item_latest.set(i, item_map.get(r.getProductId()));
                }
            }
            putLatestProductModel(receivingItemModels, expandableSelectModel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.receiving_increase_menu, menu);
        return true;
    }

//    Save
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.d("asd", "rlLastestmodel Size :" + item_listview.size());
        Log.d("asd", "rlLastestmodel :" + item_listview.toString());
        int itemSize = item_listview.size();
        Date receivingDate = ObjectUtil.stringToDate_onlyDate(datePicker_btn.getText().toString());
        Date createDate = new Date();
        String orderRemark = remark_edit.getText().toString();

        if (receivingDate == null) {
            List<String> missingField = new ArrayList<>();
            missingField.add(getString(R.string.label_receivingDate));
            ClassicDialog classicDialog = new ClassicDialog(this);
            classicDialog.showMissingField(missingField);
            return super.onOptionsItemSelected(menuItem);
        }

        //
        ReceivingOrderModel order = getReceivingOrder(itemSize, receivingDate, createDate, orderRemark);
        List<ReceivingItemModel> item = getReceivingItem(receivingDate, createDate);
        //
        if (order != null) {
            order.setReceivingItem(item);
            String result_json = null;
            try {
                Gson gson = GsonUtil.toJson();
                result_json = gson.toJson(order);
            } catch (Exception e) {
                e.printStackTrace();
                return super.onOptionsItemSelected(menuItem);
            }
            ReceivingOrderModel saveResult = receivingOrderDao.saveOrderAndItem(result_json);
            if (saveResult != null) {
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
        switch (v.getId()) {
            case receiving_increase_fab:
                createDialogBox();
                break;
            case receiving_increase_datePicker:
                showDatePicker();
                break;
        }
    }

    public void createDialogBox() {
        ReceivingIncreaseDialog receivingIncreaseDialog = new ReceivingIncreaseDialog();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("receiving_increase");
        if (fragment != null) {
            fm.beginTransaction().remove(fragment).commit();
        }
        receivingIncreaseDialog.show(fm, "receiving_increase");
        Communicate_Interface communicateInterface = receivingIncreaseDialog;
        Log.d("asd", "[ReceivingIncrease]--[item_original] :" + item_original);
        Log.d("asd", "[ReceivingIncrease]--[item_latest] :" + item_latest);

        communicateInterface.putOriginalProductModels(item_original, item_latest, expandableSelectModel);
        Toast.makeText(this, "ReceivingIncrease", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ClassicDialog classicDialog = new ClassicDialog(this);
        classicDialog.showBackPrevious(getString(R.string.previous));

    }

    public List<ReceivingItemModel> getReceivingItem(Date receivingDate, Date createDate) {
        List<ReceivingItemModel> receivingItemModels = new ArrayList<>();
        try {
            for (ReceivingItemModel item : item_listview) {
                ReceivingItemModel receivingItemModel = new ReceivingItemModel();
                Long receivingId = item.getReceivingId();
                Long productId = item.getProduct().getProductId();
                Long orderId = item.getOrderId();
                Date itemCreateDate = createDate;
                Date itemReceivingDate = receivingDate;
                BigDecimal itemGrossWeight = item.getItemGrossWeight();
                Integer itemQty = item.getItemQty();
                String itemRemark = item.getItemRemark();

                receivingItemModel.setReceivingId(receivingId);
                receivingItemModel.setProductId(productId);
                receivingItemModel.setItemCreateDate(itemCreateDate);
                receivingItemModel.setItemCreateBy(common_partyId);
                receivingItemModel.setPartyId(common_partyId);
                receivingItemModel.setItemStatus(Status.PROGRESS.name());
                receivingItemModel.setItemReceivingDate(itemReceivingDate);
                receivingItemModel.setItemGrossWeight(itemGrossWeight);
                receivingItemModel.setItemQty(itemQty);
                receivingItemModel.setItemRemark(itemRemark);
                receivingItemModel.setOrderId(orderId);
                receivingItemModels.add(receivingItemModel);
            }
        } catch (Exception e) {
            Log.d("asd", "[ReceivingIncrease]-[Save]-[Error] : ReceivingItemModel  is null ");
        }
        return receivingItemModels;
    }

    public ReceivingOrderModel getReceivingOrder(int itemSize, Date receivingDate, Date createDate, String orderRemark) {
        if (createDate != null && receivingDate != null && ObjectUtil.isNotNullEmpty(common_partyId)) {
            orderModel.setPartyId(common_partyId);
            orderModel.setCreateBy(common_partyId);
            orderModel.setStatus(Status.PROGRESS.name());
            orderModel.setReceivingDate(receivingDate);
            orderModel.setItemQty(itemSize);
            orderModel.setCreateDate(createDate);
            orderModel.setRemark(orderRemark);
            return orderModel;
        }
        Log.d("asd", "[ReceivingIncrease]-[Save]-[Error] : ReceivingOrderModel is null ");
        return null;
    }

    private void showDatePicker() {
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
            receiving_increase_toolbar_title.setText(R.string.receiving_increase_title);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassicDialog classicDialog = new ClassicDialog(v.getContext());
                    classicDialog.showBackPrevious(getString(R.string.previous));
                }
            });
        }
    }

    public void setLatestCheckbox(List<ReceivingItemModel> latest_receivingItemModels, LinkedHashMap<Long, Boolean> originalSelected) {
        LinkedHashMap<Long, Boolean> isSelected = new LinkedHashMap<>(originalSelected);
        for (int i = 0; i < latest_receivingItemModels.size(); i++) {
            if (originalSelected.containsKey(latest_receivingItemModels.get(i).getProductId())) {
                isSelected.put(latest_receivingItemModels.get(i).getProductId(), true);
            }
        }
        expandableSelectModel.setIsItemSelected(isSelected);
    }

    public void setOriginalCheckbox(List<ReceivingItemModel> receivingItemModels) {
        LinkedHashMap<Long, Boolean> isSelected = new LinkedHashMap<>();
        for (int i = 0; i < receivingItemModels.size(); i++) {
            isSelected.put(receivingItemModels.get(i).getProductId(), false);
        }
        expandableSelectModel = new ExpandableSelectedModel();
        expandableSelectModel.setIsItemSelected(isSelected);
    }

    public List<ReceivingItemModel> modelConvert(List<ProductModel> productModels) {
        List<ReceivingItemModel> receivingItemModels = new ArrayList<>();
        if (productModels != null) {
            for (ProductModel item : productModels) {
                if (item == null) {
                    Log.v("asd", "ReceivingOrderModel--ProductConvertModel have null empty");
                    continue;
                }
                ReceivingItemModel receivingItemModel = new ReceivingItemModel();
                receivingItemModel.setProduct(item);
                receivingItemModel.setProductId(item.getProductId());
                receivingItemModels.add(receivingItemModel);
            }
        }
        return receivingItemModels;
    }

    @Override
    public void passDateToReceivingIncrease(String date_str, Date date) {
        datePicker_btn.setText(date_str);
        Log.v("asd", "passDateToReceivingIncrease :" + date_str);
    }

    @Override
    public void from(NumberDialogModel numberDialogModel, Object o) {
        //Nothins to do
        return;
    }

    @Override
    public void returnData(NumberDialogModel numberDialogModel) {
        switch (numberDialogModel.getKey()) {
            case KeyModel.qty:
                this.item_listview.get(numberDialogModel.getPosition()).setItemQty(numberDialogModel.getQty());
                break;
            case KeyModel.gw:
                this.item_listview.get(numberDialogModel.getPosition()).setItemGrossWeight(numberDialogModel.getGrossWeight());
                break;
        }

        Log.d("asd", "[ReceivingIncrease]-[returnData]-[item_original] :" + item_original);
        Log.d("asd", "[ReceivingIncrease]-[returnData]-[item_latest] :" + item_latest);
        Log.d("asd", "[ReceivingIncrease]-[returnData]-[item_listview] :" + item_listview);
        if (listView != null) {
            listView.invalidateViews();
        }
    }

    @Override
    public void putOriginalProductModels(List<ReceivingItemModel> item_original, List<ReceivingItemModel> item_latest, ExpandableSelectedModel expandableSelectModel) {

    }

    @Override
    public void putLatestProductModel(List<ReceivingItemModel> item_latest, ExpandableSelectedModel expandableSelectModel) {
        this.expandableSelectModel = expandableSelectModel;
        this.item_listview = new ArrayList<>(item_latest);
        ReceivingIncreaseListAdapter receivingIncreaseListAdapter = new ReceivingIncreaseListAdapter(this, item_listview);
        listView.setAdapter(receivingIncreaseListAdapter);
        Log.v("asd", "putLatestProductModel_listview :" + item_listview.toString());
    }

}
