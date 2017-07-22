package com.example.james.rms.ITF.Model;

/**
 * Created by Jamie on 22/7/2017.
 */

public class RefreshModel {
    private String className;
    private int rid;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "RefreshModel{" +
                "className='" + className + '\'' +
                ", rid=" + rid +
                '}';
    }
}
