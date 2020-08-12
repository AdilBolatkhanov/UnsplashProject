package com.example.unsplashproject.feature.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.unsplashproject.api.RELEVANT
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.entity.Image


class SearchDataSourceFactory(
    private val remoteDataSource: ImagesRemoteDataSource,
    private val query: String,
    private val orderBy: String = RELEVANT
) : DataSource.Factory<Int, Image>(){
    private val sourceLiveData = MutableLiveData<SearchDataSource>()
    private var latestSource : SearchDataSource? = null

    override fun create(): DataSource<Int, Image> {
        latestSource = SearchDataSource(remoteDataSource,
        query, orderBy)
        sourceLiveData.postValue(latestSource)
        return latestSource as SearchDataSource
    }

}