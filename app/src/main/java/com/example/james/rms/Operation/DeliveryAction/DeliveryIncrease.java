package com.example.james.rms.Operation.DeliveryAction;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.MyDatePicker;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.Core.Combine.DeliveryOrderSearchCombine;
import com.example.james.rms.Core.Dao.DeliveryOrderDao;
import com.example.james.rms.Core.Dao.DeliveryOrderDaoImpl;
import com.example.james.rms.Core.Dao.ReceivingItemDao;
import com.example.james.rms.Core.Dao.ReceivingItemDaoImpl;
import com.example.james.rms.Core.Dao.ReceivingOrderDao;
import com.example.james.rms.Core.Dao.ReceivingOrderDaoImpl;
import com.example.james.rms.Core.Model.DeliveryItemModel;
import com.example.james.rms.Core.Model.DeliveryOrderModel;
import com.example.james.rms.Core.Model.ReceivingItemModel;
import com.example.james.rms.Core.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Operation.ReceivingAction.Communicate_Interface;
import com.example.james.rms.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.james.rms.R.id.delivery_increase_datePicker;
import static com.example.james.rms.R.id.delivery_increase_fab;
/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryIncrease extends AppCompatActivity implements View.OnClickListener,MyDatePicker.MyDatePickerService,
Communicate_Interface<ReceivingOrderModel>{

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

    //
//    private DeliveryOrderDao deliveryOrderDao = new DeliveryOrderDaoImpl();
    private ReceivingOrderDao receivingOrderDao = new ReceivingOrderDaoImpl();

    //
    private String common_partyId;
    //
    private SelectedModel selectedModel = new SelectedModel();
    //
    private List<ReceivingOrderModel>  order_original;
    private List<ReceivingOrderModel>  order_latest;
    private List<ReceivingOrderModel>  order_listview;
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
        //HttpOK
//        String combine_partyIdAndStatus = DeliveryOrderSearchCombine.combine_partyIdAndStatus(common_partyId, Status.PROGRESS);
//        List<ReceivingOrderModel> deliveryModels = receivingOrderDao.findByOrderIdAndStatus(combine_partyIdAndStatus);

        //
        ReceivingOrderDao receivingOrderDao = new ReceivingOrderDaoImpl();
        List<ReceivingOrderModel> receivingOrderModels = receivingOrderDao.findByPartyIdAndStatus(combine_partyIdAndstatus);
        Log.d("asd","receivingOrderModels :" + receivingOrderModels);
        //
        order_original = new ArrayList<>(receivingOrderModels);
        order_latest = new ArrayList<>(receivingOrderModels);
        order_listview = new ArrayList<>();
        //
        selectedModel = setOriginalCheckbox(order_original);

        Log.v("asd","DeliveryIncrease-[order_original] :" + order_original);
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
        communicateInterface.putOriginalProductModels(order_original,order_latest,selectedModel.getIsItemSelected());
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

    public SelectedModel setOriginalCheckbox(List<ReceivingOrderModel>  receivingOrderModel){
        SelectedModel selectedModel = new SelectedModel();
        LinkedHashMap<Long,Boolean> isOrderSelected = new LinkedHashMap<>();
        LinkedHashMap<Long,Boolean> isItemSelected = new LinkedHashMap<>();

        for (int i=0; i<receivingOrderModel.size();i++){
                isOrderSelected.put(receivingOrderModel.get(i).getOrderId(),false);

            List<ReceivingItemModel> receivingItemModels = receivingOrderModel.get(i).getReceivingItem();
            for(int j=0; j< receivingItemModels.size();j++){
                isItemSelected.put(receivingItemModels.get(j).getReceivingId(),false);
            }
            selectedModel.setIsOrderSelected(isOrderSelected);
            selectedModel.setIsItemSelected(isItemSelected);

        }
        return selectedModel;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

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
    public void putOriginalProductModels(List<ReceivingOrderModel> item_original, List<ReceivingOrderModel> item_latest, LinkedHashMap<Long, Boolean> isSelected) {

    }

    @Override
    public void putLatestProductModel(List<ReceivingOrderModel> item_listview, LinkedHashMap<Long, Boolean> isSelected) {

    }

    private class SelectedModel{
        private LinkedHashMap<Long, Boolean> isOrderSelected;
        private LinkedHashMap<Long, Boolean> isItemSelected;

        public LinkedHashMap<Long, Boolean> getIsOrderSelected() {
            return isOrderSelected;
        }

        public void setIsOrderSelected(LinkedHashMap<Long, Boolean> isOrderSelected) {
            this.isOrderSelected = isOrderSelected;
        }

        public LinkedHashMap<Long, Boolean> getIsItemSelected() {
            return isItemSelected;
        }

        public void setIsItemSelected(LinkedHashMap<Long, Boolean> isItemSelected) {
            this.isItemSelected = isItemSelected;
        }

        @Override
        public String toString() {
            return "SelectedModel{" +
                    "isOrderSelected=" + isOrderSelected +
                    ", isItemSelected=" + isItemSelected +
                    '}';
        }
    }
}
