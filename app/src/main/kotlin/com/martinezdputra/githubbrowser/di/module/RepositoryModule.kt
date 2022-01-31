package com.martinezdputra.githubbrowser.di.module

import android.content.Context
import androidx.room.Room
import com.martinezdputra.githubbrowser.db.AppDatabase
import com.martinezdputra.githubbrowser.repository.ApiService
import com.martinezdputra.githubbrowser.repository.accessor.UsersAccessor
import com.martinezdputra.githubbrowser.repository.datastore.UserRemoteDataStore
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, AppDatabase.DB_NAME
        ).build()
    }

    @Provides
    fun provideHomepageRemoteDataStore(apiService: ApiService) = UserRemoteDataStore(apiService)

    @Provides
    fun provideHomepageRepository(remoteDataStore: UserRemoteDataStore, appDatabase: AppDatabase): UsersAccessor {
        return UsersAccessor(remoteDataStore, appDatabase)
    }
}