package com.example.unsplashproject.feature.domain

import com.example.unsplashproject.db.entity.Image


interface UnsplashRepository {
    suspend fun getImagesByLatest() : List<Image>

    suspend fun getImagesByOldest() : List<Image>

    suspend fun getImagesByPopular() : List<Image>

    suspend fun searchImagesByRelevance(query: String) : List<Image>

    suspend fun searchImagesByLatest(query: String) : List<Image>
}