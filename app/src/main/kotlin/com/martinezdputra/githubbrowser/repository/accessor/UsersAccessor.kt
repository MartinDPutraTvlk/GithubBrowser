package com.martinezdputra.githubbrowser.repository.accessor

import com.martinezdputra.githubbrowser.datamodel.response.*
import com.martinezdputra.githubbrowser.db.AppDatabase
import com.martinezdputra.githubbrowser.db.entity.UserDetailsEntity
import com.martinezdputra.githubbrowser.repository.datastore.UserLocalDataStore
import com.martinezdputra.githubbrowser.repository.datastore.UserRemoteDataStore
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class UsersAccessor @Inject constructor(private val remoteDataStore: UserRemoteDataStore,
                                        private val localDataStore: UserLocalDataStore,
                                        private val appDatabase: AppDatabase) {
    fun fetchUsersData(query: String): Observable<SearchUserResponse> {
        return remoteDataStore.getSearchUsers(query)
    }

    fun fetchUserDetails(userId: String): Observable<UserDetailResponse> {
        return remoteDataStore.getUserDetails(userId)
            .doOnNext(updateUserDetailsCache())
    }

    fun fetchUserDetailsFromLocalCache(): Observable<Map<String, UserDetailResponse>> {
        return localDataStore.getUserDetails()
    }

    fun fetchUserRepositories(userId: String): Observable<List<UserRepositoriesResponse>> {
        return remoteDataStore.getUserRepositories(userId)
    }

    private fun updateUserDetailsCache() : Consumer<UserDetailResponse> {
        return Consumer { response ->
            response?.also { user ->
                val userEntity = UserDetailsEntity(
                    id = user.userId,
                    dpUrl = user.avatarUrl,
                    name = user.name,
                    bio = user.bio,
                    company = user.company,
                    location = user.location,
                    email = user.email,
                    followers = user.followersCount,
                    following = user.followingCount
                )
                appDatabase.userDetailDao().insertOrUpdateAll(userEntity)
            }
        }
    }
}