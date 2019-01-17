package com.example.james.rms.Core.Model;

/**
 * Created by Jamie on 3/7/2017.
 */
@Deprecated
public enum OperationType {
    IN("IN"),
    OUT("OUT");

    // 成员变量
    private String name;
    /**
     * @return
     */
    OperationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OperationType{" +
                "name='" + name + '\'' +
                '}';
    }
}
