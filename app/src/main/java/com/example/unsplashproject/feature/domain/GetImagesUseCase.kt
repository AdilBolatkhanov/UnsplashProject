package com.example.unsplashproject.feature.domain

import com.example.unsplashproject.db.entity.Image


class GetImagesUseCase(
    private val repository: UnsplashRepository
) {
    suspend fun getImagesByLatest() : List<Image> {
        return repository.getImagesByLatest()
    }

    suspend fun getImagesByOldest() : List<Image> {
        return repository.getImagesByOldest()
    }

    suspend fun getImagesByPopular() : List<Image> {
        return repository.getImagesByPopular()
    }
}