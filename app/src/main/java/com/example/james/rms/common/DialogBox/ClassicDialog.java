package com.example.james.rms.common.DialogBox;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.james.rms.common.DialogBox.service.ClassicDialogService;
import com.example.james.rms.common.Language.LocalizationModel;
import com.example.james.rms.common.Language.LocalizationUtil;
import com.example.james.rms.common.StartActivityForResultKey;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.controller.NavigationAct;
import com.example.james.rms.core.combine.MovementRecordCombine;
import com.example.james.rms.core.model.KeyModel;
import com.example.james.rms.core.model.MovementRecord;
import com.example.james.rms.core.model.QuantityProfileModel;
import com.example.james.rms.core.model.Status;
import com.example.james.rms.core.model.WeightProfileModel;
import com.example.james.rms.main.setting.SettingAct;
import com.example.james.rms.network.ServeProfile;
import com.example.james.rms.operation.product_action.ProductIncreaseAct;
import com.example.james.rms.R;
import com.example.james.rms.operation.unit_action.UnitIncrease;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by james on 15/3/2017.
 */

public class ClassicDialog {

    private static MaterialDialog materialDialog;

    public static void showIndeterminate(Context context,int color, String title, String content){
                 materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
//                .canceledOnTouchOutside(false)
//                .cancelable(false)
                .show();
    }

    public static void showLeave(final Context context,String content){
        materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent=new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        System.exit(0);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void showBackPrevious(final Context context,String content, final MovementRecord movementRecord){
        materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                            Intent intent=new Intent();
                            intent.setClass(context,Class.forName(ObjectUtil.isNotNullEmpty(movementRecord.getOriginalClass_string())?movementRecord.getOriginalClass_string(): NavigationAct.class.getCanonicalName()));
                            MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);
                            intent.putExtra(StartActivityForResultKey.movementRecord,movementRecordCombine.modelToJson(movementRecord));
                            context.startActivity(intent);
                        }catch (ClassNotFoundException e){
                            e.printStackTrace();
                        }

                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void showMissingField(final Context context,List<String> name){
        materialDialog = new MaterialDialog.Builder(context)
                .title(R.string.missingField)
                .canceledOnTouchOutside(false)
                .items(name)
                .positiveText(R.string.yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void showBasicInputBox(final DialogModel dialogModel){
        new MaterialDialog.Builder(dialogModel.getContext())
                .title(dialogModel.getTitle())
                .content(null)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT)
                .input(dialogModel.getContent(), dialogModel.getContent(), new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if(input != null){
                            ServeProfile.INSTANCE.setServe(input.toString());
                            Toast.makeText(dialog.getContext(),input,Toast.LENGTH_SHORT).show();
                        }
                        // Do something
                    }
                }).show();
    }
    public static void showInputBox(final Context context,String title, final String content, String hint,final String key,final String partyId){
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .inputRangeRes(1, 20, R.color.colorAccent)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .input(hint, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        Log.d("asd","[ClassicDialog]-showInputBox : " +input);
                        dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                        ClassicDialogService c;
                        if(context instanceof UnitIncrease){
                            UnitIncrease unitIncrease = (UnitIncrease)context;
                            c = unitIncrease;
                            switch (key){
                                case KeyModel.gw:
                                    WeightProfileModel weightProfileModel = new WeightProfileModel();
                                    weightProfileModel.setCreateDate(new Date());
                                    weightProfileModel.setCreateBy(partyId);
                                    weightProfileModel.setPartyId(partyId);
                                    weightProfileModel.setWeightUnit(String.valueOf(input));
                                    weightProfileModel.setStatus(Status.PROGRESS);
                                    c.settingPagesWeight(weightProfileModel);
                                    break;
                                case KeyModel.qty:
                                    QuantityProfileModel quantityProfileModel = new QuantityProfileModel();
                                    quantityProfileModel.setCreateDate(new Date());
                                    quantityProfileModel.setCreateBy(partyId);
                                    quantityProfileModel.setQuantityUnit(String.valueOf(input));
                                    quantityProfileModel.setPartyId(partyId);
                                    quantityProfileModel.setStatus(Status.PROGRESS);
                                    c.settingPagesQty(quantityProfileModel);
                            }
                        }
                    }
                }).show();
    }

    public static void showSingleChoice(DialogModel dialogModel){
       final List modeles = dialogModel.getModeles();
       final Context context = dialogModel.getContext();
       final String key = dialogModel.getKey();
       final String title = dialogModel.getTitle();
       final Long itemId = dialogModel.getItemId();
       final String itemId_str = dialogModel.getItemId_str();

        List<String> stringList = new ArrayList<>();
        int defaultSelected= -1;

        if(modeles != null && modeles instanceof List<?>){
            for(int i=0; i<modeles.size(); i++){
                Object obj = modeles.get(i);
                if (obj instanceof WeightProfileModel) {
                    WeightProfileModel w = (WeightProfileModel)obj;
                    stringList.add(w.getWeightUnit());
                    if(itemId != null && w.getWeightId().equals(itemId))defaultSelected = i;
                } else if (obj instanceof QuantityProfileModel) {
                    QuantityProfileModel q = (QuantityProfileModel)obj;
                    stringList.add(q.getQuantityUnit());
                    if(itemId != null && q.getQuantityId().equals(itemId))defaultSelected = i;
                } else if ( obj instanceof LocalizationModel){
                    LocalizationModel localizationModel = (LocalizationModel)obj;
                    stringList.add(localizationModel.getFullName());
                    if(itemId_str != null && localizationModel.getLanguageCode().equals(itemId_str))defaultSelected = i;
                }
            }
        }

        new MaterialDialog.Builder(context)
                .title(title)
                .items(stringList)
                .positiveText(R.string.choose)
                .canceledOnTouchOutside(false)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .itemsCallbackSingleChoice(defaultSelected, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if(context instanceof ProductIncreaseAct) {
                            ProductIncreaseAct productIncreaseAct = (ProductIncreaseAct)context;
                            ClassicDialogService c = productIncreaseAct;

                            List<WeightProfileModel> weightProfileModelList = new ArrayList<>();
                            List<QuantityProfileModel> quantityProfileModelList = new ArrayList<>();
                            for (Object obj : modeles) {
                                if (obj instanceof WeightProfileModel) {
                                    WeightProfileModel w = (WeightProfileModel) obj;
                                    weightProfileModelList.add(w);
                                } else if (obj instanceof QuantityProfileModel) {
                                    QuantityProfileModel q = (QuantityProfileModel) obj;
                                    quantityProfileModelList.add(q);
                                }
                            }
                            switch (key){
                                case KeyModel.gw:
                                    if(weightProfileModelList != null && !weightProfileModelList.isEmpty() && which != -1) {
                                        c.settingPagesWeight(weightProfileModelList.get(which));
                                    }
                                    break;
                                case KeyModel.qty:
                                    if(quantityProfileModelList != null && !quantityProfileModelList.isEmpty() && which != -1) {
                                        c.settingPagesQty(quantityProfileModelList.get(which));
                                    }
                            }

                        }

                        //Setting
                        if(context instanceof SettingAct){
                            List<LocalizationModel> localizationModels = new ArrayList<>();
                            for (Object obj : modeles) {
                                if (obj instanceof LocalizationModel) {
                                    LocalizationModel localizationModel = (LocalizationModel) obj;
                                    localizationModels.add(localizationModel);
                                }
                            }

                            switch (key){
                                case KeyModel.language:
                                    if(localizationModels != null && !localizationModels.isEmpty() && which !=-1) {
                                        String locationCode = localizationModels.get(which).getLanguageCode();
                                        String countryCode = Locale.getDefault().getCountry();
                                        if("zh".equalsIgnoreCase(locationCode))countryCode = "HK";
                                        LocalizationUtil.INSTANCE.setLanguage(context,locationCode,countryCode);
                                        Intent intent = new Intent();
                                        intent.setClass(context, NavigationAct.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        context.startActivity(intent);
                                    }
                                    break;
                            }
                        }
                        return true;
                    }
                })
                .show();
    }
    public static void dismiss(){
        Log.v("asd","Dialog Dismiss :" + materialDialog.isCancelled());
        materialDialog.dismiss();

    }
}
