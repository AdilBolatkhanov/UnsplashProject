package com.example.unsplashproject.api


import com.example.unsplashproject.feature.data.dto.json_image_model.ImageRemoteItem
import com.example.unsplashproject.feature.data.dto.json_search_model.SearchRemoteDTO

import retrofit2.http.GET
import retrofit2.http.Query

const val CLIENT_ID = "o5aR91RQtbbYZA50X7ifF7xkXsTmMKSR19f-i1nYAHY"
const val OLDEST = "oldest"
const val POPULAR = "popular"
const val LATEST = "latest"
const val RELEVANT = "relevant"

interface UnsplashApi {

    @GET("photos")
    suspend fun getImages(
        @Query("client_id") accessKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") order: String = LATEST
    ): List<ImageRemoteItem>


    @GET("search/photos")
    suspend fun searchImages(
        @Query("client_id") accessKey: String,
        @Query("page") page: Int = 1,
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("order_by") order: String = RELEVANT
    ): SearchRemoteDTO


}