package com.example.unsplashproject.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.unsplashproject.feature.data.ImagesDataSourceFactory
import com.example.unsplashproject.feature.domain.GetSearchImagesUseCase
import com.example.unsplashproject.feature.domain.SortImagesUseCase
import com.example.unsplashproject.feature.domain.SortSearchImagesUseCase
import com.example.unsplashproject.feature.domain.UpdateSearchImagesUseCase
import com.example.unsplashproject.feature.domain.entity.Image
import java.lang.Exception
import java.lang.IllegalArgumentException

class SearchImagesViewModel(
    private val getSearchImagesUseCase: GetSearchImagesUseCase,
    private val sortSearchImagesUseCase: SortSearchImagesUseCase,
    private val updateSearchImagesUseCase: UpdateSearchImagesUseCase
) : ViewModel() {

    val status: LiveData<Resource<String>> get() = _status
    private val _status = MutableLiveData<Resource<String>>()

    val detailsActivity: LiveData<Image> get() = _detailsActivity
    private val _detailsActivity = MutableLiveData<Image>()

    lateinit var photos: LiveData<PagedList<Image>>

    fun onSearchClicked(query: String){
        _status.value = Resource.Loading
        try{
            photos = getSearchImagesUseCase.searchImages(query)
            _status.value = Resource.Success
        }catch (throwable: Throwable){
            _status.value = Resource.Error(throwable.message ?: "Something went wrong")
        }
    }

    fun updatePhotos(){
        _status.value = Resource.Loading
        try{
            photos = updateSearchImagesUseCase.updateSearchImages()
            _status.value = Resource.Success
        }catch (throwable: Throwable){
            _status.value = Resource.Error(throwable.message ?: "Something went wrong")
        }
    }

    fun setSorting(sortBy: String){
        _status.value = Resource.Loading
        try{
            photos = sortSearchImagesUseCase.sortSearchImages(sortBy)
            _status.value = Resource.Success
        }catch (throwable: Throwable){
            _status.value = Resource.Error(throwable.message ?: "Something went wrong")
        }
    }

    fun onPhotoClicked(photo: Image){
        _detailsActivity.value = photo
    }
}

