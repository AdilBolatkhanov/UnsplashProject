package com.example.unsplashproject.feature.data

import android.provider.MediaStore
import com.example.unsplashproject.api.UnsplashApi
import com.example.unsplashproject.db.entity.Image
import com.example.unsplashproject.feature.data.dto.mappers.toDomainModel
import com.example.unsplashproject.feature.domain.UnsplashRepository

class UnsplashRepositoryImpl(
    private val unsplashApi: UnsplashApi
) : UnsplashRepository {
    override suspend fun getImagesByLatest(): List<Image> {
       return unsplashApi.getImagesByLatest().toDomainModel()
    }

    override suspend fun getImagesByOldest(): List<Image> {
        return unsplashApi.getImagesByOldest().toDomainModel()
    }

    override suspend fun getImagesByPopular(): List<Image> {
        return unsplashApi.getImagesByPopular().toDomainModel()
    }

    override suspend fun searchImagesByRelevance(query: String): List<Image> {
        return unsplashApi.searchImagesByRelevance(query = query).toDomainModel()
    }

    override suspend fun searchImagesByLatest(query: String): List<Image> {
        return unsplashApi.searchImagesByLatest(query = query).toDomainModel()
    }
}