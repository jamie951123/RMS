package com.example.james.rms.CommonProfile.DialogBox;

import android.content.Context;

import java.util.List;

/**
 * Created by Jamie on 19/7/2017.
 */

public class DialogModel {
    private Context context;
    private String title;
    private String hint;
    private List modeles;
    private String key;
    private Long itemId;
    private String itemId_str;

    //Edit
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List getModeles() {
        return modeles;
    }

    public void setModeles(List modeles) {
        this.modeles = modeles;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemId_str() {
        return itemId_str;
    }

    public void setItemId_str(String itemId_str) {
        this.itemId_str = itemId_str;
    }
}
