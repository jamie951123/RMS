package com.example.james.rms.Operation.ProductAction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.Service.ClassicDialogService;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.ProductPool.ProductCombine;
import com.example.james.rms.R;
import com.example.james.rms.Setting.SettingContainer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by james on 13/3/2017.
 */

public class ProductIncrease extends AppCompatActivity implements View.OnClickListener, ClassicDialogService {

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

    @BindView(R.id.product_increase_weight_unit)
    Button product_increase_weight_unit;
    @BindView(R.id.product_increase_quantity_unit)
    Button product_increase_quantity_unit;
    private Long weightId = null;
    private Long quantityId = null;
    //
    private ProductDao productDao = new ProductDaoImpl();
    //
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    //
    private WeightProfileDao weightProfileDao = new WeightProfileDaoImpl();
    //
    private QuantityProfileDao quantityProfileDao = new QuantityProfileDaoImpl();

    String common_partyId;
    private List<WeightProfileModel> weightProfileModelList;
    private List<QuantityProfileModel> quantityProfileModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_increase);
        ButterKnife.bind(this);

        increase_submit.setOnClickListener(this);
        product_increase_weight_unit.setOnClickListener(this);
        product_increase_quantity_unit.setOnClickListener(this);
        //Preferences
        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(this,"loginInformation",MODE_PRIVATE);
        common_partyId =  partyIdPreferences.getPreferences_PartyId().get("partyId");
        //HttpOK
        String combine_partyId = ProductCombine.combine_partyId(common_partyId);
        weightProfileModelList = weightProfileDao.findByPartyId(combine_partyId);
        quantityProfileModelList  = quantityProfileDao.findByPartyId(combine_partyId);
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
        String partyId       = common_partyId;

        ClassicDialog classicDialog = new ClassicDialog(this);
        switch (v.getId()){
            case (R.id.increase_submit):
                if(!checkConCatFieldIsNotNUll(productCode,puductName)){
                    Toast.makeText(this,R.string.productAction,Toast.LENGTH_LONG).show();
                    break;
                }
                String result = ProductCombine.combine_AddProduct(productCode,puductName,descriptionCN,
                        descriptionEN,remark,createDate,partyId,weightId,quantityId);
                ProductModel productModel = productDao.insertProduct(result);
                if(productModel != null) {
                    Toast.makeText(this,R.string.insert_successful,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent = intent.setClass(this, NavigationController.class);
                    startActivity(intent);
                    break;
                }
                Toast.makeText(this,R.string.insert_fail,Toast.LENGTH_SHORT).show();
                break;

            case (R.id.product_increase_weight_unit):
                classicDialog.showSingleChoice(getString(R.string.title_select_weight_unit),null,this.weightProfileModelList, KeyModel.gw);
                break;
            case (R.id.product_increase_quantity_unit):
                classicDialog.showSingleChoice(getString(R.string.title_select_quantity_unit),null,this.quantityProfileModelList, KeyModel.qty);
                break;
        }
    }

    public boolean checkConCatFieldIsNotNUll(String productCode,String puductName){
        boolean result = true;
        if(ObjectUtil.isNullEmpty(productCode) || ObjectUtil.isNullEmpty(puductName)){
            result = false;
        }
        return result;
    }

    @Override
    public void settingPagesWeight(WeightProfileModel weightProfileModel) {
        product_increase_weight_unit.setText(weightProfileModel.getWeightUnit());
        weightId = weightProfileModel.getWeightId();
        Log.v("asd","settingPagesWeight :" + weightProfileModel.toString());
    }

    @Override
    public void settingPagesQty(QuantityProfileModel quantityProfileModel) {
        product_increase_quantity_unit.setText(quantityProfileModel.getQuantityUnit());
        quantityId = quantityProfileModel.getQuantityId();
        Log.v("asd","settingPagesQty :" + quantityProfileModel.toString());
    }
}
