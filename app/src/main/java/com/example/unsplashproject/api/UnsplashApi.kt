package com.example.unsplashproject.api

import com.example.unsplashproject.feature.data.dto.json_image_model.ImagesRemoteDTO
import com.example.unsplashproject.feature.data.dto.json_search_model.SearchImagesRemoteDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getImagesByLatest(@Query("page") page: Int = 1) : ImagesRemoteDTO

    @GET("photos?order_by=oldest")
    suspend fun getImagesByOldest(@Query("page") page: Int = 1) : ImagesRemoteDTO

    @GET("photos?order_by=popular")
    suspend fun getImagesByPopular(@Query("page") page: Int = 1) : ImagesRemoteDTO


    @GET("search/photos?order_by=relevant")
    suspend fun searchImagesByRelevance(@Query("page") page: Int = 1,
                                        @Query("query") query: String) : SearchImagesRemoteDTO

    @GET("search/photos?order_by=latest")
    suspend fun searchImagesByLatest(@Query("page") page: Int = 1,
                                     @Query("query") query: String) : SearchImagesRemoteDTO

}