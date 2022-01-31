package com.martinezdputra.githubbrowser.repository.accessor

import com.martinezdputra.githubbrowser.datamodel.response.*
import com.martinezdputra.githubbrowser.repository.datastore.UserRemoteDataStore
import io.reactivex.Observable
import javax.inject.Inject

class UsersAccessor @Inject constructor(private val remoteDataStore: UserRemoteDataStore) {
    fun fetchUsersData(query: String): Observable<SearchUserResponse> {
        return remoteDataStore.getSearchUsers(query)
    }

    fun fetchUserDetails(userId: String): Observable<UserDetailResponse> {
        return remoteDataStore.getUserDetails(userId)
    }

    fun fetchUserRepositories(userId: String): Observable<List<UserRepositoriesResponse>> {
        return remoteDataStore.getUserRepositories(userId)
    }
}