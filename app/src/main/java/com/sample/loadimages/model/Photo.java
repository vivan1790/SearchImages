package com.sample.loadimages.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Photo {
    @SerializedName("id") private String id;
    @SerializedName("secret") private String secret;
    @SerializedName("server") private String server;
    @SerializedName("farm") private int farm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return farm == photo.farm &&
                Objects.equals(id, photo.id) &&
                Objects.equals(secret, photo.secret) &&
                Objects.equals(server, photo.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secret, server, farm);
    }
}
