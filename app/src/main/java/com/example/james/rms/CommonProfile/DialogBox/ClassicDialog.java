package com.example.james.rms.CommonProfile.DialogBox;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.james.rms.CommonProfile.DialogBox.Service.ClassicDialogService;
import com.example.james.rms.Core.Model.KeyModel;
import com.example.james.rms.Core.Model.QuantityProfileModel;
import com.example.james.rms.Core.Model.WeightProfileModel;
import com.example.james.rms.Operation.OperationContainer;
import com.example.james.rms.Operation.ProductAction.ProductIncrease;
import com.example.james.rms.R;
import com.example.james.rms.Setting.SettingContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by james on 15/3/2017.
 */

public class ClassicDialog {
    Context context;
    MaterialDialog materialDialog;


    public ClassicDialog(Context context){
        this.context = context;
    }
    public void showIndeterminate(int color, String title, String content){
                 materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void showLeave(String content){
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

    public void showBackPrevious(String content){
        materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent=new Intent();
                        intent.setClass(context,OperationContainer.class);
                        context.startActivity(intent);
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

    public void showMissingField(List<String> name){
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
    public void showReceivingPreInsertBox(){
        materialDialog = new MaterialDialog.Builder(context)
                .title(R.string.createReceivingOrder)
                .content(R.string.createQusetion)
                .canceledOnTouchOutside(false)
                .positiveText(R.string.yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
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

    public void showInputBox(String title, final String content, String hint,final String key,final String partyId){
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
                        if(context instanceof SettingContainer){
                            SettingContainer settingContainer = (SettingContainer)context;
                            c = settingContainer;
                            switch (key){
                                case KeyModel.gw:
                                    WeightProfileModel weightProfileModel = new WeightProfileModel();
                                    weightProfileModel.setCreateDate(new Date());
                                    weightProfileModel.setPartyId(partyId);
                                    weightProfileModel.setWeightUnit(String.valueOf(input));
                                    c.settingPagesWeight(weightProfileModel);
                                    break;
                                case KeyModel.qty:
                                    QuantityProfileModel quantityProfileModel = new QuantityProfileModel();
                                    quantityProfileModel.setCreateDate(new Date());
                                    quantityProfileModel.setQuantityUnit(String.valueOf(input));
                                    quantityProfileModel.setPartyId(partyId);
                                    c.settingPagesQty(quantityProfileModel);
                            }
                        }
                    }
                }).show();
    }

    public void showSingleChoice(String title, String hint,final List modeles,final String key,Long itemId){
        List<String> stringList = new ArrayList<>();
        int defaultSelected= -1;

        if(modeles instanceof List<?>){
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
                }
            }
        }

        new MaterialDialog.Builder(this.context)
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

                        if(context instanceof ProductIncrease) {
                            ProductIncrease productIncrease = (ProductIncrease)context;
                            ClassicDialogService c = productIncrease;

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
                                    c.settingPagesWeight(weightProfileModelList.get(which));
                                    break;
                                case KeyModel.qty:
                                    c.settingPagesQty(quantityProfileModelList.get(which));
                            }

                        }
                        return true;
                    }
                })
                .show();
    }
    public void dismiss(){
        materialDialog.dismiss();
    }
}
