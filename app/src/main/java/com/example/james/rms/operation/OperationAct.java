package com.example.james.rms.operation;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.james.rms.common.StartActivityForResultKey;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.controller.NavigationAct;
import com.example.james.rms.core.combine.MovementRecordCombine;
import com.example.james.rms.core.model.MovementRecord;
import com.example.james.rms.operation.adapter.OperationGridAdapter;
import com.example.james.rms.operation.delivery_action.DeliveryIncreaseAct;
import com.example.james.rms.operation.product_action.ProductIncreaseAct;
import com.example.james.rms.operation.receiving_action.ReceivingIncreaseAct;
import com.example.james.rms.operation.unit_action.UnitIncrease;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 26/3/2017.
 */

public class OperationAct extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.operation_gridview)
     GridView gridView;

    //PutExtra
    private String movementRecord_json;
    private MovementRecord movementRecord;
    //Combine
    private MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_operation);
        ButterKnife.bind(this);
        List<GridComponent> gridMap = getGridComponent();
        OperationGridAdapter operationGridAdapter = new OperationGridAdapter(this,gridMap);
        gridView.setNumColumns(1);
        gridView.setAdapter(operationGridAdapter);
        gridView.setOnItemClickListener(this);

        movementRecord_json = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                movementRecord_json = null;
            } else {
                movementRecord_json = extras.getString(StartActivityForResultKey.movementRecord);
            }
        }
        if(ObjectUtil.isNotNullEmpty(movementRecord_json)){
            movementRecord = movementRecordCombine.jsonToModel(movementRecord_json);
        }
    }


    public List<GridComponent> getGridComponent(){
        List<GridComponent> arrayList = new ArrayList<>();
        arrayList.add(new GridComponent(getString(R.string.add_product), ContextCompat.getDrawable(this,R.drawable.mailbox_black)));
        arrayList.add(new GridComponent(getString(R.string.add_receiving_order),ContextCompat.getDrawable(this,R.drawable.input)));
        arrayList.add(new GridComponent(getString(R.string.add_stock_out_order),ContextCompat.getDrawable(this,R.drawable.output)));
        arrayList.add(new GridComponent(getString(R.string.add_unit), ContextCompat.getDrawable(this,R.drawable.ruler_black)));
        return arrayList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        MovementRecord movementRecord = new MovementRecord();
        movementRecord.setOriginalClass_string(OperationAct.class.getCanonicalName());
        switch(position){
            case 0:
                movementRecord.setTargetClass_string(ProductIncreaseAct.class.getCanonicalName());
                movementRecord.setExist_fragment(this.movementRecord!=null?this.movementRecord.getExist_fragment():R.id.nav_all_product);
                intent = intent.setClass(this, ProductIncreaseAct.class);
                break;
            case 1:
                movementRecord.setTargetClass_string(ReceivingIncreaseAct.class.getCanonicalName());
                movementRecord.setExist_fragment(this.movementRecord!=null?this.movementRecord.getExist_fragment():R.id.nav_receiving);
                intent = intent.setClass(this, ReceivingIncreaseAct.class);
                break;
            case 2:
                movementRecord.setTargetClass_string(DeliveryIncreaseAct.class.getCanonicalName());
                movementRecord.setExist_fragment(this.movementRecord!=null?this.movementRecord.getExist_fragment():R.id.nav_stockOut);
                intent = intent.setClass(this, DeliveryIncreaseAct.class);
                break;
            case 3:
                movementRecord.setTargetClass_string(DeliveryIncreaseAct.class.getCanonicalName());
                movementRecord.setExist_fragment(this.movementRecord!=null?this.movementRecord.getExist_fragment():R.id.nav_all_product);
                intent = intent.setClass(this, UnitIncrease.class);
                break;
        }
        MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);
        String movement_json = movementRecordCombine.modelToJson(movementRecord);
        intent.putExtra(StartActivityForResultKey.movementRecord,movement_json);
        startActivity(intent);
    }

    public class GridComponent{
        String label;
        Drawable image;

        public GridComponent(String label, Drawable image) {
            this.label = label;
            this.image = image;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }
    }
    public void onBackPressed() {
        if(movementRecord !=null && NavigationAct.class.getCanonicalName().equals(movementRecord.getOriginalClass_string())){
            super.onBackPressed();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, NavigationAct.class);
        intent.putExtra(StartActivityForResultKey.movementRecord,movementRecord_json);
        startActivity(intent);
    }
}
