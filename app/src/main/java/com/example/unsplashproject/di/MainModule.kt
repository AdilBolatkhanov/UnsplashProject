package com.example.unsplashproject.di

import com.example.unsplashproject.feature.data.UnsplashRepositoryImpl
import com.example.unsplashproject.feature.data.local.ImagesLocalDataSource
import com.example.unsplashproject.feature.data.local.ImagesLocalDataSourceImpl
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSourceImpl
import com.example.unsplashproject.feature.domain.*
import com.example.unsplashproject.feature.presentation.ListImagesViewModel
import com.example.unsplashproject.feature.presentation.SearchImageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<ImagesLocalDataSource> {
        ImagesLocalDataSourceImpl(listImagesDao = get())
    }

    single<ImagesRemoteDataSource> {
        ImagesRemoteDataSourceImpl(unsplashApi = get())
    }

    single<UnsplashRepository> {
        UnsplashRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    factory {
        GetImagesUseCase(repository = get())
    }

    factory {
        UpdateImageUseCase(repository = get())
    }
    factory {
        SortImagesUseCase(repository = get())
    }

    factory {
        SearchImagesUseCase(repository = get())
    }

    factory {
        SortSearchImagesUseCase(repository = get())
    }

    factory {
        UpdateSearchImagesUseCase(repository = get())
    }

    viewModel {
        ListImagesViewModel(
            getImagesUseCase = get(),
            updateImageUseCase = get(),
            sortImagesUseCase = get()
        )
    }

    viewModel {
        SearchImageViewModel(
            searchImagesUseCase = get(),
            updateSearchImagesUseCase = get(),
            sortSearchImagesUseCase = get()
        )
    }

}