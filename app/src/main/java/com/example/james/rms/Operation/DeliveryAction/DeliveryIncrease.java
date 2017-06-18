package com.example.james.rms.Operation.DeliveryAction;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.MyDatePicker;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.james.rms.R.id.delivery_increase_datePicker;
import static com.example.james.rms.R.id.delivery_increase_fab;
/**
 * Created by Jamie on 18/6/2017.
 */

public class DeliveryIncrease extends AppCompatActivity implements View.OnClickListener,MyDatePicker.MyDatePickerService {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_increase);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        datePicker.setOnClickListener(this);
        setUpToolbar();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case delivery_increase_fab:
//                createDialogBox();
                break;
            case delivery_increase_datePicker:
                showDatePicker();
                break;
        }
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
}
