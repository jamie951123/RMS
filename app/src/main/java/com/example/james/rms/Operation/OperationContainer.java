package com.example.james.rms.Operation;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Operation.Adapter.OperationGridAdapter;
import com.example.james.rms.Operation.DeliveryAction.DeliveryIncrease;
import com.example.james.rms.Operation.ProductAction.ProductIncrease;
import com.example.james.rms.Operation.ReceivingAction.ReceivingIncrease;
import com.example.james.rms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 26/3/2017.
 */

public class OperationContainer extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.operation_gridview)
     GridView gridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_container);
        ButterKnife.bind(this);
        List<GridComponent> gridMap = getGridComponent();
        OperationGridAdapter operationGridAdapter = new OperationGridAdapter(this,gridMap);
        gridView.setNumColumns(1);
        gridView.setAdapter(operationGridAdapter);
        gridView.setOnItemClickListener(this);
    }


    public List<GridComponent> getGridComponent(){
        List<GridComponent> arrayList = new ArrayList<>();
        arrayList.add(new GridComponent(getString(R.string.add_product), ContextCompat.getDrawable(this,R.drawable.mailbox_black)));
        arrayList.add(new GridComponent(getString(R.string.add_receiving_order),ContextCompat.getDrawable(this,R.drawable.input)));
        arrayList.add(new GridComponent(getString(R.string.add_stock_out_order),ContextCompat.getDrawable(this,R.drawable.output)));
        return arrayList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();

        switch(position){
            case 0:
                intent = intent.setClass(this, ProductIncrease.class);
                break;
            case 1:
                intent = intent.setClass(this, ReceivingIncrease.class);
                break;
            case 2:
                intent = intent.setClass(this, DeliveryIncrease.class);
                break;
        }
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
//        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this, NavigationController.class);
        startActivity(intent);
    }
}
