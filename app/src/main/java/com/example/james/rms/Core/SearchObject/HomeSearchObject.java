package com.example.james.rms.Core.SearchObject;

/**
 * Created by jamie on 2017/4/18.
 */

public class HomeSearchObject {

    private String partyId;

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    @Override
    public String toString() {
        return "ProductSearchObject [partyId=" + partyId + "]";
    }

}
