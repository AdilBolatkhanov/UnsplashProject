package com.example.unsplashproject.feature.data.dto.mappers

import com.example.unsplashproject.feature.data.dto.json_image_model.ImagesRemoteDTO
import com.example.unsplashproject.feature.data.dto.json_search_model.SearchImagesRemoteDTO
import com.example.unsplashproject.feature.domain.entity.Image

fun ImagesRemoteDTO.toDomainModel(): ArrayList<Image> {

    val domainImageList = ArrayList<Image>()

    for (image in this) {
        domainImageList.add(
            Image(
                id = image.id,
                created_at = image.created_at,
                likes = image.likes,
                description = image.alt_description,
                username = image.user.name,
                thumb_url = image.urls.thumb,
                full_url = image.urls.full
            )
        )
    }

    return domainImageList;
}

fun SearchImagesRemoteDTO.toDomainModel(): ArrayList<Image> {
    return results.toDomainModel();
}