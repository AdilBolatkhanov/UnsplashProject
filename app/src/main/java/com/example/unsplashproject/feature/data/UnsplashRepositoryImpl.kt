package com.example.unsplashproject.feature.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.unsplashproject.api.LATEST
import com.example.unsplashproject.feature.data.local.ImagesLocalDataSource
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.UnsplashRepository
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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


    override suspend fun searchImages(
        clientId: String,
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): List<Image> {
        return listOf()
    }

}