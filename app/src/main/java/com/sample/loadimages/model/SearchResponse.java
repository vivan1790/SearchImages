package com.sample.loadimages.model;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {
    @SerializedName("photos") private Photos photos;
    @SerializedName("stat") private String status;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
