package com.example.james.rms.Core.Model;

import com.example.james.rms.CommonProfile.StartActivityForResultKey;

/**
 * Created by Jamie on 14/7/2017.
 */

public class MovementRecord {

    private String originalClass_string;
    private String targetClass_string;
    private int exist_fragment;


    public int getExist_fragment() {
        return exist_fragment;
    }

    public void setExist_fragment(int exist_fragment) {
        this.exist_fragment = exist_fragment;
    }

    public String getOriginalClass_string() {
        return originalClass_string;
    }

    public void setOriginalClass_string(String originalClass_string) {
        this.originalClass_string = originalClass_string;
    }

    public String getTargetClass_string() {
        return targetClass_string;
    }

    public void setTargetClass_string(String targetClass_string) {
        this.targetClass_string = targetClass_string;
    }
}
