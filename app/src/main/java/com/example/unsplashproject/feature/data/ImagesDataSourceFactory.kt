package com.example.unsplashproject.feature.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.unsplashproject.api.LATEST
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.entity.Image

class ImagesDataSourceFactory(
    private val remoteDataSource: ImagesRemoteDataSource,
    private val query: String,
    private val orderBy: String = LATEST
) :  DataSource.Factory<Int, Image>(){

    private val sourceLiveData = MutableLiveData<ImagesPageKeyedDataSource>()

    override fun create(): DataSource<Int, Image> {
        val imagesPageKeyedDataSource = ImagesPageKeyedDataSource(
            remoteDataSource, query, orderBy
        )
        sourceLiveData.postValue(imagesPageKeyedDataSource)
        return imagesPageKeyedDataSource
    }
}

