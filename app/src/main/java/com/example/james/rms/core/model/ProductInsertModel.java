package com.example.james.rms.core.model;

import java.util.List;

/**
 * Created by james on 26/3/2017.
 */

public class ProductInsertModel {

    String insertMessage;
    String selection;
    List<String> missingField;

    public String getInsertMessage() {
        return insertMessage;
    }

    public void setInsertMessage(String insertMessage) {
        this.insertMessage = insertMessage;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public List<String> getMissingField() {
        return missingField;
    }

    public void setMissingField(List<String> missingField) {
        this.missingField = missingField;
    }
}
