package com.example.unsplashproject.di

import androidx.room.Room
import com.example.unsplashproject.db.AppDatabase
import com.example.unsplashproject.db.dao.ListImagesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "unsplash-database")
            .build()
    }
    fun getListImagesDao(database: AppDatabase): ListImagesDao {
        return database.listImagesDao()
    }
    single {
        getListImagesDao(get())
    }
}