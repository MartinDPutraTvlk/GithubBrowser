package com.martinezdputra.githubbrowser.di.module

import com.martinezdputra.githubbrowser.repository.ApiService
import com.martinezdputra.githubbrowser.repository.accessor.UsersAccessor
import com.martinezdputra.githubbrowser.repository.datastore.UserRemoteDataStore
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideHomepageRemoteDataStore(apiService: ApiService) = UserRemoteDataStore(apiService)

    @Provides
    fun provideHomepageRepository(remoteDataStore: UserRemoteDataStore): UsersAccessor {
        return UsersAccessor(remoteDataStore)
    }
}