package com.example.james.rms.CommonProfile.DialogBox;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.james.rms.Operation.OperationContainer;
import com.example.james.rms.R;

import java.util.List;

/**
 * Created by james on 15/3/2017.
 */

public class ClassicDialog {
    Context context;
    int color;
    String title;
    String content;
    MaterialDialog materialDialog;

    public ClassicDialog(Context context, int color, String title, String content) {
        this.context = context;
        this.color = color;
        this.title = title;
        this.content = content;
    }
    public ClassicDialog(Context context,String content){
        this.context = context;
        this.content = content;
    }
    public ClassicDialog(Context context){
        this.context = context;
    }
    public void showIndeterminate(){
                 materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void showLeave(){
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

    public void showBackPrevious(){
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
    public void dismiss(){
        materialDialog.dismiss();
    }
}
