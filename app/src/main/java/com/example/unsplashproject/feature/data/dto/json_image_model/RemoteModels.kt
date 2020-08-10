package com.example.unsplashproject.feature.data.dto.json_image_model

data class ImageRemoteItem(
    val alt_description: String?,
    val categories: List<Any?>?,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any?>?,
    val description: Any?,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: ImageLinks,
    val promoted_at: Any?,
    val sponsorship: Sponsorship?,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
)

data class ImageLinks(
    val download: String,
    val download_location: String,
    val html: String,
    val self: String
)

data class Sponsorship(
    val impression_urls: List<String>,
    val sponsor: Sponsor,
    val tagline: String,
    val tagline_url: String
)

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class User(
    val accepted_tos: Boolean,
    val bio: String,
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: Any?,
    val links: UserLinks,
    val location: Any?,
    val name: String,
    val portfolio_url: String?,
    val profile_image: UserProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String?,
    val updated_at: String,
    val username: String
)

data class Sponsor(
    val accepted_tos: Boolean,
    val bio: String,
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: Any?,
    val links: UserLinks,
    val location: Any?,
    val name: String,
    val portfolio_url: String,
    val profile_image: ProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String,
    val updated_at: String,
    val username: String
)

data class UserLinks(
    val followers: String,
    val following: String,
    val html: String,
    val likes: String,
    val photos: String,
    val portfolio: String,
    val self: String
)

data class ProfileImage(
    val large: String,
    val medium: String,
    val small: String
)


data class UserProfileImage(
    val large: String,
    val medium: String,
    val small: String
)