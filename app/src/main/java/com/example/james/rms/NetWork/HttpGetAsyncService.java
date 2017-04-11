package com.example.james.rms.NetWork;

import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by James on 21/1/2017.
 */

public class HttpGetAsyncService {

    public String findAllFromUserProfile(String url) {
        String result="";
        try {
            result = new HttpGetAsync().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.v("sv","findAllFromUserProfile : "+result);
        return result;
    }
}
