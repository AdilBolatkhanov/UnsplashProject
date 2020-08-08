package com.example.unsplashproject.feature.domain.entity

data class Image(
    val id : String,
    val created_at: String,
    val description: String,
    val likes: Int,
    val username: String,
    val thumb_url: String,
    val full_url: String)