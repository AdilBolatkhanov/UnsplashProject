package com.example.unsplashproject.feature.domain

import com.example.unsplashproject.db.entity.Image


class SearchImagesUseCase(
    private val repository: UnsplashRepository
) {
    suspend fun searchImagesByRelevance(query: String) : List<Image> {
        return repository.searchImagesByRelevance(query)
    }

    suspend fun searchImagesByOldest(query: String) : List<Image> {
        return repository.searchImagesByLatest(query)
    }
}