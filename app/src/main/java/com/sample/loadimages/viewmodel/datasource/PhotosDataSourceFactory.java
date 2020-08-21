package com.sample.loadimages.viewmodel.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.sample.loadimages.model.Photo;

public class PhotosDataSourceFactory extends DataSource.Factory<Integer, Photo> {

    private PhotosDataSource photosDataSource;

    public PhotosDataSourceFactory(PhotosDataSource dataSource) {
        photosDataSource = dataSource;
    }

    @NonNull
    @Override
    public DataSource<Integer, Photo> create() {
        return photosDataSource;
    }
}
