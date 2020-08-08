package com.example.unsplashproject.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.GetImagesUseCase
import com.example.unsplashproject.feature.domain.SearchImagesUseCase

class ListImagesViewModel(
    private val imagesUseCase: GetImagesUseCase
) : ViewModel(){
    private val config by lazy {
        PagedList.Config.Builder()
            .setPageSize(30)
            .setPrefetchDistance(50)
            .setEnablePlaceholders(false)
            .build()
    }

    val userList = LivePagedListBuilder(
    imagesUseCase.,
    pagedListConfig
    ).setBoundaryCallback(userBoundaryCallback).build()
}