package com.example.james.rms.Core.SearchObject;

import com.example.james.rms.Core.Model.Status;

/**
 * Created by jamie on 2017/4/18.
 */

public class HomeSearchObject {

    private Long id;

    private String partyId;

    Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeSearchObject{" +
                "id=" + id +
                ", partyId='" + partyId + '\'' +
                ", status=" + status +
                '}';
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
