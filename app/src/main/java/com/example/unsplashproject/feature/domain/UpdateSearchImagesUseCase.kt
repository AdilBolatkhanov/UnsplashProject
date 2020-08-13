package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image

class UpdateSearchImagesUseCase(
    private val repository: UnsplashRepository
) {
    operator fun invoke(): LiveData<PagedList<Image>> {
        return repository.updateSearchImages()
    }

}