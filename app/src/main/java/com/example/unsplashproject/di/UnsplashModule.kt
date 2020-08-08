package com.example.unsplashproject.di

import com.example.unsplashproject.api.UnsplashApi
import com.example.unsplashproject.feature.data.UnsplashRepositoryImpl
import com.example.unsplashproject.feature.domain.GetImagesUseCase
import com.example.unsplashproject.feature.domain.SearchImagesUseCase
import com.example.unsplashproject.feature.domain.UnsplashRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val UNSPLAH_API_ACCESS_KEY = "wAuCTjTfumrccN_FFsD7xBWl6sAxi3RHgmRbnW_Ey2s"

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Client-ID $UNSPLAH_API_ACCESS_KEY")
            .build()
        return chain.proceed(request)
    }
}

val unsplashModule = module {

    single(named("unsplash")) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        UnsplashRepositoryImpl(unsplashApi = get())
    }

    factory {
        GetImagesUseCase(repository = get())
    }

    factory {
        SearchImagesUseCase(repository = get())
    }

    single {
        get<Retrofit>(named("unsplash")).create(UnsplashApi::class.java)
    }
}