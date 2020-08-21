package com.sample.loadimages.networking;

import com.sample.loadimages.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotosApi {

    @GET("/services/rest/?method=flickr.photos.search&api_key=e4c876932ae551689ff04241b0279061&format=json&nojsoncallback=1&perpage=100")
    Call<SearchResponse> getPhotosByTextOnPage(@Query("text") String search, @Query("page") int page);
}
