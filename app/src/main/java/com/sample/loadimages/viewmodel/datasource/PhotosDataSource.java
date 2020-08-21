package com.sample.loadimages.viewmodel.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.sample.loadimages.model.Photo;
import com.sample.loadimages.model.SearchResponse;
import com.sample.loadimages.networking.PhotosService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosDataSource extends PageKeyedDataSource<Integer, Photo> {

    public static final int PAGE_SIZE = 100;

    private PhotosService photosService = new PhotosService();

    private String searchQuery;

    public PhotosDataSource(final String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Photo> callback) {
        photosService.fetchPhotos(searchQuery, 1).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                assert response.body() != null;
                callback.onResult(response.body().getPhotos().getPhotosList(), null, 2);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer, Photo> callback) {
        photosService.fetchPhotos(searchQuery, 1).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                assert response.body() != null;
                callback.onResult(response.body().getPhotos().getPhotosList(), params.key > 1 ? params.key - 1 : null);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Photo> callback) {
        photosService.fetchPhotos(searchQuery, 1).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                assert response.body() != null;
                callback.onResult(response.body().getPhotos().getPhotosList(), params.key + 1);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }
}
