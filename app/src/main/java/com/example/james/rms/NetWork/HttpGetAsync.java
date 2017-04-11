package com.example.james.rms.NetWork;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by James on 21/1/2017.
 */

public class HttpGetAsync extends AsyncTask<String, Void, String>{
    OkHttpClient client = new OkHttpClient();
    @Override
    protected String doInBackground(String... params) {
        String url  = params[0];
        String result = "";
        try {
            result = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
