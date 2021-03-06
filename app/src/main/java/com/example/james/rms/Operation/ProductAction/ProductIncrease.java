package com.example.james.rms.Operation.ProductAction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.DialogBox.DialogModel;
import com.example.james.rms.CommonProfile.DialogBox.Service.ClassicDialogService;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.StartActivityForResultKey;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.MovementRecordCombine;
import com.example.james.rms.Core.Combine.ProductCombine;
import com.example.james.rms.Core.Dao.ProductDao;
import com.example.james.rms.Core.Dao.ProductDaoImpl;
import com.example.james.rms.Core.Dao.QuantityProfileDao;
import com.example.james.rms.Core.Dao.QuantityProfileDaoImpl;
import com.example.james.rms.Core.Dao.WeightProfileDao;
import com.example.james.rms.Core.Dao.WeightProfileDaoImpl;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.MovementRecord;
import com.example.james.rms.Core.Model.ProductModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Core.SearchObject.SearchCombine;
import com.example.james.rms.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

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
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    //MyPreferences
    private MyPreferences myPreferences;
    private String combine_partyIdAndStatus;
    private String partyId;

    //
    private List<WeightProfileModel> weightProfileModelList;
    private List<QuantityProfileModel> quantityProfileModelList;
    private Long defaultWeightId;
    private Long defaultQtyId;
    ProductModel gateInProductModel = new ProductModel();
    //
    //
    private WeightProfileDao weightProfileDao;
    private QuantityProfileDao quantityProfileDao;
    private ProductDao productDao;

    //PutExtra
    private MovementRecord movementRecord;
    //
    //Combine
    private MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_increase);
        ButterKnife.bind(this);
        // Dao
        weightProfileDao = new WeightProfileDaoImpl(this);
        quantityProfileDao = new QuantityProfileDaoImpl(this);
        productDao = new ProductDaoImpl(this);
        //
        defaultWeightId = null;
        defaultQtyId = null;
        increase_submit.setOnClickListener(this);
        product_increase_weight_unit.setOnClickListener(this);
        product_increase_quantity_unit.setOnClickListener(this);
        //Preferences
        myPreferences = new MyPreferences(this, PreferencesKey.login_information);
        partyId =  myPreferences.getPreferences_PartyId().get("partyId");
        //HttpOK
        String combine_partyId = SearchCombine.combine_partyId(partyId);
        weightProfileModelList = weightProfileDao.findByPartyId(combine_partyId);
        quantityProfileModelList  = quantityProfileDao.findByPartyId(combine_partyId);

        String editJson = null;
        String movementRecord_json = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                editJson= null;
                movementRecord_json = null;
            } else {
                editJson= extras.getString(StartActivityForResultKey.productModel);
                movementRecord_json = extras.getString(StartActivityForResultKey.movementRecord);
            }
        }

        if(ObjectUtil.isNotNullEmpty(movementRecord_json)){
            movementRecord = movementRecordCombine.jsonToModel(movementRecord_json);
        }

        if(ObjectUtil.isNotNullEmpty(editJson)){
            ProductCombine productCombine = new ProductCombine(ProductModel.class);
            ProductModel editModel = productCombine.jsonToModel(editJson);
            setAllField(editModel);
            return;
        }

        setActionDate();

    }

    private void setAllField(ProductModel productModel){
        this.gateInProductModel = productModel;
        String weight_unit = null;
        String quantity_unit = null;
        if(productModel.getWeightprofile()!=null){
            if(ObjectUtil.isNotNullEmpty(productModel.getWeightprofile().getWeightUnit())){
                weight_unit = productModel.getWeightprofile().getWeightUnit();
                defaultWeightId = productModel.getWeightprofile().getWeightId();
            }
        }
        if(productModel.getQuantityProfile()!=null){
            if(ObjectUtil.isNotNullEmpty(productModel.getQuantityProfile().getQuantityUnit())){
                quantity_unit = productModel.getQuantityProfile().getQuantityUnit();
                defaultQtyId =  productModel.getQuantityProfile().getQuantityId();
            }
        }
        increase_puductCode.setText(productModel.getProductCode());
        increase_puductName.setText(productModel.getProductName());
        product_increase_weight_unit.setText(weight_unit);
        product_increase_quantity_unit.setText(quantity_unit);
        increase_descriptionEN.setText(productModel.getProductDescriptionEN());
        increase_descriptionCN.setText(productModel.getProductDescriptionCH());
        increase_createDate.setText(ObjectUtil.dateToString(productModel.getCreateDate()));
        increase_remark.setText(productModel.getRemark());
    }

    private void setActionDate() {
        increase_createDate.setText(dateFormat.format(new Date()));
    }

    @Override
    public void onClick(View v) {
        Long productId = null;
        if(gateInProductModel != null){
            productId = gateInProductModel.getProductId();
        }
        String productCode   = increase_puductCode.getText().toString();
        String puductName    = increase_puductName.getText().toString();
        String descriptionCN = increase_descriptionCN.getText().toString();
        String descriptionEN = increase_descriptionEN.getText().toString();
        String remark        = increase_remark.getText().toString();
        Date createDate      = new Date();
        String partyId       = this.partyId;

//        ClassicDialog classicDialog = new ClassicDialog(this);
        DialogModel dialogModel = new DialogModel();
        dialogModel.setContext(this);

        switch (v.getId()){
            case (R.id.increase_submit):
                if(!checkConCatFieldIsNotNUll(productCode,puductName)){
                    Toast.makeText(this,R.string.productAction,Toast.LENGTH_LONG).show();
                    break;
                }
                String result = ProductCombine.combine_AddProduct(productId,productCode,puductName,descriptionCN,
                        descriptionEN,remark,createDate,partyId,defaultWeightId,defaultQtyId);
                ProductModel productModel = productDao.save(result);
                if(productModel != null) {
                    Toast.makeText(this,R.string.insert_successful,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent = intent.setClass(this, NavigationController.class);
                    movementRecord.setExist_fragment(R.id.nav_all_product);
                    intent.putExtra(StartActivityForResultKey.movementRecord, movementRecordCombine.modelToJson(movementRecord));
                    startActivity(intent);
                    break;
                }
                Toast.makeText(this,R.string.insert_fail,Toast.LENGTH_SHORT).show();
                break;

            case (R.id.product_increase_weight_unit):
                dialogModel.setTitle(getString(R.string.title_select_weight_unit));
                dialogModel.setModeles(weightProfileModelList);
                dialogModel.setKey(KeyModel.gw);
                dialogModel.setItemId(defaultWeightId);
                ClassicDialog.showSingleChoice(dialogModel);
                break;
            case (R.id.product_increase_quantity_unit):
                dialogModel.setTitle(getString(R.string.title_select_quantity_unit));
                dialogModel.setModeles(quantityProfileModelList);
                dialogModel.setKey(KeyModel.qty);
                dialogModel.setItemId(defaultQtyId);
                ClassicDialog.showSingleChoice(dialogModel);
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
        defaultWeightId= weightProfileModel.getWeightId();
//        weightId = weightProfileModel.getWeightId();
        Log.v("asd","settingPagesWeight :" + weightProfileModel.toString());
    }

    @Override
    public void settingPagesQty(QuantityProfileModel quantityProfileModel) {
        product_increase_quantity_unit.setText(quantityProfileModel.getQuantityUnit());
        defaultQtyId = quantityProfileModel.getQuantityId();
//        quantityId = quantityProfileModel.getQuantityId();
        Log.v("asd","settingPagesQty :" + quantityProfileModel.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    @Override
    public void onBackPressed() {
        ClassicDialog.showBackPrevious(this,getString(R.string.previous),movementRecord);
    }

}
