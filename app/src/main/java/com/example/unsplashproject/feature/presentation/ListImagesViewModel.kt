package com.example.unsplashproject.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.unsplashproject.feature.data.ImagesBoundaryCallback
import com.example.unsplashproject.feature.domain.GetImagesUseCase
import com.example.unsplashproject.feature.domain.SortImagesUseCase
import com.example.unsplashproject.feature.domain.UpdateImageUseCase
import com.example.unsplashproject.feature.domain.entity.Image


class ListImagesViewModel(
    private val getImagesUseCase: GetImagesUseCase,
    private val updateImageUseCase: UpdateImageUseCase,
    private val sortImagesUseCase: SortImagesUseCase
) : ViewModel() {
    lateinit var photos: LiveData<PagedList<Image>>

    val status: LiveData<Resource<String>> get() = _status
    private val _status = MutableLiveData<Resource<String>>()

    val nextActivity: LiveData<Image> get() = _nextActivity
    private val _nextActivity = MutableLiveData<Image>()

    val searchActivity: LiveData<Boolean> get() = _searchActivity
    private val _searchActivity = MutableLiveData<Boolean>()

    init {
        getPhotos {
            getImagesUseCase()
        }
    }

    fun updatePhotos() {
        getPhotos {
            updateImageUseCase()
        }
    }

    fun setSorting(sorting: String) {
        getPhotos {
            sortImagesUseCase(sorting)
        }
    }

    fun onPhotoClicked(photo: Image) {
        _nextActivity.value = photo
    }

    fun onSearchBtnClicked() {
        _searchActivity.value = true
    }

    private fun getPhotos(action: () -> LiveData<PagedList<Image>>) {
        _status.value = Resource.Loading
        try {
            photos = action()
            _status.value = Resource.Success
        } catch (throwable: Throwable) {
            _status.value = Resource.Error(throwable.message ?: "Something went wrong")
        }
    }

}

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    object Success : Resource<Nothing>()
    class Error<T>(val message: String) : Resource<T>()
}