package com.example.unsplashproject.feature.data.dto.json_search_model

data class ResultUser(
    val accepted_tos: Boolean,
    val bio: String,
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: Any,
    val links: ResultUserLinks,
    val location: String,
    val name: String,
    val portfolio_url: String,
    val profile_image: ResultUserProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String,
    val updated_at: String,
    val username: String
)