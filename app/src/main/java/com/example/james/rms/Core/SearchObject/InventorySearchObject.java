package com.example.james.rms.Core.SearchObject;

import com.example.james.rms.Core.Model.Status;

/**
 * Created by jamie on 2017/4/27.
 */

public class InventorySearchObject extends HomeSearchObject{
    Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InventorySearchObject{" +
                "status=" + status +
                '}';
    }
}
