package com.example.unsplashproject.feature.data.dto.json_search_model

data class SearchRemoteDTO(
    val results: List<SearchResult>,
    val total: Int,
    val total_pages: Int
)

data class SearchResult(
    val alt_description: String?,
    val categories: List<Any?>?,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any?>?,
    val description: String?,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val promoted_at: String?,
    val sponsorship: Any?,
    val tags: List<Tag>,
    val updated_at: String,
    val urls: Urls,
    val user: UserX,
    val width: Int
)

data class Links(
    val download: String,
    val download_location: String,
    val html: String,
    val self: String
)

data class Tag(
    val source: Source?,
    val title: String,
    val type: String
)


data class UserX(
    val accepted_tos: Boolean,
    val bio: Any?,
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: String?,
    val links: LinksXX,
    val location: Any?,
    val name: String,
    val portfolio_url: String?,
    val profile_image: ProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: Any?,
    val updated_at: String,
    val username: String
)

data class Source(
    val ancestry: Ancestry,
    val cover_photo: CoverPhoto,
    val description: String,
    val meta_description: String,
    val meta_title: String,
    val subtitle: String,
    val title: String
)

data class Ancestry(
    val category: Category,
    val subcategory: Subcategory,
    val type: Type
)

data class CoverPhoto(
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
    val links: Links,
    val promoted_at: Any?,
    val sponsorship: Any?,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
)

data class Category(
    val pretty_slug: String,
    val slug: String
)

data class Subcategory(
    val pretty_slug: String,
    val slug: String
)

data class Type(
    val pretty_slug: String,
    val slug: String
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
    val bio: String?,
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: String,
    val links: LinksXX,
    val location: String?,
    val name: String,
    val portfolio_url: String?,
    val profile_image: ProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: Any?,
    val updated_at: String,
    val username: String
)

data class LinksXX(
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

