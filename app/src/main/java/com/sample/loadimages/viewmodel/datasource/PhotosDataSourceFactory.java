package com.sample.loadimages.viewmodel.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.sample.loadimages.model.Photo;

public class PhotosDataSourceFactory extends DataSource.Factory<Integer, Photo> {

    private PhotosDataSource photosDataSource;
    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> photosDataSourceLiveData
            = new MutableLiveData<>();

    /*private val articleLiveDataSource : MutableLiveData<PageKeyedDataSource<Int, Article>>
            = MutableLiveData()
    private val articleDataSource = dataSource

    override fun create(): DataSource<Int, Article> {
        //val articleDataSource = ArticleDataSource()
        articleLiveDataSource.postValue(articleDataSource)
        return articleDataSource
    }*/

    public PhotosDataSourceFactory(PhotosDataSource dataSource) {
        photosDataSource = dataSource;
    }

    @NonNull
    @Override
    public DataSource<Integer, Photo> create() {
        photosDataSourceLiveData.postValue(photosDataSource);
        return photosDataSource;
    }
}
