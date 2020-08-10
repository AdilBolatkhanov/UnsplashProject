package com.example.unsplashproject.di

import com.example.unsplashproject.api.CLIENT_ID
import com.example.unsplashproject.api.UnsplashApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Client-ID $CLIENT_ID")
            .build()
        return chain.proceed(request)
    }
}

val networkModule = module {
    val connectTimeout : Long = 40
    val readTimeout : Long  = 40

    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ApiInterceptor())
            .build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl="https://api.unsplash.com/"
        provideRetrofit(get(),baseUrl)
    }

    fun provideUnsplashApi(retrofit: Retrofit):UnsplashApi{
        return retrofit.create(UnsplashApi::class.java)
    }

    single {
        provideUnsplashApi(get())
    }

}