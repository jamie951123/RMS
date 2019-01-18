package com.example.james.rms.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.james.rms.core.combine.MovementRecordCombine;
import com.example.james.rms.core.model.MovementRecord;
import com.example.james.rms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jamie on 15/7/2017.
 */

public class CommonFactory {

    public static View updateView(View view, String meassage , Drawable image){
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        viewHolder.crouton_message.setText(meassage);
        viewHolder.imageView.setImageDrawable(image);
        return view;
    }

    static class ViewHolder{
        @BindView(R.id.crouton_image)
        ImageView imageView;
        @BindView(R.id.crouton_message)
        TextView crouton_message;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public static String movementFactory_str(String orginalClass,String tragetClass,int exist_fragment){
        MovementRecord movementRecord = new MovementRecord();
        movementRecord.setOriginalClass_string(orginalClass);
        movementRecord.setTargetClass_string(tragetClass);
        movementRecord.setExist_fragment(exist_fragment);

        MovementRecordCombine movementRecordCombine = new MovementRecordCombine(MovementRecord.class);
        String movementRecord_str = movementRecordCombine.modelToJson(movementRecord);
        return  movementRecord_str;
    }

    public static boolean isNetworkConnection(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
