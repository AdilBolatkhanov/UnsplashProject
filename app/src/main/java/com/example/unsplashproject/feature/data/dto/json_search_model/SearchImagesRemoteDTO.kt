package com.example.unsplashproject.feature.data.dto.json_search_model

import com.example.unsplashproject.feature.data.dto.json_image_model.ImagesRemoteDTO

data class SearchImagesRemoteDTO(
    val results: ImagesRemoteDTO,
    val total: Int,
    val total_pages: Int
)