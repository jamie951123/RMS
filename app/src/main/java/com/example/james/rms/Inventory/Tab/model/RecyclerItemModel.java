package com.example.james.rms.Inventory.Tab.model;

/**
 * Created by james on 16/3/2017.
 */

public class RecyclerItemModel {
    private String title;
    private int imageUrl;

    public RecyclerItemModel(String title,int imageUrl){

        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
