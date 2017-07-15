package com.example.james.rms.NetWork;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.james.rms.CommonProfile.AsyncMessage;
import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.R;

import java.io.IOException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by James on 21/1/2017.
 */

public class HttpGetAsync extends AsyncTask<String, Void, String>{

    private NetworkModel networkModel;
//    private ClassicDialog classicDialog;

    public HttpGetAsync(NetworkModel networkModel) {
        this.networkModel = networkModel;
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onPreExecute() {
//        classicDialog = new ClassicDialog(networkModel.getAppCompatActivity());
//        ClassicDialog.showIndeterminate(networkModel.getAppCompatActivity(),R.color.blue0895ef,networkModel.getAppCompatActivity().getString(R.string.loading),networkModel.getAppCompatActivity().getString(R.string.waiting));
    }

    @Override
    protected String doInBackground(String... params) {
        boolean a = CommonFactory.isNetworkConnection(networkModel.getAppCompatActivity());
        if(!a){
            return AsyncMessage.error_network;
        }
        String url  = params[0];
        String result = "";
        try {
            result = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.v("asd","onPostExecute");
        if(ObjectUtil.isNotNullEmpty(s) && s.equals(AsyncMessage.error_network)){
            AppCompatActivity appCompatActivity = (AppCompatActivity)networkModel.getAppCompatActivity();
            View view = CommonFactory.updateView(appCompatActivity.getLayoutInflater().inflate(R.layout.crouton_custom_view, null), networkModel.getAppCompatActivity().getString(R.string.label_bad_network_connection), ContextCompat.getDrawable(networkModel.getAppCompatActivity(), R.drawable.wifi_scan_black));
            Crouton.make(appCompatActivity, view).show();
        }
//        ClassicDialog.dismiss();
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
