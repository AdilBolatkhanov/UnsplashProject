package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image

class SortSearchImagesUseCase(
    private val repository: UnsplashRepository
) {
    operator fun invoke(sortBy: String) : LiveData<PagedList<Image>> {
        return repository.sortSearchImages(sortBy)
    }

}