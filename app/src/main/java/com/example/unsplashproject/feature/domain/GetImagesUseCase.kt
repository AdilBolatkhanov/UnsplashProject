package com.example.unsplashproject.feature.domain

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.entity.Image


class GetImagesUseCase(
    private val repository: UnsplashRepository
) {
    operator fun invoke(): LiveData<PagedList<Image>> {
        return repository.getImages()
    }

}