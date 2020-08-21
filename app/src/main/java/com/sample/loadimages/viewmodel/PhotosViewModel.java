package com.sample.loadimages.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.sample.loadimages.model.Photo;
import com.sample.loadimages.viewmodel.datasource.PhotosDataSource;
import com.sample.loadimages.viewmodel.datasource.PhotosDataSourceFactory;

public class PhotosViewModel extends ViewModel {

    private LiveData<PagedList<Photo>> photosPagedList;

    public LiveData<PagedList<Photo>> getPhotosPagedList(String searchQuery) {
        PhotosDataSourceFactory factory = new PhotosDataSourceFactory(
                new PhotosDataSource(searchQuery));
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PhotosDataSource.PAGE_SIZE)
                .build();
        photosPagedList = new LivePagedListBuilder<>(factory, config).build();
        return photosPagedList;
    }
}
