package com.sample.loadimages.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @SerializedName("photo") private List<Photo> photosList;

    public List<Photo> getPhotosList() {
        return photosList;
    }

    public void setPhotosList(List<Photo> photosList) {
        this.photosList = photosList;
    }
}
