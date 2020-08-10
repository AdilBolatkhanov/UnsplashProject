package com.example.unsplashproject.feature.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val ListImagesTable = "list_images"
@Entity(tableName = ListImagesTable)
@Parcelize
data class Image(
    @PrimaryKey
    val id : String,
    val created_at: String,
    val description: String?,
    val likes: Int,
    val username: String,
    val name: String,
    val location: String?,
    val user_photo_url: String,
    val thumb_url: String,
    val full_url: String
):Parcelable