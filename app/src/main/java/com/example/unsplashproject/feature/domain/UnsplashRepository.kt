package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image


interface UnsplashRepository {
    fun getImages() : LiveData<PagedList<Image>>

    fun updateImages():  LiveData<PagedList<Image>>

    fun sortImages(sortBy: String): LiveData<PagedList<Image>>

    suspend fun searchImages(clientId: String,query: String, page: Int, perPage: Int, orderBy: String) : List<Image>

}