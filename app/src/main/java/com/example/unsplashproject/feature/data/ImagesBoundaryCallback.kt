package com.example.unsplashproject.feature.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.paging.PagedList
import com.example.unsplashproject.api.CLIENT_ID
import com.example.unsplashproject.api.LATEST
import com.example.unsplashproject.feature.App
import com.example.unsplashproject.feature.data.dto.mappers.toDomainModel
import com.example.unsplashproject.feature.data.local.ImagesLocalDataSource
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ImagesBoundaryCallback(
    private val remoteDataSource: ImagesRemoteDataSource,
    private val localDataSource: ImagesLocalDataSource
) : PagedList.BoundaryCallback<Image>() {

    private var page = 1
    private var isRequestingInProgress = false
    private var orderBy = LATEST

    override fun onZeroItemsLoaded() {
        if (isNetworkConnected()) {
            requestAndSave()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Image) {
        if (isNetworkConnected()) {
            requestAndSave()
        }
    }

    fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            page = 1
            localDataSource.deleteAllImages()
        }
    }

    fun setSorting(sortBy: String) {
        CoroutineScope(Dispatchers.IO).launch  {
            page = 1
            orderBy = sortBy
            localDataSource.deleteAllImages()
        }
    }

    private fun requestAndSave() {
        if (isRequestingInProgress) return

        Log.d("ImagesBoundaryCallback", "page: $page ----------$orderBy")

        isRequestingInProgress = true
        CoroutineScope(Dispatchers.IO).launch {
            val list: List<Image> =
                remoteDataSource.getPhotos(CLIENT_ID, page, PER_PAGE, orderBy).map {
                    it.toDomainModel()
                }
            localDataSource.insertImages(list)
            page++
            isRequestingInProgress = false
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

}