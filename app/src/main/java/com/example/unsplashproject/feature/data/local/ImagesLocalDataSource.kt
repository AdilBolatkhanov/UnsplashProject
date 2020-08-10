package com.example.unsplashproject.feature.data.local

import androidx.paging.DataSource
import com.example.unsplashproject.db.dao.ListImagesDao
import com.example.unsplashproject.feature.domain.entity.Image


interface ImagesLocalDataSource {
    fun getAllImagesByPage(): DataSource.Factory<Int, Image>

    suspend fun insertImages(list: List<Image>)

    suspend fun deleteAllImages()
}

class ImagesLocalDataSourceImpl(
    private val listImagesDao: ListImagesDao
):ImagesLocalDataSource{
    override fun getAllImagesByPage(): DataSource.Factory<Int, Image> =
        listImagesDao.getAllImagesByPage()


    override suspend fun insertImages(list: List<Image>) {
        listImagesDao.save(list)
    }

    override suspend fun deleteAllImages() {
        listImagesDao.deleteAll()
    }
}

