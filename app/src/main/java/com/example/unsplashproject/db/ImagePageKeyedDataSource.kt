package com.example.unsplashproject.db

import androidx.paging.PageKeyedDataSource
import com.example.unsplashproject.api.UnsplashApi
import com.example.unsplashproject.db.dao.ListImagesDao
import com.example.unsplashproject.db.entity.Image
import com.example.unsplashproject.feature.data.dto.mappers.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImagePageKeyedDataSource(
    private val imagesDao : ListImagesDao,
    private val unsplashApi: UnsplashApi
) : PageKeyedDataSource<Int, Image>() {

    private val page = 1;

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Image>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val images = imagesDao.getAllImagesByPage()
            callback.onResult(images, null, page + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        GlobalScope.launch(Dispatchers.IO) {
            val images = unsplashApi.getImagesByLatest().toDomainModel()
            imagesDao.save(images)
            callback.onResult(images, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        TODO("Not yet implemented")
    }

}