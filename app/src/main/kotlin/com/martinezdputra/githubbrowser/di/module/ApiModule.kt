package com.martinezdputra.githubbrowser.di.module

import com.martinezdputra.githubbrowser.repository.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class ApiModule {
    @Named("baseUrl")
    @Provides
    fun providesBaseUrl() = "https://api.github.com"

    @Provides
    fun providesRetrofit(@Named("baseUrl") baseUrl: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    fun providesApiService(@Named("baseUrl") baseUrl: String) : ApiService {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
            .create(ApiService::class.java)
    }
}