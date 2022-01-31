package com.martinezdputra.githubbrowser.repository.datastore

import com.martinezdputra.githubbrowser.datamodel.response.*
import com.martinezdputra.githubbrowser.db.AppDatabase
import io.reactivex.Observable
import javax.inject.Inject

class UserLocalDataStore @Inject constructor(private val appDatabase: AppDatabase): UserDataStore {
    override fun getSearchUsers(query: String): Observable<SearchUserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserDetails(userId: String): Observable<UserDetailResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserRepositories(userId: String): Observable<List<UserRepositoriesResponse>> {
        TODO("Not yet implemented")
    }

    fun getUserDetails(): Observable<Map<String, UserDetailResponse>> {
        return appDatabase.userDetailDao().getAll().map { userDetails ->
            return@map userDetails.associateBy({ it.id }, { user ->
                UserDetailResponse(
                    userId = user.id,
                    avatarUrl = user.dpUrl,
                    name = user.name,
                    bio = user.bio,
                    company = user.company,
                    location = user.location,
                    email = user.email,
                    followersCount = user.followers,
                    followingCount = user.following
                )
            })
        }
    }
}