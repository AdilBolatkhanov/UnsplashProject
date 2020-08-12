package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image


interface UnsplashRepository {
    fun getImages() : LiveData<PagedList<Image>>

    fun sortImages(sortBy: String): LiveData<PagedList<Image>>

    fun searchImages(query: String): LiveData<PagedList<Image>>

    fun sortSearchImages(sortBy: String): LiveData<PagedList<Image>>

    fun updateImages():  LiveData<PagedList<Image>>

    fun updateSearchImages(): LiveData<PagedList<Image>>
}