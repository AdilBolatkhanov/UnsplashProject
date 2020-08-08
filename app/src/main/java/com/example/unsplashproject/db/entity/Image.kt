package com.example.unsplashproject.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ListImagesTable = "list_images"
@Entity(tableName = ListImagesTable)
data class Image(
    @PrimaryKey
    val id : String,
    val created_at: String,
    val description: String,
    val likes: Int,
    val username: String,
    val thumb_url: String,
    val full_url: String)