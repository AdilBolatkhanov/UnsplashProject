package com.example.unsplashproject.feature.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.paging.PageKeyedDataSource
import com.example.unsplashproject.api.CLIENT_ID
import com.example.unsplashproject.api.RELEVANT
import com.example.unsplashproject.feature.App
import com.example.unsplashproject.feature.data.dto.mappers.toDomainModel
import com.example.unsplashproject.feature.data.remote.ImagesRemoteDataSource
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchDataSource(
    private val remoteDataSource: ImagesRemoteDataSource,
    private val newQuery: String,
    private val newOrder: String
):PageKeyedDataSource<Int, Image>() {

    private var query =newQuery
    private var orderBy = newOrder


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Image>
    ) {
        if (isNetworkConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val searchResult: List<Image?>? = remoteDataSource
                    .searchPhotos(CLIENT_ID, query, 1, params.requestedLoadSize, orderBy)
                    .results.map {
                        it.toDomainModel()
                    }
                callback.onResult(searchResult ?: listOf(), null, 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        if (isNetworkConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val searchResult = remoteDataSource
                    .searchPhotos(CLIENT_ID, query, params.key, params.requestedLoadSize, orderBy)
                val searchList = searchResult.results.map {
                    it.toDomainModel()
                }
                val nextKey = if (params.key == searchResult.total_pages) null else params.key + 1
                callback.onResult(searchList, nextKey)

            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
    }

    private fun isNetworkConnected():Boolean{
        val cm = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}