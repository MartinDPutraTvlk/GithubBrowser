package com.martinezdputra.githubbrowser.di.module

import android.content.Context
import android.os.Build
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.martinezdputra.githubbrowser.BuildConfig
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
    fun providesApiService(
        @Named("baseUrl") baseUrl: String,
        okHttpClient: OkHttpClient
    ) : ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return if(BuildConfig.DEBUG) {
             OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chuckerInterceptor)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesChuckerLoggingInterceptor(appContext: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(appContext).build()
    }
}