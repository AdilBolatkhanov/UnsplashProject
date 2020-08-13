package com.example.unsplashproject.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.unsplashproject.feature.domain.SearchImagesUseCase
import com.example.unsplashproject.feature.domain.SortSearchImagesUseCase
import com.example.unsplashproject.feature.domain.UpdateSearchImagesUseCase
import com.example.unsplashproject.feature.domain.entity.Image

class SearchImageViewModel(
    private val searchImagesUseCase: SearchImagesUseCase,
    private val updateSearchImagesUseCase: UpdateSearchImagesUseCase,
    private val sortSearchImagesUseCase: SortSearchImagesUseCase
) : ViewModel() {
    lateinit var photos: LiveData<PagedList<Image>>

    val status: LiveData<Resource<String>> get() = _status
    private val _status = MutableLiveData<Resource<String>>()

    val nextActivity: LiveData<Image> get() = _nextActivity
    private val _nextActivity = MutableLiveData<Image>()


    fun searchImages(query: String) {
        search {
            searchImagesUseCase(query)
        }
    }

    fun setSoring(sorting: String) {
        search {
            sortSearchImagesUseCase(sorting)
        }
    }

    fun updateImages() {
        search {
            updateSearchImagesUseCase()
        }
    }

    fun onPhotoClicked(photo: Image) {
        _nextActivity.value = photo
    }

    private fun search(action: () -> LiveData<PagedList<Image>>) {
        _status.value = Resource.Loading
        try {
            photos = action()
            _status.value = Resource.Success
        } catch (throwable: Throwable) {
            _status.value = Resource.Error(throwable.message ?: "Something went wrong")
        }
    }

}