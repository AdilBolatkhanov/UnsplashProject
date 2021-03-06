package com.example.unsplashproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.unsplashproject.db.dao.ListImagesDao
import com.example.unsplashproject.feature.domain.entity.Image


@Database(
    entities = [Image::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listImagesDao(): ListImagesDao
}