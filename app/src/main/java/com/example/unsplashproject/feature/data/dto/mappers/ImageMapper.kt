package com.example.unsplashproject.feature.data.dto.mappers


import com.example.unsplashproject.feature.data.dto.json_image_model.ImageRemoteItem
import com.example.unsplashproject.feature.data.dto.json_search_model.SearchResult
import com.example.unsplashproject.feature.domain.entity.Image

fun ImageRemoteItem.toDomainModel(): Image {
    val location = if (user.location == null) "Not specified" else user.location.toString()
    return Image(
        id = id,
        created_at = created_at,
        description = alt_description,
        likes = likes,
        thumb_url = urls.thumb,
        full_url = urls.regular,
        username = user.username,
        name = user.name,
        location = location,
        user_photo_url = user.profile_image.small
    )
}

fun SearchResult.toDomainModel(): Image{
    val location = if (user.location == null) "Not specified" else user.location.toString()
    return Image(
        id = id,
        created_at = created_at,
        description = alt_description,
        likes = likes,
        thumb_url = urls.thumb,
        full_url = urls.regular,
        username = user.username,
        name = user.name,
        location = location,
        user_photo_url = user.profile_image.small
    )
}

