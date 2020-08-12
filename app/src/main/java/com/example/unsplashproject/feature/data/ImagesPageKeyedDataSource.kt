package com.example.unsplashproject.feature.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.paging.PageKeyedDataSource
import com.example.unsplashproject.api.CLIENT_ID
import com.example.unsplashproject.api.LATEST
import com.example.unsplashproject.feature.App
import com.example.unsplashproject.feature.data.dto.mappers.toDomainModel
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.lang.Exception

class ImagesPageKeyedDataSource(
    private val remoteDataSource : ImagesRemoteDataSource,
    private val newQuery: String,
    private val newOrder: String
) : PageKeyedDataSource<Int, Image>() {

    private var page = 1
    private var orderBy = newOrder
    private var query = newQuery

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Image>
    ) {
        if (isNetworkConnected()){
            GlobalScope.launch(Dispatchers.IO) {
                val images : List<Image> =
                    remoteDataSource.searchPhotos(CLIENT_ID, query, page, PER_PAGE, orderBy).map{
                        it.toDomainModel()
                    }
                callback.onResult(images, null, page++)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        if (isNetworkConnected()){
            GlobalScope.launch(Dispatchers.IO) {
                val images : List<Image> =
                    remoteDataSource.searchPhotos(CLIENT_ID, query, page, PER_PAGE, orderBy).map{
                        it.toDomainModel()
                    }
                callback.onResult(images, page++)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {}

    private fun isNetworkConnected():Boolean{
        val cm = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}