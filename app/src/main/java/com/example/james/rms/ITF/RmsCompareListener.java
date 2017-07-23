package com.example.james.rms.ITF;

/**
 * Created by EricH on 2/9/2016.
 */
public interface RmsCompareListener<T> {
    boolean productCodeMatch(T t, String string);
    boolean productNameMatch(T t, String string,int position);
    boolean receivingRemarkMatch(T t, String string);
}
