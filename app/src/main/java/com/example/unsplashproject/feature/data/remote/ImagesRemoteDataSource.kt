package com.example.unsplashproject.feature.data.remote

import com.example.unsplashproject.api.UnsplashApi
import com.example.unsplashproject.feature.data.dto.json_image_model.ImageRemoteItem
import com.example.unsplashproject.feature.data.dto.json_search_model.SearchRemoteDTO
import com.example.unsplashproject.feature.data.dto.json_search_model.SearchResult

interface ImagesRemoteDataSource {
    suspend fun getPhotos(
        clientId: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): List<ImageRemoteItem>

    suspend fun searchPhotos(
        clientId: String,
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): SearchRemoteDTO
}

class ImagesRemoteDataSourceImpl(
    private val unsplashApi: UnsplashApi
) : ImagesRemoteDataSource {
    override suspend fun getPhotos(
        clientId: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): List<ImageRemoteItem> {
        return unsplashApi.getImages(clientId, page, perPage, orderBy)
    }

    override suspend fun searchPhotos(
        clientId: String,
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): SearchRemoteDTO {
        return unsplashApi.searchImages(clientId, page, query, perPage, orderBy)
    }

}