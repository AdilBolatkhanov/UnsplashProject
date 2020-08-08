package com.example.unsplashproject.db

import android.provider.Settings
import androidx.paging.PagedList
import com.example.unsplashproject.api.UnsplashApi
import com.example.unsplashproject.db.dao.ListImagesDao
import com.example.unsplashproject.db.entity.Image
import com.example.unsplashproject.feature.data.dto.mappers.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImagesBoundaryCallback (
    private val imagesDao: ListImagesDao,
    private val unsplashApi: UnsplashApi
) : PagedList.BoundaryCallback<Image>() {

    private var page = 1;

    override fun onZeroItemsLoaded() {
        GlobalScope.launch(Dispatchers.IO) {
            val resultImages = unsplashApi.getImagesByLatest(page = page++).toDomainModel()
            imagesDao.save(resultImages)
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Image) {
        GlobalScope.launch(Dispatchers.IO) {
            val resultImages = unsplashApi.getImagesByLatest(page = page++).toDomainModel()
            imagesDao.save(resultImages)
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Image) {
        super.onItemAtFrontLoaded(itemAtFront)
    }
}