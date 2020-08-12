package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image

class GetSearchImagesUseCase(
    private val repository: UnsplashRepository
) {
    fun searchImages(query: String) : LiveData<PagedList<Image>> {
        return repository.searchImages(query)
    }
}