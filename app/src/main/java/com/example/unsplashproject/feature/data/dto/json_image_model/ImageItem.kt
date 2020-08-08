package com.example.unsplashproject.feature.data.dto.json_image_model

import com.example.unsplashproject.feature.data.dto.json_search_model.Tag

data class ImageItem(
    val alt_description: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: Any,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: ImageLinks,
    val promoted_at: Any,
    val sponsorship: Sponsorship,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int,
    val tags: List<Tag>
)