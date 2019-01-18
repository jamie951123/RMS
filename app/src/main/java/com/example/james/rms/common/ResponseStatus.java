package com.example.james.rms.common;

/**
 * Created by jamie on 2017/5/7.
 */

public class ResponseStatus {
    final static String successful = "SUCCESSFUL";
    final static String fail = "FAIL";

    public static String getSuccessful() {
        return successful;
    }

    public static String getFail() {
        return fail;
    }
}
