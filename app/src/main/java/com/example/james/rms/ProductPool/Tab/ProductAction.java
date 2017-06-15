//package com.example.james.rms.ProductPool.Tab;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.james.rms.CommonProfile.MyBaseFragment;
//import com.example.james.rms.CommonProfile.ObjectUtil;
//import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
//import com.example.james.rms.Core.Product.Model.ProductInsertModel;
//import com.example.james.rms.Core.Combine.ProductCombine;
//import com.example.james.rms.ProductPool.Service.ProductService;
//import com.example.james.rms.ProductPool.Service.ProductServiceImpl;
//import com.example.james.rms.R;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class ProductAction extends MyBaseFragment implements View.OnClickListener{
//
//    @BindView(R.id.action_imageView)
//    ImageView action_imageView;
//    @BindView(R.id.action_puductCode)
//    EditText action_puductCode;
//    @BindView(R.id.action_puductName)
//    EditText action_puductName;
//    @BindView(R.id.product_descriptionCN)
//    EditText product_descriptionCN;
//    @BindView(R.id.product_descriptionEN)
//    EditText product_descriptionEN;
//    @BindView(R.id.action_remark)
//    EditText action_remark;
//    @BindView(R.id.action_createDate)
//    EditText action_createDate;
//    @BindView(R.id.action_submit)
//    Button action_submit;
//
//    //
//    private ProductCombine productCombine = new ProductCombine();
//    private ProductService productService = new ProductServiceImpl();
//    //
//    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//    //
//    String partyId_Preferences="";
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.product_action, container, false);
//        ButterKnife.bind(this, rootView);
//        action_submit.setOnClickListener(this);
//        //Preferences
//        PartyIdPreferences partyIdPreferences = new PartyIdPreferences(getActivity(),"loginInformation",getActivity().MODE_PRIVATE);
//        partyId_Preferences =  partyIdPreferences.getPreferences_PartyId().get("partyId");
//        //
//        setActionDate();
//        return rootView;
//    }
//
//    private void setActionDate() {
//        action_createDate.setText(dateFormat.format(new Date()));
//    }
//
//    @Override
//    public void onClick(View v) {
//        String productCode   = action_puductCode.getText().toString();
//        String puductName    = action_puductName.getText().toString();
//        String descriptionCN = product_descriptionCN.getText().toString();
//        String descriptionEN = product_descriptionEN.getText().toString();
//        String remark        = action_remark.getText().toString();
//        Date createDate      = ObjectUtil.dateToString(new Date());
//        String partyId       = partyId_Preferences;
//        switch (v.getId()){
//            case (R.id.action_submit):
//                if(!checkConCatFieldIsNotNUll(productCode,puductName)){
//                    Toast.makeText(getActivity(),R.string.productAction,Toast.LENGTH_LONG).show();
//                    break;
//                }
//                String result = productCombine.combine_AddProduct(productCode,puductName,descriptionCN,
//                        descriptionEN,remark,createDate,partyId);
//                Log.v("mylog:","combine_AddProduct: :"+result);
//                ProductInsertModel productInsertModel = productService.insertProduct(result);
//                Toast.makeText(getActivity(),productInsertModel.getInsertMessage(),Toast.LENGTH_SHORT).show();
//                Log.v("mylog:","actionResponseModel.getInsertMessage(): :"+productInsertModel.getInsertMessage());
//        }
//    }
//
//    public boolean checkConCatFieldIsNotNUll(String productCode,String puductName){
//        boolean result = true;
//        if(ObjectUtil.isNullEmpty(productCode) || ObjectUtil.isNullEmpty(puductName)){
//            result = false;
//        }
//        return result;
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        return false;
//    }
//
//    @Override
//    public void transferViewPager(int rid, List models) {
//        Toast.makeText(getActivity(),"ProductAction",Toast.LENGTH_SHORT).show();
//    }
//}
