package com.example.james.rms.core.model;

/**
 * Created by jamie on 2017/4/22.
 */

public enum Status {
    PROGRESS("PROGRESS"),
    STOP("STOP");

    // 成员变量
    private String name;
    /**
     * @return
     */
    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                '}';
    }
}

