package com.example.james.rms.Login;

import com.example.james.rms.NetWork.HttpPostAsync;

import java.util.concurrent.ExecutionException;

/**
 * Created by James on 21/1/2017.
 */

public class LoginHttpPostService {

    public String checkLogin(String url, String json){
        String result = "";
        try {
            result = new HttpPostAsync().execute(url,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
