package com.example.unsplashproject.feature.data.dto.json_search_model

data class Source(
    val ancestry: Ancestry,
    val cover_photo: CoverPhoto,
    val description: String,
    val meta_description: String,
    val meta_title: String,
    val subtitle: String,
    val title: String
)