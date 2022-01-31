package com.martinezdputra.githubbrowser.repository.datastore

import com.martinezdputra.githubbrowser.datamodel.response.*
import com.martinezdputra.githubbrowser.repository.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class UserRemoteDataStore @Inject constructor(private val apiService: ApiService): UserDataStore {
    override fun getSearchUsers(query: String): Observable<SearchUserResponse> = apiService.getSearchUsers(query)

    override fun getUserDetails(userId: String): Observable<UserDetailResponse> = apiService.getUserDetails(userId)

    override fun getUserRepositories(userId: String): Observable<List<UserRepositoriesResponse>> = apiService.getUserRepositories(userId)
}