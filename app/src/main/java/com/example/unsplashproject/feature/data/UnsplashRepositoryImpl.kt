package com.example.unsplashproject.feature.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.unsplashproject.api.RELEVANT
import com.example.unsplashproject.feature.data.local.ImagesLocalDataSource
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.UnsplashRepository
import com.example.unsplashproject.feature.domain.entity.Image
import java.util.concurrent.Executors

const val PER_PAGE = 30

class UnsplashRepositoryImpl(
    private val remoteDataSource: ImagesRemoteDataSource,
    private val localDataSource: ImagesLocalDataSource
) : UnsplashRepository {
    private val config = PagedList.Config.Builder()
        .setPageSize(PER_PAGE)
        .setEnablePlaceholders(false)
        .build()
    private val boundaryCallback =
        ImagesBoundaryCallback(
            remoteDataSource,
            localDataSource
        )

    private var newQuery = ""
    private var newOrder = RELEVANT

    override fun getImages(): LiveData<PagedList<Image>> {
        return localDataSource.getAllImagesByPage()
            .toLiveData(
                config = config,
                boundaryCallback = boundaryCallback,
                fetchExecutor = Executors.newFixedThreadPool(5)
            )
    }

    override fun updateImages(): LiveData<PagedList<Image>> {
        boundaryCallback.update()
        return getImages()
    }

    override fun sortImages(sortBy: String): LiveData<PagedList<Image>> {
        boundaryCallback.setSorting(sortBy)
        return getImages()
    }

    override fun sortSearchImages(sortBy: String): LiveData<PagedList<Image>> {
        newOrder = sortBy
        return updateSearchImages()
    }

    override fun searchImages(
        query: String
    ): LiveData<PagedList<Image>> {
        newQuery = query
        return updateSearchImages()
    }

    override fun updateSearchImages(): LiveData<PagedList<Image>> {
        val searchDataSourceFactory = SearchDataSourceFactory(remoteDataSource, newQuery, newOrder)
        return LivePagedListBuilder<Int, Image>(searchDataSourceFactory, config).build()
    }

}
