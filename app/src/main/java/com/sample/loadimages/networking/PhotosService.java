package com.sample.loadimages.networking;

import androidx.annotation.NonNull;

import com.sample.loadimages.model.SearchResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosService {

    private final String BASE_URL = "https://www.flickr.com/";

    private PhotosApi photosApi;

    public PhotosService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        photosApi = retrofit.create(PhotosApi.class);
    }

    public Call<SearchResponse> fetchPhotos(@NonNull String text, @NonNull int page) {
        return photosApi.getPhotosByTextOnPage(text, page);
    }
}
