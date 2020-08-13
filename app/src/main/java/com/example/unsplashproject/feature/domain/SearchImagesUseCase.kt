package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image

class SearchImagesUseCase(
    private val repository: UnsplashRepository
) {
    operator fun invoke(query: String): LiveData<PagedList<Image>> {
        return repository.searchImages(query)
    }
}