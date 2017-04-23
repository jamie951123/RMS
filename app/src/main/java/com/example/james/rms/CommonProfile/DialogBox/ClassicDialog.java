package com.example.james.rms.CommonProfile.DialogBox;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.james.rms.Operation.OperationContainer;
import com.example.james.rms.R;

import java.util.ArrayList;
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

    public void showSingleChoice(String title, List<String> list){
        new MaterialDialog.Builder(this.context)
                .title(title)
                .items(list)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        return true;
                    }
                })
                .positiveText(R.string.choose)
                .show();
    }
    public void dismiss(){
        materialDialog.dismiss();
    }
}
