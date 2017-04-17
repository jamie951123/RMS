package com.example.james.rms.Operation.ProductAction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Product.Model.ProductInsertModel;
import com.example.james.rms.Core.Product.Model.ProductModel;
import com.example.james.rms.ProductPool.ProductCombine;
import com.example.james.rms.ProductPool.Service.ProductService;
import com.example.james.rms.ProductPool.Service.ProductServiceImpl;
import com.example.james.rms.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 13/3/2017.
 */

public class ProductIncrease extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.increase_imageView)
    ImageView increase_imageView;
    @BindView(R.id.increase_puductCode)
    EditText increase_puductCode;
    @BindView(R.id.increase_puductName)
    EditText increase_puductName;
    @BindView(R.id.increase_descriptionCN)
    EditText increase_descriptionCN;
    @BindView(R.id.increase_descriptionEN)
    EditText increase_descriptionEN;
    @BindView(R.id.increase_remark)
    EditText increase_remark;
    @BindView(R.id.increase_createDate)
    EditText increase_createDate;
    @BindView(R.id.increase_submit)
    Button increase_submit;

    //
    private ProductCombine productCombine = new ProductCombine();
    private ProductService productService = new ProductServiceImpl();
    //
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    //
    String partyId_Preferences="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_increase);
        ButterKnife.bind(this);

        increase_submit.setOnClickListener(this);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        partyId_Preferences =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //
        setActionDate();

    }

    private void setActionDate() {
        increase_createDate.setText(dateFormat.format(new Date()));
    }

    @Override
    public void onClick(View v) {
        String productCode   = increase_puductCode.getText().toString();
        String puductName    = increase_puductName.getText().toString();
        String descriptionCN = increase_descriptionCN.getText().toString();
        String descriptionEN = increase_descriptionEN.getText().toString();
        String remark        = increase_remark.getText().toString();
        Date createDate      = new Date();
        String partyId       = partyId_Preferences;
        switch (v.getId()){
            case (R.id.increase_submit):
                if(!checkConCatFieldIsNotNUll(productCode,puductName)){
                    Toast.makeText(this,R.string.productAction,Toast.LENGTH_LONG).show();
                    break;
                }
                String result = productCombine.combine_AddProduct(productCode,puductName,descriptionCN,
                        descriptionEN,remark,createDate,partyId);
                ProductModel productModel = productService.insertProduct(result);
                if(productModel != null) {
                    Toast.makeText(this,R.string.insert_successful,Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                intent = intent.setClass(this, NavigationController.class);
                startActivity(intent);
        }
    }

    public boolean checkConCatFieldIsNotNUll(String productCode,String puductName){
        boolean result = true;
        if(ObjectUtil.isNullEmpty(productCode) || ObjectUtil.isNullEmpty(puductName)){
            result = false;
        }
        return result;
    }
}
