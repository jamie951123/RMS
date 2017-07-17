package com.example.james.rms.NetWork;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.james.rms.CommonProfile.AsyncMessage;
import com.example.james.rms.CommonProfile.CommonFactory;
import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
import com.example.james.rms.Core.Model.NetworkModel;
import com.example.james.rms.Login.LoginActivity;
import com.example.james.rms.R;

import java.io.IOException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by James on 21/1/2017.
 */

public class HttpPostAsync extends AsyncTask<String,Integer,String> {

    private NetworkModel networkModel;

    public HttpPostAsync(NetworkModel networkModel) {
        this.networkModel = networkModel;
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        boolean a = CommonFactory.isNetworkConnection(networkModel.getAppCompatActivity());
        if(!a){
            return AsyncMessage.error_network;
        }
        String url = params[0];
        String json = params[1];
        String result="";
        try {
//            publishProgress(25);
            result = post(url,json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
        if(ObjectUtil.isNotNullEmpty(s) && s.equals(AsyncMessage.error_network)){
            AppCompatActivity appCompatActivity = (AppCompatActivity)networkModel.getAppCompatActivity();
            View view = CommonFactory.updateView(appCompatActivity.getLayoutInflater().inflate(R.layout.crouton_custom_view, null), networkModel.getAppCompatActivity().getString(R.string.label_bad_network_connection), ContextCompat.getDrawable(networkModel.getAppCompatActivity(), R.drawable.wifi_scan_black));
            Crouton.make(appCompatActivity, view).show();
        }
//        ClassicDialog.dismiss();
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
